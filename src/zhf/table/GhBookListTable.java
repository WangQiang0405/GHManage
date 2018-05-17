package zhf.table;

public class GhBookListTable {
	
	private int wangShenId;
	private String studentName;
	private String chuLiSts;
	private String offerSts;
	private String bookByPj;
	private String ghOrInt;
	private String repMgr;
	private String offerOnboardRq;
	
		public GhBookListTable(int wangShenId,
				               String studentName,
				               String chuLiSts,
				               String offerSts,
				               String bookByPj,
				               String ghOrInt,
				               String repMgr,
				               String offerOnboardRq){
			
			this.wangShenId = wangShenId;
			this.studentName = studentName;
			this.chuLiSts = chuLiSts;
			this.offerSts = offerSts;
			this.bookByPj = bookByPj;
			this.ghOrInt = ghOrInt;
			this.repMgr = repMgr;
			this.offerOnboardRq = offerOnboardRq;
			
	}
	
	public int getWangShenId(){
		return this.wangShenId;
	}
	
	public String getStudentName(){
		return this.studentName;
	}

	public String getChuLiSts(){
		return this.chuLiSts;
	}
	
	public String getOfferSts(){
		return this.offerSts;
	}
	
	public String getBookByPj(){
		return this.bookByPj;
	}
	
	public String getGhOrInt(){
		return this.ghOrInt;
	}
	
	public String getRepMgr(){
		return this.repMgr;
	}
	
	public String getOfferOnboardRq(){
		return this.offerOnboardRq;
	}
}
