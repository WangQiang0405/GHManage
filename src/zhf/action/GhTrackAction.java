package zhf.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import zhf.logic.GhTrackLogic;
import zhf.util.DBUtil;

public class GhTrackAction extends ActionSupport {
    
    private String eventid = "0";//0：初始化；1：页数，项目改变；2：上下页;3:确定
    private int pageSpan = 10;
    private int currenPageNo = 1;
    private String projecName = "0000";
    private int maxPageNo;
    private int totalMessage;

    public void setEventid(String eventid) {
	this.eventid = eventid;
    }

    public String getEventid() {
	return this.eventid;
    }
    
    public void setPageSpan(int pageSpan) {
	this.pageSpan = pageSpan;
	ActionContext.getContext().getSession().put("blPageSpan", pageSpan);
	if (this.eventid.equals("1")) {
	    this.setCurrenPageNo(1);
	}
    }

    public int getPageSpan() {
	Object obj = ActionContext.getContext().getSession().get("blPageSpan");
	if (obj != null) {
	    this.pageSpan = (Integer) obj;
	}
	return this.pageSpan;
    }

    public Map getSpanList() {
	Map map = new HashMap();
	map.put(10, "每页10条");
	map.put(20, "每页20条");
	return map;
    }

    public int getMaxPageNo() {
	Map session = ActionContext.getContext().getSession();
	if (session.get("blPageSpan") != null) {
	    this.pageSpan = (Integer) session.get("blPageSpan");
	}
	totalMessage = this.getTotalMessage();
	return totalMessage % pageSpan == 0 ? totalMessage / pageSpan : (totalMessage / pageSpan + 1);
    }

    public int getTotalMessage() {
	Map session = ActionContext.getContext().getSession();
	if (session.get("blProjectName") != null) {
	    this.projecName = (String) session.get("blProjectName");
	}
	GhTrackLogic lgc = new GhTrackLogic();
	return lgc.getTotalRecs(projecName);
    }

    public void setCurrenPageNo(int currenPageNo) {
	if (currenPageNo <= this.getMaxPageNo() && currenPageNo >= 1) {
	    this.currenPageNo = currenPageNo;
	}
    }

    public int getCurrenPageNo() {
	return this.currenPageNo;
    }

    public void setProjecName(String projectName) {
	this.projecName = projectName;
	ActionContext.getContext().getSession().put("blProjectName", projectName);
	if (this.eventid.equals("1")) {
	    this.setCurrenPageNo(1);
	}
    }

    public String getProjecName() {
	Object obj = (ActionContext.getContext().getSession().get("blProjectName"));
	if (obj != null) {
	    this.projecName = (String) obj;
	}
	return this.projecName;
    }

    public Map getProjectList() {
	Map map = DBUtil.getProjectList();
	map.put("0000", "全部");
	return map;
    }

    public List getGhBookList() {
	
	Map session = ActionContext.getContext().getSession();
	if (session.get("blProjectName") != null) {
	    this.projecName = (String) session.get("blProjectName");
	}
	if (session.get("blPageSpan") != null) {
	    this.pageSpan = (Integer) session.get("blPageSpan");
	}

	GhTrackLogic lgc = new GhTrackLogic();
	List list = lgc.getGhDetailList(projecName, pageSpan, currenPageNo);

	return list;
    }

    public String execute() {
	return SUCCESS;
    }
}
