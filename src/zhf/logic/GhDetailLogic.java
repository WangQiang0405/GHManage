package zhf.logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import zhf.common.GhCommon;
import zhf.common.SqlText;
import zhf.table.GhDetailTable;
import zhf.util.DBUtil;
import zhf.util.DateUtil;
import zhf.util.StringUtil;

public class GhDetailLogic {

    // 获取ghdetail指定id的详细数据信息
    public static List getGhDetailList(int id) {
	ArrayList list = new ArrayList();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = DBUtil.getConnection();
	    stmt = conn.createStatement();
	    String sqlWhere = " where id = " + id;
	    String sql = SqlText.SQL_GH_DETAIL_S + sqlWhere;
	    rs = stmt.executeQuery(sql);
	    while (rs.next()) {
		int no = rs.getInt("id");
		String project = StringUtil.nullToEmpty(rs.getString("pjname"));
		String name = StringUtil.nullToEmpty(rs.getString("name"));
		String sex = StringUtil.nullToEmpty(rs.getString("sex"));
		String education = StringUtil.nullToEmpty(rs.getString("education"));
		String school = StringUtil.nullToEmpty(rs.getString("school"));
		String major = StringUtil.nullToEmpty(rs.getString("major"));
		String language = StringUtil.nullToEmpty(rs.getString("language"));
		String bookStatus = StringUtil.nullToEmpty(rs.getString("bookStatus"));
		String offerStatus = StringUtil.nullToEmpty(rs.getString("offerStatus"));
		String offerWaitingReason = StringUtil.nullToEmpty(rs.getString("offerWReason"));
		String internFlag = StringUtil.nullToEmpty(rs.getString("internFlag"));
		String sectorOrSL = StringUtil.nullToEmpty(rs.getString("sectorOrSL"));
		String reportManager = StringUtil.nullToEmpty(rs.getString("reportManager"));
		String internOBD = DateUtil.getDateFormat(rs.getDate("internOBD"),GhCommon.YYYYMMDD);
		String offerOBDPlan = DateUtil.getDateFormat(rs.getDate("offerOBDPlan"),GhCommon.YYYYMMDD);
		String offerOBDActual = DateUtil.getDateFormat(rs.getDate("offerOBDActual"),GhCommon.YYYYMMDD);
		String comments = StringUtil.nullToEmpty(rs.getString("comments"));
		GhDetailTable gdt = new GhDetailTable(
			no, 
			project, 
			name, 
			sex, 
			education, 
			school, 
			major,
			language, 
			bookStatus, 
			offerStatus, 
			offerWaitingReason, 
			internFlag, 
			sectorOrSL, 
			reportManager,
			internOBD, 
			offerOBDPlan, 
			offerOBDActual, 
			comments);

		list.add(gdt);
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

}
