package zhf.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zhf.common.SqlText;
import zhf.table.GhBookListTable;
import zhf.table.GhDetailTable;
import zhf.table.GhTargetTable;
import zhf.table.UserInfoTable;

public class DBUtil {

    // 获得数据库连接
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

    // 关闭数据库连接
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

    // 关闭事物(Statement)
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

    // 关闭事物(PreparedStatement)
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

    // 关闭数据集
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

    // 用户登录时验证
    public static String[] login(String user, String pwd) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String[] userinfo = new String[2];// impinfo[0]:0表示没有这个用户，1表示管理员，2表示普通用户

	try {
	    conn = getConnection();
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
	    try {
		if (conn != null) {
		    conn.close();
		}
		if (pstmt != null) {
		    pstmt.close();
		}
		if (rs != null) {
		    rs.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return userinfo;
    }

    // 获取Project Name 列表
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

    // 获取UserName 列表
    public static Map getUserList() {
	Map map = new HashMap();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery(SqlText.SQL7_USER_NAMET_LIST);
	    while (rs.next()) {
		String uid = rs.getString("userid");
		map.put(uid, uid);
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

    // 获取各种list数据全部信息
    public static List getTargetGhList(String pjname, int ps, int cp, String actionName) {
	ArrayList list = new ArrayList();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = getConnection();
	    stmt = conn.createStatement();
	    String sql = pjname.equals("0000") ? "" : " where pjname = '" + pjname + "'";

	    if (actionName.equals("GhTargetListAction")) {
		String sql1 = SqlText.SQL3_GHTARGETALL + sql;
		rs = stmt.executeQuery(sql1);
		while (rs.next()) {
		    String pjname1 = rs.getString("pjname");
		    int ghhcs = rs.getInt(2);
		    GhTargetTable gt = new GhTargetTable(pjname1, ghhcs);
		    list.add(gt);
		}
	    } else if (actionName.equals("GhTrackAction")) {
		int recNum = 0;
		String sql1 = SqlText.SQL4_GHBOOKLISTALL + sql;
		rs = stmt.executeQuery(sql1);
		if (cp != 1) {
		    rs.absolute((cp - 1) * ps);
		}
		while (recNum < ps && rs.next()) {
		    int wsID = rs.getInt("wsid");
		    String ghName = rs.getString("name");
		    String chuLiZt = rs.getString("procstatus");
		    String offerZt = rs.getString("offerstatus");
		    String yudingPj = rs.getString("pjname");
		    String ghorIntern = rs.getString("ghinflag");
		    String reportMgr = rs.getString("pem");
		    String onboardRqinOffer = rs.getDate("offeronbdate").toString();

		    recNum++;

		    GhBookListTable gl = new GhBookListTable(wsID, ghName, chuLiZt, offerZt, yudingPj, ghorIntern,
			    reportMgr, onboardRqinOffer);
		    list.add(gl);
		}
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

	return list;

    }

    // 获取User list数据全部信息
    public static List getUserList(String uid, int ps, int cp) {
	ArrayList list = new ArrayList();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = getConnection();
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
		String userType = rs.getInt("usertype") == 1 ? "管理员" : "普通用户";
		recNum++;

		UserInfoTable ul = new UserInfoTable(userId, userPwd, userType);
		list.add(ul);
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

	return list;

    }

    // 查询GHBookinglist里面符合条件的记录数
    public static int getTotalRecs(String pjname) {
	int totalRecs = 0;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    stmt = conn.createStatement();
	    String sql = pjname.equals("0000") ? "" : " where pjname = '" + pjname + "'";
	    String sql1 = SqlText.SQL5_GHBOOKLISTRECS + sql;
	    rs = stmt.executeQuery(sql1);
	    if (rs.next()) {
		totalRecs = rs.getInt(1);
	    }
	} catch (SQLException e) {
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
	return totalRecs;
    }

    // 查询用户list里面符合条件的记录数
    public static int getTotalUserRecs(String userId) {
	int totalRecs = 0;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
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
	return totalRecs;
    }

    // 获取各种ghdetail数据全部信息
    public static List getGhDetailList(String name) {
	ArrayList list = new ArrayList();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = getConnection();
	    stmt = conn.createStatement();
	    String sql = " where name = '" + name + "'";
	    String sql1 = SqlText.SQL_GH_DETAIL_S + sql;
	    rs = stmt.executeQuery(sql1);
	    while (rs.next()) {
		int No = rs.getInt("id");
		String Project = rs.getString("pjname");
		String Name = rs.getString("name");
		String Sex = rs.getString("sex");
		String Education = rs.getString("education");
		String GraduatedSchool = rs.getString("school");
		String Major = rs.getString("major");
		String Language = rs.getString("language");
		String BookStatus = rs.getString("bookStatus");
		String OfferStatus = rs.getString("offerStatus");
		String OfferWaitingReason = rs.getString("OfferWReason");
		String InternFlag = rs.getString("internFlg");
		String SectorOrSL = rs.getString("sectorOrSL");
		String Reporting = rs.getString("reportManager");
		Date InternOBD1 = rs.getDate("InternOBD");
		String InternOBD = null;
		if (InternOBD1 != null) {
		    InternOBD = rs.getDate("InternOBD").toString();
		}
		Date OfferOBDPlan1 = rs.getDate("offerOBDPlan");
		String OfferOBDPlan = null;
		if (OfferOBDPlan1 == null) {
		    OfferOBDPlan = rs.getDate("offerOBDPlan").toString();
		}
		Date OfferOBDActual1 = rs.getDate("offerOBDActual");
		String OfferOBDActual = null;
		if (OfferOBDActual1 != null) {
		    OfferOBDActual = rs.getDate("offerOBDActual").toString();
		}
		String Comments = rs.getString("comments");
		GhDetailTable gdt = new GhDetailTable(No, Project, Name, Sex, Education, GraduatedSchool, Major,
			Language, BookStatus, OfferStatus, OfferWaitingReason, InternFlag, SectorOrSL, Reporting,
			InternOBD, OfferOBDPlan, OfferOBDActual, Comments);

		list.add(gdt);
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
	return list;
    }

}
