package zhf.table;

import java.math.BigDecimal;

public class GhUpdateTable {
    private String pjname;
    private String offerStatus;
    private String offerWaitingReason;
    private String internFlag;
    private String internOBD;
    private String offerOBDPlan;
    private String offerOBDActual;

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

    public String getOfferStatus() {
	return this.offerStatus;
    }
    
    public String getOfferWaitingReason() {
	return this.offerWaitingReason;
    }

    public String getInternFlag() {
	return this.internFlag;
    }

    public String getInternOBD() {
	return this.internOBD;
    }

    public String getOfferOBDPlan() {
	return this.offerOBDPlan;
    }

    public String getOfferOBDActual() {
	return this.offerOBDActual;
    }

}
