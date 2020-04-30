package Common.Commands;

import Common.*;
import Utility.ByteToObject;
import Utility.ServerReceiver;
import Utility.ServerSender;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * The type Insert key.
 */
public class InsertKey implements Command {

    TicketCollection ticketCollection = new TicketCollection();
    CreateTicket createTicket = new CreateTicket();

    /**
     * Instantiates a new Insert key.
     */
    public InsertKey(){
        Invoker.regist("insert_key",this);
    }
    @Override
    public void execute(String par1) throws IOException {
        try {
            if (ExecuteScript.inExecution){
                if(ExecuteScript.getExecuteData().equals("")) {
                    ServerSender.send("Поля для билеты не были заполнены,билет не создан.",2);
                } else {
                    if (!(par1.equals(""))) {
                        Decoder.setKeyForInsertAndUpdate(Long.parseLong(par1));}
                    Map.Entry entry = (Decoder.decodeIntoCollection(ExecuteScript.getExecuteData()).firstEntry());
                    Ticket ticket = (Ticket)entry.getValue();
                    Long key = (long)entry.getKey();
                    ticketCollection.putTicket(key,ticket);
                    ServerSender.send("В коллекцию был добавлен элемент.",2);
                }
            }
            else
            if (par1 == null) {
                String key;
                ServerSender.send("Укажите ключ для нового элемента.",1);
                key = (String) ByteToObject.Cast(ServerReceiver.receive());

                if (key.equals("") || key == null) {
                    ServerSender.send("Ключ не может быть null",2);
                    this.execute(par1);
                } else this.execute(key);
            } else {
                long key = Long.parseLong(par1);
                boolean keyExist = false;
                TreeMap<Long, Ticket> tickets = ticketCollection.getTickets();
                if (tickets.size() > 0) {
                    for (Map.Entry<Long, Ticket> entry : tickets.entrySet()) {
                        if (entry.getKey() == key) {
                            keyExist = true;
                        }
                    }
                }
                if (!keyExist) {
                    if (tickets.size()==0) TicketCollection.setDateOFCreation(ZonedDateTime.now());
                    ServerSender.send((String.valueOf(key)),3);
                    Ticket ticket = (Ticket)ByteToObject.Cast(ServerReceiver.receive());
                    ticketCollection.putTicket(key,ticket);
                    ServerSender.send("В коллекцию успешно добавлен элемент.",0);

                }
                if (keyExist) {
                    ServerSender.send("Элемент с таким ключом уже есть в коллекции,желаете заменить его на новый?(YES/ДА)",1);
                    String answer = (String) ByteToObject.Cast(ServerReceiver.receive());
                    if (answer.toUpperCase().equals("YES") || answer.toUpperCase().equals("ДА")) {
                        ServerSender.send((String.valueOf(key)),3);
                        Ticket ticket = (Ticket)ByteToObject.Cast(ServerReceiver.receive());
                        ticketCollection.putTicket(key,ticket);
                        ServerSender.send("В коллекцию успешно добавлен элемент.",0);
                    } else ServerSender.send("Ну тогда ничего не выйдет,попробуйте что-нибудь другое получается соответственно.",0);

                }
            }
        } catch (NumberFormatException e) {
            ServerSender.send("Число должно быть типа Long,попробуйте ещё раз.",2);
            this.execute(null);
        }
    }

    @Override
    public String getInfo() {
        return "---> Добавить элемент в коллекцию.";
    }
}
