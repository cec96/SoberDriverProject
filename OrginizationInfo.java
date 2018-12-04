public class OrginizationInfo extends User implements WhoAmI{

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

    @Override
    public String getWhoIAm() {
        return "ORG";
    }
}
