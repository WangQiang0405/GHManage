package zhf.logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import zhf.common.GhCommon;
import zhf.common.SqlText;
import zhf.table.GhBookListTable;
import zhf.util.DBUtil;
import zhf.util.DateUtil;
import zhf.util.StringUtil;

public class GhTrackLogic {

    // 获取GHDetaillist全部数据信息
    public List getGhDetailList(String pjname, int ps, int cp) {

	ArrayList list = new ArrayList();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = DBUtil.getConnection();
	    stmt = conn.createStatement();
	    String sqlWhere = pjname.equals("0000") ? "" : " where pjname = '" + pjname + "'";

	    String sql = SqlText.SQL_GH_DETAIL_S + sqlWhere;
	    rs = stmt.executeQuery(sql);
	    if (cp != 1) {
		rs.absolute((cp - 1) * ps);
	    }

	    int recNum = 0;
	    GhBookListTable gl = null;
	    while (recNum < ps && rs.next()) {
		int id = rs.getInt("id");
		String projectName = StringUtil.nullToEmpty(rs.getString("pjname"));
		String name = StringUtil.nullToEmpty(rs.getString("name"));
		String sex = StringUtil.nullToEmpty(rs.getString("sex"));
		String offerStatus = StringUtil.nullToEmpty(rs.getString("offerStatus"));
		String internFlag = StringUtil.nullToEmpty(rs.getString("internFlag"));
		internFlag = StringUtil.internFlagConv(internFlag);
		String reportManager = StringUtil.nullToEmpty(rs.getString("reportManager"));
		String offerOBDPlan =  DateUtil.getDateFormat(
			rs.getDate("offerOBDPlan"),GhCommon.YYYYMMDD);

		recNum++;
		gl = new GhBookListTable(id, projectName, name, sex, offerStatus, internFlag, reportManager,
			offerOBDPlan);
		list.add(gl);
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

    // 查询GHDetaillist里面符合条件的记录数
    public int getTotalRecs(String pjname) {
	int totalRecs = 0;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	try {
	    conn = DBUtil.getConnection();
	    stmt = conn.createStatement();
	    String sqlWhere = pjname.equals("0000") ? "" : " where pjname = '" + pjname + "'";
	    String sql = SqlText.SQL_GH_DETAIL_S_CNT + sqlWhere;
	    rs = stmt.executeQuery(sql);
	    if (rs.next()) {
		totalRecs = rs.getInt(1);
	    }
	} catch (SQLException e) {
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
