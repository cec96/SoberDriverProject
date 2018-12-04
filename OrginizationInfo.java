public class OrginizationInfo extends User {

    private String orgName;
    public OrginizationInfo(){
        super();
    }

    public void setOrgName(String orgName){
        this.orgName = orgName;
    }

    public String getOrgName(){
        return this.orgName;
    }
}
