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

	// countno表数据更新用SQL语句
	String strSqlCountnoU = SqlText.SQL_COUNT_NO_U;
	// ghdetail表数据登陆用SQL语句
	String strSqlGhdetailI = SqlText.SQL_GH_DETAIL_I;
	// ghdetail表数据删除用SQL语句
	String strSqlGhdetailD = SqlText.SQL_GH_DETAIL_D;

	try {
	    
	    /** 读入Excel数据Check **/
	    ExcelRead ex = new ExcelRead();
	    List<String> lstExcelData = ex.exportListFromExcel(filePath, "GH Details");
	    // 清除空白行
	    ex.listBlankLineClear(lstExcelData);
	    if (lstExcelData == null || lstExcelData.size() == 0) {
		result = GhCommon.FAIL;
		return result;
	    }
	    /** ghdetail表数据删除 **/
	    // 获取DB连接
	    conn = DBUtil.getConnection();
	    // 删除ghdetail表中的全部数据
	    pstmtD = conn.prepareStatement(strSqlGhdetailD);
	    intRusult = pstmtD.executeUpdate();
	    
	    /** 采番值取得**/
	    // 采番值取得
	    int intCountId = getCountNo();
	    // 采番用计数器
	    int intCountNo = intCountId;
	    
	    /** 从Excel中读入数据后，插入到ghdetail表中 **/
	    // 从Excel中读入数据
	    pstmtI = conn.prepareStatement(strSqlGhdetailI);
	    // 把Excel数据循环登陆到ghdetail表中
	    for (int i = 0; i < lstExcelData.size(); i++) {
		String strLineData = (String) lstExcelData.get(i);
		String[] strLineDataArray = strLineData.split(GhCommon.ALARM);

		/*// 确认ghdetail表中数据是否已经存在
		boolean blnExist = chkExistInGhdetail(strLineDateArray[3]);
		// 已经存在时，跳出本次循环直接执行下次循环
		if (blnExist) {
		    continue;
		}*/

		// Id
		if ("".equals(StringUtil.getValueOfArray(strLineDataArray,0))) {
		    pstmtI.setInt(1, intCountNo);
		    intCountNo++;// 增番
		} else {
		    pstmtI.setInt(1, Integer.parseInt(StringUtil.getValueOfArray(strLineDataArray,0)));
		}
		// 项目
		pstmtI.setString(2, StringUtil.getValueOfArray(strLineDataArray,1));
		// 姓名
		pstmtI.setString(3, StringUtil.getValueOfArray(strLineDataArray,2));
		// 性别
		pstmtI.setString(4, StringUtil.getValueOfArray(strLineDataArray,3));
		// 教育程度
		pstmtI.setString(5, StringUtil.getValueOfArray(strLineDataArray,4));
		// 毕业院校
		pstmtI.setString(6, StringUtil.getValueOfArray(strLineDataArray,5));
		// 专业
		pstmtI.setString(7, StringUtil.getValueOfArray(strLineDataArray,6));
		// 外语
		pstmtI.setString(8, StringUtil.getValueOfArray(strLineDataArray,7));
		// 处理状态
		pstmtI.setString(9, StringUtil.getValueOfArray(strLineDataArray,8));
		// Offer状态
		pstmtI.setString(10, StringUtil.getValueOfArray(strLineDataArray,9));
		// Offer等待理由
		pstmtI.setString(11, StringUtil.getValueOfArray(strLineDataArray,10));
		// 实习
		pstmtI.setString(12, StringUtil.getValueOfArray(strLineDataArray,11));
		// 部门
		pstmtI.setString(13, StringUtil.getValueOfArray(strLineDataArray,12));
		// 项目经理
		pstmtI.setString(14, StringUtil.getValueOfArray(strLineDataArray,13));
		// 实习日
		pstmtI.setDate(15, 
			DateUtil.getDateFormat(StringUtil.getValueOfArray(strLineDataArray,14), GhCommon.YYYYMMDD));
		// Offer计划日
		pstmtI.setDate(16, 
			DateUtil.getDateFormat(StringUtil.getValueOfArray(strLineDataArray,15), GhCommon.YYYYMMDD));
		// Offer实际日
		pstmtI.setDate(17, 
			DateUtil.getDateFormat(StringUtil.getValueOfArray(strLineDataArray,16), GhCommon.YYYYMMDD));
		// 备注
		pstmtI.setString(18, StringUtil.getValueOfArray(strLineDataArray,17));
		// ghdetail表数据登陆
		intRusult = pstmtI.executeUpdate();
		if (intRusult < 0) {
		    result = GhCommon.FAIL;
		    return result;
		}
	    }

	    /** 采番表更新**/
	    // 采番表更新
	    pstmtU = conn.prepareStatement(strSqlCountnoU);
	    if (intCountNo > intCountId) {
		pstmtU.setInt(1, intCountNo);
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
	    DBUtil.closeResultSet(rs);
	    DBUtil.closeStatement(pstmtU);
	    DBUtil.closeStatement(pstmtI);
	    DBUtil.closeConnection(conn);
	}

	// 返回结果
	return result;
    }

    // 采番值取得
    private int getCountNo() throws SQLException {

	int countId = 0;
	Connection conn = null;
	PreparedStatement pstmtS = null;
	ResultSet rs = null;

	// countno表数据取得用SQL语句
	String strSqlCountnoS = SqlText.SQL_COUNT_NO_S;

	// 获取DB连接
	conn = DBUtil.getConnection();

	// 采番表数据取得
	pstmtS = conn.prepareStatement(strSqlCountnoS);
	rs = pstmtS.executeQuery();
	while (rs.next()) {
	    countId = rs.getInt("id");
	}
	DBUtil.closeResultSet(rs);
	DBUtil.closeStatement(pstmtS);
	DBUtil.closeConnection(conn);

	// 返回结果
	return countId;

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
	conn = DBUtil.getConnection();

	// 采番表数据取得
	pstmtS = conn.prepareStatement(strSqlGhdetailS);
	pstmtS.setString(1, name);
	rs = pstmtS.executeQuery();
	while (rs.next()) {
	    blnIsExist = true;
	}
	DBUtil.closeResultSet(rs);
	DBUtil.closeStatement(pstmtS);
	DBUtil.closeConnection(conn);

	// 返回结果
	return blnIsExist;

    }

}
