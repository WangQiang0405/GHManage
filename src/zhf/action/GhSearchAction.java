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
    private BigDecimal mtid = new BigDecimal(0);
    private String eventid = "0";// 0：初始化；1：检索,表示件数；2：上下页;3:确定;4:更新
    private List list;
    private int totalMessage = 0;
    private int currenPageNo = 1;
    private int pageSpan = 5;
    private int maxPageNo;
    private String wherest = "";
    private String ProjecName = "0000";
    private String OfferStatus = "0";
    private String Ghname = "";

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
	return this.mtid;
    }

    public void setMtid(BigDecimal mtid) {
	this.mtid = mtid;
    }

    public BigDecimal getMtid() {
	return this.mtid;
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
	map.put(5, "每页5条");
	map.put(10, "每页10条");
	return map;
    }

    public int getMaxPageNo() {
	return this.maxPageNo;
    }

    public void setMaxPageNo(int maxPageNo) {
	this.maxPageNo = maxPageNo;
    }

    // 以上是调整每页显示条数，页数等处理用
    public void setGhname(String Ghname) {
	this.Ghname = Ghname;
    }

    public String getGhname() {
	return this.Ghname;
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
	map.put("0000", "全部");
	return map;
    }

    public List getGhSearchList() {
	return list;
    }

    public String execute() {
	// JSP下拉列表框项目名
	if (!this.ProjecName.equals("0000")) {
	    wherest = "pjname=\"" + this.ProjecName + "\"";
	}
	// JSP Gh姓名
	if (!this.Ghname.equals("")) {
	    if (!wherest.equals("")) {
		wherest = wherest + " and name like \"" + this.Ghname.trim() + "\"";
	    } else {
		wherest = "name like \"" + this.Ghname.trim() + "\"";
	    }
	}
	// JSP OfferStatus
	if (!this.OfferStatus.equals("0")) {
	    String status;
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
	    if (!status.equals("")) {
		if (!wherest.equals("")) {
		    wherest = wherest + " and OfferStatus=\"" + status + "\"";
		} else {
		    wherest = "OfferStatus=\"" + status + "\"";
		}
	    }
	}

	if (!this.wherest.equals("")) {
	    wherest = " where " + wherest;
	}

	Map session = ActionContext.getContext().getSession();
	if (session.get("pageSpan") != null) {
	    this.pageSpan = (Integer) session.get("pageSpan");
	}
	
	GhSearchlogic lgc = new GhSearchlogic();
	this.list = lgc.getSearchGhList(wherest, this.pageSpan, this.currenPageNo);

	this.totalMessage = lgc.getSearchGhListRecs(wherest);
	ActionContext.getContext().getSession().put("totalMessage", totalMessage);

	// 最大页数
	this.maxPageNo = totalMessage % pageSpan == 0 ? totalMessage / pageSpan : (totalMessage / pageSpan + 1);
	ActionContext.getContext().getSession().put("maxPageNo", maxPageNo);

	// 正常跳转到更新画面标记
	if ("update".equals(session.get("updateflg"))) {
	    ActionContext.getContext().getSession().put("updateflg", "init");
	    this.setUpdateflg("update");
	}
	this.result = "";
	if ("4".equals(this.eventid)) {
	    if (this.mtid.equals(new BigDecimal(0))) {
		this.result = "请选择更新目标数据！";
		return SUCCESS;
	    }

	    if ("init".equals(this.updateflg)) {
		ActionContext.getContext().getSession().put("updateflg", "update");
		this.setUpdateflg("update");
		return "update";
	    } else if ("update".equals(this.updateflg)) {
		this.result = "请选择更新目标数据！";
		return SUCCESS;
	    }
	}
	return SUCCESS;
    }

    public String init() {
	return SUCCESS;
    }
}
