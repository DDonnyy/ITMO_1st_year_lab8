package Utility;

import Common.Command;
import Common.Commands.ExecuteScript;
import Common.Ticket;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewConnection implements Runnable {
    static ExecutorService executeIt = Executors.newCachedThreadPool();
    public static Integer[] userColor;
    private Socket clientSocket;
    private String newuser;
    public NewConnection(Socket client){
        this.clientSocket=client;
    }
    @Override
    public void run() {
        try {

            DBworking dBworking = new DBworking();
            ServerSender serverSender =new ServerSender();
            ServerReceiver serverReceiver = new ServerReceiver();
            if (dBworking.ConnectionToDB()) {
                String key = "";
                boolean islogged = false;
                while (!islogged) {
                    Integer[] color = new Integer[]{(int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)};
                    String answer = (String) serverReceiver.receive(clientSocket);
                    String k[] = answer.split("-");
                    key = k[0];
                    String login = k[1];
                    String password = CreateServer.PasswordCoder(k[2]);
                    if (key.equals("login")){

                        if (dBworking.userExist(login, password)) {
                            newuser = login;
                            serverSender.send(clientSocket, "yes-"+Arrays.toString(userColor),null,null);
                            islogged = true;
                        }
                        else {
                            serverSender.send(clientSocket,"no-",null,null);
                        }
                    } else
                        if (key.equals("reg")){
                            if (dBworking.addNewUser(login, password,color)) {
                                serverSender.send(clientSocket, "yes-"+ Arrays.toString(color),null,null);
                                islogged = true;
                                newuser = login;
                            } else serverSender.send(clientSocket,"no-",null,null);
                        }
                }
                Thread.sleep(1000);
                TicketsUpdating.sendAllTickets(clientSocket);
                TicketsUpdating.clients.add(clientSocket);

                try {
                    while (clientSocket.isConnected()) {
                        System.out.println("Ожидаю команду от клиента с адресом: " + clientSocket.getLocalAddress() + clientSocket.getPort()
                        );
                        Map<Command, Object> commandStringMap;
                        Object o = serverReceiver.receive(clientSocket);
                        commandStringMap = (Map<Command, Object>) o;
                        System.out.println("Выполняю команду " + commandStringMap.entrySet().iterator().next().getKey().getClass().getName());
                        commandStringMap.entrySet().iterator().next().getKey().execute(commandStringMap.entrySet().iterator().next().getValue(),clientSocket,newuser);

                    }
                } catch (IOException e){
                    throw new NullPointerException();

                } catch (SQLException e){
                    serverSender.send(clientSocket,"",null,null);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            ServerSender serverSender =new ServerSender();
            serverSender.send(clientSocket,"Нет подключения к базе данных,попробуйте переподключится.",null,null);

        }
        catch (NullPointerException e){
            System.out.println("Клиент с адресом:"+clientSocket.getLocalAddress() + clientSocket.getPort()+" отключился");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
