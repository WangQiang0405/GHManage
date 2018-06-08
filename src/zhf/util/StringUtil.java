package zhf.util;

import java.util.HashMap;
import java.util.Map;
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
    
    // �ж��ַ����Ƿ�Ϊ���ֺ���ĸ�����
    public static String nullToEmpty(String strValue) {
	
	if (strValue == null) {
	    strValue = "";
	}
	
	return strValue;
    }
    
    // ��ȡOfferStatus
    public static Map getOfferStatusMap() {
	
	Map map = new HashMap();
	GhCommon.OfferStatusEnum[] enums = GhCommon.OfferStatusEnum.values(); // ���ö�ٶ�������
        // ����ö�ٶ���
        for (GhCommon.OfferStatusEnum offerStatusEnum : enums) {
            map.put(offerStatusEnum.getCd(), offerStatusEnum.getValue());
        }
	
	return map;
    }
    
    // offerStatusת��(����Cdȡֵ)
    public static String offerStatusCdToValue(String cd) {
	
	String value = "";
	// ���ö�ٶ�������
	GhCommon.OfferStatusEnum[] offerStatusEnums = GhCommon.OfferStatusEnum.values();
        // ����ö�ٶ���
        for (GhCommon.OfferStatusEnum offerStatusEnum : offerStatusEnums) {
            if (offerStatusEnum.getCd().equals(cd)) {
        	value = offerStatusEnum.getValue();
            }
        }
	
	return value;
    }
    
    // offerStatusת��(����ֵȡCd)
    public static String offerStatusValueToCd(String value) {
	
	String cd = "";
	GhCommon.OfferStatusEnum[] offerStatusEnums = GhCommon.OfferStatusEnum.values(); // ���ö�ٶ�������
        // ����ö�ٶ���
        for (GhCommon.OfferStatusEnum offerStatusEnum : offerStatusEnums) {
            if (offerStatusEnum.getValue().equals(value)) {
        	cd = offerStatusEnum.getCd();
            }
        }
	
	return cd;
    }
    
    
    // ��ȡOfferWaitingReason
    public static Map getOfferWaitingReasonMap() {
	
	Map map = new HashMap();
	GhCommon.OfferWaitingReasonEnum[] enums = GhCommon.OfferWaitingReasonEnum.values(); // ���ö�ٶ�������
        // ����ö�ٶ���
        for (GhCommon.OfferWaitingReasonEnum offerWReasonEnum : enums) {
            map.put(offerWReasonEnum.getCd(), offerWReasonEnum.getValue());
        }
	
	return map;
    }
    
    // offerWaitingReasonת��(����Cdȡֵ)
    public static String offerWaitingReasonCdToValue(String cd) {
	
	String value = "";
	GhCommon.OfferWaitingReasonEnum[] offerWReasonEnums = GhCommon.OfferWaitingReasonEnum.values(); // ���ö�ٶ�������
        // ����ö�ٶ���
        for (GhCommon.OfferWaitingReasonEnum offerWReasonEnum : offerWReasonEnums) {
            if (offerWReasonEnum.getCd().equals(cd)) {
        	value = offerWReasonEnum.getValue();
            }
        }
	
	return value;
    }
    
    // offerWaitingReasonת��(����ֵȡCd)
    public static String offerWaitingReasonValueToCd(String value) {
	
	String cd = "";
	GhCommon.OfferWaitingReasonEnum[] offerWReasonEnums = GhCommon.OfferWaitingReasonEnum.values(); // ���ö�ٶ�������
        // ����ö�ٶ���
        for (GhCommon.OfferWaitingReasonEnum offerWReasonEnum : offerWReasonEnums) {
            if (offerWReasonEnum.getValue().equals(value)) {
        	cd = offerWReasonEnum.getCd();
            }
        }
	
	return cd;
    }
    
    // ��ȡInternFlag
    public static Map getInternFlagMap() {
	
	Map map = new HashMap();
	GhCommon.InternFlagEnum[] enums = GhCommon.InternFlagEnum.values(); // ���ö�ٶ�������
        // ����ö�ٶ���
        for (GhCommon.InternFlagEnum internFlagEnum : enums) {
            map.put(internFlagEnum.getCd(), internFlagEnum.getDisplay());
        }
	
	return map;
    }
    
    // internFlagת��(����Cdȡֵ)
    public static String internFlagCdToValue(String cd) {
	
	String value = "";
	GhCommon.InternFlagEnum[] internFlagEnums = GhCommon.InternFlagEnum.values(); // ���ö�ٶ�������
        // ����ö�ٶ���
        for (GhCommon.InternFlagEnum internFlagEnum : internFlagEnums) {
            if (internFlagEnum.getCd().equals(cd)) {
        	value = internFlagEnum.getValue();
            }
        }
	
	return value;
    }
    
    // internFlagת��(����ֵȡCd)
    public static String internFlagValueToCd(String value) {
	
	String cd = "";
	GhCommon.InternFlagEnum[] internFlagEnums = GhCommon.InternFlagEnum.values(); // ���ö�ٶ�������
        // ����ö�ٶ���
        for (GhCommon.InternFlagEnum internFlagEnum : internFlagEnums) {
            if (internFlagEnum.getDisplay().equals(value)) {
        	cd = internFlagEnum.getCd();
            }
        }
	
	return cd;
    }
    
    // internFlagת��(����ֵȡDisplay)
    public static String internFlagConv(String internFlag) {
	
	String display = "";
	GhCommon.InternFlagEnum[] internFlagEnums = GhCommon.InternFlagEnum.values(); // ���ö�ٶ�������
        // ����ö�ٶ���
        for (GhCommon.InternFlagEnum internFlagEnum : internFlagEnums) {
            if (internFlagEnum.getValue().equals(internFlag)) {
        	display = internFlagEnum.getDisplay();
            }
        }
	
	return display;
    }
    
    

    
    

    
    
}
