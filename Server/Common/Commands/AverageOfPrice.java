package Common.Commands;
import Common.Command;
import Common.Invoker;
import Common.TicketCollection;
import Utility.DBworking;
import Utility.ServerSender;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The type Average of price.
 */
public class AverageOfPrice implements Command {
    /**
     * Instantiates a new Average of price.
     */
    public AverageOfPrice(){
        Invoker.regist("average_of_price",this);
    }


    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {
        DBworking dBworking = new DBworking();ServerSender serverSender = new ServerSender();
        dBworking.loadAllTickets();
        TicketCollection ticketCollection = new TicketCollection();
        int size = ticketCollection.getSize();
        AtomicReference<Double> price = new AtomicReference<>((double) 0);
        if (size>0) {
            ticketCollection.getTickets().entrySet().stream().forEach(x->price.set(price.get()+x.getValue().getPrice()));

            double result = (price.get())/size;
            serverSender.send(clientSocket,String.valueOf(result),null,null);
        } else {
            serverSender.send(clientSocket,"no",null,null);
        }
    }

    @Override
    public String getInfo() {
        return "---> Возвращает среднюю цену всех билетов";
    }
}
