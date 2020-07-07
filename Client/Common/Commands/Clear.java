package Common.Commands;

import Common.Command;
import Common.Invoker;
import Common.TicketCollection;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
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


    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {

    }

    @Override
    public String getInfo() {
        return "---> Удаление элементов,принадлежащих вам";
    }
}
