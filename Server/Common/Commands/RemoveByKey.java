package Common.Commands;

import Common.Command;
import Common.Invoker;
import Common.Ticket;
import Common.TicketCollection;
import Utility.DBworking;
import Utility.ServerSender;
import Utility.TicketsUpdating;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.TreeMap;

/**
 * The type Remove by key.
 */
public class RemoveByKey implements Command {
    /**
     * Instantiates a new Remove by key.
     */
    public RemoveByKey() {
        Invoker.regist("remove_key", this);
    }


    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {
        TicketCollection ticketCollection = new TicketCollection();
        DBworking dBworking = new DBworking();
        dBworking.ConnectionToDB();
        dBworking.loadAllTickets();
        TreeMap<Long, Ticket> ticket= new TreeMap<>();
        Ticket t = ticketCollection.getTicket((Long)par1);
        ticket.put(t.getMapKey(),t);
        ticketCollection.removeTicket((Long) par1);
        dBworking.uploadAllTickets();
        TicketsUpdating.ticketToDelete(ticket);
    }

    @Override
    public String getInfo() {
        return "---> Удалить элемент из коллекции по ключу.";
    }
}
