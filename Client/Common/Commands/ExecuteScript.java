package Common.Commands;

import Common.Command;
import Common.CreateTicket;
import Common.FileRead;
import Common.Invoker;
import Utility.ClientSender;
import Utility.FileHandler;
import Utility.ReceiverAndCanvasChanger;


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
    String name = "execute_script";
    public static String result = "";
    public String getName() {
        return name;
    }
    /**
     * Instantiates a new Execute script.
     */
    public ExecuteScript(){
        Invoker.regist("execute_script",this);
    }


    @Override
    public void execute(Object par1, Socket clientSocket, String user) throws IOException, SQLException {
//        try {
//            String filename = new FileHandler().getFilename();
//            CreateTicket creater = new CreateTicket();
//            try {
//                ClientSender sender = App.getSender();
//
//                String data = .read(filename);
//                Commands command = new Commands();
//                String res = "";
//                //System.out.println("data " + data);
//                if (data != null) {
//                    String[] commands = data.split("\n|\r\n");
//                    //System.out.println("command.lenght " + commands.length);
//                    for (int i = 0; i < commands.length; i++) {
//                        if (!(commands[i].equals("execute_script " + filename))) {
//                            command.setCommand(commands[i]);
//                            if (command.getCommand() != null) {
//                                String tempCommandName = commands[i];
//                                String received = "";
//                                sender.sendCommand(command);
//                                String whyFailed = "";
//                                if (sender.isCommandWithObject())
//                                    if (receiver.receive().equals("newHuman")) {
//                                        String[] params = new String[11];
//                                        for (int j = 0; j < 11; j++) {
//                                            i++;
//                                            params[j] = commands[i];
//                                        }
//                                        HumanBeing newHuman = creater.createFromFile(params);
//                                        if (newHuman != null)
//                                            sender.send(newHuman);
//                                        else {
//                                            whyFailed = creater.getWhyFailed();
//                                            sender.send(new HumanBeing());
//                                        }
//                                    }
//                                receiver.receiveCollection();
//                                received = receiver.receive();
//                                if (received != null) {
//                                    res += "Выполнение команды \"" + tempCommandName + "\":\n" + received + whyFailed + "\n\n";
//                                    ;
//                                }
//                            }
//                            if (command.getMessage() != (null)) {
//                                String[] sentence = command.getMessage().split(",");
//                                res += "Командa \"" + commands[i] + "\": " + sentence[0] + ".\n\n";
//                            }
//                        } else res += "Командa \"" + commands[i] + "\": невыполнима.\n";
//                    }
//                }
//                result = (res);
//
//            } catch (NullPointerException | ClassNotFoundException e) {
//
//            }
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }

    }


    @Override
    public String getInfo() {
        return "---> Cчитать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
