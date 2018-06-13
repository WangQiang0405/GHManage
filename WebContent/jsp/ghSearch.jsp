<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<title><s:text name="search.title"/></title>
		<link href="css/style.css" rel="stylesheet" type="text/css"/>
		<link href="css/mainstyle.css" rel="stylesheet" type="text/css"/>
		<link href="css/commonStyle.css" rel="stylesheet" type="text/css"/>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">  
		<script language="JavaScript" src="js/manage.js"></script>
		<script language="JavaScript">
		function mymouseout(id,styleclass)
		{
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
		    var eventid = document.getElementById("eventid");
		    eventid.value = "2";
		}
		function submitPrevious() {
		    var obj = document.getElementById("currenPageNo");
		    obj.value = parseInt(obj.value) - 1;
		    var eventid = document.getElementById("eventid");
		    eventid.value = "2";
		}
		function submitNext() {
		    var obj = document.getElementById("currenPageNo");
		    obj.value = parseInt(obj.value) + 1;
		    var eventid = document.getElementById("eventid");
		    eventid.value = "2";
		}
		function submitTo() {
		    var obj = document.getElementById("currenPageNo");
		    var objMax = document.getElementById("maxPageNo");
		    obj.value = objMax.value;
		    var eventid = document.getElementById("eventid");
		    eventid.value = "2";
		}
		function submitOnPagespan(){
		    var eventid = document.getElementById("eventid");
		    if (eventid.value != "0") {
			    var varSearch = document.getElementById("submit");					
			    varSearch.click();
		    }
		}
		function submitOnOk() {
		    var obj = document.getElementById("eventid");
		    obj.value = "3";
		}
		function trOnClick(id,index){
			var selId = document.getElementById("selectid");
			selId.value = id;
		    var selRowIndex = document.getElementById("selRowIndex");
		    selRowIndex.value = index;
		    
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
		function update(){
		    var obj = document.getElementById("eventid");
			obj.value = "4";
		}
		</script>
	</head>
	<body>
	<div id="container">
		<div id="tophead">
			<s:include value="top.jsp"/>
		</div>
		<div id="leftcontent">
			<s:include value="lefttree.jsp"/>
		</div>
		<s:form action="GhSearch" theme="simple" method="post" onsubmit="" validate="true">
		<s:hidden id="eventid" name="eventid"/>
		<s:hidden id="selectid" name="selectid"/>
		<s:hidden id="selRowIndex" name="selRowIndex"/>
		<s:hidden id="result" name="result" cssStyle="display:none"/>
		<div id="maincontent">
			<div id="operate">
				<H3 style="margin-top:10px"><img src="images/h3.gif"><s:text name="search.condition"/></H3>
				<table border="0px" bgcolor="" cellspacing="0" align="center" width="100%">
					<tr>
						<td style="font-size: 14px">
							<s:text name="field.project"/>
						</td>
						<td>
							<s:select name="projecName"  
								list="ProjectList" listKey="key" listValue="value" style="width:120px"/>
						</td>
						<td colspan="2"></td>
						<td width="45%" rowspan="2" ><s:submit id="submit" value="%{getText(\"botton.search\")}"
							cssClass="search" onmouseover="this.className='searchover';" onmouseout="this.className='search';" onclick="submitOnSearch()"/></td>
					</tr>
					<tr>
						<td style="font-size: 14px" width="6%"><s:text name="field.name"/></td>
						<td width="20%"><s:textfield id="ghname" name="ghname" cssClass="inname" onblur="trim(this)"/></td>
						<td style="font-size: 14px" width="10%"><s:text name="field.offerStatus"/></td>
						<td ><s:select name="OfferStatus"
								list="#{'0':'全部','1':'Sent','2':'Accept','3':'Decline','4':'Waiting'}"
								style="width:100px"/></td>
					</tr>
				</table>
				<s:textfield id="result" name="result" cssStyle="display:none"/>
			</div>
			<H3 style="margin-top:5px"><img src="images/h3.gif"><s:text name="search.result"/></H3>
			<table  style="border-width:thin thin thin thin" border="0" bgcolor="#e2e2e2" cellspacing="1" align="center" width="100%">
				<tr class="headerClass" style="boder-bottom-width:1px">
					<th width="14%"><s:text name="field.project"/></th>
					<th width="12%"><s:text name="field.name"/></th>
					<th width="6%"><s:text name="field.sex"/></th>
					<th width="19%"><s:text name="field.graduatedSchool"/></th>
					<th width="29%"><s:text name="field.major"/></th>
					<th width="10%"><s:text name="field.language"/></th>
					<th width="10%"><s:text name="field.offerStatus"/></th>
				</tr>
				<s:if test="ghSearchList.size()!=0">
					<s:iterator id="ghSearchList" value="ghSearchList" status="ghl">
						<tr id="ghl<s:property value="#ghl.index"/>" 
							<s:if test="#ghl.odd">class="oddRow"</s:if>
							<s:else>class="evenRow"</s:else>
								onmouseover="this.className='selectedRow';" 
								onmouseout="mymouseout('ghl<s:property value='#ghl.index'/>',
							<s:if test='#ghl.odd'>'oddRow'</s:if>
							<s:else>'evenRow'</s:else>);"
								onclick="trOnClick('<s:property value="id"/>',
								'<s:property value='#ghl.index'/>')"
							>
							<td><s:property value="pjname"/>
								<s:hidden name="ghSearchList[%{#ghl.index}].id" value="%{ghSearchList[#ghl.index].id}"/>
								<s:hidden name="ghSearchList[%{#ghl.index}].pjname" value="%{ghSearchList[#ghl.index].pjname}"/></td>
							<td><s:property value="name"/><s:hidden name="ghSearchList[%{#ghl.index}].name" value="%{ghSearchList[#ghl.index].name}"/></td>
							<td><s:property value="sex"/><s:hidden name="ghSearchList[%{#ghl.index}].sex" value="%{ghSearchList[#ghl.index].sex}"/></td>	
							<td><s:property value="school"/><s:hidden name="ghSearchList[%{#ghl.index}].school" value="%{ghSearchList[#ghl.index].school}"/></td>	
							<td><s:property value="major"/><s:hidden name="ghSearchList[%{#ghl.index}].major" value="%{ghSearchList[#ghl.index].major}"/></td>	
							<td><s:property value="language"/><s:hidden name="ghSearchList[%{#ghl.index}].language" value="%{ghSearchList[#ghl.index].language}"/></td>	
							<td><s:property value="offerStatus"/><s:hidden name="ghSearchList[%{#ghl.index}].offerStatus" value="%{ghSearchList[#ghl.index].offerStatus}"/></td>
						</tr>
					</s:iterator>
				</s:if>
			</table>
			<div id="buttomop">
				<div class="info">
					<span class="totalMessage"><s:text name="impleeyList.gong"/><s:property value="maxPageNo"/>
					<s:hidden id="maxPageNo" name="maxPageNo"/><s:text name="impleeyList.ye"/></span>
					<span class="totalnum"><s:property value="totalMessage"/><s:hidden id="totalMessage" name="totalMessage"/><s:text name="impleeyList.jilushu"/></span>
				</div>
				<div class="pageop">
					<s:select name="pageSpan" id="pagespan"
						list="spanList" listKey="key" listValue="value" onchange="submitOnPagespan();"/>
					<span class="shangye">
						<s:if test="eventid==0||ghSearchList.size()==0">
							<s:submit id="submit" value=" "
								cssClass="from" onmouseout="this.className='from';"/>
							<s:submit id="submit" value=" "
								cssClass="previous" onmouseout="this.className='previous';"/>
							<s:submit id="submit" value=" "
								cssClass="next" onmouseout="this.className='next';"/>
							<s:submit id="submit" value=" "
								cssClass="to" onmouseout="this.className='to';"/>
						</s:if>
						<s:if test="ghSearchList.size()!=0&&currenPageNo==1">
							<s:submit id="submit" value=" "
								cssClass="from" onmouseout="this.className='from';"/>
							<s:submit id="submit" value=" "
								cssClass="previous" onmouseout="this.className='previous';"/>
						</s:if>
						<s:if test="currenPageNo>1">
							<s:submit id="submit" value=" " onclick="submitFrom()"
								cssClass="from" onmouseover="this.className='fromover';" onmouseout="this.className='from';"/>
							<s:submit id="submit" value=" " onclick="submitPrevious()"
								cssClass="previous" onmouseover="this.className='previousover';" onmouseout="this.className='previous';"/>
							<!-- <a href="GhInfoListExe.action?currenPageNo=<s:property value="currenPageNo-1"/>&eventid='2'"><s:text name="impleeyList.shangye"/></a> -->
						</s:if>
						<s:if test="currenPageNo<maxPageNo">
							<s:submit id="submit" value=" " onclick="submitNext()"
								cssClass="next" onmouseover="this.className='nextover';" onmouseout="this.className='next';"/>
							<s:submit id="submit" value=" " onclick="submitTo()"
								cssClass="to" onmouseover="this.className='toover';" onmouseout="this.className='to';"/>
							<!-- <a href="GhInfoListExe.action?currenPageNo=<s:property value="currenPageNo+1"/>&eventid='2'"><s:text name="impleeyList.xiaye"/></a>-->
						</s:if>
						<s:if test="currenPageNo==maxPageNo">
							<s:submit id="submit" value=" "
								cssClass="next" onmouseout="this.className='next';"/>
							<s:submit id="submit" value=" "
								cssClass="to" onmouseout="this.className='to';"/>
							<!-- <a href="GhInfoListExe.action?currenPageNo=<s:property value="currenPageNo+1"/>&eventid='2'"><s:text name="impleeyList.xiaye"/></a>-->
						</s:if>
					</span>
					<span class="tiaozhuan"><s:text name="impleeyList.tiaozhuan"/></span>
					<s:textfield id="currenPageNo" name="currenPageNo" cssClass="yefield" onmouseover="this.select();"/>
					<span class="ye"><s:text name="impleeyList.ye"/></span>
					<s:if test="eventid==0">
						<s:submit value="%{getText(\"impleeyList.queding\")}" cssClass="queding" 
							onmouseover="this.className='quedingover';" onmouseout="this.className='queding';" onclick="return false;"/>
					</s:if>
					<s:else>
						<s:submit value="%{getText(\"impleeyList.queding\")}" cssClass="queding" 
							onmouseover="this.className='quedingover';" onmouseout="this.className='queding';" onclick="submitOnOk()"/>
					</s:else>
				</div>
			</div>
			<div id="foot" style="margin-top:20px">
				<table border="0" align="center" >
					<tr>
						<td ><s:submit id="execute" name="execute" value="决 定"
								class="footsearch" onmouseover="this.className='footsearchover';" onmouseout="this.className='footsearch';" 
								onclick="update()" />
						</td>
					</tr>
				</table>
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
	        if(errMsg!="")
	        {//若有错误消息则调用错误信息气球显示
	           showErrMsg(errMsg,"submit");                   
	        }
			var result=document.getElementById("result").value;
			if(result!="")
			{	
				showErrMsg(result,"execute");
			}
			function InitFromBack(){
			    var obj = document.getElementById("eventid");
				if (obj.value == "5") {
					var selRowIndex = document.getElementById("selRowIndex");
					document.getElementById("ghl" + selRowIndex.value).style.background = "yellow";
				}
			}
			window.body.onload = InitFromBack(); 
		</script>
	</body>
</html>
