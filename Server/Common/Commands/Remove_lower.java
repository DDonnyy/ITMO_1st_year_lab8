package Common.Commands;

import Common.Command;
import Common.Invoker;
import Common.Ticket;
import Common.TicketCollection;
import Utility.DBworking;
import Utility.ServerReceiver;
import Utility.ServerSender;
import Utility.TicketsUpdating;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.DoubleToIntFunction;

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
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {
        TicketCollection ticketCollection = new TicketCollection();
        DBworking dBworking = new DBworking();
        dBworking.ConnectionToDB();
        dBworking.loadAllTickets();
        Float price = (Float)par1;
        TreeMap<Long,Ticket> ticketsnottodelete = new TreeMap<Long, Ticket>();
        TreeMap<Long,Ticket> ticketsToDelete = new TreeMap<Long, Ticket>();
        ticketCollection.getTickets().entrySet().forEach(x-> {
            if (!x.getValue().getUser().equals(user)||x.getValue().getPrice()>=price) ticketsnottodelete.put(x.getKey(),x.getValue());
        });
        ticketCollection.getTickets().entrySet().forEach(x-> {
            if (x.getValue().getUser().equals(user)||x.getValue().getPrice()<price) ticketsToDelete.put(x.getKey(),x.getValue());
        });
        ticketCollection.setTickets(ticketsnottodelete);
        TicketsUpdating.ticketToDelete(ticketsToDelete);
        dBworking.uploadAllTickets();
    }

    @Override
    public String getInfo() {
        return "---> Удалить все элементы,чья цена меньше данного" ;
    }
}
