package zhf.action;

import com.opensymphony.xwork2.ActionSupport;

public class GhUpdateAction extends ActionSupport {
    private String id;

    public void setId(String id) {
	this.id = id;
    }

    public String getId() {
	return this.id;
    }

    public void setModel(String id) {
	this.id = id;
    }

    public String execute() {
	
	return SUCCESS;
	
    }
}
