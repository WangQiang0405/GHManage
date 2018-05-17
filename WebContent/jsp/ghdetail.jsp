<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<title><s:text name="mainmanage.title"/></title>
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
	<div>
		<div>
		<s:if test="GhDetailList.size()!=0">
			<table border="1" bgcolor="#e2e2e2" cellspacing="1" align="center" width="1400px">
				
					<tr class="headerClass">
						<th>No</th>
						<th>Project</th>
						<th>Name</th>
						<th>Sex</th>
						<th>Education</th>
						<th>Graduated School</th>
						<th>Major</th>
						<th>Language</th>
						<th>Book Status</th>
						<th>Offer Status</th>
						<th>Offer Waiting Reason</th>
						<th>Intern Flag</th>
						<th>Sector/SL</th>
						<th>Reporting Manager</th>
						<th>Intern OBD</th>
						<th>Offer OBD Plan</th>
						<th>Offer OBD Actual</th>
						<th>Comments</th>
					</tr>
					<s:iterator id="List" value="GhDetailList" status="ghdl">
						<tr id="ghdl<s:property value="#ghdl.index"/>"
							<s:if test="#ghdl.odd">class="oddRow"</s:if>
							<s:else>class="evenRow"</s:else>
							onmouseover="this.className='selectedRow';" 
							onmouseout="mymouseout('ghl<s:property value='#ghdl.index'/>',
							<s:if test='#ghdl.odd'>'oddRow'</s:if>
							<s:else>'evenRow'</s:else>);" 
							>
							<td><s:property value="No"/></td>
							<td><s:property value="Project"/></td>
							<td><s:property value="Name"/></td>
							<td><s:property value="Sex"/></td>
							<td><s:property value="Education"/></td>
							<td><s:property value="Graduated_School"/></td>
							<td><s:property value="Major"/></td>
							<td><s:property value="Language"/></td>
							<td><s:property value="Book_Status"/></td>
							<td><s:property value="Offer_Status"/></td>
							<td><s:property value="Offer_Waiting_Reason"/></td>
							<td><s:property value="Intern_Flag"/></td>
							<td><s:property value="SectorOrSL"/></td>
							<td><s:property value="Reporting_Manager"/></td>
							<td><s:property value="Intern_OBD"/></td>
							<td><s:property value="Offer_OBD_Plan"/></td>
							<td><s:property value="Offer_OBD_Actual"/></td>
							<td><s:property value="Comments"/></td>
						</tr>
					</s:iterator>
				</table>
				
				<script language="javascript">
				function custom_close(){
				if 
				(confirm("您确定要关闭本页吗？")){
				window.opener=null;
				window.open('','_self');
				window.close();
				}
				else{}
				}
				</script>
				<div id=closebtn>
				<input id="btnClose" type="button" value="关闭本页" onClick="custom_close()" />
				</div>
			</s:if>
			<s:else>
				<p>没有相应的学生信息</p>
				<script language="javascript">
				function custom_close(){
				if 
				(confirm("您确定要关闭本页吗？")){
				window.opener=null;
				window.open('','_self');
				window.close();
				}
				else{}
				}
				</script>
				<input id="btnClose" type="button" value="关闭本页" onClick="custom_close()" />
			</s:else>		
		</div>
	</div>	
	</body>
</html>