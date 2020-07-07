package Common.Commands;

import Common.Command;
import Common.Invoker;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

/**
 * The type Update bykey.
 */
public class UpdateBykey implements Command {
    /**
     * Instantiates a new Update bykey.
     */
    public UpdateBykey() {
        Invoker.regist("update_key", this);
    }

    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {
    }

    @Override
    public String getInfo() {
        return "---> Обновить значения элемента по его ключу.";
    }
}
