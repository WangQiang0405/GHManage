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
		function mtupdate(mtid,index){
			var obj1 = document.getElementById("mtid");
		    obj1.value = mtid;
		    var obj2 = document.getElementById("updateflg");
		    obj2.value = "init";
		    
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
			if (document.getElementById("mtid").value == 0){
		    	var obj2 = document.getElementById("updateflg");
			    obj2.value = "null";
			    alert("请选择更新目标数据！");
		    }
		    else{
		    	var obj = document.getElementById("eventid");
			    obj.value = "4";
		    	window.location.href="GhUpdate.action?id="+document.getElementById("mtid");
		    }
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
		<s:form action="GhSearchList" theme="simple" method="post" onsubmit="" validate="true">
		<s:hidden id="eventid" name="eventid" value="0"/>
		<s:hidden id="mtid" name="mtid" value="0"/>
		<s:hidden id="updateflg" name="updateflg" value="init"/>
		<s:hidden id="result" name="result" cssStyle="display:none"/>
		<div id="maincontent">
			<div id="operate">
				<H3><img src="images/h3.gif"><s:text name="search.condition"/></H3>
				<table border="0px" bgcolor="" cellspacing="0" align="center" width="100%">
					<tr>
						<td width="5%">
						<s:text name="项目"/>
						</td>
						<td width="10%">
							<s:select name="projecName"  
								list="ProjectList" listKey="key" listValue="value"/>
						</td>
						<td colspan="4"></td>
					</tr>
					<tr>
						<td width="5%">姓名</td>
						<td width="10%"><input type="text" id="name" name="Ghname" value="${Ghname}" cssClass="inname" onblur="trim(this)"/></td>
						<td width="3%"></td>
						<td width="5%"><s:text name="状态"/></td>
						<td ><s:select name="OfferStatus"
								list="#{'0':'全部','1':'发送','2':'接受','3':'拒绝','4':'等待'}"/></td>
						<td width="5%" rowspan="2"><s:submit id="submit" value="%{getText(\"botton.search\")}"
							cssClass="search" onmouseover="this.className='searchover';" onmouseout="this.className='search';" onclick="submitOnSearch()"/></td>
					</tr>
					<tr></tr>
				</table>
				<s:textfield id="result" name="result" cssStyle="display:none"/>
			</div>
			<H3 style="margin-top:20px"><img src="images/h3.gif"><s:text name="search.result"/></H3>
			<table  style="border-width:thin thin thin thin" border="0" bgcolor="#e2e2e2" cellspacing="1" align="center" width="100%">
				<tr class="headerClass" style="boder-bottom-width:1px">
					<th width="14%">项目</th>
					<th width="12%">姓名</th>
					<th width="6%">性别</th>
					<th width="19%">毕业学校</th>
					<th width="29%">专业</th>
					<th width="10%">语言</th>
					<th width="10%">状态</th>
				</tr>
				<s:if test="GhSearchList.size()!=0">
					<s:iterator id="List" value="GhSearchList" status="ghl">
						<tr id="ghl<s:property value="#ghl.index"/>" 
							<s:if test="#ghl.odd">class="oddRow"</s:if>
							<s:else>class="evenRow"</s:else>
								onmouseover="this.className='selectedRow';" 
								onmouseout="mymouseout('ghl<s:property value='#ghl.index'/>',
							<s:if test='#ghl.odd'>'oddRow'</s:if>
							<s:else>'evenRow'</s:else>);"
								onclick="mtupdate('<s:property value="id"/>',
								'<s:property value='#ghl.index'/>')"
									style="boder-bottom-width:1px"
							>
							<td><s:property value="pjname"/></td>
							<td><s:property value="name"/></td>
							<td><s:property value="sex"/></td>	
							<td><s:property value="school"/></td>	
							<td><s:property value="major"/></td>	
							<td><s:property value="language"/></td>	
							<td><s:property value="offerStatus"/></td>					
						</tr>
					</s:iterator>
				</s:if>
			</table>
			<div id="buttomop">
				<div class="info">
					<span class="totalMessage"><s:text name="impleeyList.gong"/><s:property value="maxPageNo"/>
					<s:hidden id="maxPageNo" name="maxPageNo"/><s:text name="impleeyList.ye"/></span>
					<span class="totalnum"><s:property value="totalMessage"/><s:text name="impleeyList.jilushu"/></span>
				</div>
				<div class="pageop">
					<s:select name="pageSpan" id="pagespan"
						list="spanList" listKey="key" listValue="value" onchange="submitOnPagespan();"/>
					<span class="shangye">
						<s:if test="GhSearchList.size()!=0&&currenPageNo==1">
							<s:submit id="submit" value=" "
								cssClass="from" onmouseout="this.className='from';"/>
							<s:submit id="submit" value=" "
								cssClass="previous" onmouseout="this.className='previous';"/>
							<!-- <a href="GhInfoListExe.action?currenPageNo=<s:property value="currenPageNo-1"/>&eventid='2'"><s:text name="impleeyList.shangye"/></a> -->
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
					<s:submit value="%{getText(\"impleeyList.queding\")}" cssClass="queding" 
						onmouseover="this.className='quedingover';" onmouseout="this.className='queding';" onclick="submitOnOk()"/>
				</div>
			</div>
			<div id="foot" style="margin-top:20px">
				<table border="0" align="center" >
					<tr>
						<td ><s:submit value="%{getText(\"botton.execute\")}"
								cssClass="footsearch" onmouseover="this.className='footsearchover';" onmouseout="this.className='footsearch';" 
								onclick="update()" />
						</td>
					</tr>
				</table>
			</div>
		</div>
		</s:form>
	</div>	
		<script language="JavaScript">
			var result=document.getElementById("result").value;
			if(result!="")
			{	
				alert(result);
			}
		</script>
	</body>
</html>
