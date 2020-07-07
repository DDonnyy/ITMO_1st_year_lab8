package Common.Commands;

import Common.Command;
import Common.Invoker;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

/**
 * The type Remove lower.
 */
public class Remove_lower implements Command {
    /**
     * Instantiates a new Remove lower.
     */
    public Remove_lower() {
        Invoker.regist("remove_lower", this);
    }


    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {

    }

    @Override
    public String getInfo() {
        return "---> Удалить все элементы,чья цена меньше данного";
    }
}
