package zhf.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import zhf.common.SqlText;
import zhf.util.DBUtil;

public class GhLoginLogic {
    
    // 用户登录时验证
    public String[] login(String user, String pwd) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String[] userinfo = new String[2];// impinfo[0]:0表示没有这个用户，1表示管理员，2表示普通用户

	try {
	    conn = DBUtil.getConnection();
	    pstmt = conn.prepareStatement(SqlText.SQL1_LOGIN);
	    pstmt.setString(1, user);
	    pstmt.setString(2, pwd);
	    rs = pstmt.executeQuery();
	    if (rs.next()) {
		userinfo[0] = rs.getInt("usertype") + "";
		userinfo[1] = rs.getString("userid");
	    } else {
		userinfo[0] = "0";
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    // 释放资源
	    DBUtil.closeResultSet(rs);
	    DBUtil.closeStatement(pstmt);
	    DBUtil.closeConnection(conn);
	}
	return userinfo;
    }
    
}
