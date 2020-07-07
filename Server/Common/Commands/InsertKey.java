package Common.Commands;

import Common.*;
import Utility.*;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Insert key.
 */
public class InsertKey implements Command {
    /**
     * Instantiates a new Insert key.
     */
    public InsertKey(){
        Invoker.regist("insert_key",this);
    }


    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {
        TicketCollection ticketCollection = new TicketCollection();
        DBworking dBworking = new DBworking();
        dBworking.ConnectionToDB();
        dBworking.loadAllTickets();
        Ticket ticket = (Ticket) par1;
        ticket.setUser(user);
        String[]kek=(Arrays.toString(NewConnection.userColor)).replace("[","").replace("]","").replace(" ","").split(",");
        int l1 = Integer.parseInt(kek[0]);
        int l2 = Integer.parseInt(kek[1]);
        int l3 = Integer.parseInt(kek[2]);
        ticket.setColor(new Color(l1,l2,l3));
        ticket.setCreationDate(Timestamp.from(Instant.now()));
        ticket.getPerson().setPassportID(dBworking.getNewPassportID());
        ticketCollection.putTicket(ticket.getMapKey(),ticket);
        dBworking.uploadAllTickets();
        TreeMap<Long, Ticket> tickets = new TreeMap<>();
        tickets.put(ticket.getMapKey(),ticket);
        TicketsUpdating.ticketToadd(tickets);
    }

    @Override
    public String getInfo() {
        return "---> Добавить элемент в коллекцию.";
    }
}
