package zhf.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;

import zhf.common.GhCommon;

public class StringUtil {
    
    /**
     * ���web��Ŀ��Ŀ¼
     */
    public static String getWebRootPath() {
	//ActionContext actionContext = ActionContext.getContext();
	//ServletContext servletContext = (ServletContext) actionContext.get(ServletActionContext.SERVLET_CONTEXT);
	//String rootPath = servletContext.getRealPath("/");
	String rootPath = ServletActionContext.getServletContext().getRealPath("/");
	return rootPath;
    }
    
    // �ж��ַ����Ƿ񳬳�
    public static boolean isOverMaxLenhth(String str, int intMax) {
	if (str.length() > intMax) {
	    return true;
	}
	return false;
    }
    
    // �ж��ַ����Ƿ�Ϊ����
    public static boolean isNumeric(String str) {
	for (int i = 0; i < str.length(); i++) {
	    System.out.println(str.charAt(i));
	    if (!Character.isDigit(str.charAt(i))) {
		return false;
	    }
	}
	return true;
    }

    // �ж��ַ����Ƿ�Ϊ���ֺ���ĸ�����
    public static boolean isNumAndChar(String str) {
	Pattern pattern = Pattern.compile("[0-9a-zA-Z]*");
	Matcher isNumChar = pattern.matcher(str);
	if (!isNumChar.matches()) {
	    return false;
	}
	return true;
    }
    
    // ȡ��ָ��������ֵ
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
