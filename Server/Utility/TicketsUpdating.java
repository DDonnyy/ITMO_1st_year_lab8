package Utility;

import Common.Ticket;
import Common.TicketCollection;

import java.net.Socket;
import java.util.ArrayList;
import java.util.TreeMap;

public class TicketsUpdating {

    public static ArrayList<Socket> clients = new ArrayList<>();

    public static void ticketToDelete(TreeMap<Long, Ticket> tickets) {
        deleteuselessSockets();
        ServerSender serverSender = new ServerSender();
        clients.forEach(x -> serverSender.send(x, null, tickets, "remove"));

    }

    public static void ticketToadd(TreeMap<Long, Ticket> tickets) {
        deleteuselessSockets();
        ServerSender serverSender = new ServerSender();
        clients.forEach(x -> serverSender.send(x, null, tickets, "add"));
    }

    public static void ticketToupdate(TreeMap<Long, Ticket> tickets) {
        ServerSender serverSender = new ServerSender();
        deleteuselessSockets();
        clients.forEach(x -> serverSender.send(x, null, tickets, "update"));

    }
    private static void deleteuselessSockets(){
        clients.forEach(x->{
            if (x.isClosed()||!x.isConnected()||!x.isBound()){
                System.out.println(x);
                clients.remove(x);
            }
        });
    }
    public static void sendAllTickets(Socket socket) {
        DBworking dBworking = new DBworking();
        dBworking.loadAllTickets();
        ServerSender serverSender = new ServerSender();
        serverSender.send(socket, null, new TicketCollection().getTickets(), "add");
    }
}
