package Common.Commands;

import Common.Command;
import Common.Invoker;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class RemoveAllByPerson implements Command {
    public RemoveAllByPerson() {
        Invoker.regist("remove_all_by_person", this);
    }


    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {

    }

    @Override
    public String getInfo() {
        return "------>Удалить из коллекции все элементы,значение поля person которого совпадает с введённым.";
    }
}
