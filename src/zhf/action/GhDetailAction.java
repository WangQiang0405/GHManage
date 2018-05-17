package zhf.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import zhf.util.DBUtil;

public class GhDetailAction extends ActionSupport{
	private String name;
	public List getGhDetailList()
	{
		List list=DBUtil.getGhDetailList(name);
		System.out.println(name);
		return list;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String execute()
	{
		return SUCCESS;
	}
	
}
