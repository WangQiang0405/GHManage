package zhf.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;

import zhf.logic.GhLoginLogic;

@Validation()
public class GhLoginAction extends ActionSupport {
    private String uid = "";
    private String pwd = "";
    private String yanzhengma;
    private String result = "";

    @RequiredStringValidator(message = "${getText(\"validate.uidempty\")}")
    public void setUid(String uid) {
	this.uid = uid;
    }

    public String getUid() {
	return this.uid;
    }

    @RequiredStringValidator(message = "${getText(\"validate.pwdempty\")}")
    public void setPwd(String pwd) {
	this.pwd = pwd;
    }

    public String getPwd() {
	return this.pwd;
    }

    public void setYanzhengma(String yanzhengma) {
	this.yanzhengma = yanzhengma;
    }

    public String getYanzhengma() {
	return this.yanzhengma;
    }

    public String getResult() {
	String temp = this.result;
	this.result = "";
	return temp;
    }

    public String execute() {

	// ˆÌÐÐ½Y¹û
	String strReturn = SUCCESS;

	String yanzhengma = (String) ActionContext.getContext().getSession().get("yanzhengma");
	if (yanzhengma != null) {
	    if (!yanzhengma.equals(this.yanzhengma.toLowerCase())) {
		this.result = this.getText("login.yanzhengmaerror");
	    } else {
		GhLoginLogic lgc = new GhLoginLogic();
		String[] impinfo = lgc.login(uid, pwd);
		Map session = ActionContext.getContext().getSession();
		if (Integer.parseInt(impinfo[0]) == 1) {
		    session.put("right", "manage");
		    session.put("user", impinfo[1]);
		    strReturn = "manage";
		} else if (Integer.parseInt(impinfo[0]) == 2) {
		    session.put("right", "personal");
		    session.put("user", impinfo[1]);
		    strReturn = "personal";
		} else {
		    this.result = this.getText("login.wronguid");
		}
	    }
	}
	return strReturn;
    }
}
