package zhf.table;

import java.math.BigDecimal;

public class GhSearchTable {
    private BigDecimal id;
    private String pjname;
    private String name;
    private String sex;
    private String education;
    private String school;
    private String major;
    private String language;
    private String offerStatus;

    public GhSearchTable(BigDecimal id, String pjname, String name, String sex, String education, String school,
	    String major, String language, String offerStatus) {
	this.id = id;
	this.pjname = pjname;
	this.name = name;
	this.sex = sex;
	this.education = education;
	this.school = school;
	this.major = major;
	this.language = language;
	this.offerStatus = offerStatus;
    }

    public BigDecimal getId() {
	return this.id;
    }

    public String getPjname() {
	return this.pjname;
    }

    public String getName() {
	return this.name;
    }

    public String getSex() {
	return this.sex;
    }

    public String getEducation() {
	return this.education;
    }

    public String getSchool() {
	return this.school;
    }

    public String getMajor() {
	return this.major;
    }

    public String getLanguage() {
	return this.language;
    }

    public String getOfferStatus() {
	return this.offerStatus;
    }
}
