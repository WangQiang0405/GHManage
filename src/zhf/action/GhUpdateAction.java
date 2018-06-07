package zhf.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import zhf.common.GhCommon;
import zhf.logic.GhUpdatelogic;
import zhf.table.GhUpdateTable;
import zhf.util.DBUtil;

public class GhUpdateAction extends ActionSupport {
    private String id;
    private List list;
    private String projecName = "0000";
    private String selOfferStatus = "0";
    private String selOfferWaitingReason = "0";
    private String selInternFlag = "0";
    private String selInternOBD = "";
    private String selOfferOBDPlan = "";
    private String selOfferOBDActual = "";

    public void setId(String id) {
	
	this.id = id;
    }

    public String getId() {
	return this.id;
    }

    public void setModel(String id) {
	this.id = id;
    }
    
    public List getList() {
	return list;
    }
    
    public void setProjecName(String projecName) {
	this.projecName = projecName;
    }

    public String getProjecName() {
	return this.projecName;
    }

    public Map getProjectList() {
	Map map = DBUtil.getProjectList();
	map.put("0000", "");
	return map;
    }
    
    public void setSelOfferStatus(String selOfferStatus) {
	this.selOfferStatus = selOfferStatus;
    }

    public String getSelOfferStatus() {
	return this.selOfferStatus;
    }
    
    public Map getSelOfferStatusList() {
	Map map = new HashMap();
	GhCommon.OfferStatusEnum[] enums = GhCommon.OfferStatusEnum.values(); // ���ö�ٶ�������
        // ����ö�ٶ���
        for (GhCommon.OfferStatusEnum offerStatusEnum : enums) {
            map.put(offerStatusEnum.getCd(), offerStatusEnum.getValue());
        }
	return map;
    }
    
    public void setSelOfferWaitingReason(String selOfferWaitingReason) {
	this.selOfferWaitingReason = selOfferWaitingReason;
    }

    public String getSelOfferWaitingReason() {
	return this.selOfferWaitingReason;
    }
    
    public Map getSelOfferWaitingReasonList() {
	Map map = new HashMap();
	GhCommon.OfferWaitingReasonEnum[] enums = GhCommon.OfferWaitingReasonEnum.values(); // ���ö�ٶ�������
        // ����ö�ٶ���
        for (GhCommon.OfferWaitingReasonEnum offerWReasonEnum : enums) {
            map.put(offerWReasonEnum.getCd(), offerWReasonEnum.getValue());
        }
	
	return map;
    }
    
    public void setSelInternFlag(String selInternFlag) {
	this.selInternFlag = selInternFlag;
    }

    public String getSelInternFlag() {
	return this.selInternFlag;
    }
    
    public Map getSelInternFlagList() {
	Map map = new HashMap();
	GhCommon.InternFlagEnum[] enums = GhCommon.InternFlagEnum.values(); // ���ö�ٶ�������
        // ����ö�ٶ���
        for (GhCommon.InternFlagEnum internFlagEnum : enums) {
            map.put(internFlagEnum.getCd(), internFlagEnum.getValue());
        }

	return map;
    }
    
    public void setSelInternOBD(String selInternOBD) {
	this.selInternOBD = selInternOBD;
    }

    public String getSelInternOBD() {
	return this.selInternOBD;
    }
    
    public void setSelOfferOBDPlan(String selOfferOBDPlan) {
	this.selOfferOBDPlan = selOfferOBDPlan;
    }

    public String getSelOfferOBDPlan() {
	return this.selOfferOBDPlan;
    }
    
    public void setSelOfferOBDActual(String selOfferOBDActual) {
	this.selOfferOBDActual = selOfferOBDActual;
    }

    public String getSelOfferOBDActual() {
	return this.selOfferOBDActual;
    }

    public String init() {
	System.out.println(this.id);
	
	//Where����
	String where = " where id = '" + this.id + "'";
	GhUpdatelogic lgc = new GhUpdatelogic();
	this.list = lgc.getGhInfoList(where);
	
	GhUpdateTable tbl = (GhUpdateTable)this.list.get(0);
	
	// ��Ŀ
	this.projecName = tbl.getPjname();
	
	// Offer״̬
	String offerStatus = "0";
	GhCommon.OfferStatusEnum[] offerStatusEnums = GhCommon.OfferStatusEnum.values(); // ���ö�ٶ�������
        // ����ö�ٶ���
        for (GhCommon.OfferStatusEnum offerStatusEnum : offerStatusEnums) {
            if (offerStatusEnum.getValue().equals(tbl.getOfferStatus())) {
        	offerStatus = offerStatusEnum.getCd();
            }
        }
	this.selOfferStatus = offerStatus;
	
	// Offer�ȴ�����
	String offerWaitingReason = "0";
	GhCommon.OfferWaitingReasonEnum[] offerWReasonEnums = GhCommon.OfferWaitingReasonEnum.values(); // ���ö�ٶ�������
        // ����ö�ٶ���
        for (GhCommon.OfferWaitingReasonEnum offerWReasonEnum : offerWReasonEnums) {
            if (offerWReasonEnum.getValue().equals(tbl.getOfferWaitingReason())) {
        	offerWaitingReason = offerWReasonEnum.getCd();
            }
        }
        this.selOfferWaitingReason = offerWaitingReason;
        
        // ʵϰ
	String internFlag = "0";
	GhCommon.InternFlagEnum[] internFlagEnums = GhCommon.InternFlagEnum.values(); // ���ö�ٶ�������
        // ����ö�ٶ���
        for (GhCommon.InternFlagEnum internFlagEnum : internFlagEnums) {
            if (internFlagEnum.getValue().equals(tbl.getInternFlag())) {
        	internFlag = internFlagEnum.getCd();
            }
        }
        this.selInternFlag = internFlag;
        
	// ʵϰ��
	this.selInternOBD = tbl.getInternOBD();
	// Offer�ƻ���
	this.selOfferOBDPlan = tbl.getOfferOBDPlan();
	// Offerʵ����
	this.selOfferOBDActual = tbl.getOfferOBDActual();
	
	return SUCCESS;
    }
    
    public String execute() {
	
	return SUCCESS;
	
    }
}
