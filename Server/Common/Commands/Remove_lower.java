package Common.Commands;

import Common.Command;
import Common.Invoker;
import Common.Ticket;
import Common.TicketCollection;
import Utility.ByteToObject;
import Utility.ServerReceiver;
import Utility.ServerSender;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The type Remove lower.
 */
public class Remove_lower implements Command {
    /**
     * Instantiates a new Remove lower.
     */
    public Remove_lower(){
        Invoker.regist("remove_lower",this);
    }
    @Override
    public void execute(String par1) throws IOException {
        if(par1==null&& ExecuteScript.inExecution){
            ServerSender.send("Параметр не был указан,выполнение команды \"remove_lower\" невозможно.",2);
        } else
        if (par1 == null) {
            String key;
            ServerSender.send("Введите цену для сравнения.",1);
            key = (String)ByteToObject.Cast(ServerReceiver.receive());
            if (key.equals("") || key == null || (Integer.parseInt(key)<=0)) {
                ServerSender.send("Цена не может быть null или меньше 0.",2);
                this.execute(par1);
            } else this.execute(key);
        } else {
            try {
                TicketCollection ticketCollection = new TicketCollection();
                if (ticketCollection.getSize() == 0) {
                    if (ExecuteScript.inExecution) ServerSender.send("Коллекция как бы пустая.",2);
                     else ServerSender.send("Коллекция как бы пустая.",0);
                } else {
                    float givenId = Float.parseFloat(par1);
                    TreeMap<Long, Ticket> tickets = new TreeMap<>();
                    ticketCollection
                            .getTickets()
                            .entrySet()
                            .stream()
                            .filter((s)->s.getValue().getPrice()>=givenId)
                            .forEach((ticket)->tickets.put(ticket.getKey(),ticket.getValue()));
                    ticketCollection.setTickets(tickets);
                    if (ExecuteScript.inExecution) ServerSender.send("Все возможные обьекты были удалены.",2);
                    else ServerSender.send("Все возможные обьекты были удалены.",0);
                }
            } catch (NumberFormatException | NullPointerException e) {
                if (ExecuteScript.inExecution) ServerSender.send("Ключ указан некорректно,попробуйте ещё раз.",2);
                else ServerSender.send("Ключ указан некорректно,попробуйте ещё раз.",0);
            }
        }
    }
    @Override
    public String getInfo() {
        return "---> Удалить все элементы,чья цена меньше данного" ;
    }
}
