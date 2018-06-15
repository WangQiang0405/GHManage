package zhf.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import zhf.common.SqlText;
import zhf.util.DBUtil;

public class GhLoginLogic {
    
    // �û���¼ʱ��֤
    public String[] login(String user, String pwd) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String[] userinfo = new String[2];// impinfo[0]:0��ʾû������û���1��ʾ����Ա��2��ʾ��ͨ�û�

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
	    // �ͷ���Դ
	    DBUtil.closeResultSet(rs);
	    DBUtil.closeStatement(pstmt);
	    DBUtil.closeConnection(conn);
	}
	return userinfo;
    }
    
}
