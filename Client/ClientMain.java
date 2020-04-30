import Common.CreatePerson;
import Utility.ClientReceiver;
import Utility.ClientSender;
import Common.Invoker;
import Common.Commands.*;
import Common.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.nio.channels.DatagramChannel;
import java.sql.SQLOutput;
import java.util.Map;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        RemoveAllByPerson removeAllByPerson = new RemoveAllByPerson();
        Remove_lower remove_lower = new Remove_lower();
        RemoveGreater removeGreater = new RemoveGreater();
        KillKirill killKirill = new KillKirill();
        Exit exit = new Exit();
        Show show = new Show();
        Help help = new Help();
        Clear clear = new Clear();
        Info info = new Info();
        FilterByPrice filterByPrice = new FilterByPrice();
        AverageOfPrice averageOfPrice = new AverageOfPrice();
        RemoveByKey removeByKey = new RemoveByKey();
        InsertKey insertKey = new InsertKey();
        UpdateBykey updateBykey = new UpdateBykey();
        ExecuteScript executeScript = new ExecuteScript();
        ReplaceIfGreater replaceIfGreater = new ReplaceIfGreater();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                DatagramSocket ds = new DatagramSocket();
                ClientReceiver.sock = ds ;
                ClientReceiver.clientport = ds.getLocalPort();
            while (true) {
                System.out.println("Введите команду для отправки на сервер: ");
                System.out.print("$ ");
                String s = reader.readLine();
               Map<Command,String> commandparamMap= Invoker.execute(s);
                if (commandparamMap!=null) {
                    ClientSender.send(commandparamMap);
                    try {
                        ClientReceiver.receive();
                    }
                    catch (SocketTimeoutException e){
                        System.out.println("Сервер не отвечает или занят,попробуйте ещё раз и убедитесь,что сервер работает.");
                    }
                }
            }
        }
    }


