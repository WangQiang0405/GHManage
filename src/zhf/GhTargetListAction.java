package zhf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.*;

import com.opensymphony.xwork2.ActionContext;

public class GhTargetListAction extends ActionSupport {

	private String ProjecName = "0000";

	public void setProjecName(String projecName) {
		this.ProjecName = projecName;
		ActionContext.getContext().getSession().put("projecName", projecName);

	}

	public String getProjecName() {
		Object obj = (ActionContext.getContext().getSession().get("projecName"));
		if (obj != null) {
			this.ProjecName = (String) obj;
		}
		return this.ProjecName;
	}

	public Map getProjectList() {
		Map map = DButil.getProjectList();
		map.put("0000", "È«²¿");
		return map;
	}

	public List getGhTargetList() {
		Map session = ActionContext.getContext().getSession();
		if (session.get("projecName") != null) {
			this.ProjecName = (String) session.get("projecName");
		}

		List list = DButil.getTargetGhList(ProjecName, 0, 0, "GhTargetListAction");
		return list;
	}

	public String execute() {
		return SUCCESS;
	}
}
