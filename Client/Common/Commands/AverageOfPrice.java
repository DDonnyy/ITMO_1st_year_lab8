package Common.Commands;
import Common.Command;
import Common.Invoker;
import Common.TicketCollection;

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

    }

    @Override
    public String getInfo() {
        return "---> Возвращает среднюю цену всех билетов";
    }
}
