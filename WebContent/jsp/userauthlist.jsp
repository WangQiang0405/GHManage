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
		</script>
	</head>
	<body>
	<div id="container">
		<div id="tophead">
			<s:include value="/jsp/top.jsp"/>
		</div>
		<div id="leftcontent">
			<s:include value="/jsp/lefttreemanage.jsp"/>
		</div>
		<div id="maincontent">
			<div id="topop">
				<s:form action="UserAuthList" >
				<s:select name="userId" label="%{getText(\"userIdList.xuanzheyonghu\")}" labelposition="left" 
				list="UserList" listKey="key" listValue="value" onchange="this.form.submit();"/>
				</s:form>
											
			</div>
			<s:if test="UserInfoList.size()!=0">
				
				<table border="1" bgcolor="#e2e2e2" cellspacing="1" align="center" width="770px">
				
					<tr class="headerClass">
						<th>用户名</th>
						<th>用户密码</th>
						<th>权限级别</th>
						<th>修改用户信息</th>
						<th>删除用户信息</th>						
					</tr>
					<s:iterator id="List" value="UserInfoList" status="ul">
						<tr id="ul<s:property value="#ul.index"/>"
							<s:if test="#ul.odd">class="oddRow"</s:if>
							<s:else>class="evenRow"</s:else>
							onmouseover="this.className='selectedRow';" 
							onmouseout="mymouseout('ul<s:property value='#ul.index'/>',
							<s:if test='#ul.odd'>'oddRow'</s:if>
							<s:else>'evenRow'</s:else>);" >
							<td><s:property value="userId"/></td>
							<td><s:property value="userPwd"/></td>
							<td><s:property value="userType"/></td>
							<td><a href="ghDetail.action?wsId=<s:property value="wsId"/>" target="_blank">修改</a></td>
							<td><a href="ghDetail.action?wsId=<s:property value="wsId"/>" target="_blank">删除</a></td>
						</tr>
					</s:iterator>
				</table>
				
				<div id="buttomop">
				<div class="info">
				<span class="totalpage"><s:text name="impleeyList.gong"/><s:property value="maxPageNo"/><s:text name="impleeyList.ye"/></span>
				<span class="totalnum"><s:property value="totalMessage"/><s:text name="impleeyList.jilushu"/></span>
				</div>
				<div class="pageop">
				<s:form action="UserAuthList" >
				<s:select name="pageSpan" id="pagespan"
				list="spanList" listKey="key" listValue="value" onchange="this.form.submit();"/>
				</s:form>
				<span class="shangye">
					<s:if test="currenPageNo>1">
						<a href="UserAuthList.action?currenPageNo=<s:property value="currenPageNo-1"/>"><s:text name="impleeyList.shangye"/></a>
					</s:if>
				</span>
				<span class="xiaye">
				<s:if test="currenPageNo<maxPageNo">
					<a href="UserAuthList.action?currenPageNo=<s:property value="currenPageNo+1"/>"><s:text name="impleeyList.xiaye"/></a>
				</s:if>
				</span>
				
				<s:form action="UserAuthList" theme="simple" >
					<span class="tiaozhuan"><s:text name="impleeyList.tiaozhuan"/></span>
					<s:textfield id="tiaoye" name="currenPageNo" cssClass="yefield" onmouseover="this.select();"/><span class="ye"><s:text name="impleeyList.ye"/></span>
					<s:submit value="%{getText(\"impleeyList.queding\")}" cssClass="queding" 
					onmouseover="this.className='quedingover';" onmouseout="this.className='queding';"/>
					
				</s:form>
				
				</div>
			
				</div>	
				
			</s:if>
			<s:else>
				<p>没有相应的用户信息</p>
			</s:else>
		</div>
	</div>	
	</body>
</html>