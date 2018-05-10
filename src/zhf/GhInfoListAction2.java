package zhf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GhInfoListAction2 extends ActionSupport {
    private int pageSpan = 10;
    private int currenPageNo = 1;
    private int maxPageNo;
    private int totalMessage;

    public void setPageSpan(int pageSpan) {
	this.pageSpan = pageSpan;
	ActionContext.getContext().getSession().put("pageSpan", pageSpan);
	this.setCurrenPageNo(1);
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
	map.put(10, "每页10条");
	map.put(20, "每页20条");
	return map;
    }

    public int getMaxPageNo() {
	Map session = ActionContext.getContext().getSession();
	if (session.get("pageSpan") != null) {
	    this.pageSpan = (Integer) session.get("pageSpan");
	}
	totalMessage = this.getTotalMessage();
	return totalMessage % pageSpan == 0 ? totalMessage / pageSpan : (totalMessage / pageSpan + 1);
    }

    public int getTotalMessage() {
	Map session = ActionContext.getContext().getSession();
	GhInfoLogic infoLogic = new GhInfoLogic();
	return infoLogic.getTotalRecs("");
    }

    public void setCurrenPageNo(int currenPageNo) {
	if (currenPageNo <= this.getMaxPageNo() && currenPageNo >= 1) {
	    this.currenPageNo = currenPageNo;
	}
    }

    public int getCurrenPageNo() {
	return this.currenPageNo;
    }

//    public List getGhInfoList() {
//	
//	Map session = ActionContext.getContext().getSession();
//	if (session.get("pageSpan") != null) {
//	    this.pageSpan = (Integer) session.get("pageSpan");
//	}
//	GhInfoLogic infoLogic = new GhInfoLogic();
//	List list = infoLogic.getTargetGhList(pageSpan, currenPageNo);
//	
//	return list;
//    }

    public String execute() {
	return SUCCESS;
    }
}
