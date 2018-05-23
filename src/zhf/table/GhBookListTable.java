package zhf.table;

public class GhBookListTable {

    private int id;
    private String projectName;
    private String name;
    private String sex;
    private String offerStatus;
    private String internFlag;
    private String reportManager;
    private String offerOBDPlan;

    public GhBookListTable(int id, 
	    String projectName, 
	    String name, 
	    String sex, 
	    String offerStatus,
	    String internFlag, 
	    String reportManager, 
	    String offerOBDPlan) {

	this.id = id;
	this.projectName = projectName;
	this.name = name;
	this.sex = sex;
	this.offerStatus = offerStatus;
	this.internFlag = internFlag;
	this.reportManager = reportManager;
	this.offerOBDPlan = offerOBDPlan;

    }

    public int getId() {
	return this.id;
    }
    
    public String getProjectName() {
	return this.projectName;
    }

    public String getName() {
	return this.name;
    }

    public String getSex() {
	return this.sex;
    }

    public String getOfferStatus() {
	return this.offerStatus;
    }

    public String getInternFlag() {
	return this.internFlag;
    }

    public String getReportManager() {
	return this.reportManager;
    }

    public String getOfferOBDPlan() {
	return this.offerOBDPlan;
    }

}
