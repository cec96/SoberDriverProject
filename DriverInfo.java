public class DriverInfo extends User implements WhoAmI{

    private String rideRequestLocation = "";
    private String organization = "";
    private String driverName = "";
    private String rideToGiveLocation = "";
    private String rideRequestName = "";
    public DriverInfo(){
        super();
    }

    public void setRideRequestName(String name){
        this.rideRequestName = name;
    }

    public String getRiderRequestName(){
        return this.rideRequestName;
    }

    public void setRideRequestLocation(String location){
        this.rideRequestLocation = location;
    }

    public String getRideRequestLocation(){
        return this.rideRequestLocation;
    }

    public void setDriverName(String name){
        this.driverName = name;
    }

    public String getDriverName(){
        return this.driverName;
    }

    public void setDriverOrg(String organization){
        this.organization = organization;
    }
    public String getDriverOrg(){
        return this.organization;
    }


    @Override
    public String getWhoIAm() {
        return "DRIVER";
    }
}
