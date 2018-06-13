package zhf.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import zhf.common.GhCommon;
import zhf.logic.GhSearchlogic;
import zhf.table.GhSearchTable;
import zhf.util.DBUtil;

public class GhSearchAction extends ActionSupport implements ModelDriven {
    private String result = "";
    private String selRowIndex = "0";
    private BigDecimal selectid = new BigDecimal(0);
    private String eventid = "0";// 0：初始化；1：检索,表示件数；2：上下页;3:确定;4:更新
    private List<GhSearchTable> ghSearchList;
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

    public void setSelRowIndex(String selRowIndex) {
	this.selRowIndex = selRowIndex;
    }

    public String getSelRowIndex() {
	return this.selRowIndex;
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

    public void setTotalMessage(int totalMessage) {
	this.totalMessage = totalMessage;
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
	map.put("0000", "全部");
	return map;
    }

    public List<GhSearchTable> getGhSearchList() {
	return this.ghSearchList;
    }
    
    public void setGhSearchList(List<GhSearchTable> ghSearchList) {
	this.ghSearchList = ghSearchList;
    }

    public String execute() {
	
	this.result = "";
	if ("4".equals(this.eventid)) {
	    if (this.selectid.equals(new BigDecimal(0))) {
		this.result = this.getText("search.errselect");
		return GhCommon.FAIL;
	    }
	}
	
	//返回时检索条件取得
	if ("5".equals(this.eventid)) {
	    Map pram = (Map)ActionContext.getContext().getSession().get("GH_SEARCH_PRAM");
	    this.selectid = (BigDecimal)pram.get("SELECT_ID");
	    this.selRowIndex = (String)pram.get("ROW_INDEX");
	    this.ProjecName = (String)pram.get("PROJECT_NAME");
	    this.ghname = (String)pram.get("GH_NAME");
	    this.OfferStatus = (String)pram.get("OFFER_STATUS");
	    this.currenPageNo = (Integer)pram.get("CURREN_PAGE_NO");
	    //pram.put("PAGE_SPAN", this.pageSpan);
	}
	
	//SQL条件
	StringBuffer strWhereEdit = new StringBuffer();
	
	strWhereEdit.append("");
	// 项目
	if (!this.ProjecName.equals("0000")) {
	    strWhereEdit.append(" and pjname = '" + this.ProjecName + "'");
	}
	
	// 姓名
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
	
	//检索条件保持
	if ("4".equals(this.eventid)) {
	    Map pram = new HashMap();
	    pram.put("SELECT_ID", this.selectid);
	    pram.put("ROW_INDEX", this.selRowIndex);
	    pram.put("PROJECT_NAME", this.ProjecName);
	    pram.put("GH_NAME", this.ghname);
	    pram.put("OFFER_STATUS", this.OfferStatus);
	    pram.put("PAGE_SPAN", this.pageSpan);
	    pram.put("CURREN_PAGE_NO", this.currenPageNo);
	    ActionContext.getContext().getSession().put("GH_SEARCH_PRAM", pram);
	    return GhCommon.UPDATE;
	}
	
	GhSearchlogic lgc = new GhSearchlogic();
	this.ghSearchList = lgc.getSearchGhList(strWhere.toString(), this.pageSpan, this.currenPageNo);

	this.totalMessage = lgc.getSearchGhListRecs(strWhere.toString());
	ActionContext.getContext().getSession().put("totalMessage", totalMessage);

	// 最大页数
	this.maxPageNo = totalMessage % pageSpan == 0 ? totalMessage / pageSpan : (totalMessage / pageSpan + 1);
	ActionContext.getContext().getSession().put("maxPageNo", maxPageNo);

	return SUCCESS;
    }

    public String init() {
	this.eventid = "0";
	return SUCCESS;
    }
}
