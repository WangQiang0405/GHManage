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
		function submitFrom() {
		    var obj = document.getElementById("eventid");
		    obj.value = "2";
		    var obj = document.getElementById("currenPageNo");
		    obj.value = 1;
		}
		function submitPrevious() {
		    var obj = document.getElementById("eventid");
		    obj.value = "2";
		    var obj = document.getElementById("currenPageNo");
		    obj.value = parseInt(obj.value) - 1;
		}
		function submitNext() {
		    var obj = document.getElementById("eventid");
		    obj.value = "2";
		    var obj = document.getElementById("currenPageNo");
		    obj.value = parseInt(obj.value) + 1;
		}
		function submitTo() {
		    var obj = document.getElementById("eventid");
		    obj.value = "2";
		    var obj = document.getElementById("currenPageNo");
		    var objMax = document.getElementById("maxPageNo");
		    obj.value = objMax.value;
		}
		function submitOnOk() {
		    var obj = document.getElementById("eventid");
		    obj.value = "3";
		}
		function submitOnChange(){
		    var objEvent = document.getElementById("eventid");
		    objEvent.value = "1";
		    var objClick = document.getElementById("submitSearch");
		    objClick.click();
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
		<s:form id="bookList"  name="bookListName" action="GhBookList" theme="simple" method="post" onsubmit="" validate="true">
		<s:hidden id="eventid" name="eventid" value="0"/>
		<div id="maincontent">
			<div id="topop">
				<H3><img src="images/h3.gif"><s:text name="projectList.xuanzhexiangmu"/>&nbsp;<s:select name="projecName" label="%{getText(\"projectList.xuanzhexiangmu\")}" labelposition="left" 
				list="ProjectList" listKey="key" listValue="value" onchange="submitOnChange();"/></H3>
			</div>
			<s:if test="GhBookList.size()!=0">
				<table border="1" bgcolor="#e2e2e2" cellspacing="1" align="center" width="770px">
					<tr class="headerClass">
						<th>������</th>
						<th>ѧ������</th>
						<th>����״̬</th>
						<th>Offer״̬</th>
						<th>Ԥ����Ŀ</th>
						<th>�Ƿ�ʵϰ</th>
						<th>���澭��</th>
						<th>Offer��ְ����</th>
						<th>��ϸ��Ϣ</th>
					</tr>
					<s:iterator id="List" value="GhBookList" status="ghl">
						<tr id="ghl<s:property value="#ghl.index"/>"
							<s:if test="#ghl.odd">class="oddRow"</s:if>
							<s:else>class="evenRow"</s:else>
							onmouseover="this.className='selectedRow';" 
							onmouseout="mymouseout('ghl<s:property value='#ghl.index'/>',
							<s:if test='#ghl.odd'>'oddRow'</s:if>
							<s:else>'evenRow'</s:else>);" 
							>
							<td><s:property value="wangShenId"/></td>
							<td><s:property value="studentName"/></td>
							<td><s:property value="chuLiSts"/></td>
							<td><s:property value="offerSts"/></td>
							<td><s:property value="bookByPj"/></td>
							<td><s:property value="ghOrInt"/></td>
							<td><s:property value="repMgr"/></td>
							<td><s:property value="offerOnboardRq"/></td>
							<td><a href="javascript:void(0);" onclick="windowpopup(this)">�鿴</a></td>
						</tr>
					</s:iterator>
				</table>
				
				<div id="buttomop">
				<div class="info">
				<span style="margin-left:5px;" class="totalpage"><s:text name="impleeyList.gong"/><s:property value="maxPageNo"/><s:hidden id="maxPageNo" name="maxPageNo"/><s:text name="impleeyList.ye"/></span>
				<span class="totalnum"><s:property value="totalMessage"/><s:text name="impleeyList.jilushu"/></span>
				</div>
				<div class="pageop">
				<s:select name="pageSpan" id="pagespan"
				list="spanList" listKey="key" listValue="value" onchange="submitOnChange();"/>
				<span class="shangye">
					<s:if test="GhBookList.size()!=0&&currenPageNo==1">
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
				<s:textfield id="currenPageNo" name="currenPageNo" cssClass="yefield" onmouseover="this.select();"/><span class="ye"><s:text name="impleeyList.ye"/></span>
				<s:submit value="%{getText(\"impleeyList.queding\")}" cssClass="queding" 
				onmouseover="this.className='quedingover';" onmouseout="this.className='queding';"/>
				<s:submit id="submitSearch" value="%{getText(\"botton.hidden\")}" cssClass="btnHidden" style="display:none"/>
				
				</div>
				</div>	
				
			</s:if>
			<s:else>
				<p>û����Ӧ��ѧ����Ϣ</p>
			</s:else>
		</div>
	</s:form>
	</div>	
	</body>
</html>