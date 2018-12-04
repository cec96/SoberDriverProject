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
            try{
                InputStreamReader isr = new InputStreamReader(client.getInputStream());
                PrintWriter os = new PrintWriter(client.getOutputStream(),true);
                BufferedReader in = new BufferedReader(isr);

                String userType = "";
                System.out.println(userType);

                String fromClient = in.readLine();
                System.out.println(fromClient);

                String[] commandLine = fromClient.split("-");

                if(commandLine[0].equals("DRIVER")){
                    switch (commandLine[1]){
                        case "LOGIN":
                            DriverInfo thisDriver = null;
                            boolean driverExists = false;
                            for(int i = 0;i<usersOnServer.size();i++){
                                if(usersOnServer.get(i) instanceof DriverInfo){
                                    thisDriver = (DriverInfo) usersOnServer.get(i);
                                    if(thisDriver.getDriverName().equals(commandLine[2])){
                                        driverExists = true;
                                        break;
                                    }
                                }
                            }

                            if(driverExists){
                                os.println(thisDriver.getDriverOrg()+"-"+thisDriver.getRideRequestLocation()+"-"+thisDriver.getRiderRequestName());
                            }else{
                                os.println("ERROR");
                            }

                            break;

                        case "GETLOCATION":

                    }
                }else if(commandLine[0].equals("RIDER")){
                    switch (commandLine[1]){
                        case "REQUESTRIDE":
                            //boolean driverExists = false;
                            for(int i = 0;i<usersOnServer.size();i++){
                                if(usersOnServer.get(i) instanceof DriverInfo){
                                    DriverInfo thisDriver = (DriverInfo) usersOnServer.get(i);
                                    if(thisDriver.getDriverOrg().equals(commandLine[2])){
                                        thisDriver.setRideRequestLocation(commandLine[3]);
                                        thisDriver.setRideRequestName(commandLine[4]);
                                        //driverExists = true;
                                        //break;
                                    }
                                }
                            }
                            break;
                    }
                }else if(commandLine[0].equals("ORG")){
                    switch (commandLine[1]){
                        case "ADDDRIVER":
                            DriverInfo thisDriver = new DriverInfo();
                            thisDriver.setDriverName(commandLine[2]);
                            thisDriver.setDriverOrg(commandLine[3]);
                            usersOnServer.add(thisDriver);
                            break;
                    }
                }



            }catch (IOException e){
                e.printStackTrace();
            }

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
