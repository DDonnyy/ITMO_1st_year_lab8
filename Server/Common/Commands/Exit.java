package Common.Commands;

import Common.Command;
import Common.Invoker;
import Utility.CreateServer;
import Utility.Save;

import java.io.IOException;

/**
 * The type Common.Commands.Exit.
 */
public class Exit implements Command {
    /**
     * Instantiates a new Common.Commands.Exit.
     */
    public Exit(){
        Invoker.regist("exit",this);
    }
    @Override
    public void execute(String par1) throws IOException {
        System.out.println("Клиент отключился,запускаю автосохранение.\nОжидаю подключение нового клиента.");
        Save save =new Save();
        save.execute(null);
    }

    @Override
    public String getInfo() {
        return "---> Выход из программы(С сохранением на сервере).";
    }
}
