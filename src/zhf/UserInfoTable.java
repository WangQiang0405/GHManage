package zhf;

public class UserInfoTable {
	
	private String userId;
	private String userPwd;
	private String userType;
	
		public UserInfoTable(  String userId,
				               String userPwd,
				               String userType){
			
			this.userId = userId;
			this.userPwd = userPwd;
			this.userType = userType;
						
	}
	
	public String getUserId(){
		return this.userId;
	}
	
	public String getUserPwd(){
		return this.userPwd;
	}

	public String getUserType(){
		return this.userType;
	}

}
