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
    // ��ȡGh������Ϣ
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
	    // �ͷ���Դ
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

	// ghdetail������������SQL���
	String strSqlGhdetailU = SqlText.SQL_GH_DETAIL_U;

	try {

	    /** ghdetail���������� **/
	    conn = DBUtil.getConnection();
	    pstmtU = conn.prepareStatement(strSqlGhdetailU);

	    // ��Ŀ
	    pstmtU.setString(1, pjname);
	    // Offer״̬
	    pstmtU.setString(2, StringUtil.offerStatusCdToValue(offerStatus));
	    // Offer�ȴ�����
	    pstmtU.setString(3, StringUtil.offerWaitingReasonCdToValue(offerWReason));
	    // ʵϰ
	    pstmtU.setString(4, StringUtil.internFlagCdToValue(internFlag));
	    // ʵϰ��
	    pstmtU.setDate(5, DateUtil.getDateFormat(internOBD, GhCommon.YYYYMMDD));
	    // Offer�ƻ���
	    pstmtU.setDate(6, DateUtil.getDateFormat(offerOBDPlan, GhCommon.YYYYMMDD));
	    // Offerʵ����
	    pstmtU.setDate(7, DateUtil.getDateFormat(offerOBDActual, GhCommon.YYYYMMDD));
	    // id
	    pstmtU.setBigDecimal(8, id);

	    // ghdetail����������
	    intRusult = pstmtU.executeUpdate();
	    if (intRusult < 0) {
		result = GhCommon.FAIL;
		return result;
	    }

	    // �ύ
	    conn.commit();

	} catch (Exception e) {
	    // �ع�
	    conn.rollback();
	    e.printStackTrace();
	} finally {
	    // �ͷ���Դ
	    DBUtil.closeStatement(pstmtU);
	    DBUtil.closeConnection(conn);
	}

	// ���ؽ��
	return result;
    }

}
