package Common.Commands;

import Common.Command;
import Common.Invoker;
import Common.TicketCollection;
import Utility.ServerSender;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Common.Commands.Clear.
 */
public class Clear implements Command {
    /**
     * Instantiates a new Common.Commands.Clear.
     */
    public Clear(){
        Invoker.regist("clear",this);
    }

    TicketCollection ticketCollection = new TicketCollection();
    @Override
    public void execute(String par1) throws IOException {
        if (ticketCollection.getTickets().size()>0){
            ticketCollection.getTickets().clear();
            if(ExecuteScript.inExecution){
                ServerSender.send("Коллекция очищена!",2);
            } else ServerSender.send("Коллекция очищена!",0);
            ticketCollection.setDateOFCreation(null);
        }
        else
        if(ExecuteScript.inExecution){
            ServerSender.send("Коллекция уже пуста.",2);
        } else ServerSender.send("Коллекция уже пуста.",0);

    }

    @Override
    public String getInfo() {
        return "---> Очистить коллекцию.";
    }
}
