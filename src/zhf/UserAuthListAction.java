package zhf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import zhf.util.DBUtil;

public class UserAuthListAction extends ActionSupport{
	private int pageSpan=5;
	private int currenPageNo=1;
	//private String departId="0000";
	//private String projecName="0000";
	private String userId="0000";
	//private int orderBySalary=0;//0:�����������1:����-1:����
	//private int orderByAge=0;//0:�����������1:����-1:����
	private int maxPageNo;
	private int totalMessage;
	
	public void setPageSpan(int pageSpan)
	{
		this.pageSpan=pageSpan;
		ActionContext.getContext().getSession().put("pageSpan",pageSpan);
		this.setCurrenPageNo(1);
	}
	
	public int getPageSpan()
	{
		Object obj=ActionContext.getContext().getSession().get("pageSpan");
		if(obj!=null)
		{
			this.pageSpan=(Integer)obj;
		}
		return this.pageSpan;
	}
	
	public Map getSpanList()
	{
		Map map=new HashMap();
		map.put(5,"ÿҳ5��");
		map.put(10,"ÿҳ10��");
		return map;
	}
	
	public int getMaxPageNo()
	{
		Map session=ActionContext.getContext().getSession();
		if(session.get("pageSpan")!=null)
		{
			this.pageSpan=(Integer)session.get("pageSpan");
		}
		totalMessage=this.getTotalMessage();
		return totalMessage%pageSpan==0?totalMessage/pageSpan:(totalMessage/pageSpan+1);
	}
	
	public int getTotalMessage()
	{
		Map session=ActionContext.getContext().getSession();
		if(session.get("userId")!=null)
		{
			this.userId=(String)session.get("userId");
		}
		return DBUtil.getTotalUserRecs(userId);
	}
	
	public void setCurrenPageNo(int currenPageNo)
	{
		if(currenPageNo<=this.getMaxPageNo()&&currenPageNo>=1)
		{
			this.currenPageNo=currenPageNo;
		}
	}
	
	public int getCurrenPageNo()
	{
		return this.currenPageNo;
	}
	
	public void setUserId(String userId)
	{
		this.userId=userId;
		ActionContext.getContext().getSession().put("userId",userId);
		this.setCurrenPageNo(1);
	}
	
	public String getUserId()
	{
		Object obj=(ActionContext.getContext().getSession().get("userId"));
		if(obj!=null)
		{
			this.userId=(String)obj;
		}
		return this.userId;
	}
	
	public Map getUserList()
	{
		Map map = DBUtil.getUserList();
		map.put("0000","ȫ��");
		return map;
	}
	
	public List getUserInfoList()
	{
		Map session=ActionContext.getContext().getSession();
		if(session.get("userId")!=null)
		{
			this.userId=(String)session.get("userId");
		}
		if(session.get("pageSpan")!=null)
		{
			this.pageSpan=(Integer)session.get("pageSpan");
		}
		
		List list=DBUtil.getUserList(userId,pageSpan,currenPageNo);
	
		return list;
	}
	public String execute()
	{
		return SUCCESS;
	}

}
