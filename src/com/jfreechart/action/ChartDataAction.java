package com.jfreechart.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.*;
import java.util.*;
import zhf.DButil;
import zhf.GhTargetTable;
import zhf.SqlText;

//import zhf.SqlText;

public class ChartDataAction {

    public static List getUserList() {
	ArrayList list = new ArrayList();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = DButil.getConnection();
	    stmt = conn.createStatement();

	    String sql1 = SqlText.SQL3_GHTARGETALL;
	    rs = stmt.executeQuery(sql1);

	    while (rs.next()) {

		String pjname1 = rs.getString("pjname");
		int ghhcs = rs.getInt(2);
		GhTargetTable gt = new GhTargetTable(pjname1, ghhcs);
		list.add(gt);

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

    public static int getGhactualcsRecs(String pjname) {
	int totalRecs = 0;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
	    conn = DButil.getConnection();
	    stmt = conn.createStatement();

	    String sqlWhere = " and pjname = '" + pjname + "'";
	    String sql = SqlText.SQL_PLANFACT_GH_DETAIL_S + sqlWhere;
	    rs = stmt.executeQuery(sql);

	    if (rs.next()) {
		totalRecs = rs.getInt(1);
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

	return totalRecs;

    }
}
