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
import java.util.TreeMap;

public class RemoveAllByPerson implements Command {
     public RemoveAllByPerson(){
            Invoker.regist("remove_all_by_person",this);
         }


    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {
        TicketCollection ticketCollection = new TicketCollection();
        DBworking dBworking = new DBworking();
        dBworking.ConnectionToDB();
        dBworking.loadAllTickets();
        Ticket ticket = (Ticket)par1;
        TreeMap<Long,Ticket> ticketsnottodelete = new TreeMap<Long, Ticket>();
        TreeMap<Long,Ticket> ticketsToDelete = new TreeMap<Long, Ticket>();
        ticketCollection.getTickets().entrySet().forEach(x-> {
            if (!x.getValue().getUser().equals(user)||!x.getValue().getPerson().equals(ticket.getPerson())) ticketsnottodelete.put(x.getKey(),x.getValue());
        });
        ticketCollection.getTickets().entrySet().forEach(x-> {
            if (x.getValue().getUser().equals(user)||x.getValue().getPerson().equals(ticket.getPerson())) ticketsToDelete.put(x.getKey(),x.getValue());
        });
        ticketCollection.setTickets(ticketsnottodelete);
        TicketsUpdating.ticketToDelete(ticketsToDelete);
        dBworking.uploadAllTickets();
    }

    @Override
    public String getInfo() {
        return "------>Удалить из коллекции все элементы,значение поля person которого совпадает с введённым.";
    }
}
