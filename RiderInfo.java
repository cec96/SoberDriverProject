public class RiderInfo extends User implements WhoAmI{

    String riderName;
    String riderLocation;

    public RiderInfo(){
        super();
    }

    public void setRiderName(String riderName){
        this.riderName = riderName;
    }

    @Override
    public String getWhoIAm() {
        return "RIDER";
    }
}
