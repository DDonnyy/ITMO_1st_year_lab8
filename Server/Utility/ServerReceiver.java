package Utility;

import Utility.CreateServer;
import com.sun.corba.se.spi.ior.IORFactories;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.*;

public class ServerReceiver {
    ExecutorService executorService = Executors.newCachedThreadPool();
    public static ArrayList<Socket> clientSockets;

    public Object receive(Socket client) {
        Receiver receiver = new Receiver(client);
        Future future = executorService.submit(receiver);
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
return null;
    }

    public class Receiver implements Callable {
        Socket client = null;

        public Receiver(Socket socket) {
            this.client = socket;
        }

        @Override
        public Object call() throws Exception {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
                return objectInputStream.readObject();
            } catch (ClassNotFoundException | IOException e) {
                return null;
            }

        }
    }
}

