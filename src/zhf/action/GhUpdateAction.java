package zhf.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import zhf.common.GhCommon;
import zhf.logic.GhImportLogic;
import zhf.logic.GhUpdatelogic;
import zhf.table.GhUpdateTable;
import zhf.util.DBUtil;
import zhf.util.DateUtil;
import zhf.util.StringUtil;

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
    private String result = "";

    public void setId(String id) {
	this.id = id;
    }

    public String getId() {
	return this.id;
    }

    public void setModel(String id) {
	this.id = id;
    }

    public void setList(List st) {
	this.list = st;
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
	Map map = StringUtil.getOfferStatusMap();
	return map;
    }

    public void setSelOfferWaitingReason(String selOfferWaitingReason) {
	this.selOfferWaitingReason = selOfferWaitingReason;
    }

    public String getSelOfferWaitingReason() {
	return this.selOfferWaitingReason;
    }

    public Map getSelOfferWaitingReasonList() {
	Map map = StringUtil.getOfferWaitingReasonMap();
	return map;
    }

    public void setSelInternFlag(String selInternFlag) {
	this.selInternFlag = selInternFlag;
    }

    public String getSelInternFlag() {
	return this.selInternFlag;
    }

    public Map getSelInternFlagList() {
	Map map = StringUtil.getInternFlagMap();
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

    public String getResult() {
	String temp = this.result;
	this.result = "";
	return temp;
    }

    public String init() {

	// Where����
	String where = " where id = '" + this.id + "'";
	GhUpdatelogic lgc = new GhUpdatelogic();
	this.list = lgc.getGhInfoList(where);

	GhUpdateTable tbl = (GhUpdateTable) this.list.get(0);

	// ��Ŀ
	this.projecName = tbl.getPjname();
	// Offer״̬
	this.selOfferStatus = StringUtil.offerStatusValueToCd(tbl.getOfferStatus());
	// Offer�ȴ�����
	this.selOfferWaitingReason = StringUtil.offerWaitingReasonValueToCd(tbl.getOfferWaitingReason());
	// ʵϰ
	this.selInternFlag = StringUtil.internFlagValueToCd(tbl.getInternFlag());
	// ʵϰ��
	this.selInternOBD = tbl.getInternOBD();
	// Offer�ƻ���
	this.selOfferOBDPlan = tbl.getOfferOBDPlan();
	// Offerʵ����
	this.selOfferOBDActual = tbl.getOfferOBDActual();

	return SUCCESS;
    }

    public String execute() {

	String exeResult = GhCommon.SUCCESS;

	/** ����ȷ�Ͻ���ʵʩ START **/
	// ʵϰ��
	if (!"".equals(this.selInternOBD)) {
	    // �������������ʱ������
	    if (!DateUtil.isDateFormat(this.selInternOBD, GhCommon.YYYYMMDD)) {
		this.result = this.getText("update.errtypeInternOBD");
		exeResult = GhCommon.FAIL;
		return exeResult;
	    }
	}
	// Offer�ƻ���
	if (!"".equals(this.selOfferOBDPlan)) {
	    // �������������ʱ������
	    if (!DateUtil.isDateFormat(this.selOfferOBDPlan, GhCommon.YYYYMMDD)) {
		this.result = this.getText("update.errtypeOfferOBDPlan");
		exeResult = GhCommon.FAIL;
		return exeResult;
	    }
	}
	// Offerʵ����
	if (!"".equals(this.selOfferOBDActual)) {
	    // �������������ʱ������
	    if (!DateUtil.isDateFormat(this.selOfferOBDActual, GhCommon.YYYYMMDD)) {
		this.result = this.getText("update.errtypeOfferOBDActual");
		exeResult = GhCommon.FAIL;
		return exeResult;
	    }
	}
	/** ����ȷ�Ͻ���ʵʩ END **/

	/** ghdetail���������� START **/
	try {
	    GhUpdatelogic lgc = new GhUpdatelogic();
	    this.result = lgc.UpdateDetail(this.projecName, this.selOfferStatus, this.selOfferWaitingReason,
		    this.selInternFlag, this.selInternOBD, this.selOfferOBDPlan, this.selOfferOBDActual, this.id);
	    if (this.result.equals(SUCCESS)) {
		// Where����
		String where = " where id = '" + this.id + "'";
		this.list = lgc.getGhInfoList(where);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	/** ghdetail���������� END **/

	return SUCCESS;

    }
}
