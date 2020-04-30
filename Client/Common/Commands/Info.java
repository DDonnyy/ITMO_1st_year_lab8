package Common.Commands;

import Common.Command;
import Common.TicketCollection;
import Common.Invoker;

import java.io.IOException;

/**
 * The type Common.Commands.Info.
 */
public class Info implements Command {
    /**
     * Instantiates a new Common.Commands.Info.
     */
    public Info(){
        Invoker.regist("info",this);

    }

    @Override
    public void execute(String par1) throws IOException {
        TicketCollection ticketCollection = new TicketCollection();
        ticketCollection.getInfo();
    }

    @Override
    public String getInfo() {
        return "---> Вывести информацию об коллекции.";
    }
}
