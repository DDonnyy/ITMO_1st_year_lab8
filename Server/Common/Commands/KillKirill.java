package Common.Commands;

import Common.Command;
import Common.Invoker;
import Utility.ServerSender;

import java.io.IOException;

/**
 * The type Kill kirill.
 */
public class KillKirill implements Command {
    /**
     * Instantiates a new Kill kirill.
     */
    public KillKirill(){
        Invoker.regist("kill_kirill",this);
    }
    @Override
    public void execute(String par1) throws IOException {
        if (ExecuteScript.inExecution)
        ServerSender.send("Кирилл успешно уничтожен."+par1,2);
    else ServerSender.send("Кирилл успешно уничтожен."+par1,0);
    }

    @Override
    public String getInfo() {
        return "уничтожение кирилла";
    }
}
