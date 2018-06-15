package zhf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import zhf.common.SqlText;

public class DBUtil {

    // ������ݿ�����
    public static Connection getConnection() {
	Connection conn = null;
	try {
	    Class.forName(SqlText.DIRVER);
	    conn = DriverManager.getConnection(SqlText.URL, SqlText.USER, SqlText.PWD);
	    conn.setAutoCommit(false);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return conn;
    }

    // �ر����ݿ�����
    public static void closeConnection(Connection conn) {
	try {
	    if (conn != null) {
		conn.close();
	    }
	    conn = null;
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    // �ر�����(Statement)
    public static void closeStatement(Statement stmt) {
	try {
	    if (stmt != null) {
		stmt.close();
	    }
	    stmt = null;
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    // �ر�����(PreparedStatement)
    public static void closeStatement(PreparedStatement stmt) {
	try {
	    if (stmt != null) {
		stmt.close();
	    }
	    stmt = null;
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    // �ر����ݼ�
    public static void closeResultSet(ResultSet rs) {
	try {
	    if (rs != null) {
		rs.close();
	    }
	    rs = null;
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    // ��ȡProject Name �б�
    public static Map getProjectList() {
	Map map = new HashMap();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery(SqlText.SQL2_PROJECT_NAMET_LIST);
	    while (rs.next()) {
		String pjname = rs.getString("pjname");
		map.put(pjname, pjname);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (conn != null) {
		    conn.close();
		}
		if (stmt != null) {
		    stmt.close();
		}
		if (rs != null) {
		    rs.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return map;
    }

}
