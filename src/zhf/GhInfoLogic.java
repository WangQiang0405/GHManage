package zhf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GhInfoLogic {

    // 获取各种list数据全部信息
    public List getTargetGhList(String sqlWhere,int ps, int cp) {

	List list = new ArrayList();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    // 获取DB连接
	    conn = DButil.getConnection();
	    stmt = conn.createStatement();

	    int recNum = 0;
	    String sql = "";
	    if (!"".equals(sqlWhere)) {
		sql = SqlText.SQL_INFO_GH_DETAIL_S + " where " + sqlWhere;
	    } else {
		sql = SqlText.SQL_INFO_GH_DETAIL_S;
	    }
	    rs = stmt.executeQuery(sql);
	    if (cp != 1) {
		rs.absolute((cp - 1) * ps);
	    }
	    while (recNum < ps && rs.next()) {
		String wsID = rs.getString("wsid");
		String ghName = rs.getString("anme");
		String xb = rs.getString("xb");
		String byxx = rs.getString("byxx");
		String zhuanye = rs.getString("zhuanye");
		String bysj = rs.getDate("bysj").toString();
		String comments = rs.getString("comments");

		recNum++;

		GhInfoListTable gl = new GhInfoListTable(wsID, ghName, xb, byxx, zhuanye, bysj, comments);
		list.add(gl);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    // 释放资源
	    DButil.closeResultSet(rs);
	    DButil.closeStatement(stmt);
	    DButil.closeConnection(conn);
	}

	return list;

    }

    // 查询GHDetaillist里面符合条件的记录数
    public int getTotalRecs(String sqlWhere) {
	int totalRecs = 0;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	try {
	    // 获取DB连接
	    conn = DButil.getConnection();
	    stmt = conn.createStatement();
	    String sql = "";
	    if (!"".equals(sqlWhere)) {
		sql = SqlText.SQL_INFO_TOTAL_GH_DETAIL_S + " where " + sqlWhere;
	    } else {
		sql = SqlText.SQL_INFO_TOTAL_GH_DETAIL_S;
	    }
	    rs = stmt.executeQuery(sql);
	    if (rs.next()) {
		totalRecs = rs.getInt(1);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    // 释放资源
	    DButil.closeResultSet(rs);
	    DButil.closeStatement(stmt);
	    DButil.closeConnection(conn);
	}
	return totalRecs;
    }

}
