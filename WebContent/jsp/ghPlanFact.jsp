<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<title><s:text name="planfact.title"/></title>
		<link href="css/style.css" rel="stylesheet" type="text/css"/>
		<link href="css/mainstyle.css" rel="stylesheet" type="text/css"/>
		<link href="css/commonStyle.css" rel="stylesheet" type="text/css"/>
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
				
				<table border="0" bgcolor="#e2e2e2" cellspacing="1" align="center" width="100%">
				
					<tr class="headerClass">
						<th width="50%"><s:text name="field.project"/></th>
						<th width="20%"><s:text name="field.membersPlan"/></th>
						<th width="20%"><s:text name="field.membersFact"/></th>
						<th width="10%"><font style="color:red;font-weight:bold"><s:text name="field.membersDifference"/></font></th>
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
							<td><s:property value="fact"/></td>
							<td><font style="color:red;font-weight:bold"><s:property value="difference"/></font></td>
						</tr>
					</s:iterator>
				</table>		
				
			</s:if>
			<s:else>
				<p>没有相应的信息</p>
			</s:else>
			<div id="chartLeft">
				<img id="bar" src="barChart.action"/>
			</div>
			<div id="chartRight">
				<div style="">
					<img src="pieChartMf.action"/>
				</div>
				<div style="margin-top: 5px">
					<img src="pieChartPj.action"/>
				</div>
			</div>
		</div>
	</div>
	</body>
</html>