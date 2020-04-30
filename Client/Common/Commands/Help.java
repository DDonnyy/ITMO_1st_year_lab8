package Common.Commands;

import Common.Command;
import Common.Invoker;

import java.io.IOException;
import java.util.Map;

/**
 * The type Common.Commands.Help.
 */
public class Help implements Command {
    /**
     * Instantiates a new Common.Commands.Help.
     */
    public Help(){
    Invoker.regist("help",this);
}
    @Override
    public void execute(String par1) throws IOException {
        Map<String,Command> commands = Invoker.getCommandCollection();
        for (Map.Entry<String,Command> entry:commands.entrySet()) {

            System.out.println(entry.getKey()+": "+entry.getValue().getInfo());
        }
        System.out.print("$ ");
    }

    @Override
    public String getInfo() {
        return "---> Вывести справку по доступным командам.";
    }

}

