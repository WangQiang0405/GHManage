package zhf.logic;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import zhf.common.SqlText;
import zhf.table.GhSearchTable;
import zhf.util.DBUtil;

public class GhSearchlogic {
    // 获取Searchlist数据信息
    public List getSearchGhList(String wherest, int ps, int cp) {
	ArrayList list = new ArrayList();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = DBUtil.getConnection();
	    stmt = conn.createStatement();

	    int recNum = 0;
	    String sql1 = SqlText.SQL_GH_DETAIL_S + wherest;
	    rs = stmt.executeQuery(sql1);
	    if (cp != 1) {
		rs.absolute((cp - 1) * ps);
	    }
	    while (recNum < ps && rs.next()) {
		BigDecimal id = rs.getBigDecimal(1);
		String pjname = rs.getString("pjname");
		String name = rs.getString("name");
		String sex = rs.getString("sex");
		String education = rs.getString("education");
		String school = rs.getString("school");
		String major = rs.getString("major");
		String language = rs.getString("language");
		String offerStatus = rs.getString("offerStatus");
		GhSearchTable gt = new GhSearchTable(id, pjname, name, sex, education, school, major, language,
			offerStatus);
		list.add(gt);

		recNum++;
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

    // 查询ghdetail里面符合条件的记录数
    public int getSearchGhListRecs(String wherest) {
	int totalRecs = 0;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = DBUtil.getConnection();
	    stmt = conn.createStatement();
	    String sql1 = SqlText.SQL_GH_DETAIL_S_CNT + wherest;
	    rs = stmt.executeQuery(sql1);
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
