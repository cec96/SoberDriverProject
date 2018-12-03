import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;

public class Server extends Thread{
    ServerSocket serverSocket;
    Socket client;
    ArrayList<User> usersOnServer;
    ArrayList<OrginizationInfo> orginizationList;

    public boolean open = true;

    public Server(){


        usersOnServer = new ArrayList<>();

        try{
            serverSocket = new ServerSocket(12345);
        }catch (IOException e){
            e.printStackTrace();
        }

        while (open){
            try{
                client = serverSocket.accept();
                Thread runServer = new Runnable();

                runServer.start();
                }catch (IOException f){
                f.printStackTrace();
            }
        }
    }

    class Runnable extends Thread{
        public void run(){

        }
    }
/*
    public String getOrgList(){
        String orgList = "";
        for(int i = 0;i<orginizationList.size();i++) {
            if (i + 1 == orginizationList.size()) {
                orgList += orginizationList.get(i).getOrgName();
            } else {
                orgList += orginizationList.get(i).getOrgName() + "-";
            }
        }
        return orgList;
    }
*/
    public static void main(String[] args){
        Server myServer  = new Server();
    }
}
