package zhf.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;

import zhf.common.GhCommon;

public class StringUtil {
    
    /**
     * 获得web项目根目录
     */
    public static String getWebRootPath() {
	//ActionContext actionContext = ActionContext.getContext();
	//ServletContext servletContext = (ServletContext) actionContext.get(ServletActionContext.SERVLET_CONTEXT);
	//String rootPath = servletContext.getRealPath("/");
	String rootPath = ServletActionContext.getServletContext().getRealPath("/");
	return rootPath;
    }
    
    // 判断字符串是否超长
    public static boolean isOverMaxLenhth(String str, int intMax) {
	if (str.length() > intMax) {
	    return true;
	}
	return false;
    }
    
    // 判断字符串是否为数字
    public static boolean isNumeric(String str) {
	for (int i = 0; i < str.length(); i++) {
	    System.out.println(str.charAt(i));
	    if (!Character.isDigit(str.charAt(i))) {
		return false;
	    }
	}
	return true;
    }

    // 判断字符串是否为数字和字母的组合
    public static boolean isNumAndChar(String str) {
	Pattern pattern = Pattern.compile("[0-9a-zA-Z]*");
	Matcher isNumChar = pattern.matcher(str);
	if (!isNumChar.matches()) {
	    return false;
	}
	return true;
    }
    
    // 取得指定索引的值
    public static String getValueOfArray(String[] strArray, int index) {
	String value = GhCommon.BLANK;
	try {
	    value = strArray[index];
	} catch (ArrayIndexOutOfBoundsException e) {
	    value = GhCommon.BLANK;
	}
	
	return value;
    }
    
    

}
