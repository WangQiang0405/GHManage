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
		function mymouseout(id,styleclass) {
			document.getElementById(id).className=styleclass;
		}
		function trim(obj){ //删除左右两端的空格
			var objValue = obj.value;
			obj.value = objValue.replace(/(^\s*)|(\s*$)/g, "");
		}
		function toUpperCase(obj) {
			var objValue = obj.value;
			obj.value = objValue.toUpperCase();
		}
		function submitOnSearch() {
		    var obj = document.getElementById("eventid");
		    obj.value = "1";
		}
		function submitFrom() {
		    var obj = document.getElementById("currenPageNo");
		    obj.value = 1;
		}
		function submitPrevious() {
		    var obj = document.getElementById("currenPageNo");
		    obj.value = parseInt(obj.value) - 1;
		}
		function submitNext() {
		    var obj = document.getElementById("currenPageNo");
		    obj.value = parseInt(obj.value) + 1;
		}
		function submitTo() {
		    var obj = document.getElementById("currenPageNo");
		    var objMax = document.getElementById("maxPageNo");
		    obj.value = objMax.value;
		}
		function submitOnPagespan(){
		    var varSearch = document.getElementById("submit");					
		    varSearch.click();
		}
		function submitOnOk() {
		    var obj = document.getElementById("eventid");
		    obj.value = "3";
		}
		function onClickTr(id,index) {
		    var curTable = document.getElementById("ghl" + index).parentNode;
		    for(var i = 1; i < curTable.children.length; i++) {
				if((i-1) % 2 == 1){    //奇数
				    curTable.children[i].style.background = "#e3fcff";
				} else {    //偶数
			  		curTable.children[i].style.background = "#f4f4f4";
			    }
			 }
		    document.getElementById("ghl" + index).style.background = "yellow";
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
		<s:hidden id="eventid" name="eventid" value="0"/>
		<s:hidden id="list" name="list"/>
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
								<td class="oddRow"><s:property value="pjname"/></td>
								<td class="updatedRow"><s:select name="projecName"  
									list="ProjectList" listKey="key" listValue="value" style="width:150px"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.offerStatus"/></td>
								<td class="oddRow"><s:property value="offerStatus"/></td>
								<td class="updatedRow"><s:select name="selOfferStatus"
									list="selOfferStatusList" listKey="key" listValue="value" style="width:150px"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.offerWaitingReason"/></td>
								<td class="oddRow"><s:property value="offerWaitingReason"/></td>
								<td class="updatedRow"><s:select name="selOfferWaitingReason"  
									list="selOfferWaitingReasonList" listKey="key" listValue="value" style="width:150px"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.internFlag"/></td>
								<td class="oddRow"><s:property value="internFlag"/></td>
								<td class="updatedRow"><s:select name="selInternFlag"
									list="selInternFlagList" listKey="key" listValue="value" style="width:150px"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.internOBD"/></td>
								<td class="oddRow"><s:property value="internOBD"/></td>
								<td class="updatedRow"><s:textfield id="selInternOBD" name="selInternOBD" cssClass="inschool" onblur="trim(this)"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.offerOBDPlan"/></td>
								<td class="oddRow"><s:property value="offerOBDPlan"/></td>
								<td class="updatedRow"><s:textfield id="selOfferOBDPlan" name="selOfferOBDPlan" cssClass="inschool" onblur="trim(this)"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.offerOBDActual"/></td>
								<td class="oddRow"><s:property value="offerOBDActual"/></td>
								<td class="updatedRow"><s:textfield id="selOfferOBDActual" name="selOfferOBDActual" cssClass="inschool" onblur="trim(this)"/></td>
							</tr>
						</table>
					</s:iterator>
				</s:if>
			</div>
			<div id="buttomop">
			</div>
			<div id="foot" style="margin-top:20px">
				<div style="float:left;margin-left:33%">
					<input type="button" id="return" name="return" value="返 回"
						class="footsearch" onmouseover="this.className='footsearchover';" onmouseout="this.className='footsearch';" 
						onclick="update()" />
			    </div>
			    <div style="float:right;margin-right:33%">
					<input type="button" id="update" name="update" value="修 正"
						class="footsearch" onmouseover="this.className='footsearchover';" onmouseout="this.className='footsearch';" 
						onclick="update()" />
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
			if (result != "") {
			    if (result.indexOf("MSG01S01") >= 0
				    || result.indexOf("MSG01S02") >= 0
				    || result.indexOf("MSG01S03") >= 0) { 
					showErrMsg(result, "wsid");
			    } else if (result.indexOf("MSG01S04") >= 0) {
					showErrMsg(result, "name");
				} else if (result.indexOf("MSG01S05") >= 0) {
				    showErrMsg(result, "school");
				} else if (result.indexOf("MSG01S06") >= 0) {
				    showErrMsg(result, "type");
				}
			}
		</script>
	</body>
</html>