package Common.Commands;

import Common.Command;
import Common.FileRead;
import Common.Invoker;
import Utility.ServerSender;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * The type Execute script.
 */
public class ExecuteScript implements Command {
    /**
     * Instantiates a new Execute script.
     */
    public ExecuteScript(){
        Invoker.regist("execute_script",this);
    }


    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {

    }

    @Override
    public String getInfo() {
        return "---> Cчитать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
