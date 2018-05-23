package zhf.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import zhf.util.DBUtil;

public class GhBookListAction extends ActionSupport {
    
    private String eventid = "0";//0����ʼ����1��ҳ������Ŀ�ı䣻2������ҳ;3:ȷ��
    private int pageSpan = 5;
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
	map.put(5, "ÿҳ5��");
	map.put(10, "ÿҳ10��");
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
	return DBUtil.getTotalRecs(projecName);
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
	map.put("0000", "ȫ��");
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

	List list = DBUtil.getTargetGhList(projecName, pageSpan, currenPageNo, "GhBookListAction");

	return list;
    }

    public String execute() {
	return SUCCESS;
    }
}
