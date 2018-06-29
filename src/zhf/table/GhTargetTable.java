package zhf.table;

public class GhTargetTable {
    private String pjname;
    private int ghhcs;
    private int fact;
    private int difference;

    public GhTargetTable(String pjname, int ghhcs, int fact, int difference) {
	this.pjname = pjname;
	this.ghhcs = ghhcs;
	this.fact = fact;
	this.difference = difference;
    }

    public String getPjname() {
	return this.pjname;
    }

    public int getGhhcs() {
	return this.ghhcs;
    }

    public int getFact() {
	return this.fact;
    }

    public int getDifference() {
	return this.difference;
    }

}
