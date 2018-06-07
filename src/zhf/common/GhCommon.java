package zhf.common;

import java.util.HashMap;
import java.util.Map;

//常量定义
public class GhCommon {

    // 初始化
    public static final String INIT = "init";
    // 成功
    public static final String SUCCESS = "success";
    // 失败
    public static final String FAIL = "fail";
    // 零
    public static final String ZERO = "0";
    // 壹
    public static final String ONE = "1";
    // 贰
    public static final String TWO = "2";
    // 叁
    public static final String THREE = "3";
    // 肆
    public static final String FOUR = "4";
    // 伍
    public static final String FIVE = "5";
    // #
    public static final String ALARM = "#";
    // 空白
    public static final String BLANK = "";
    // xls
    public static final String EXCEL_XLS = "xls";
    // xlsx
    public static final String EXCEL_XLSX = "xlsx";
    // 00000000
    public static final String EIGHTZERO = "00000000";

    // yyyy-MM-dd
    public static final String YYYYMMDD = "yyyy-MM-dd";

    // 日期默认值
    public static final String YYYYMMDD_DEFAULT = "1900-01-01";

    // ghbooklist列表记录条数取得
    public static final String SQL5_GHBOOKLISTRECS = "select count(*) from ghbooklist";
    // userinfo列表记录条数取得
    public static final String SQL6_USERINFORRECS = "select count(*) from userinfo";
    // UserNameList 查询
    public static final String SQL7_USER_NAMET_LIST = "select userid from userinfo";
    // userinfo列表信息全部取得
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

	// 构造方法
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

	// 空
	EMPTY("0", ""),
	// 测试未完成
	TEXT_UNCOMPLETED("1", "测试未完成"),
	// 测试特批中
	TEXT_APPROVING("2", "测试特批中"),
	// offer流程中
	OFFER_PROCESSING("3", "offer流程中"),
	// 无项目预定
	NON_PROJECT_RESERVATION("4", "无项目预定"),
	// 项目考虑中
	PROJECT_CONSIDERATION("5", "项目考虑中");

	private String cd;
	private String value;

	// 构造方法
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

	// 空
	EMPTY("0", ""),
	// 是
	YES("1", "是"),
	// 否
	NO("2", "否");

	private String cd;
	private String value;

	// 构造方法
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
