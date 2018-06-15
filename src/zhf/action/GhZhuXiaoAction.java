package zhf.action;

import com.opensymphony.xwork2.ActionContext;

public class GhZhuXiaoAction {

    public String execute() {
	ActionContext.getContext().getSession().put("right", "no");
	return "success";
    }
}
