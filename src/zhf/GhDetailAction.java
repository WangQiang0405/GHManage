package zhf;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class GhDetailAction extends ActionSupport{
	private String name;
	public List getGhDetailList()
	{
		List list=DButil.getGhDetailList(name);
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
