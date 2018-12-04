import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    /*
    BufferedReader in;
    PrintWriter out;

    String serverName = "localhost";
    int port = 12345;
    Socket client;
*/
    private Driver thisDriver;

    private Orginization thisOrg;

    private Rider thisRider;
    private LogonScreen stayLogon;
    private User currentUser;
    ArrayList<User> currentusers = new ArrayList<>();

    public Client(){

        stayLogon = new LogonScreen(this);


        thisDriver = new Driver(this);

        thisOrg = new Orginization(this);

        thisRider = new Rider(this);
        stayLogon.setVisible(true);
    }


/*
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
*/
    public String getMapImage(){
        return "https://maps.googleapis.com/maps/api/staticmap?center=501+Buckland+Rd+Matamata&zoom=13&size=400x400&key=AIzaSyCULZ5IzPsVtZQXGTPoiE--RTy2JFQRjyo";
    }

    public void runOrgScreen(){
        thisOrg.startOrgScreen();
        currentUser = new OrginizationInfo();
    }

    public void runDriverScreen(){
        thisDriver.setVisible(true);
        currentUser = new DriverInfo();
    }

    public void runRiderScreen(){
        thisRider.setVisible(true);
        currentUser = new RiderInfo();
    }

    public void addNewUser( ){

    }

    public String logonDriver(String DriverName){
      for(int i = 0; i<currentusers.size();i++){
          if(currentusers.get(i) instanceof DriverInfo){
              DriverInfo tempDriver = (DriverInfo) currentusers.get(i);
              if(tempDriver.getDriverName().equals(DriverName)){
                  if(tempDriver.getRiderRequestName().equals("")){
                      return tempDriver.getDriverOrg()+"- -NO RIDES REQUESTED";
                  }else {
                      return tempDriver.getDriverOrg() + "-" + tempDriver.getRideRequestLocation() + "-" + tempDriver.getRiderRequestName();
                  }
              }
          }

      }
      return "DRIVER NOT FOUND";
    }

    public String requestRide(String orginizationName, String address, String name){
       DriverInfo searchDrive;
       for(int i = 0;i<currentusers.size();i++){
           if(currentusers.get(i) instanceof DriverInfo){
               searchDrive = (DriverInfo) currentusers.get(i);
               if(searchDrive.getDriverOrg().equals(orginizationName)){
                   searchDrive.setRideRequestLocation(address);
                   searchDrive.setRideRequestName(name);
                   currentusers.set(i,searchDrive);
                   return "DRIVER FOUND";
               }
           }
       }
       return "DRIVER NOT FOUND";
    }

    public void addDriver(String driveName, String orgName){

        DriverInfo tempDriver = new DriverInfo();
        tempDriver.setDriverName(driveName);
        tempDriver.setDriverOrg(orgName);
        currentusers.add(tempDriver);
        /*
        out.println("ORG-ADDDRIVER-"+driveName+"-"+orgName);
        */
    }

    public String deleteDriver(String DriverName,String driverOrg){
        for(int i =0;i<currentusers.size();i++){
            if(currentusers.get(i) instanceof DriverInfo){
                DriverInfo tempDriver = (DriverInfo) currentusers.get(i);
                if(tempDriver.getDriverName().equals(DriverName)&& tempDriver.getDriverOrg().equals(driverOrg)){
                    currentusers.remove(i);
                    return "DRIVER DELETED";
                }
            }
        }
        return "DRIVER NOT FOUND";
    }





    public static void main(String[] args){

        Client myClient = new Client();
  //      myClient.connectToServer();
    }

}

