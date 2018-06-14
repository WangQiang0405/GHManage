package zhf.table;

public class GhUpdateTable {
    private String pjname;
    private String offerStatus;
    private String offerWaitingReason;
    private String internFlag;
    private String internOBD;
    private String offerOBDPlan;
    private String offerOBDActual;

    public GhUpdateTable() {
        super();
    }
    
    public GhUpdateTable(String pjname, 
	    String offerStatus,
	    String offerWaitingReason,
	    String internFlag,
	    String internOBD,
	    String offerOBDPlan,
	    String offerOBDActual) {
	
	this.pjname = pjname;
	this.offerStatus = offerStatus;
	this.offerWaitingReason = offerWaitingReason;
	this.internFlag = internFlag;
	this.internOBD = internOBD;
	this.offerOBDPlan = offerOBDPlan;
	this.offerOBDActual = offerOBDActual;
    }
    
    public String getPjname() {
	return this.pjname;
    }
    
    public void setPjname(String pjname) {
	this.pjname = pjname;
    }
    
    public String getOfferStatus() {
	return this.offerStatus;
    }
    
    public void setOfferStatus(String offerStatus) {
	this.offerStatus = offerStatus;
    }
    
    public String getOfferWaitingReason() {
	return this.offerWaitingReason;
    }
    
    public void setOfferWaitingReason(String offerWaitingReason) {
	this.offerWaitingReason = offerWaitingReason;
    }

    public String getInternFlag() {
	return this.internFlag;
    }
    
    public void setInternFlag(String internFlag) {
	this.internFlag = internFlag;
    }

    public String getInternOBD() {
	return this.internOBD;
    }
    
    public void setInternOBD(String internOBD) {
	this.internOBD = internOBD;
    }

    public String getOfferOBDPlan() {
	return this.offerOBDPlan;
    }
    
    public void setOfferOBDPlan(String offerOBDPlan) {
	this.offerOBDPlan = offerOBDPlan;
    }

    public String getOfferOBDActual() {
	return this.offerOBDActual;
    }
    
    public void setOfferOBDActual(String offerOBDActual) {
	this.offerOBDActual = offerOBDActual;
    }

}
