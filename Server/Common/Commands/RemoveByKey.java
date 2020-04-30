package Common.Commands;

import Common.Command;
import Common.Invoker;
import Common.Ticket;
import Common.TicketCollection;
import Utility.ByteToObject;
import Utility.ServerReceiver;
import Utility.ServerSender;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * The type Remove by key.
 */
public class RemoveByKey implements Command {
    /**
     * Instantiates a new Remove by key.
     */
    public RemoveByKey(){
        Invoker.regist("remove_key",this);
    }
    @Override
    public void execute(String par1) throws IOException {
        if(par1==null&& ExecuteScript.inExecution){
            ServerSender.send("Параметр не был указан,выполнение команды \"remove_key\" невозможно.",2);
        } else
        if (par1 == null) {
            String key;
            ServerSender.send("Введите ключ",1);
            key = (String) ByteToObject.Cast(ServerReceiver.receive());
            if (key.equals("") || key == null) {
                ServerSender.send("Ключ не может быть null",2);
                this.execute(par1);
            } else this.execute(key);
        } else {
            try {
                TicketCollection ticketCollection = new TicketCollection();
                if (ticketCollection.getSize() == 0) {
                    if (ExecuteScript.inExecution) ServerSender.send("Коллекция как бы пустая.",2);
                    else ServerSender.send("Коллекция как бы пустая.",0);
                } else {
                    long givenId = Long.parseLong(par1);
                    TreeMap<Long, Ticket> tickets = new TreeMap<>();
                    int oldSize = ticketCollection.getSize();
                    ticketCollection
                            .getTickets()
                            .entrySet()
                            .stream()
                            .filter((s) -> s.getValue().getMapKey() != givenId)
                            .forEach((ticket) -> tickets.put(ticket.getKey(), ticket.getValue()));
                    ticketCollection.setTickets(tickets);
                    int newSize = ticketCollection.getSize();
                    if (newSize < oldSize) {
                        if (ExecuteScript.inExecution)
                            ServerSender.send("Элемент с ключом " + givenId + " удалён из коллекции.", 2);
                        else ServerSender.send("Элемент с ключом " + givenId + " удалён из коллекции.", 0);
                    } else if (ExecuteScript.inExecution)
                        ServerSender.send("В коллекции нет элемента с заданным ключом.", 2);
                    else ServerSender.send("В коллекции нет элемента с заданным ключом.", 0);
                }

            } catch (NumberFormatException | NullPointerException e) {
                if (ExecuteScript.inExecution)
                    ServerSender.send("Ключ указан некорректно,попробуйте ещё раз.", 2);
                else ServerSender.send("Ключ указан некорректно,попробуйте ещё раз.", 0);
            }
        }
    }
    @Override
    public String getInfo() {
        return "---> Удалить элемент из коллекции по ключу.";
    }
}
