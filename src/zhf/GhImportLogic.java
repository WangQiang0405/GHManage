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

	// countno表数据更新用SQL语句
	String strSqlCountnoU = SqlText.SQL_COUNT_NO_U;
	// ghdetail表数据登陆用SQL语句
	String strSqlGhdetailI = SqlText.SQL_IMPORT_GH_DETAIL_I;

	try {
	    // 采番值取得
	    String strCountNo = getCountNo();
	    // 采番值的前4位
	    String strCountNoPre = strCountNo.substring(0, 4);
	    // 采番值的后6位
	    String strCountNoSuf = strCountNo.substring(4);
	    int intCountNoSuf = Integer.parseInt(strCountNoSuf);
	    // 采番用计数器
	    int intCountNo = intCountNoSuf;

	    // 获取DB连接
	    conn = DButil.getConnection();
	    // 从Excel中读入数据
	    pstmtI = conn.prepareStatement(strSqlGhdetailI);
	    ExcelRead ex = new ExcelRead();
	    List<String> lstExcelData = ex.exportListFromExcel(filePath, "GH Details");
	    // 清除空白行
	    ex.listBlankLineClear(lstExcelData);
	    // 把Excel数据循环登陆到ghdetail表中
	    for (int i = 0; i < lstExcelData.size(); i++) {
		String strLineData = (String) lstExcelData.get(i);
		String[] strLineDateArray = strLineData.split(GhCommon.ALARM);

		// 确认ghdetail表中数据是否已经存在
		boolean blnExist = chkExistInGhdetail(strLineDateArray[3]);
		// 已经存在时，跳出本次循环直接执行下次循环
		if (blnExist) {
		    continue;
		}

		// 网申ID
		if ("".equals(strLineDateArray[2])) {
		    strCountNoSuf = df.format(intCountNo);
		    intCountNo++;// 增番
		    strCountNo = strCountNoPre + strCountNoSuf;
		    pstmtI.setString(1, strCountNo);
		} else {
		    pstmtI.setString(1, strLineDateArray[2]);
		}
		// 姓名
		pstmtI.setString(2, strLineDateArray[3]);
		// 性别
		pstmtI.setString(3, GhCommon.BLANK);
		// 毕业院校
		pstmtI.setString(4, GhCommon.BLANK);
		// 专业
		pstmtI.setString(5, GhCommon.BLANK);
		// 毕业时间
		pstmtI.setDate(6, DateUtil.getDateFormat(strLineDateArray[9], GhCommon.YYYYMMDD));
		// 备注
		pstmtI.setString(7, strLineDateArray[13]);
		// ghdetail表数据登陆
		intRusult = pstmtI.executeUpdate();
		if (intRusult < 0) {
		    result = GhCommon.FAIL;
		    return result;
		}
	    }

	    // 采番表更新
	    pstmtU = conn.prepareStatement(strSqlCountnoU);
	    if (intCountNo > intCountNoSuf) {
		strCountNoSuf = df.format(intCountNo);
		strCountNo = strCountNoPre + strCountNoSuf;
		pstmtU.setString(1, strCountNo);
		// countno表数据更新
		intRusult = pstmtU.executeUpdate();
		if (intRusult < 0) {
		    result = GhCommon.FAIL;
		    return result;
		}
	    }

	    // 提交
	    conn.commit();

	} catch (Exception e) {
	    // 回滚
	    conn.rollback();
	    e.printStackTrace();
	} finally {
	    // 释放资源
	    DButil.closeResultSet(rs);
	    DButil.closeStatement(pstmtU);
	    DButil.closeStatement(pstmtI);
	    DButil.closeConnection(conn);
	}

	// 返回结果
	return result;
    }

    // 采番值取得
    private String getCountNo() throws SQLException {

	String countNo = GhCommon.BLANK;
	Connection conn = null;
	PreparedStatement pstmtS = null;
	ResultSet rs = null;

	// countno表数据取得用SQL语句
	String strSqlCountnoS = SqlText.SQL_COUNT_NO_S;

	// 获取DB连接
	conn = DButil.getConnection();

	// 采番表数据取得
	pstmtS = conn.prepareStatement(strSqlCountnoS);
	rs = pstmtS.executeQuery();
	while (rs.next()) {
	    countNo = rs.getString("no");
	}
	DButil.closeResultSet(rs);
	DButil.closeStatement(pstmtS);
	DButil.closeConnection(conn);

	// 返回结果
	return countNo;

    }

    // 根据姓名进行确认ghdetail表中数据是否存在
    private boolean chkExistInGhdetail(String name) throws SQLException {

	boolean blnIsExist = false;
	Connection conn = null;
	PreparedStatement pstmtS = null;
	ResultSet rs = null;

	// ghdetail表数据取得用SQL语句
	String strSqlGhdetailS = SqlText.SQL_IMPORT_GH_DETAIL_S;

	// 获取DB连接
	conn = DButil.getConnection();

	// 采番表数据取得
	pstmtS = conn.prepareStatement(strSqlGhdetailS);
	pstmtS.setString(1, name);
	rs = pstmtS.executeQuery();
	while (rs.next()) {
	    blnIsExist = true;
	}
	DButil.closeResultSet(rs);
	DButil.closeStatement(pstmtS);
	DButil.closeConnection(conn);

	// 返回结果
	return blnIsExist;

    }

}
