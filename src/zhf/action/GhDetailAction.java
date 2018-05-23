package zhf.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import zhf.logic.GhDetailLogic;

public class GhDetailAction extends ActionSupport {
    private int id;
    private String name;

    public List getGhDetailList() {
	
	GhDetailLogic lgc = new GhDetailLogic();
	List list = lgc.getGhDetailList(id);
	return list;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }
    
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String execute() {
	return SUCCESS;
    }

}
