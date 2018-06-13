package zhf.logic;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import zhf.common.GhCommon;
import zhf.common.SqlText;
import zhf.table.GhUpdateTable;
import zhf.util.DBUtil;
import zhf.util.DateUtil;
import zhf.util.StringUtil;

public class GhUpdatelogic {
    // 获取Gh数据信息
    public List getGhInfoList(String where) {
	ArrayList list = new ArrayList();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = DBUtil.getConnection();
	    stmt = conn.createStatement();

	    int recNum = 0;
	    String sql1 = SqlText.SQL_GH_DETAIL_S + where;
	    rs = stmt.executeQuery(sql1);
	    while (rs.next()) {
		String pjname = StringUtil.nullToEmpty(rs.getString("pjname"));
		String offerStatus = StringUtil.nullToEmpty(rs.getString("offerStatus"));
		String offerWaitingReason = StringUtil.nullToEmpty(rs.getString("offerWReason"));
		String internFlag = StringUtil.nullToEmpty(rs.getString("internFlag"));
		internFlag = StringUtil.internFlagConv(internFlag);
		String internOBD = DateUtil.getDateFormat(rs.getDate("internOBD"), GhCommon.YYYYMMDD);
		String offerOBDPlan = DateUtil.getDateFormat(rs.getDate("offerOBDPlan"), GhCommon.YYYYMMDD);
		String offerOBDActual = DateUtil.getDateFormat(rs.getDate("offerOBDActual"), GhCommon.YYYYMMDD);

		GhUpdateTable gt = new GhUpdateTable(pjname, offerStatus, offerWaitingReason, internFlag, internOBD,
			offerOBDPlan, offerOBDActual);

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

    public String UpdateDetail(String pjname,
	    String offerStatus,
	    String offerWReason,
	    String internFlag,
	    String internOBD,
	    String offerOBDPlan,
	    String offerOBDActual,
	    BigDecimal id) throws SQLException {

	String result = GhCommon.SUCCESS;
	int intRusult = 0;

	Connection conn = null;
	PreparedStatement pstmtU = null;

	// ghdetail表数据修正用SQL语句
	String strSqlGhdetailU = SqlText.SQL_GH_DETAIL_U;

	try {

	    /** ghdetail表数据修正 **/
	    conn = DBUtil.getConnection();
	    pstmtU = conn.prepareStatement(strSqlGhdetailU);

	    // 项目
	    pstmtU.setString(1, pjname);
	    // Offer状态
	    pstmtU.setString(2, StringUtil.offerStatusCdToValue(offerStatus));
	    // Offer等待理由
	    pstmtU.setString(3, StringUtil.offerWaitingReasonCdToValue(offerWReason));
	    // 实习
	    pstmtU.setString(4, StringUtil.internFlagCdToValue(internFlag));
	    // 实习日
	    pstmtU.setDate(5, DateUtil.getDateFormat(internOBD, GhCommon.YYYYMMDD));
	    // Offer计划日
	    pstmtU.setDate(6, DateUtil.getDateFormat(offerOBDPlan, GhCommon.YYYYMMDD));
	    // Offer实际日
	    pstmtU.setDate(7, DateUtil.getDateFormat(offerOBDActual, GhCommon.YYYYMMDD));
	    // id
	    pstmtU.setBigDecimal(8, id);

	    // ghdetail表数据修正
	    intRusult = pstmtU.executeUpdate();
	    if (intRusult < 0) {
		result = GhCommon.FAIL;
		return result;
	    }

	    // 提交
	    conn.commit();

	} catch (Exception e) {
	    // 回滚
	    conn.rollback();
	    e.printStackTrace();
	} finally {
	    // 释放资源
	    DBUtil.closeStatement(pstmtU);
	    DBUtil.closeConnection(conn);
	}

	// 返回结果
	return result;
    }

}
