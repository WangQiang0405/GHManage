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
	    // 释放资源
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

	// countno表数据更新用SQL语句
	String strSqlCountnoU = SqlText.SQL_COUNT_NO_U;
	// ghdetail表数据登陆用SQL语句
	String strSqlGhdetailI = SqlText.SQL_GH_DETAIL_I;
	// ghdetail表数据删除用SQL语句
	String strSqlGhdetailD = SqlText.SQL_GH_DETAIL_D;

	try {
	    
	    /** ghdetail表数据删除 **/
	    // 获取DB连接
	    conn = DBUtil.getConnection();
	    // 删除ghdetail表中的全部数据
	    pstmtD = conn.prepareStatement(strSqlGhdetailD);
	    intRusult = pstmtD.executeUpdate();
	    
	    /** 从Excel中读入数据后，插入到ghdetail表中 **/
	    // 从Excel中读入数据
	    pstmtI = conn.prepareStatement(strSqlGhdetailI);
	    ExcelRead ex = new ExcelRead();
	    List<String> lstExcelData = ex.exportListFromExcel(filePath, "GH Details");
	    // 清除空白行
	    ex.listBlankLineClear(lstExcelData);
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
		pstmtI.setInt(1, Integer.parseInt(StringUtil.getValueOfArray(strLineDataArray,0)));
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
	    DBUtil.closeResultSet(rs);
	    DBUtil.closeStatement(pstmtU);
	    DBUtil.closeStatement(pstmtI);
	    DBUtil.closeConnection(conn);
	}

	// 返回结果
	return result;
    }
    
}
