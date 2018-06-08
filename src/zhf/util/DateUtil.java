package zhf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

    // �������͵��ж�
    public static boolean isDateFormat(String datevalue, String dateFormat) {

	if (datevalue == null || "".equals(datevalue)) {
	    return false;
	}
	try {
	    SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
	    fmt.setLenient(false);
	    fmt.parse(datevalue);
	    //if (datevalue.equals(fmt.format(dd))) {
	    return true;
	    
	} catch (ParseException e) {
	    return false;
	}
    }

    // ��ҵʱ��༭
    public static java.sql.Date getDateFormat(String datevalue, String dateFormat) {

	java.sql.Date dd = null;

	if (datevalue == null || "".equals(datevalue) || "-".equals(datevalue)) {
	    return null;
	}
	try {
	    SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
	    java.util.Date d = fmt.parse(datevalue);
	    dd = new java.sql.Date(d.getTime());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return dd;
    }

    // ��ҵʱ��༭(java.sql.Date-->String)
    public static String getDateFormat(java.sql.Date datevalue, String dateFormat) {

	String strFmt = "";

	if (datevalue == null) {
	    return strFmt;
	}
	try {
	    SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
	    strFmt = fmt.format(datevalue);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return strFmt;

    }

}
