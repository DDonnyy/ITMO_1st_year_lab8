package Common.Commands;

import Common.Command;
import Common.Invoker;
import Common.TicketCollection;
import Utility.ServerSender;

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
        if (ExecuteScript.inExecution)  ServerSender.send(ticketCollection.getInfo(),2);
        else ServerSender.send(ticketCollection.getInfo(),0);
    }

    @Override
    public String getInfo() {
        return "---> Вывести информацию об коллекции.";
    }
}
