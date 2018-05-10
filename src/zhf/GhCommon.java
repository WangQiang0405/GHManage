package zhf;

//常量定义
public class GhCommon {
	//成功
	public static final String SUCCESS = "success";
	//失败
	public static final String FAIL = "fail";
	//零
	public static final String ZERO = "0";
	//壹
	public static final String ONE = "1";
	//贰
	public static final String TWO ="2";
	//叁
    public static final String THREE="3";
    //肆
    public static final String FOUR = "4";
    //伍
    public static final String FIVE = "5";
    //#
    public static final String ALARM = "#";
    //空白
    public static final String BLANK = "";
    //xls
    public static final String EXCEL_XLS = "xls";
	//xlsx
    public static final String EXCEL_XLSX = "xlsx";
    //000000
    public static final String SIXZERO = "000000";
    
    //yyyy-MM-dd
    public static final String YYYYMMDD = "yyyy-MM-dd";
    
    //日期默认值
    public static final String YYYYMMDD_DEFAULT = "1900-01-01";
    
    
    //ghbooklist列表记录条数取得
    public static final String SQL5_GHBOOKLISTRECS = "select count(*) from ghbooklist";
    //userinfo列表记录条数取得
    public static final String SQL6_USERINFORRECS = "select count(*) from userinfo";
	//UserNameList 查询
    public static final String SQL7_USER_NAMET_LIST="select userid from userinfo";
    //userinfo列表信息全部取得
    public static final String SQL8_USERINFOGETALL = "select * from userinfo";
}
