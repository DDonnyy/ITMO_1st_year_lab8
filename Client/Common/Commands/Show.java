package Common.Commands;

import Common.Command;
import Common.Invoker;
import Common.Ticket;
import Common.TicketCollection;

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
        for (Map.Entry<Long, Ticket> entry : tickets.entrySet()) {
            Ticket ticket = entry.getValue();
            System.out.println(ticket.getTicket() + "\n");
        }
        }   else System.out.println("Коллекция пуста.");
        System.out.print("$ ");
    }

    @Override
    public String getInfo() {
        return "---> Вывести все элементы коллекции и их информацию.";
    }

}
