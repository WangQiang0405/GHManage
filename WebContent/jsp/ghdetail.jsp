<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<title><s:text name="detail.title"/></title>
		<link href="css/commonStyle.css" rel="stylesheet" type="text/css"/>
		<script language="JavaScript" src="js/manage.js"></script>
		<script language="JavaScript">
			function mymouseout(id,styleclass) {
				document.getElementById(id).className=styleclass;
			}
			function custom_close(){
				window.opener=null;
				window.open('','_self');
				window.close();
			}
		</script>
	</head>
	<body style="height:150px;">
	<div id="container">
		<div id="maincontent">
			<table border="0" bgcolor="#e2e2e2" cellspacing="1" align="center" width="1586px">
				<tr class="headerClass">
					<th width="6%"><s:text name="field.project"/></th>
					<th width="4%"><s:text name="field.name"/></th>
					<th width="3%"><s:text name="field.sex"/></th>
					<th width="5%"><s:text name="field.education"/></th>
					<th width="10%"><s:text name="field.graduatedSchool"/></th>
					<th width="10%"><s:text name="field.major"/></th>
					<th width="5%"><s:text name="field.language"/></th>
					<th width="4%"><s:text name="field.bookStatus"/></th>
					<th width="5%"><s:text name="field.offerStatus"/></th>
					<th width="7%"><s:text name="field.offerWaitingReason"/></th>
					<th width="3%"><s:text name="field.internFlag"/></th>
					<th width="8%"><s:text name="field.sectorSL"/></th>
					<th width="12%"><s:text name="field.reportingManager"/></th>
					<th width="6%"><s:text name="field.internOBD"/></th>
					<th width="6%"><s:text name="field.offerOBDPlan"/></th>
					<th width="6%"><s:text name="field.offerOBDActual"/></th>
				</tr>
				<s:if test="GhDetailList.size()!=0">
				<s:iterator id="List" value="GhDetailList" status="ghdl">
					<tr id="ghl<s:property value="#ghdl.index"/>"
						<s:if test="#ghdl.odd">class="oddRow"</s:if>
						<s:else>class="evenRow"</s:else>
						onmouseover="this.className='selectedRow';" 
						onmouseout="mymouseout('ghl<s:property value='#ghdl.index'/>',
						<s:if test='#ghdl.odd'>'oddRow'</s:if>
						<s:else>'evenRow'</s:else>);" 
						>
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
						<td><s:property value="Sector_SL"/></td>
						<td><s:property value="Reporting_Manager"/></td>
						<td><s:property value="Intern_OBD"/></td>
						<td><s:property value="Offer_OBD_Plan"/></td>
						<td><s:property value="Offer_OBD_Actual"/></td>
					</tr>
				</s:iterator>
				</s:if>
			</table>
			<div id="operate" style="margin-top:50px;margin-left:750px">
				<input id="btnClose" type="button" value="¹Ø ±Õ" onClick="custom_close()"
					class="close" onmouseover="this.className='closeover';" onmouseout="this.className='close';"/>
			</div>
		</div>
	</div>	
	</body>
</html>