package zhf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.Validation;

import zhf.util.StringUtil;

@Validation()
public class GhInfoListExeAction extends ActionSupport {

    private String eventid = "0";//0：初始化；1：检索,表示件数；2：上下页;3:确定
    private String wsid = "";
    private String name = "";
    private String school = "";
    private String type = "";
    private String result = "";
    private List ghInfoList = new ArrayList();
    private int currenPageNo = 1;
    private int pageSpan = 1;
    private int maxPageNo = 0;
    private int totalCount = 0;

    public void setEventid(String eventid) {
	this.eventid = eventid;
    }

    public String getEventid() {
	return this.eventid;
    }
    
    public void setWsid(String wsid) {
	this.wsid = wsid;
    }

    public String getWsid() {
	return this.wsid;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return this.name;
    }

    public void setSchool(String school) {
	this.school = school;
    }

    public String getSchool() {
	return this.school;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getType() {
	return this.type;
    }

    public String getResult() {
	String temp = this.result;
	this.result = "";
	return temp;
    }

    public List getGhInfoList() {
	return this.ghInfoList;
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
	map.put(1, "每页1条");
	map.put(2, "每页2条");
	return map;
    }
    
    public int getMaxPageNo() {
	return this.maxPageNo;
    }
    
    public void setMaxPageNo(int maxPageNo) {
	this.maxPageNo = maxPageNo;
    }
    
    public int getTotalCount() {
	return this.totalCount;
    }
    
    public void setTotalCount(int totalCount) {
	this.totalCount = totalCount;
    }
    
    public String init() {
	return SUCCESS;
    }
    
    public String execute() {

	String exeResult = GhCommon.SUCCESS;
	
	/** 输入确认进行实施 START**/
	// 网申编号和网申编号以外的检索项目同时输入时，错误
	if (!"".equals(this.wsid) && (!"".equals(this.name) || !"".equals(this.school) || !"".equals(this.type))) {
	    exeResult = GhCommon.FAIL;
	    this.result = this.getText("search.errconflict");
	}

	// 网申编号
	if (!"".equals(this.wsid)) {
	    // 输入的位数超过10时，错误
	    if (StringUtil.isOverMaxLenhth(this.wsid, 10)) {
		exeResult = GhCommon.FAIL;
		this.result = this.getText("search.errmaxlenwsid");
		// 数字和字母以外的字符输入时，错误
	    } else if (!StringUtil.isNumAndChar(this.wsid)) {
		exeResult = GhCommon.FAIL;
		this.result = this.getText("search.errtype");
	    }
	}
	
	// 姓名
	if (!"".equals(this.name)) {
	    // 输入超过5个文字时，错误
	    if (StringUtil.isOverMaxLenhth(this.name, 5)) {
		exeResult = GhCommon.FAIL;
		this.result = this.getText("search.errmaxlenname");
	    }
	}
	
	// 毕业学校
	if (!"".equals(this.school)) {
	    // 输入超过15个文字时，错误
	    if (StringUtil.isOverMaxLenhth(this.school, 15)) {
		exeResult = GhCommon.FAIL;
		this.result = this.getText("search.errmaxlenschool");
	    }
	}
	
	// 专业
	if (!"".equals(this.type)) {
	    // 输入超过10个文字时，错误
	    if (StringUtil.isOverMaxLenhth(this.type, 10)) {
		exeResult = GhCommon.FAIL;
		this.result = this.getText("search.errmaxlentype");
	    }
	}
	
	//错误时，返回
	if (exeResult.equals(GhCommon.FAIL)) {
	    return exeResult;
	}
	
	/** 输入确认进行实施 END**/
	
	//SQL条件
	StringBuffer strWhere = new StringBuffer();
	// 网申编号
	if (!"".equals(this.wsid)) {
	    strWhere.append("wsid = '" + this.wsid + "'");
	}
	
	// 姓名
	if (!"".equals(this.name)) {
	    strWhere.append(" anme like '%" + this.name + "%'");
	}
	// 毕业学校
	if (!"".equals(this.school)) {
	    if (!"".equals(strWhere.toString())) {
		strWhere.append(" and byxx like '%" + this.school + "%'");
	    } else {
		strWhere.append(" byxx like '%" + this.school + "%'");
	    }
	}
	// 专业
	if (!"".equals(this.type)) {
	    if (!"".equals(strWhere.toString())) {
		strWhere.append(" and zhuanye like '%" + this.type + "%'");
	    } else {
		strWhere.append(" zhuanye like '%" + this.type + "%'");
	    }
	}
	
	Map session = ActionContext.getContext().getSession();
	if (session.get("pageSpan") != null) {
	    this.pageSpan = (Integer) session.get("pageSpan");
	}
	
	// ghdetail表数据取得
	GhInfoLogic infoLogic = new GhInfoLogic();
	this.ghInfoList = infoLogic.getTargetGhList(strWhere.toString(),this.pageSpan, this.currenPageNo);

	// ghdetail列表记录条数取得
	this.totalCount = infoLogic.getTotalRecs(strWhere.toString());
	ActionContext.getContext().getSession().put("totalCount", totalCount);
	
	// 最大页数
	this.maxPageNo
	    = this.totalCount % pageSpan == 0 ? this.totalCount / pageSpan : (this.totalCount / pageSpan + 1);
	ActionContext.getContext().getSession().put("maxPageNo", maxPageNo);
	
	
	return exeResult;
    }
}
