package zhf.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import zhf.logic.GhSearchlogic;
import zhf.util.DBUtil;

public class GhSearchAction extends ActionSupport implements ModelDriven {
    private String result = "";
    private String updateflg = "init";
    private BigDecimal selectid = new BigDecimal(0);
    private String eventid = "0";// 0����ʼ����1������,��ʾ������2������ҳ;3:ȷ��;4:����
    private List list;
    private int totalMessage = 0;
    private int currenPageNo = 1;
    private int pageSpan = 5;
    private int maxPageNo;
    private String ProjecName = "0000";
    private String OfferStatus = "0";
    private String ghname = "";

    public String getResult() {
	String temp = result;
	this.result = "";
	return temp;
    }

    public void setUpdateflg(String updateflg) {
	this.updateflg = updateflg;
    }

    public String getUpdateflg() {
	return this.updateflg;
    }

    public Object getModel() {
	return this.selectid;
    }

    public void setSelectid(BigDecimal selectid) {
	this.selectid = selectid;
    }

    public BigDecimal getSelectid() {
	return this.selectid;
    }

    public void setEventid(String eventid) {
	this.eventid = eventid;
    }

    public String getEventid() {
	return this.eventid;
    }

    public int getTotalMessage() {
	return totalMessage;
    }

    public void setCurrenPageNo(int currenPageNo) {
	Object obj = ActionContext.getContext().getSession().get("maxPageNo");
	if (obj != null) {
	    this.maxPageNo = (Integer) obj;
	}
	if (currenPageNo <= this.getMaxPageNo() && currenPageNo >= 1) {
	    this.currenPageNo = currenPageNo;
	}
    }

    public int getCurrenPageNo() {
	return this.currenPageNo;
    }

    public void setPageSpan(int pageSpan) {
	this.pageSpan = pageSpan;
	ActionContext.getContext().getSession().put("pageSpan", pageSpan);
	if (this.eventid.equals("1")) {
	    this.setCurrenPageNo(1);
	}
    }

    public int getPageSpan() {
	Object obj = ActionContext.getContext().getSession().get("pageSpan");
	if (obj != null) {
	    this.pageSpan = (Integer) obj;
	}
	return this.pageSpan;
    }

    public Map getSpanList() {
	Map map = new HashMap();
	map.put(5, "ÿҳ5��");
	map.put(10, "ÿҳ10��");
	return map;
    }

    public int getMaxPageNo() {
	return this.maxPageNo;
    }

    public void setMaxPageNo(int maxPageNo) {
	this.maxPageNo = maxPageNo;
    }

    // �����ǵ���ÿҳ��ʾ������ҳ���ȴ�����
    public void setGhname(String ghname) {
	this.ghname = ghname;
    }

    public String getGhname() {
	return this.ghname;
    }

    public void setOfferStatus(String OfferStatus) {
	this.OfferStatus = OfferStatus;
    }

    public String getOfferStatus() {
	return this.OfferStatus;
    }

    public void setProjecName(String projecName) {
	this.ProjecName = projecName;
    }

    public String getProjecName() {
	return this.ProjecName;
    }

    public Map getProjectList() {
	Map map = DBUtil.getProjectList();
	map.put("0000", "ȫ��");
	return map;
    }

    public List getGhSearchList() {
	return list;
    }

    public String execute() {
	
	//SQL����
	StringBuffer strWhereEdit = new StringBuffer();
	
	strWhereEdit.append("");
	// ��Ŀ
	if (!this.ProjecName.equals("0000")) {
	    strWhereEdit.append(" and pjname = '" + this.ProjecName + "'");
	}
	
	// ����
	if (!this.ghname.equals("")) {
	    strWhereEdit.append(" and name like '%" + this.ghname + "%'");
	}
	// OfferStatus
	if (!this.OfferStatus.equals("0")) {
	    String status = "";
	    switch (this.OfferStatus) {
    	    	case "1":
    	    	    status = "Sent";
    	    	    break;
    	    	case "2":
    	    	    status = "Accept";
    	    	    break;
    	    	case "3":
    	    	    status = "Decline";
    	    	    break;
    	    	case "4":
    	    	    status = "Waiting";
    	    	    break;
    	    	default:
    	    	    status = "";
	    }
	    strWhereEdit.append(" and OfferStatus = '" + status + "'");
	}
	
	StringBuffer strWhere = new StringBuffer();
	if (strWhereEdit.toString().length() > 0) {
	    strWhere.append(" where ");
	    strWhere.append(strWhereEdit.toString().substring(4));
	}
	
	Map session = ActionContext.getContext().getSession();
	if (session.get("pageSpan") != null) {
	    this.pageSpan = (Integer) session.get("pageSpan");
	}
	
	GhSearchlogic lgc = new GhSearchlogic();
	this.list = lgc.getSearchGhList(strWhere.toString(), this.pageSpan, this.currenPageNo);

	this.totalMessage = lgc.getSearchGhListRecs(strWhere.toString());
	ActionContext.getContext().getSession().put("totalMessage", totalMessage);

	// ���ҳ��
	this.maxPageNo = totalMessage % pageSpan == 0 ? totalMessage / pageSpan : (totalMessage / pageSpan + 1);
	ActionContext.getContext().getSession().put("maxPageNo", maxPageNo);

	// ������ת�����»�����
	if ("update".equals(session.get("updateflg"))) {
	    ActionContext.getContext().getSession().put("updateflg", "init");
	    this.setUpdateflg("update");
	}
	this.result = "";
	if ("4".equals(this.eventid)) {
	    if (this.selectid.equals(new BigDecimal(0))) {
		this.result = "��ѡ�����Ŀ�����ݣ�";
		return SUCCESS;
	    }

	    if ("init".equals(this.updateflg)) {
		ActionContext.getContext().getSession().put("updateflg", "update");
		this.setUpdateflg("update");
		return "update";
	    } else if ("update".equals(this.updateflg)) {
		this.result = "��ѡ�����Ŀ�����ݣ�";
		return SUCCESS;
	    }
	}
	return SUCCESS;
    }

    public String init() {
	this.eventid = "0";
	return SUCCESS;
    }
}
