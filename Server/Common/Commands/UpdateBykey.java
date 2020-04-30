package Common.Commands;

import Common.*;
import Utility.ByteToObject;
import Utility.ServerReceiver;
import Utility.ServerSender;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * The type Update bykey.
 */
public class UpdateBykey implements Command {
    /**
     * Instantiates a new Update bykey.
     */
    public UpdateBykey(){
        Invoker.regist("update_key",this);
    }
    @Override
    public void execute(String par1) {
        try {
            if (ExecuteScript.inExecution && (par1 == null || par1.equals(""))) {
                ServerSender.send("Параметр не был указан,выполнение команды \"update_key\" невозможно.", 2);
            } else if (ExecuteScript.inExecution) {
                Long key = Long.parseLong(par1);
                TicketCollection ticketCollection = new TicketCollection();
                TreeMap<Long, Ticket> tickets = ticketCollection.getTickets();
                boolean keyExist = false;
                if (tickets.size() > 0) {
                    for (Map.Entry<Long, Ticket> entry : tickets.entrySet()) {
                        if (entry.getKey() == key) {
                            keyExist = true;
                            break;
                        }
                    }
                } else if (ExecuteScript.inExecution) ServerSender.send("Коллекция пуста", 2);
                else ServerSender.send("Коллекция пуста", 0);
                if (keyExist) {
                    if (!(par1.equals(""))) {
                        Decoder.setKeyForInsertAndUpdate(Long.parseLong(par1));
                    }
                    Map.Entry entry = (Decoder.decodeIntoCollection(ExecuteScript.getExecuteData()).firstEntry());
                    Ticket ticket = (Ticket) entry.getValue();
                    key = (long) entry.getKey();
                    ticketCollection.replaceMovie(key, ticket);
                    ServerSender.send("Элемент с ключом " + key + " обновлён.", 2);
                } else {
                    ServerSender.send("Элемент с заданным ключом не существует,попробуйте ввести команду снова.", 2);
                }
            } else if (par1 == null) {
                String key;
                ServerSender.send("Укажите ключ для нового элемента.", 1);
                key = (String) ByteToObject.Cast(ServerReceiver.receive());
                if (key.equals("") || key == null) {
                    ServerSender.send("Ключ не может быть null", 2);
                    this.execute(par1);
                } else this.execute(key);
            } else {
                Long key = Long.parseLong(par1);
                TicketCollection ticketCollection = new TicketCollection();
                TreeMap<Long, Ticket> tickets = ticketCollection.getTickets();
                boolean keyExist = false;
                if (tickets.size() > 0) {
                    for (Map.Entry<Long, Ticket> entry : tickets.entrySet()) {
                        if (entry.getKey() == key) {
                            keyExist = true;
                            break;
                        }
                    }
                    if (keyExist) {
                        ServerSender.send((String.valueOf(key)), 3);
                        Ticket ticket = (Ticket) ByteToObject.Cast(ServerReceiver.receive());
                        ticketCollection.putTicket(key, ticket);
                        ServerSender.send("В коллекцию успешно добавлен элемент.", 0);
                    } else {
                        ServerSender.send("Элемент с заданным ключом не существует,попробуйте ввести команду снова.", 0);
                    }
                } else ServerSender.send("Коллекция пуста", 0);

            }
        } catch (NumberFormatException e) {
            ServerSender.send("Число должно быть типа Long,попробуйте ещё раз.",2);
            this.execute(null);
        }
    }

    @Override
    public String getInfo() {
        return "---> Обновить значения элемента по его ключу.";
    }
}
