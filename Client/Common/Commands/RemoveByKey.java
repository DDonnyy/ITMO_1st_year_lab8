package Common.Commands;

import Common.Command;
import Common.Invoker;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

/**
 * The type Remove by key.
 */
public class RemoveByKey implements Command {
    /**
     * Instantiates a new Remove by key.
     */
    public RemoveByKey() {
        Invoker.regist("remove_key", this);
    }


    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {

    }

    @Override
    public String getInfo() {
        return "---> Удалить элемент из коллекции по ключу.";
    }
}
