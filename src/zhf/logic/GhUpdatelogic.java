package zhf.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import zhf.common.ExcelRead;
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
		String internOBD =  DateUtil.getDateFormat(
			rs.getDate("internOBD"),GhCommon.YYYYMMDD);
		String offerOBDPlan =  DateUtil.getDateFormat(
			rs.getDate("offerOBDPlan"),GhCommon.YYYYMMDD);
		String offerOBDActual =  DateUtil.getDateFormat(
			rs.getDate("offerOBDActual"),GhCommon.YYYYMMDD);

		GhUpdateTable gt = new GhUpdateTable(pjname, 
			offerStatus, 
			offerWaitingReason, 
			internFlag, 
			internOBD, 
			offerOBDPlan, 
			offerOBDActual);
		
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

    public String importDetail(String filePath) throws SQLException {

	String result = GhCommon.SUCCESS;
	int intRusult = 0;
	//DecimalFormat df = new DecimalFormat(GhCommon.EIGHTZERO);

	Connection conn = null;
	PreparedStatement pstmtU = null;
	PreparedStatement pstmtI = null;
	PreparedStatement pstmtD = null;
	ResultSet rs = null;

	// countno�����ݸ�����SQL���
	String strSqlCountnoU = SqlText.SQL_COUNT_NO_U;
	// ghdetail�����ݵ�½��SQL���
	String strSqlGhdetailI = SqlText.SQL_GH_DETAIL_I;
	// ghdetail������ɾ����SQL���
	String strSqlGhdetailD = SqlText.SQL_GH_DETAIL_D;

	try {
	    
	    /** ghdetail������ɾ�� **/
	    // ��ȡDB����
	    conn = DBUtil.getConnection();
	    // ɾ��ghdetail���е�ȫ������
	    pstmtD = conn.prepareStatement(strSqlGhdetailD);
	    intRusult = pstmtD.executeUpdate();
	    
	    /** ��Excel�ж������ݺ󣬲��뵽ghdetail���� **/
	    // ��Excel�ж�������
	    pstmtI = conn.prepareStatement(strSqlGhdetailI);
	    ExcelRead ex = new ExcelRead();
	    List<String> lstExcelData = ex.exportListFromExcel(filePath, "GH Details");
	    // ����հ���
	    ex.listBlankLineClear(lstExcelData);
	    // ��Excel����ѭ����½��ghdetail����
	    for (int i = 0; i < lstExcelData.size(); i++) {
		String strLineData = (String) lstExcelData.get(i);
		String[] strLineDataArray = strLineData.split(GhCommon.ALARM);

		/*// ȷ��ghdetail���������Ƿ��Ѿ�����
		boolean blnExist = chkExistInGhdetail(strLineDateArray[3]);
		// �Ѿ�����ʱ����������ѭ��ֱ��ִ���´�ѭ��
		if (blnExist) {
		    continue;
		}*/

		// Id
		pstmtI.setInt(1, Integer.parseInt(StringUtil.getValueOfArray(strLineDataArray,0)));
		// ��Ŀ
		pstmtI.setString(2, StringUtil.getValueOfArray(strLineDataArray,1));
		// ����
		pstmtI.setString(3, StringUtil.getValueOfArray(strLineDataArray,2));
		// �Ա�
		pstmtI.setString(4, StringUtil.getValueOfArray(strLineDataArray,3));
		// �����̶�
		pstmtI.setString(5, StringUtil.getValueOfArray(strLineDataArray,4));
		// ��ҵԺУ
		pstmtI.setString(6, StringUtil.getValueOfArray(strLineDataArray,5));
		// רҵ
		pstmtI.setString(7, StringUtil.getValueOfArray(strLineDataArray,6));
		// ����
		pstmtI.setString(8, StringUtil.getValueOfArray(strLineDataArray,7));
		// ����״̬
		pstmtI.setString(9, StringUtil.getValueOfArray(strLineDataArray,8));
		// Offer״̬
		pstmtI.setString(10, StringUtil.getValueOfArray(strLineDataArray,9));
		// Offer�ȴ�����
		pstmtI.setString(11, StringUtil.getValueOfArray(strLineDataArray,10));
		// ʵϰ
		pstmtI.setString(12, StringUtil.getValueOfArray(strLineDataArray,11));
		// ����
		pstmtI.setString(13, StringUtil.getValueOfArray(strLineDataArray,12));
		// ��Ŀ����
		pstmtI.setString(14, StringUtil.getValueOfArray(strLineDataArray,13));
		// ʵϰ��
		pstmtI.setDate(15, 
			DateUtil.getDateFormat(StringUtil.getValueOfArray(strLineDataArray,14), GhCommon.YYYYMMDD));
		// Offer�ƻ���
		pstmtI.setDate(16, 
			DateUtil.getDateFormat(StringUtil.getValueOfArray(strLineDataArray,15), GhCommon.YYYYMMDD));
		// Offerʵ����
		pstmtI.setDate(17, 
			DateUtil.getDateFormat(StringUtil.getValueOfArray(strLineDataArray,16), GhCommon.YYYYMMDD));
		// ��ע
		pstmtI.setString(18, StringUtil.getValueOfArray(strLineDataArray,17));
		// ghdetail�����ݵ�½
		intRusult = pstmtI.executeUpdate();
		if (intRusult < 0) {
		    result = GhCommon.FAIL;
		    return result;
		}
	    }

	    /** �ɷ������**/
	    // �ɷ������
	    pstmtU = conn.prepareStatement(strSqlCountnoU);
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
	    DBUtil.closeResultSet(rs);
	    DBUtil.closeStatement(pstmtU);
	    DBUtil.closeStatement(pstmtI);
	    DBUtil.closeConnection(conn);
	}

	// ���ؽ��
	return result;
    }
    
}
