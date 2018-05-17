<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<title><s:text name="mainmanage.title"/></title>
		<link href="images/style.css" rel="stylesheet" type="text/css"/>
		<link href="images/mainstyle.css" rel="stylesheet" type="text/css"/>
		<link href="images/impliststyle.css" rel="stylesheet" type="text/css"/>
		<script language="JavaScript" src="js/manage.js"></script>
		<script language="JavaScript">
		function mymouseout(id,styleclass)
		{
			document.getElementById(id).className=styleclass;
		}
		</script>
	</head>
	<body>
	<div id="container">
		<div id="tophead">
			<s:include value="/jsp/top.jsp"/>
		</div>
		<div id="leftcontent">
			<s:include value="/jsp/lefttree.jsp"/>
		</div>
		<div id="maincontent">
		    <div id="operate">
			    <div style="float:left">
			    	<H3 style="margin-top:10px"><img src="images/h3.gif"><s:text name="label.recruitmentPlan"/></H3>
			    </div>
			    <div style="float:right;margin-top:12px">
			    	<s:text name="label.unit"/>
			    </div>
		    </div>
			<s:if test="GhTargetList.size()!=0">
				
				<table border="1" bgcolor="#e2e2e2" cellspacing="0" align="center" width="770px">
				
					<tr class="headerClass">
						<th><s:text name="field.project"/></th>
						<th><s:text name="field.membersPlan"/></th>
					</tr>
					<s:iterator id="List" value="GhTargetList" status="ghl">
						<tr id="ghl<s:property value="#ghl.index"/>"
							<s:if test="#ghl.odd">class="oddRow"</s:if>
							<s:else>class="evenRow"</s:else>
							onmouseover="this.className='selectedRow';" 
							onmouseout="mymouseout('ghl<s:property value='#ghl.index'/>',
							<s:if test='#ghl.odd'>'oddRow'</s:if>
							<s:else>'evenRow'</s:else>);">
							<td><s:property value="pjname"/></td>
							<td><s:property value="ghhcs"/></td>							
						</tr>
					</s:iterator>
				</table>		
				
			</s:if>
			<s:else>
				<p>没有相应的信息</p>
			</s:else>
			<div id="chart" style="margin-top: 20px">
				<img src="barChart.action"/>
			</div>
		</div>
	</div>	
	</body>
</html>