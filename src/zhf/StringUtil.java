package zhf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

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

}
