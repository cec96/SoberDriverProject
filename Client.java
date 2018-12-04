import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class Client {
    BufferedReader in;
    PrintWriter out;

    String serverName = "localhost";
    int port = 12345;
    Socket client;
    private Driver thisDriver;

    private Orginization thisOrg;

    private Rider thisRider;

    private LogonScreen stayLogon;
    private User currentUser;

    public Client(){
        stayLogon = new LogonScreen(this);

        thisDriver = new Driver(this);

        thisOrg = new Orginization(this);

        thisRider = new Rider(this);
    }



    public void connectToServer(){
        try{

            client = new Socket(serverName, port);
            out = new PrintWriter(client.getOutputStream(), true);
            InputStreamReader isr = new InputStreamReader(client.getInputStream());
            in = new BufferedReader(isr);


            stayLogon.setVisible(true);


        }catch (ConnectException con){
            System.exit(1);
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public String getMapImage(){
        return "https://maps.googleapis.com/maps/api/staticmap?center=501+Buckland+Rd+Matamata&zoom=13&size=400x400&key=AIzaSyCULZ5IzPsVtZQXGTPoiE--RTy2JFQRjyo";
    }

    public void runOrgScreen(){
        stayLogon.setVisible(false);
        thisOrg.startOrgScreen();
        currentUser = new OrginizationInfo();
    }

    public void runDriverScreen(){
        stayLogon.setVisible(false);
        thisDriver.setVisible(true);
        currentUser = new DriverInfo();
    }

    public void runRiderScreen(){
        stayLogon.setVisible(false);
        thisRider.setVisible(true);
        currentUser = new RiderInfo();
    }

    public void addNewUser( ){

    }

    public String logonDriver(String DriverName){
        out.println("DRIVER-LOGIN-"+DriverName);

       String serverMes = "";
        try {
            serverMes =  in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return serverMes;
    }

    public void requestRide(String orginizationName, String address, String name){
        RiderInfo tempRider = (RiderInfo) currentUser;
        tempRider.setRiderName(name);

            out.println("RIDER-REQUESTRIDE-"+orginizationName+"-"+address+"-"+name);

    }

    public void addDriver(String driveName, String orgName){
        out.println("ORG-ADDDRIVER-"+driveName+"-"+orgName);

    }





    public static void main(String[] args){
        Client myClient = new Client();
        myClient.connectToServer();
    }

}

