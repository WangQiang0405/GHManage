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
		function trim(obj){ //ɾ���������˵Ŀո�
			var objValue = obj.value;
			obj.value = objValue.replace(/(^\s*)|(\s*$)/g, "");
		}
		function toUpperCase(obj) {
			var objValue = obj.value;
			obj.value = objValue.toUpperCase();
		}
		function update() {
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
				if((i-1) % 2 == 1){    //����
				    curTable.children[i].style.background = "#e3fcff";
				} else {    //ż��
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
		<s:hidden id="id" name="id"/>
		<s:hidden id="list" name="list"/>
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
								<td class="oddRow"><s:property value="pjname"/><s:hidden id="pjname" name="pjname"/></td>
								<td class="updatedRow"><s:select name="projecName"  
									list="ProjectList" listKey="key" listValue="value" style="width:150px"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.offerStatus"/></td>
								<td class="oddRow"><s:property value="offerStatus"/><s:hidden id="offerStatus" name="offerStatus"/></td>
								<td class="updatedRow"><s:select name="selOfferStatus"
									list="selOfferStatusList" listKey="key" listValue="value" style="width:150px"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.offerWaitingReason"/></td>
								<td class="oddRow"><s:property value="offerWaitingReason"/><s:hidden id="offerWaitingReason" name="offerWaitingReason"/></td>
								<td class="updatedRow"><s:select name="selOfferWaitingReason"  
									list="selOfferWaitingReasonList" listKey="key" listValue="value" style="width:150px"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.internFlag"/></td>
								<td class="oddRow"><s:property value="internFlag"/><s:hidden id="internFlag" name="internFlag"/></td>
								<td class="updatedRow"><s:select name="selInternFlag"
									list="selInternFlagList" listKey="key" listValue="value" style="width:150px"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.internOBD"/></td>
								<td class="oddRow"><s:property value="internOBD"/><s:hidden id="internOBD" name="internOBD"/></td>
								<td class="updatedRow"><s:textfield id="selInternOBD" name="selInternOBD" cssClass="indate" onblur="trim(this)"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.offerOBDPlan"/></td>
								<td class="oddRow"><s:property value="offerOBDPlan"/><s:hidden id="offerOBDPlan" name="offerOBDPlan"/></td>
								<td class="updatedRow"><s:textfield id="selOfferOBDPlan" name="selOfferOBDPlan" cssClass="indate" onblur="trim(this)"/></td>
							</tr>
							<tr>
								<td class="evenRow"><s:text name="field.offerOBDActual"/></td>
								<td class="oddRow"><s:property value="offerOBDActual"/><s:hidden id="offerOBDActual" name="offerOBDActual"/></td>
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
			    	<s:submit id="back" value="%{getText(\"botton.back\")}"
						cssClass="footsearch" onmouseover="this.className='footsearchover';" onmouseout="this.className='footsearch';" onclick=""/>
			    </div>
			    <div style="float:right;margin-right:33%">
			    	<s:submit id="update" value="%{getText(\"botton.update\")}"
						cssClass="footsearch" onmouseover="this.className='footsearchover';" onmouseout="this.className='footsearch';" onclick="update()"/>
			    </div>
			</div>
		</div>
		</s:form>
	</div>
	  <!-- ����������ʾ��ʾ��Ϣ������ -->
	  <table id="myerr" width="120" border="0" class="jd" bgcolor="black" cellspacing="1" onclick="hideErr();">
		  <tr bgcolor="white"  valign="bottom">
		    	<td id="myerrs" align="left"></td>
		  </tr>
	  </table>
	  <!-- ������Ϣ����ļ�� -->
	  <img class="jd" src="images/up.gif" id="myup" onclick="hideErr();" /> 	  
	  <!-- ������Ϣ�����𽥳��ֵ��ڵ��� -->
	  <span id="errzd" style="z-index:2;visibility:hidden;position:absolute;left:20;top:30;font-size:1px;background-color:white"/>	
		
		<script language="JavaScript">
			errMsg="<s:fielderror template="myfielderror"/>";
			if (errMsg != "") {//���д�����Ϣ����ô�����Ϣ������ʾ
				showErrMsg(errMsg, "submit");
			}
			var result = document.getElementById("result").value;
			if (result == "success") {
			    alert("���³ɹ���");
			} else if (result == "fail") {
			    alert("����ʧ�ܣ�");
			} else if (result != "") {
				showErrMsg(result, "update");
			}
		</script>
	</body>
</html>