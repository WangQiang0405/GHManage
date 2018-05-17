<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<title><s:text name="mainmanage.title"/></title>
		<link href="images/style.css" rel="stylesheet" type="text/css"/>
		<link href="images/mainstyle.css" rel="stylesheet" type="text/css"/>
		<link href="images/impliststyle.css" rel="stylesheet" type="text/css"/>
		<script language="JavaScript" src="images/manage.js"></script>
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
		    //alert(index);
		    
		    var curTable = document.getElementById("ghl" + index).parentNode;
			//document.getElementById("mytable").children[0];
		    for(var i = 1; i < curTable.children.length; i++) {
			//alert(i);
				if((i-1) % 2 == 1){    //奇数
				    //curTable.children[i].className='evenRow';
				    curTable.children[i].style.background = "#e3fcff";
				} else {    //偶数
			  		//curTable.children[i].className='oddRow';
			  		curTable.children[i].style.background = "#f4f4f4";
			    }
			 }
		    document.getElementById("ghl" + index).style.background = "yellow";
		    //var obj = document.getElementById("ghl" + index);
		    //alert(obj.cells[0].innerText);
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
		<s:form action="GhInfoListExe" theme="simple" method="post" onsubmit="" validate="true">
		<s:hidden id="eventid" name="eventid" value="0"/>
		<div id="maincontent">
			<div id="operate">
					<H3><img src="images/h3.gif"><s:text name="search.condition"/></H3>
					<table border="0px" bgcolor="" cellspacing="0" align="center" width="770px">
						<tr>
							<td>网申编号</td>
							<td><s:textfield id="wsid" name="wsid" cssClass="inid" onblur="trim(this);toUpperCase(this)"/></td>
							<td colspan="3"></td>
						</tr>
						<tr>
							<td>姓名</td>
							<td><s:textfield id="name" name="name" cssClass="inname" onblur="trim(this)"/></td>
							<td colspan="2"></td>
							<td width="180px" rowspan="2"><s:submit id="submit" value="%{getText(\"search.execute\")}"
							cssClass="search" onmouseover="this.className='searchover';" onmouseout="this.className='search';" onclick="submitOnSearch()"/></td>
							
						</tr>
						<tr>
							<td width="72px">毕业学校</td>
							<td width="200px"><s:textfield id="school" name="school" cssClass="inschool" onblur="trim(this)"/></td>
							<td width="38px">专业</td>
							<td width="240px"><s:textfield id="type" name="type" cssClass="intype" onblur="trim(this)"/></td>
						</tr>
					</table>
					<s:textfield id="result" name="result" cssStyle="display:none"/>
				
			</div>
			<H3 style="margin-top:10px"><img src="images/h3.gif"><s:text name="search.result"/></H3>
			<table id = "mytable" border="1" bgcolor="#e2e2e2" cellspacing="1" align="center" width="770px">
				<tr class="headerClass">
					<th>网申编号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>毕业学校</th>
					<th>专业</th>
					<th>毕业时间</th>
					<th>备注</th>
				</tr>
			<s:if test="ghInfoList.size()!=0">
				<s:iterator id="List" value="ghInfoList" status="ghl">
					<tr id="ghl<s:property value="#ghl.index"/>"
					onclick="onClickTr('<s:property value="wangShenId"/>','<s:property value="#ghl.index"/>')"
						<s:if test="#ghl.odd">class="oddRow"</s:if>
						<s:else>class="evenRow"</s:else>
						onmouseover="this.className='selectedRow';" 
						onmouseout="mymouseout('ghl<s:property value='#ghl.index'/>',
						<s:if test='#ghl.odd'>'oddRow'</s:if>
						<s:else>'evenRow'</s:else>);" 
						>
						<td><s:property value="wangShenId"/></td>
						<td><s:property value="studentName"/></td>
						<td><s:property value="xb"/></td>
						<td><s:property value="byxx"/></td>
						<td><s:property value="zhuanye"/></td>
						<td><s:property value="bysj"/></td>
						<td><s:property value="coments"/></td>
					</tr>
				</s:iterator>
			</s:if>
			</table>
			<div id="buttomop">
				<div class="info">
					<span class="totalpage"><s:text name="impleeyList.gong"/><s:property value="maxPageNo"/><s:hidden id="maxPageNo" name="maxPageNo"/><s:text name="impleeyList.ye"/></span>
					<span class="totalnum"><s:property value="totalCount"/><s:text name="impleeyList.jilushu"/></span>
				</div>
				<div class="pageop">
					
					<s:select name="pageSpan" id="pagespan"
					list="spanList" listKey="key" listValue="value" onchange="submitOnPagespan();"/>
					
					<span class="shangye">
						<s:if test="ghInfoList.size()!=0&&currenPageNo==1">
							<s:submit id="submit" value=""
							cssClass="from" onmouseout="this.className='from';"/>
							<s:submit id="submit" value=""
							cssClass="previous" onmouseout="this.className='previous';"/>
							<!-- <a href="GhInfoListExe.action?currenPageNo=<s:property value="currenPageNo-1"/>&eventid='2'"><s:text name="impleeyList.shangye"/></a> -->
						</s:if>
						<s:if test="currenPageNo>1">
							<s:submit id="submit" value="" onclick="submitFrom()"
							cssClass="from" onmouseover="this.className='fromover';" onmouseout="this.className='from';"/>
							<s:submit id="submit" value="" onclick="submitPrevious()"
							cssClass="previous" onmouseover="this.className='previousover';" onmouseout="this.className='previous';"/>
							<!-- <a href="GhInfoListExe.action?currenPageNo=<s:property value="currenPageNo-1"/>&eventid='2'"><s:text name="impleeyList.shangye"/></a> -->
						</s:if>
						<s:if test="currenPageNo<maxPageNo">
							<s:submit id="submit" value="" onclick="submitNext()"
							cssClass="next" onmouseover="this.className='nextover';" onmouseout="this.className='next';"/>
							<s:submit id="submit" value="" onclick="submitTo()"
							cssClass="to" onmouseover="this.className='toover';" onmouseout="this.className='to';"/>
							<!-- <a href="GhInfoListExe.action?currenPageNo=<s:property value="currenPageNo+1"/>&eventid='2'"><s:text name="impleeyList.xiaye"/></a>-->
						</s:if>
						<s:if test="currenPageNo==maxPageNo">
							<s:submit id="submit" value=""
							cssClass="next" onmouseout="this.className='next';"/>
							<s:submit id="submit" value=""
							cssClass="to" onmouseout="this.className='to';"/>
							<!-- <a href="GhInfoListExe.action?currenPageNo=<s:property value="currenPageNo+1"/>&eventid='2'"><s:text name="impleeyList.xiaye"/></a>-->
						</s:if>
					</span>
				
					<span class="tiaozhuan"><s:text name="impleeyList.tiaozhuan"/></span>
					<s:textfield id="currenPageNo" name="currenPageNo" cssClass="yefield" onmouseover="this.select();"/><span class="ye"><s:text name="impleeyList.ye"/></span>
					<s:submit value="%{getText(\"impleeyList.queding\")}" cssClass="queding" 
					onmouseover="this.className='quedingover';" onmouseout="this.className='queding';" onclick="submitOnOk()"/>
					
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