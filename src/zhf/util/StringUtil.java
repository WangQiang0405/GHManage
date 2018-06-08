package zhf.util;

import java.util.HashMap;
import java.util.Map;
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
    
    // 判断字符串是否为数字和字母的组合
    public static String nullToEmpty(String strValue) {
	
	if (strValue == null) {
	    strValue = "";
	}
	
	return strValue;
    }
    
    // 获取OfferStatus
    public static Map getOfferStatusMap() {
	
	Map map = new HashMap();
	GhCommon.OfferStatusEnum[] enums = GhCommon.OfferStatusEnum.values(); // 获得枚举对象数组
        // 遍历枚举对象
        for (GhCommon.OfferStatusEnum offerStatusEnum : enums) {
            map.put(offerStatusEnum.getCd(), offerStatusEnum.getValue());
        }
	
	return map;
    }
    
    // offerStatus转化(根据Cd取值)
    public static String offerStatusCdToValue(String cd) {
	
	String value = "";
	// 获得枚举对象数组
	GhCommon.OfferStatusEnum[] offerStatusEnums = GhCommon.OfferStatusEnum.values();
        // 遍历枚举对象
        for (GhCommon.OfferStatusEnum offerStatusEnum : offerStatusEnums) {
            if (offerStatusEnum.getCd().equals(cd)) {
        	value = offerStatusEnum.getValue();
            }
        }
	
	return value;
    }
    
    // offerStatus转化(根据值取Cd)
    public static String offerStatusValueToCd(String value) {
	
	String cd = "";
	GhCommon.OfferStatusEnum[] offerStatusEnums = GhCommon.OfferStatusEnum.values(); // 获得枚举对象数组
        // 遍历枚举对象
        for (GhCommon.OfferStatusEnum offerStatusEnum : offerStatusEnums) {
            if (offerStatusEnum.getValue().equals(value)) {
        	cd = offerStatusEnum.getCd();
            }
        }
	
	return cd;
    }
    
    
    // 获取OfferWaitingReason
    public static Map getOfferWaitingReasonMap() {
	
	Map map = new HashMap();
	GhCommon.OfferWaitingReasonEnum[] enums = GhCommon.OfferWaitingReasonEnum.values(); // 获得枚举对象数组
        // 遍历枚举对象
        for (GhCommon.OfferWaitingReasonEnum offerWReasonEnum : enums) {
            map.put(offerWReasonEnum.getCd(), offerWReasonEnum.getValue());
        }
	
	return map;
    }
    
    // offerWaitingReason转化(根据Cd取值)
    public static String offerWaitingReasonCdToValue(String cd) {
	
	String value = "";
	GhCommon.OfferWaitingReasonEnum[] offerWReasonEnums = GhCommon.OfferWaitingReasonEnum.values(); // 获得枚举对象数组
        // 遍历枚举对象
        for (GhCommon.OfferWaitingReasonEnum offerWReasonEnum : offerWReasonEnums) {
            if (offerWReasonEnum.getCd().equals(cd)) {
        	value = offerWReasonEnum.getValue();
            }
        }
	
	return value;
    }
    
    // offerWaitingReason转化(根据值取Cd)
    public static String offerWaitingReasonValueToCd(String value) {
	
	String cd = "";
	GhCommon.OfferWaitingReasonEnum[] offerWReasonEnums = GhCommon.OfferWaitingReasonEnum.values(); // 获得枚举对象数组
        // 遍历枚举对象
        for (GhCommon.OfferWaitingReasonEnum offerWReasonEnum : offerWReasonEnums) {
            if (offerWReasonEnum.getValue().equals(value)) {
        	cd = offerWReasonEnum.getCd();
            }
        }
	
	return cd;
    }
    
    // 获取InternFlag
    public static Map getInternFlagMap() {
	
	Map map = new HashMap();
	GhCommon.InternFlagEnum[] enums = GhCommon.InternFlagEnum.values(); // 获得枚举对象数组
        // 遍历枚举对象
        for (GhCommon.InternFlagEnum internFlagEnum : enums) {
            map.put(internFlagEnum.getCd(), internFlagEnum.getDisplay());
        }
	
	return map;
    }
    
    // internFlag转化(根据Cd取值)
    public static String internFlagCdToValue(String cd) {
	
	String value = "";
	GhCommon.InternFlagEnum[] internFlagEnums = GhCommon.InternFlagEnum.values(); // 获得枚举对象数组
        // 遍历枚举对象
        for (GhCommon.InternFlagEnum internFlagEnum : internFlagEnums) {
            if (internFlagEnum.getCd().equals(cd)) {
        	value = internFlagEnum.getValue();
            }
        }
	
	return value;
    }
    
    // internFlag转化(根据值取Cd)
    public static String internFlagValueToCd(String value) {
	
	String cd = "";
	GhCommon.InternFlagEnum[] internFlagEnums = GhCommon.InternFlagEnum.values(); // 获得枚举对象数组
        // 遍历枚举对象
        for (GhCommon.InternFlagEnum internFlagEnum : internFlagEnums) {
            if (internFlagEnum.getDisplay().equals(value)) {
        	cd = internFlagEnum.getCd();
            }
        }
	
	return cd;
    }
    
    // internFlag转化(根据值取Display)
    public static String internFlagConv(String internFlag) {
	
	String display = "";
	GhCommon.InternFlagEnum[] internFlagEnums = GhCommon.InternFlagEnum.values(); // 获得枚举对象数组
        // 遍历枚举对象
        for (GhCommon.InternFlagEnum internFlagEnum : internFlagEnums) {
            if (internFlagEnum.getValue().equals(internFlag)) {
        	display = internFlagEnum.getDisplay();
            }
        }
	
	return display;
    }
    
    

    
    

    
    
}
