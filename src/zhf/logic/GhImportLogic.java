package zhf.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import zhf.common.ExcelRead;
import zhf.common.GhCommon;
import zhf.common.SqlText;
import zhf.util.DBUtil;
import zhf.util.DateUtil;
import zhf.util.StringUtil;

public class GhImportLogic {

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
	    
	    /** ����Excel����Check **/
	    ExcelRead ex = new ExcelRead();
	    List<String> lstExcelData = ex.exportListFromExcel(filePath, "GH Details");
	    // ����հ���
	    ex.listBlankLineClear(lstExcelData);
	    if (lstExcelData == null || lstExcelData.size() == 0) {
		result = GhCommon.FAIL;
		return result;
	    }
	    /** ghdetail������ɾ�� **/
	    // ��ȡDB����
	    conn = DBUtil.getConnection();
	    // ɾ��ghdetail���е�ȫ������
	    pstmtD = conn.prepareStatement(strSqlGhdetailD);
	    intRusult = pstmtD.executeUpdate();
	    
	    /** �ɷ�ֵȡ��**/
	    // �ɷ�ֵȡ��
	    int intCountId = getCountNo();
	    // �ɷ��ü�����
	    int intCountNo = intCountId;
	    
	    /** ��Excel�ж������ݺ󣬲��뵽ghdetail���� **/
	    // ��Excel�ж�������
	    pstmtI = conn.prepareStatement(strSqlGhdetailI);
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
		if ("".equals(StringUtil.getValueOfArray(strLineDataArray,0))) {
		    pstmtI.setInt(1, intCountNo);
		    intCountNo++;// ����
		} else {
		    pstmtI.setInt(1, Integer.parseInt(StringUtil.getValueOfArray(strLineDataArray,0)));
		}
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
	    if (intCountNo > intCountId) {
		pstmtU.setInt(1, intCountNo);
		// countno�����ݸ���
		intRusult = pstmtU.executeUpdate();
		if (intRusult < 0) {
		    result = GhCommon.FAIL;
		    return result;
		}
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

    // �ɷ�ֵȡ��
    private int getCountNo() throws SQLException {

	int countId = 0;
	Connection conn = null;
	PreparedStatement pstmtS = null;
	ResultSet rs = null;

	// countno������ȡ����SQL���
	String strSqlCountnoS = SqlText.SQL_COUNT_NO_S;

	// ��ȡDB����
	conn = DBUtil.getConnection();

	// �ɷ�������ȡ��
	pstmtS = conn.prepareStatement(strSqlCountnoS);
	rs = pstmtS.executeQuery();
	while (rs.next()) {
	    countId = rs.getInt("id");
	}
	DBUtil.closeResultSet(rs);
	DBUtil.closeStatement(pstmtS);
	DBUtil.closeConnection(conn);

	// ���ؽ��
	return countId;

    }

    // ������������ȷ��ghdetail���������Ƿ����
    private boolean chkExistInGhdetail(String name) throws SQLException {

	boolean blnIsExist = false;
	Connection conn = null;
	PreparedStatement pstmtS = null;
	ResultSet rs = null;

	// ghdetail������ȡ����SQL���
	String strSqlGhdetailS = SqlText.SQL_IMPORT_GH_DETAIL_S;

	// ��ȡDB����
	conn = DBUtil.getConnection();

	// �ɷ�������ȡ��
	pstmtS = conn.prepareStatement(strSqlGhdetailS);
	pstmtS.setString(1, name);
	rs = pstmtS.executeQuery();
	while (rs.next()) {
	    blnIsExist = true;
	}
	DBUtil.closeResultSet(rs);
	DBUtil.closeStatement(pstmtS);
	DBUtil.closeConnection(conn);

	// ���ؽ��
	return blnIsExist;

    }

}
