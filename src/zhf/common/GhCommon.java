package zhf.common;

import java.util.HashMap;
import java.util.Map;

//��������
public class GhCommon {

    // ��ʼ��
    public static final String INIT = "init";
    // �ɹ�
    public static final String SUCCESS = "success";
    // ʧ��
    public static final String FAIL = "fail";
    // ��
    public static final String ZERO = "0";
    // Ҽ
    public static final String ONE = "1";
    // ��
    public static final String TWO = "2";
    // ��
    public static final String THREE = "3";
    // ��
    public static final String FOUR = "4";
    // ��
    public static final String FIVE = "5";
    // #
    public static final String ALARM = "#";
    // �հ�
    public static final String BLANK = "";
    // xls
    public static final String EXCEL_XLS = "xls";
    // xlsx
    public static final String EXCEL_XLSX = "xlsx";
    // 00000000
    public static final String EIGHTZERO = "00000000";

    // yyyy-MM-dd
    public static final String YYYYMMDD = "yyyy-MM-dd";

    // ����Ĭ��ֵ
    public static final String YYYYMMDD_DEFAULT = "1900-01-01";

    // ghbooklist�б��¼����ȡ��
    public static final String SQL5_GHBOOKLISTRECS = "select count(*) from ghbooklist";
    // userinfo�б��¼����ȡ��
    public static final String SQL6_USERINFORRECS = "select count(*) from userinfo";
    // UserNameList ��ѯ
    public static final String SQL7_USER_NAMET_LIST = "select userid from userinfo";
    // userinfo�б���Ϣȫ��ȡ��
    public static final String SQL8_USERINFOGETALL = "select * from userinfo";
    
    public static enum OfferStatusEnum {

	// EMPTY
	EMPTY("0", ""),
	// Sent
	Sent("1", "Sent"),
	// Accept
	Accept("2", "Accept"),
	// Decline
	Decline("3", "Decline"),
	// Waiting
	Waiting("4", "Waiting");

	private String cd;
	private String value;

	// ���췽��
	private OfferStatusEnum(String cd, String value) {
	    this.cd = cd;
	    this.value = value;
	}

	public String getCd() {
	    return cd;
	}

	public String getValue() {
	    return value;
	}
    }
    
    public static enum OfferWaitingReasonEnum {

	// ��
	EMPTY("0", ""),
	// ����δ���
	TEXT_UNCOMPLETED("1", "����δ���"),
	// ����������
	TEXT_APPROVING("2", "����������"),
	// offer������
	OFFER_PROCESSING("3", "offer������"),
	// ����ĿԤ��
	NON_PROJECT_RESERVATION("4", "����ĿԤ��"),
	// ��Ŀ������
	PROJECT_CONSIDERATION("5", "��Ŀ������");

	private String cd;
	private String value;

	// ���췽��
	private OfferWaitingReasonEnum(String cd, String value) {
	    this.cd = cd;
	    this.value = value;
	}

	public String getCd() {
	    return cd;
	}

	public String getValue() {
	    return value;
	}
    }
    
    public static enum InternFlagEnum {

	// ��
	EMPTY("0", ""),
	// ��
	YES("1", "��"),
	// ��
	NO("2", "��");

	private String cd;
	private String value;

	// ���췽��
	private InternFlagEnum(String cd, String value) {
	    this.cd = cd;
	    this.value = value;
	}

	public String getCd() {
	    return cd;
	}

	public String getValue() {
	    return value;
	}
    }

}
