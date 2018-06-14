<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<title><s:text name="update.title"/></title>
		<link href="css/style.css" rel="stylesheet" type="text/css"/>
		<link href="css/mainstyle.css" rel="stylesheet" type="text/css"/>
		<link href="css/commonStyle.css" rel="stylesheet" type="text/css"/>
		<script language="JavaScript" src="js/manage.js"></script>
		<script language="JavaScript">
		function mymouseout(id,styleclass) {
			document.getElementById(id).className=styleclass;
		}
		function trim(obj){ //删除左右两端的空格
			var objValue = obj.value;
			obj.value = objValue.replace(/(^\s*)|(\s*$)/g, "");
		}
		function updateExe() {
		    var obj = document.getElementById("eventid");
		    obj.value = "1";
		}
		function backExe(){
	    	var obj = document.getElementById("eventid");
		    obj.value = "5";
	    	window.location.href="GhSearch.action?eventid=" + document.getElementById("eventid").value;
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
		<s:form action="GhUpdate" theme="simple" method="post" onsubmit="" validate="true">
		<s:hidden id="id" name="id"/>
		<s:hidden id="eventid" name="eventid"/>
		<s:hidden id="result" name="result"/>
		<div id="maincontent">
			<div id="operate">
				<s:if test="List.size()!=0">
					<s:iterator id="st" value="list" status="ghl">
						<H3 style="margin-top:10px"><img src="images/h3.gif"><s:text name="label.recruitmentStateRevise"/></H3>
						<table border="0" bgcolor="#e2e2e2" cellspacing="1" align="center" width="100%">
							<tr class="headerClass">
								<th width="19%"></th>
								<th width="39%"><s:text name="field.unrevise"/></th>
								<th width="39%"><s:text name="field.revised"/></th>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.project"/></td>
								<td class="oddRow"><s:property value="pjname"/>
									<s:hidden name="list[%{#ghl.index}].pjname" value="%{list[#ghl.index].pjname}"/>
								</td>
								<td class="updatedRow"><s:select name="projecName"  
									list="ProjectList" listKey="key" listValue="value" style="width:150px"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.offerStatus"/></td>
								<td class="oddRow"><s:property value="offerStatus"/>
									<s:hidden name="list[%{#ghl.index}].offerStatus" value="%{list[#ghl.index].offerStatus}"/>
								</td>
								<td class="updatedRow"><s:select name="selOfferStatus"
									list="selOfferStatusList" listKey="key" listValue="value" style="width:150px"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.offerWaitingReason"/></td>
								<td class="oddRow"><s:property value="offerWaitingReason"/>
									<s:hidden name="list[%{#ghl.index}].offerWaitingReason" value="%{list[#ghl.index].offerWaitingReason}"/>
								</td>
								<td class="updatedRow"><s:select name="selOfferWaitingReason"  
									list="selOfferWaitingReasonList" listKey="key" listValue="value" style="width:150px"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.internFlag"/></td>
								<td class="oddRow"><s:property value="internFlag"/>
									<s:hidden name="list[%{#ghl.index}].internFlag" value="%{list[#ghl.index].internFlag}"/>
								</td>
								<td class="updatedRow"><s:select name="selInternFlag"
									list="selInternFlagList" listKey="key" listValue="value" style="width:150px"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.internOBD"/></td>
								<td class="oddRow"><s:property value="internOBD"/>
									<s:hidden name="list[%{#ghl.index}].internOBD" value="%{list[#ghl.index].internOBD}"/>
								</td>
								<td class="updatedRow"><s:textfield id="selInternOBD" name="selInternOBD" cssClass="indate" onblur="trim(this)"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.offerOBDPlan"/></td>
								<td class="oddRow"><s:property value="offerOBDPlan"/>
									<s:hidden name="list[%{#ghl.index}].offerOBDPlan" value="%{list[#ghl.index].offerOBDPlan}"/>
								</td>
								<td class="updatedRow"><s:textfield id="selOfferOBDPlan" name="selOfferOBDPlan" cssClass="indate" onblur="trim(this)"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.offerOBDActual"/></td>
								<td class="oddRow"><s:property value="offerOBDActual"/>
									<s:hidden name="list[%{#ghl.index}].offerOBDActual" value="%{list[#ghl.index].offerOBDActual}"/>
								</td>
								<td class="updatedRow"><s:textfield id="selOfferOBDActual" name="selOfferOBDActual" cssClass="indate" onblur="trim(this)"/></td>
							</tr>
						</table>
					</s:iterator>
				</s:if>
			</div>
			<div id="buttomop">
			</div>
			<div id="foot" style="margin-top:20px">
				<div style="float:left;margin-left:33%">
			    	<input type="button" id="back" value="返 回"
						class="footsearch" onmouseover="this.className='footsearchover';" onmouseout="this.className='footsearch';" onclick="backExe()"/>
			    </div>
			    <div style="float:right;margin-right:33%">
			    	<s:submit id="update" value="%{getText(\"botton.update\")}"
						class="footsearch" onmouseover="this.className='footsearchover';" onmouseout="this.className='footsearch';" onclick="updateExe()"/>
			    </div>
			</div>
		</div>
		</s:form>
	</div>
	  <!-- 用来浮动显示提示信息的容器 -->
	  <table id="myerr" width="120" border="0" class="jd" bgcolor="black" cellspacing="1" onclick="hideErr();">
		  <tr bgcolor="white"  valign="bottom">
		    	<td id="myerrs" align="left"></td>
		  </tr>
	  </table>
	  <!-- 错误信息气球的尖角 -->
	  <img class="jd" src="images/up.gif" id="myup" onclick="hideErr();" /> 	  
	  <!-- 错误信息气球逐渐出现的遮挡物 -->
	  <span id="errzd" style="z-index:2;visibility:hidden;position:absolute;left:20;top:30;font-size:1px;background-color:white"/>	
		
		<script language="JavaScript">
			errMsg="<s:fielderror template="myfielderror"/>";
			if (errMsg != "") {//若有错误消息则调用错误信息气球显示
				showErrMsg(errMsg, "submit");
			}
			var result = document.getElementById("result").value;
			if (result == "success") {
			    alert("更新成功。");
			} else if (result == "fail") {
			    alert("更新失败！");
			} else if (result != "") {
				showErrMsg(result, "update");
			}
		</script>
	</body>
</html>