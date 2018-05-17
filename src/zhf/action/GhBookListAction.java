package zhf.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import zhf.util.DBUtil;

public class GhBookListAction extends ActionSupport
	{
		private int pageSpan=5;
		private int currenPageNo=1;
		private String projecName="0000";
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
			map.put(5,"每页5条");
			map.put(10,"每页10条");
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
			if(session.get("projectName")!=null)
			{
				this.projecName=(String)session.get("projectName");
			}
			return DBUtil.getTotalRecs(projecName);
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
		
		public void setProjecName(String projectName)
		{
			this.projecName=projectName;
			ActionContext.getContext().getSession().put("projectName",projectName);
			this.setCurrenPageNo(1);
		}
		
		public String getProjecName()
		{
			Object obj=(ActionContext.getContext().getSession().get("projectName"));
			if(obj!=null)
			{
				this.projecName=(String)obj;
			}
			return this.projecName;
		}
		
		public Map getProjectList()
		{
			Map map = DBUtil.getProjectList();
			map.put("0000","全部");
			return map;
		}
		
		public List getGhBookList()
		{
			Map session=ActionContext.getContext().getSession();
			if(session.get("projectName")!=null)
			{
				this.projecName=(String)session.get("projectName");
			}
			if(session.get("pageSpan")!=null)
			{
				this.pageSpan=(Integer)session.get("pageSpan");
			}
			
			List list=DBUtil.getTargetGhList(projecName,pageSpan,currenPageNo,"GhBookListAction");
		
			return list;
		}
		public String execute()
		{
			return SUCCESS;
		}
}
	


