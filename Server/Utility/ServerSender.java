package Utility;

import Common.Ticket;
import Utility.ServerReceiver;
import com.sun.corba.se.impl.activation.ServerMain;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

//Параметр needAnswer имеет 3 значения
//(0 - ответ от клиента не ожидается,Готов делать след команду.)
//1 - Необходим ответ от клиента,делать след команду не готов
//2 - Просто сообщение,но делать команду не готов(особенность)))))))))))))
//5 - критическая ошибка,принудительное отключение клиента.
public class ServerSender {
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    public  void send(Socket client, String message,TreeMap<Long,Ticket> ticketsToChange,String toDoWithCollection) {
        Sender sender = new Sender(client, message,ticketsToChange,toDoWithCollection);
        forkJoinPool.execute(sender);
        System.out.println("Отправляю данные клиенту c адресом: "+client.getLocalAddress()+client.getPort());
    }
    public class Sender implements Runnable{
        private Socket client;
        private String message;
        private TreeMap<Long,Ticket> ticketsToChange;
        private String toDoWithCollection;
        public Sender(Socket client, String message,TreeMap<Long,Ticket> ticketsToChange,String toDoWithCollection ){
            this.client = client;
            this.message =message;
            this.ticketsToChange = ticketsToChange;
            this.toDoWithCollection = toDoWithCollection;
        }
        @Override
        public void run() {
            try {
                HashMap <Integer,Object> map = new HashMap<>();
                map.put(1,message);
                map.put(2,ticketsToChange);
                map.put(3,toDoWithCollection);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                objectOutputStream.writeObject(map);
            } catch (IOException e) {

            }
        }
    }
}