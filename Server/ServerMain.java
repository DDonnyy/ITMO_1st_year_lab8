
import Common.*;
import Common.Commands.*;
import Utility.*;
import org.w3c.dom.ls.LSOutput;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerMain {
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
        CreateServer.create();
        SaveInFile s = new SaveInFile();
        s.checkForSaveCommand();
        System.out.println("Сервер запущен.");
        TicketCollection tickets = new TicketCollection();
        try {
            tickets.setTickets(Decoder.decodeIntoCollection(FileRead.readFromFile(FileRead.getFilePath())));
        }catch (FileNotFoundException e){
            System.out.println("Изначальный файл не найден,коллекция пустует.");
        }
        while (true) {
            GetCommand();
        }
    }

        public static void GetCommand(){
                Map<Command, String> commandStringMap;
                try {
                    System.out.println("Жду команду.");
                    Object o = ByteToObject.Cast(ServerReceiver.receive());
                    commandStringMap = (Map<Command, String>) o;
                    CreateServer.serverIsAvaible=false;
                    System.out.println("Выполняю команду "+commandStringMap.entrySet().iterator().next().getKey().getClass().getName());
                    commandStringMap.entrySet().iterator().next().getKey().execute(commandStringMap.entrySet().iterator().next().getValue());
                    CreateServer.serverIsAvaible=true;
                    if (!commandStringMap.entrySet().iterator().next().getKey().getClass().getName().equals("Common.Commands.Exit")) System.out.println("Команда выполнена! Отправляю результат клиенту с портом "+ CreateServer.currentClientPort+".");
                }
                catch (ClassCastException | IOException e){
                    ServerSender.send("Сообщение от Сервера:\"Возникли небольшие неполадки с вашим подключением,но сейчас всё по кайфу,ожидаю команд.\"\n",0);
                }
            }



    }

