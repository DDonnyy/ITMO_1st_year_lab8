package Common.Commands;

import Common.Command;
import Common.Invoker;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

/**
 * The type Common.Commands.Exit.
 */
public class Exit implements Command {
    /**
     * Instantiates a new Common.Commands.Exit.
     */


    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {

    }

    @Override
    public String getInfo() {
        return "---> Выход из программы(С сохранением на сервере).";
    }
}
