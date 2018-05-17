package zhf.action;

import com.opensymphony.xwork2.ActionSupport;

public class GhImportAction extends ActionSupport {

	private String importPath = "";

	public void setImportPath(String importPath) {
		this.importPath = importPath;
	}

	public String getImportPath() {
		return this.importPath;
	}

	public String execute() {
		return SUCCESS;
	}
}
