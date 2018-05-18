package zhf.common;

//数据库连接资源定义 以及 Sql语句大本营
public class SqlText {
    // MySql驱动
    public static final String DIRVER = "com.mysql.jdbc.Driver";
    // 数据库服务器URL
    public static final String URL = "jdbc:mysql://localhost:3306/ghinfo";
    // 数据库登录用户名
    public static final String USER = "wq";
    // 数据库登录用户密码
    public static final String PWD = "zaq12wsx";
    // 用户登录验证
    public static final String SQL1_LOGIN = "select userid,usertype from userinfo where userid=? and pwd=?";
    // ProjectNameList 查询
    public static final String SQL2_PROJECT_NAMET_LIST = "select pjname from ghtarget";
    // ghtarget列表信息全部取得
    public static final String SQL3_GHTARGETALL = "select * from ghtarget";
    // ghbooklist列表信息全部取得
    public static final String SQL4_GHBOOKLISTALL = "select * from ghbooklist";
    // ghbooklist列表记录条数取得
    public static final String SQL5_GHBOOKLISTRECS = "select count(*) from ghbooklist";
    // userinfo列表记录条数取得
    public static final String SQL6_USERINFORRECS = "select count(*) from userinfo";
    // UserNameList 查询
    public static final String SQL7_USER_NAMET_LIST = "select userid from userinfo";
    // userinfo列表信息全部取得
    public static final String SQL8_USERINFOGETALL = "select * from userinfo";

    /** 共通SQL文 **/
    // countno表数据取得
    public static final String SQL_COUNT_NO_S = "select * from countno";
    // countno表数据更新
    public static final String SQL_COUNT_NO_U = "update countno set no = ?";

    /** 计划与实际页面使用SQL文 **/
    // ghdetail列表实际人数记录条数取得
    public static final String SQL_PLANFACT_GH_DETAIL_S = "select count(*) from ghdetail where offerStatus = 'accept'";

    
    /** 导入应聘者信息页面使用SQL文 **/
    // ghdetail表数据取得
    public static final String SQL_IMPORT_GH_DETAIL_S = "select * from ghdetail where anme = ?";

    /** 应聘者信息一览页面使用SQL文 **/
    // ghdetail表数据取得
    public static final String SQL_INFO_GH_DETAIL_S = "select * from ghdetail_bakup";
    // ghdetail列表记录条数取得
    public static final String SQL_INFO_TOTAL_GH_DETAIL_S = "select count(*) from ghdetail_bakup";
    
    /** ghdetail表使用SQL文 **/
    // ghdetail表数据全部取得
    public static final String SQL_GH_DETAIL_S = "select * from ghdetail";
    // ghdetail表数据登陆
    public static final String SQL_GH_DETAIL_I = "insert into ghdetail(id,pjname,name,sex,"
    							+ "education,school,major,language,bookStatus,offerStatus,offerWReason,internFlag,"
    							+ "sectorOrSL,reportManager,internOBD,offerOBDPlan,offerOBDActual,comments) "
    							+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    // ghdetail表数据全部删除
    public static final String SQL_GH_DETAIL_D = "delete from ghdetail";

}
