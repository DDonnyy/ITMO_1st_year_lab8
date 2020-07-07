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
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Common.Commands.Clear.
 */
public class Clear implements Command {
    /**
     * Instantiates a new Common.Commands.Clear.
     */
    public Clear(){
        Invoker.regist("clear",this);
    }


    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {
        TicketCollection ticketCollection = new TicketCollection();
        DBworking dBworking = new DBworking();
        dBworking.ConnectionToDB();
        dBworking.loadAllTickets();
        TreeMap<Long,Ticket> ticketsnottodelete = new TreeMap<Long, Ticket>();
        TreeMap<Long,Ticket> ticketsToDelete = new TreeMap<Long, Ticket>();
        ticketCollection.getTickets().entrySet().forEach(x-> {
            if (!x.getValue().getUser().equals(user)) ticketsnottodelete.put(x.getKey(),x.getValue());
        });
        ticketCollection.getTickets().entrySet().forEach(x-> {
            if (x.getValue().getUser().equals(user)) ticketsToDelete.put(x.getKey(),x.getValue());
        });
        ticketCollection.setTickets(ticketsnottodelete);
        TicketsUpdating.ticketToDelete(ticketsToDelete);
        dBworking.uploadAllTickets();
    }

    @Override
    public String getInfo() {
        return "---> Удаление элементов,принадлежащих вам";
    }
}
