package zhf.common;

//���ݿ�������Դ���� �Լ� Sql����Ӫ
public class SqlText {
    // MySql����
    public static final String DIRVER = "com.mysql.jdbc.Driver";
    // ���ݿ������URL
    public static final String URL = "jdbc:mysql://localhost:3306/ghinfo";
    // ���ݿ��¼�û���
    public static final String USER = "wq";
    // ���ݿ��¼�û�����
    public static final String PWD = "zaq12wsx";
    // �û���¼��֤
    public static final String SQL1_LOGIN = "select userid,usertype from userinfo where userid=? and pwd=?";
    // ProjectNameList ��ѯ
    public static final String SQL2_PROJECT_NAMET_LIST = "select pjname from ghtarget";
    // ghtarget�б���Ϣȫ��ȡ��
    public static final String SQL3_GHTARGETALL = "select * from ghtarget";
    // ghbooklist�б���Ϣȫ��ȡ��
    public static final String SQL4_GHBOOKLISTALL = "select * from ghbooklist";
    // ghbooklist�б��¼����ȡ��
    public static final String SQL5_GHBOOKLISTRECS = "select count(*) from ghbooklist";
    // userinfo�б��¼����ȡ��
    public static final String SQL6_USERINFORRECS = "select count(*) from userinfo";
    // UserNameList ��ѯ
    public static final String SQL7_USER_NAMET_LIST = "select userid from userinfo";
    // userinfo�б���Ϣȫ��ȡ��
    public static final String SQL8_USERINFOGETALL = "select * from userinfo";

    /** ��ͨSQL�� **/
    // countno������ȡ��
    public static final String SQL_COUNT_NO_S = "select * from countno";
    // countno�����ݸ���
    public static final String SQL_COUNT_NO_U = "update countno set no = ?";

    /** �ƻ���ʵ��ҳ��ʹ��SQL�� **/
    // ghdetail�б�ʵ��������¼����ȡ��
    public static final String SQL_PLANFACT_GH_DETAIL_S = "select count(*) from ghdetail where offerStatus = 'accept'";

    
    /** ����ӦƸ����Ϣҳ��ʹ��SQL�� **/
    // ghdetail������ȡ��
    public static final String SQL_IMPORT_GH_DETAIL_S = "select * from ghdetail where anme = ?";

    /** ӦƸ����Ϣһ��ҳ��ʹ��SQL�� **/
    // ghdetail������ȡ��
    public static final String SQL_INFO_GH_DETAIL_S = "select * from ghdetail_bakup";
    // ghdetail�б��¼����ȡ��
    public static final String SQL_INFO_TOTAL_GH_DETAIL_S = "select count(*) from ghdetail_bakup";
    
    /** ghdetail��ʹ��SQL�� **/
    // ghdetail������ȫ��ȡ��
    public static final String SQL_GH_DETAIL_S = "select * from ghdetail";
    // ghdetail�����ݵ�½
    public static final String SQL_GH_DETAIL_I = "insert into ghdetail(id,pjname,name,sex,"
    							+ "education,school,major,language,bookStatus,offerStatus,offerWReason,internFlag,"
    							+ "sectorOrSL,reportManager,internOBD,offerOBDPlan,offerOBDActual,comments) "
    							+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    // ghdetail������ȫ��ɾ��
    public static final String SQL_GH_DETAIL_D = "delete from ghdetail";

}
