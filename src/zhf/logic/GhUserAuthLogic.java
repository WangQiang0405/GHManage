package zhf.logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zhf.common.SqlText;
import zhf.table.UserInfoTable;
import zhf.util.DBUtil;

public class GhUserAuthLogic {
    
    // ��ȡUser list����ȫ����Ϣ
    public List getUserList(String uid, int ps, int cp) {
	List list = new ArrayList();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = DBUtil.getConnection();
	    stmt = conn.createStatement();
	    String sql = uid.equals("0000") ? "" : " where userid = '" + uid + "'";

	    String sql1 = SqlText.SQL8_USERINFOGETALL + sql;
	    rs = stmt.executeQuery(sql1);
	    int recNum = 0;
	    if (cp != 1) {
		rs.absolute((cp - 1) * ps);
	    }
	    while (recNum < ps && rs.next()) {
		String userId = rs.getString("userid");
		String userPwd = rs.getString("pwd");
		String userType = rs.getInt("usertype") == 1 ? "����Ա" : "��ͨ�û�";
		recNum++;

		UserInfoTable ul = new UserInfoTable(userId, userPwd, userType);
		list.add(ul);
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
    
    // ��ѯ�û�list������������ļ�¼��
    public int getTotalUserRecs(String userId) {
	int totalRecs = 0;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	try {
	    conn = DBUtil.getConnection();
	    stmt = conn.createStatement();
	    String sql = userId.equals("0000") ? "" : " where userid = '" + userId + "'";
	    String sql1 = SqlText.SQL6_USERINFORRECS + sql;
	    rs = stmt.executeQuery(sql1);
	    if (rs.next()) {
		totalRecs = rs.getInt(1);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    // �ͷ���Դ
	    DBUtil.closeResultSet(rs);
	    DBUtil.closeStatement(stmt);
	    DBUtil.closeConnection(conn);
	}
	return totalRecs;
    }
    

    // ��ȡUserName �б�
    public Map getUserList() {
	Map map = new HashMap();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	try {
	    conn = DBUtil.getConnection();
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery(SqlText.SQL7_USER_NAMET_LIST);
	    while (rs.next()) {
		String uid = rs.getString("userid");
		map.put(uid, uid);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    // �ͷ���Դ
	    DBUtil.closeResultSet(rs);
	    DBUtil.closeStatement(stmt);
	    DBUtil.closeConnection(conn);
	}
	return map;
    }
    
}
