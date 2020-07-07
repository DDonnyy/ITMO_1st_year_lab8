package Common.Commands;

import Common.Command;
import Common.Invoker;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

/**
 * The type Insert key.
 */
public class InsertKey implements Command {
    /**
     * Instantiates a new Insert key.
     */
    public InsertKey() {
        Invoker.regist("insert_key", this);
    }


    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {

    }

    @Override
    public String getInfo() {
        return "---> Добавить элемент в коллекцию.";
    }
}
