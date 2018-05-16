package zhf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

public class GhImportLogic {

    public String importDetail(String filePath) throws SQLException {

	String result = GhCommon.SUCCESS;
	int intRusult = 0;
	DecimalFormat df = new DecimalFormat(GhCommon.SIXZERO);

	Connection conn = null;
	PreparedStatement pstmtU = null;
	PreparedStatement pstmtI = null;
	ResultSet rs = null;

	// countno�����ݸ�����SQL���
	String strSqlCountnoU = SqlText.SQL_COUNT_NO_U;
	// ghdetail�����ݵ�½��SQL���
	String strSqlGhdetailI = SqlText.SQL_IMPORT_GH_DETAIL_I;

	try {
	    // �ɷ�ֵȡ��
	    String strCountNo = getCountNo();
	    // �ɷ�ֵ��ǰ4λ
	    String strCountNoPre = strCountNo.substring(0, 4);
	    // �ɷ�ֵ�ĺ�6λ
	    String strCountNoSuf = strCountNo.substring(4);
	    int intCountNoSuf = Integer.parseInt(strCountNoSuf);
	    // �ɷ��ü�����
	    int intCountNo = intCountNoSuf;

	    // ��ȡDB����
	    conn = DButil.getConnection();
	    // ��Excel�ж�������
	    pstmtI = conn.prepareStatement(strSqlGhdetailI);
	    ExcelRead ex = new ExcelRead();
	    List<String> lstExcelData = ex.exportListFromExcel(filePath, "GH Details");
	    // ����հ���
	    ex.listBlankLineClear(lstExcelData);
	    // ��Excel����ѭ����½��ghdetail����
	    for (int i = 0; i < lstExcelData.size(); i++) {
		String strLineData = (String) lstExcelData.get(i);
		String[] strLineDateArray = strLineData.split(GhCommon.ALARM);

		// ȷ��ghdetail���������Ƿ��Ѿ�����
		boolean blnExist = chkExistInGhdetail(strLineDateArray[3]);
		// �Ѿ�����ʱ����������ѭ��ֱ��ִ���´�ѭ��
		if (blnExist) {
		    continue;
		}

		// ����ID
		if ("".equals(strLineDateArray[2])) {
		    strCountNoSuf = df.format(intCountNo);
		    intCountNo++;// ����
		    strCountNo = strCountNoPre + strCountNoSuf;
		    pstmtI.setString(1, strCountNo);
		} else {
		    pstmtI.setString(1, strLineDateArray[2]);
		}
		// ����
		pstmtI.setString(2, strLineDateArray[3]);
		// �Ա�
		pstmtI.setString(3, GhCommon.BLANK);
		// ��ҵԺУ
		pstmtI.setString(4, GhCommon.BLANK);
		// רҵ
		pstmtI.setString(5, GhCommon.BLANK);
		// ��ҵʱ��
		pstmtI.setDate(6, DateUtil.getDateFormat(strLineDateArray[9], GhCommon.YYYYMMDD));
		// ��ע
		pstmtI.setString(7, strLineDateArray[13]);
		// ghdetail�����ݵ�½
		intRusult = pstmtI.executeUpdate();
		if (intRusult < 0) {
		    result = GhCommon.FAIL;
		    return result;
		}
	    }

	    // �ɷ������
	    pstmtU = conn.prepareStatement(strSqlCountnoU);
	    if (intCountNo > intCountNoSuf) {
		strCountNoSuf = df.format(intCountNo);
		strCountNo = strCountNoPre + strCountNoSuf;
		pstmtU.setString(1, strCountNo);
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
	    DButil.closeResultSet(rs);
	    DButil.closeStatement(pstmtU);
	    DButil.closeStatement(pstmtI);
	    DButil.closeConnection(conn);
	}

	// ���ؽ��
	return result;
    }

    // �ɷ�ֵȡ��
    private String getCountNo() throws SQLException {

	String countNo = GhCommon.BLANK;
	Connection conn = null;
	PreparedStatement pstmtS = null;
	ResultSet rs = null;

	// countno������ȡ����SQL���
	String strSqlCountnoS = SqlText.SQL_COUNT_NO_S;

	// ��ȡDB����
	conn = DButil.getConnection();

	// �ɷ�������ȡ��
	pstmtS = conn.prepareStatement(strSqlCountnoS);
	rs = pstmtS.executeQuery();
	while (rs.next()) {
	    countNo = rs.getString("no");
	}
	DButil.closeResultSet(rs);
	DButil.closeStatement(pstmtS);
	DButil.closeConnection(conn);

	// ���ؽ��
	return countNo;

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
	conn = DButil.getConnection();

	// �ɷ�������ȡ��
	pstmtS = conn.prepareStatement(strSqlGhdetailS);
	pstmtS.setString(1, name);
	rs = pstmtS.executeQuery();
	while (rs.next()) {
	    blnIsExist = true;
	}
	DButil.closeResultSet(rs);
	DButil.closeStatement(pstmtS);
	DButil.closeConnection(conn);

	// ���ؽ��
	return blnIsExist;

    }

}
