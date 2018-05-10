package zhf;

public class GhInfoListTable {

    private String wangShenId;
    private String studentName;
    private String xb;
    private String byxx;
    private String zhuanye;
    private String bysj;
    private String comments;

    public GhInfoListTable(String wangShenId, String studentName, String xb, String byxx, String zhuanye, String bysj,
	    String comments) {

	this.wangShenId = wangShenId;
	this.studentName = studentName;
	this.xb = xb;
	this.byxx = byxx;
	this.zhuanye = zhuanye;
	this.bysj = bysj;
	this.comments = comments;

    }

    public String getWangShenId() {
	return this.wangShenId;
    }

    public String getStudentName() {
	return this.studentName;
    }

    public String getXb() {
	return this.xb;
    }

    public String getByxx() {
	return this.byxx;
    }

    public String getZhuanye() {
	return this.zhuanye;
    }

    public String getBysj() {
	return this.bysj;
    }

    public String getComments() {
	return this.comments;
    }

}
