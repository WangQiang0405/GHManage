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

    public GhSearchTable() {
        super();
    } 
    
    public BigDecimal getId() {
	return this.id;
    }
    
    public void setId(BigDecimal id) {
	this.id = id;
    }

    public String getPjname() {
	return this.pjname;
    }
    
    public void setPjname(String pjname) {
	this.pjname = pjname;
    }

    public String getName() {
	return this.name;
    }
    
    public void setName(String name) {
	this.name = name;
    }

    public String getSex() {
	return this.sex;
    }
    
    public void setSex(String sex) {
	this.sex = sex;
    }

    public String getEducation() {
	return this.education;
    }

    public void setEducation(String education) {
	this.education = education;
    }
    
    public String getSchool() {
	return this.school;
    }
    
    public void setSchool(String school) {
	this.school = school;
    }

    public String getMajor() {
	return this.major;
    }
    
    public void setMajor(String major) {
	this.major = major;
    }

    public String getLanguage() {
	return this.language;
    }
    
    public void setLanguage(String language) {
	this.language = language;
    }

    public String getOfferStatus() {
	return this.offerStatus;
    }
    
    public void setOfferStatus(String offerStatus) {
	this.offerStatus = offerStatus;
    }
    
}
