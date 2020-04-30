package Common.Commands;

import Common.Command;
import Common.Invoker;
import Common.Ticket;
import Common.TicketCollection;
import Utility.ServerSender;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalDouble;

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
    public void execute(String par1) throws IOException {
        TicketCollection ticketCollection = new TicketCollection();
        if (ticketCollection.getSize() > 0) {
            double averagePrice = (ticketCollection.getTickets().entrySet().stream().mapToDouble((s) -> s.getValue().getPrice()).reduce(Double::sum).getAsDouble()) / (ticketCollection.getSize());
            if(ExecuteScript.inExecution){
                ServerSender.send("Средняя цена за билет = " + averagePrice,2);
            } else
            ServerSender.send("Средняя цена за билет = " + averagePrice,0);
        }
        else {
            if(ExecuteScript.inExecution){
                ServerSender.send("Коллекция пуста,средней цены нет.",2);
            } else
            ServerSender.send("Коллекция пуста,средней цены нет.",0);
        }
        }

    @Override
    public String getInfo() {
        return "---> Возвращает среднюю цену всех билетов";
    }
}
