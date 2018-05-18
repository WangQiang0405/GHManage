package zhf.action;

import java.io.File;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;

import zhf.common.GhCommon;
import zhf.logic.GhImportLogic;

@Validation()
public class GhImportExeAction extends ActionSupport {

    private String importPath = "";
    private String result = "";

    public void setImportPath(String importPath) {
	this.importPath = importPath;
    }

    public String getImportPath() {
	return this.importPath;
    }

    public String getResult() {
	String temp = this.result;
	this.result = "";
	return temp;
    }

    public String init() {
	return GhCommon.INIT;
    }

    public String execute() {

	String exeResult = GhCommon.BLANK;

	/** 输入确认实施 **/
	// 文件未指定时，错误
	if ("".equals(this.importPath)) {
	    this.result = this.getText("validate.importpathempty");
	}
	// 有错误时
	if (!"".equals(this.result)) {
	    exeResult = GhCommon.FAIL;
	    return exeResult;
	}
	
	File file = new File(importPath);
	String fileName = file.getName();
	String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
	if (!file.isFile()) {
	    this.result = this.getText("import.fileerror");
	} else if (!"xlsx".equals(prefix) && !"xls".equals(prefix)) {
	    this.result = this.getText("import.filetypeerror");
	}
	// 有错误时
	if (!"".equals(this.result)) {
	    exeResult = GhCommon.FAIL;
	    return exeResult;
	}

	try {
	    GhImportLogic logic = new GhImportLogic();
	    exeResult = logic.importDetail(importPath);
	    this.result = exeResult;
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return exeResult;
    }
}
