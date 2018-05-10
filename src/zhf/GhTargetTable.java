package zhf;

public class GhTargetTable {
	private String pjname;
	private int ghhcs;
	
	public GhTargetTable(String pjname, int ghhcs){
		this.pjname = pjname;
		this.ghhcs= ghhcs;
	}
	
	public String getPjname(){
		return this.pjname;
	}
	
	public int getGhhcs(){
		return this.ghhcs;
	}
	

}
