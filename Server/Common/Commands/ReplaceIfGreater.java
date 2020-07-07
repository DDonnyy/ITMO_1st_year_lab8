package Common.Commands;

import Common.*;
import Utility.DBworking;
import Utility.ServerReceiver;
import Utility.ServerSender;
import Utility.TicketsUpdating;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class ReplaceIfGreater implements Command {
    public ReplaceIfGreater(){
        Invoker.regist("replace_if_greater",this);
    }

    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {
        TicketCollection ticketCollection = new TicketCollection();
        DBworking dBworking = new DBworking();
        dBworking.ConnectionToDB();
        dBworking.loadAllTickets();
        Ticket newTicket = (Ticket)par1;
        Ticket oldTicket = new TicketCollection().getTicket(((Ticket) par1).getMapKey());
        if (newTicket.getTicket().length()>=oldTicket.getTicket().length()){
            new TicketCollection().replaceMovie(newTicket.getMapKey(),newTicket);
            dBworking.uploadAllTickets();
            TreeMap<Long,Ticket> ticketTreeMap = new TreeMap<>();
            ticketTreeMap.put(newTicket.getMapKey(),newTicket);
            TicketsUpdating.ticketToupdate(ticketTreeMap);
        }
    }

    @Override
    public String getInfo() {
        return "-----> Заменить значение по ключу,если новое значение больше старого.";
    }
}
