package Common.Commands;

import Common.Command;
import Common.Invoker;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ReplaceIfGreater implements Command {
    public ReplaceIfGreater() {
        Invoker.regist("replace_if_greater", this);
    }

    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {

    }

    @Override
    public String getInfo() {
        return "-----> Заменить значение по ключу,если новое значение больше старого.";
    }
}
