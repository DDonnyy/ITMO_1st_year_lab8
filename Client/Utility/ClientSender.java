package Utility;

import java.io.*;
import java.net.*;

public class ClientSender {
  public static Boolean serverisconnected = false;
        public static void send(Object o) throws SocketException {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ReceiverAndCanvasChanger.sock.getOutputStream());
            objectOutputStream.writeObject(o);
    } catch (IOException e) {

            System.out.println("Произошла некоторая ошибка,необходимо переподключение.");
            ClientSender.serverisconnected = false;
            throw new SocketException();
        }
    }
    public static void tryToConnect() throws InterruptedException, IOException {
            try {
                Socket socket = new Socket("localhost", 3017);
                serverisconnected = true;
                ReceiverAndCanvasChanger.sock = socket ;
            }
            catch (IOException e){
                throw  e;
            }
    }
}

