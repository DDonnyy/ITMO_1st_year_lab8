package Common.Commands;

import Common.Command;
import Common.Ticket;
import Common.TicketCollection;
import Common.Invoker;

import java.io.IOException;
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
       double averagePrice = (ticketCollection.getTickets().entrySet().stream().mapToDouble((s)->s.getValue().getPrice()).reduce(Double::sum).getAsDouble())/(ticketCollection.getSize());
        System.out.println(averagePrice);
    }

    @Override
    public String getInfo() {
        return "---> Возвращает среднюю цену всех билетов";
    }
}
