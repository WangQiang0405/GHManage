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
			<div id="topop">
				<s:form action="GhBookList" >
				<s:select name="projecName" label="%{getText(\"projectList.xuanzhexiangmu\")}" labelposition="left" 
				list="ProjectList" listKey="key" listValue="value" onchange="this.form.submit();"/>
				</s:form>
											
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
				<span class="totalpage"><s:text name="impleeyList.gong"/><s:property value="maxPageNo"/><s:text name="impleeyList.ye"/></span>
				<span class="totalnum"><s:property value="totalMessage"/><s:text name="impleeyList.jilushu"/></span>
				</div>
				<div class="pageop">
				<s:form action="GhBookList" >
				<s:select name="pageSpan" id="pagespan"
				list="spanList" listKey="key" listValue="value" onchange="this.form.submit();"/>
				</s:form>
				<span class="shangye">
					<s:if test="currenPageNo>1">
						<a href="GhBookList.action?currenPageNo=<s:property value="currenPageNo-1"/>"><s:text name="impleeyList.shangye"/></a>
					</s:if>
				</span>
				<span class="xiaye">
				<s:if test="currenPageNo<maxPageNo">
					<a href="GhBookList.action?currenPageNo=<s:property value="currenPageNo+1"/>"><s:text name="impleeyList.xiaye"/></a>
				</s:if>
				</span>
				
				<s:form action="GhBookList" theme="simple" >
					<span class="tiaozhuan"><s:text name="impleeyList.tiaozhuan"/></span>
					<s:textfield id="tiaoye" name="currenPageNo" cssClass="yefield" onmouseover="this.select();"/><span class="ye"><s:text name="impleeyList.ye"/></span>
					<s:submit value="%{getText(\"impleeyList.queding\")}" cssClass="queding" 
					onmouseover="this.className='quedingover';" onmouseout="this.className='queding';"/>
					
				</s:form>
				
				</div>
			
				</div>	
				
			</s:if>
			<s:else>
				<p>û����Ӧ��ѧ����Ϣ</p>
			</s:else>
		</div>
	</div>	
	</body>
</html>