package Common.Commands;

import Common.Command;
import Common.Invoker;
import Common.Ticket;
import Common.TicketCollection;
import Utility.ServerSender;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Common.Commands.Show.
 */
public class Show implements Command {
    private TicketCollection ticketCollection = new TicketCollection();

    /**
     * Instantiates a new Common.Commands.Show.
     */
    public Show(){
    Invoker.regist("show",this);
    }
    @Override
    public void execute(String par1) throws IOException {
        TreeMap <Long, Ticket>  tickets = ticketCollection.getTickets();
        if(tickets.size()>0){
            tickets.entrySet().stream().forEach((ticket)->ServerSender.send(ticket.getValue().getTicket()+"\n",2));
            if (ExecuteScript.inExecution)  ServerSender.send("------------------------",2);
            else ServerSender.send("------------------------",0);
        }   else  if (ExecuteScript.inExecution) ServerSender.send("Коллекция пуста.",2);
        else ServerSender.send("Коллекция пуста.",0);

    }

    @Override
    public String getInfo() {
        return "---> Вывести все элементы коллекции и их информацию.";
    }

}
