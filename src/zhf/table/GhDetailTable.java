package zhf.table;

public class GhDetailTable {

    private int No;
    private String Project;
    private String Name;
    private String Sex;
    private String Education;
    private String Graduated_School;
    private String Major;
    private String Language;
    private String Book_Status;
    private String Offer_Status;
    private String Offer_Waiting_Reason;
    private String Intern_Flag;
    private String Sector_SL;
    private String Reporting_Manager;
    private String Intern_OBD;
    private String Offer_OBD_Plan;
    private String Offer_OBD_Actual;
    private String Comments;

    public GhDetailTable(int No, String Project, String Name, String Sex, String Education, String Graduated_School,
	    String Major, String Language, String Book_Status, String Offer_Status, String Offer_Waiting_Reason,
	    String Intern_Flag, String Sector_SL, String Reporting_Manager, String Intern_OBD, String Offer_OBD_Plan,
	    String Offer_OBD_Actual, String Comments) {

	this.No = No;
	this.Project = Project;
	this.Name = Name;
	this.Sex = Sex;
	this.Education = Education;
	this.Graduated_School = Graduated_School;
	this.Major = Major;
	this.Language = Language;
	this.Book_Status = Book_Status;
	this.Offer_Status = Offer_Status;
	this.Offer_Waiting_Reason = Offer_Waiting_Reason;
	this.Intern_Flag = Intern_Flag;
	this.Sector_SL = Sector_SL;
	this.Reporting_Manager = Reporting_Manager;
	this.Intern_OBD = Intern_OBD;
	this.Offer_OBD_Plan = Offer_OBD_Plan;
	this.Offer_OBD_Actual = Offer_OBD_Actual;
	this.Comments = Comments;
    }

    public int getNo() {
	return No;
    }

    public String getProject() {
	return Project;
    }

    public String getName() {
	return Name;
    }

    public String getSex() {
	return Sex;
    }

    public String getEducation() {
	return Education;
    }

    public String getGraduated_School() {
	return Graduated_School;
    }

    public String getMajor() {
	return Major;
    }

    public String getLanguage() {
	return Language;
    }

    public String getBook_Status() {
	return Book_Status;
    }

    public String getOffer_Status() {
	return Offer_Status;
    }

    public String getOffer_Waiting_Reason() {
	return Offer_Waiting_Reason;
    }

    public String getIntern_Flag() {
	return Intern_Flag;
    }

    public String getSector_SL() {
	return Sector_SL;
    }

    public String getReporting_Manager() {
	return Reporting_Manager;
    }

    public String getIntern_OBD() {
	return Intern_OBD;
    }

    public String getOffer_OBD_Plan() {
	return Offer_OBD_Plan;
    }

    public String getOffer_OBD_Actual() {
	return Offer_OBD_Actual;
    }

    public String getComments() {
	return Comments;
    }

}
