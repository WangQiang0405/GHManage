package zhf.logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import zhf.common.SqlText;
import zhf.table.GhBookListTable;
import zhf.table.GhTargetTable;
import zhf.util.DBUtil;

//import zhf.SqlText;

public class GhPlanFactLogic {

    // 获取各种list数据全部信息
    public List getTargetGhList(String pjname, int ps, int cp, String actionName) {
	List list = new ArrayList();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = DBUtil.getConnection();
	    stmt = conn.createStatement();
	    String sql = pjname.equals("0000") ? "" : " where pjname = '" + pjname + "'";

	    if (actionName.equals("GhTargetAction")) {
		String sql1 = SqlText.SQL3_GHTARGETALL + sql;
		rs = stmt.executeQuery(sql1);
		while (rs.next()) {
		    String pjname1 = rs.getString("pjname");
		    int ghhcs = rs.getInt(2);
		    GhTargetTable gt = new GhTargetTable(pjname1, ghhcs);
		    list.add(gt);
		}
	    } else if (actionName.equals("GhTrackAction")) {
		int recNum = 0;
		String sql1 = SqlText.SQL4_GHBOOKLISTALL + sql;
		rs = stmt.executeQuery(sql1);
		if (cp != 1) {
		    rs.absolute((cp - 1) * ps);
		}
		while (recNum < ps && rs.next()) {
		    int wsID = rs.getInt("wsid");
		    String ghName = rs.getString("name");
		    String chuLiZt = rs.getString("procstatus");
		    String offerZt = rs.getString("offerstatus");
		    String yudingPj = rs.getString("pjname");
		    String ghorIntern = rs.getString("ghinflag");
		    String reportMgr = rs.getString("pem");
		    String onboardRqinOffer = rs.getDate("offeronbdate").toString();

		    recNum++;

		    GhBookListTable gl = new GhBookListTable(wsID, ghName, chuLiZt, offerZt, yudingPj, ghorIntern,
			    reportMgr, onboardRqinOffer);
		    list.add(gl);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    // 释放资源
	    DBUtil.closeResultSet(rs);
	    DBUtil.closeStatement(stmt);
	    DBUtil.closeConnection(conn);
	}

	return list;

    }

    public List<GhTargetTable> getUserList() {
	List<GhTargetTable> list = new ArrayList<GhTargetTable>();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = DBUtil.getConnection();
	    stmt = conn.createStatement();

	    String sql1 = SqlText.SQL3_GHTARGETALL;
	    rs = stmt.executeQuery(sql1);

	    while (rs.next()) {

		String pjname1 = rs.getString("pjname");
		int ghhcs = rs.getInt(2);
		GhTargetTable gt = new GhTargetTable(pjname1, ghhcs);
		list.add(gt);

	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    // 释放资源
	    DBUtil.closeResultSet(rs);
	    DBUtil.closeStatement(stmt);
	    DBUtil.closeConnection(conn);
	}

	return list;

    }

    public int getGhactualcsRecs(String pjname) {
	int totalRecs = 0;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = DBUtil.getConnection();
	    stmt = conn.createStatement();

	    String sqlWhere = " and pjname = '" + pjname + "'";
	    String sql = SqlText.SQL_PLANFACT_GH_DETAIL_S + sqlWhere;
	    rs = stmt.executeQuery(sql);

	    if (rs.next()) {
		totalRecs = rs.getInt(1);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    // 释放资源
	    DBUtil.closeResultSet(rs);
	    DBUtil.closeStatement(stmt);
	    DBUtil.closeConnection(conn);
	}

	return totalRecs;

    }

    public int getGhsexmaleCounts() {
	int totalRecs = 0;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = DBUtil.getConnection();
	    stmt = conn.createStatement();

	    String sql = SqlText.SQL_PLANFACT_MALECOUNT_S;
	    rs = stmt.executeQuery(sql);

	    if (rs.next()) {
		totalRecs = rs.getInt(1);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    // 释放资源
	    DBUtil.closeResultSet(rs);
	    DBUtil.closeStatement(stmt);
	    DBUtil.closeConnection(conn);
	}

	return totalRecs;
    }

    public int getGhsexfemaleCounts() {
	int totalRecs = 0;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = DBUtil.getConnection();
	    stmt = conn.createStatement();

	    String sql = SqlText.SQL_PLANFACT_FEMALECOUNT_S;
	    rs = stmt.executeQuery(sql);

	    if (rs.next()) {
		totalRecs = rs.getInt(1);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    // 释放资源
	    DBUtil.closeResultSet(rs);
	    DBUtil.closeStatement(stmt);
	    DBUtil.closeConnection(conn);
	}

	return totalRecs;
    }
}
