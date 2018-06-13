<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head><meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<title><s:text name="mainmanage.title"/></title>
		<link href="css/style.css" rel="stylesheet" type="text/css"/>
		<link href="css/mainstyle.css" rel="stylesheet" type="text/css"/>
		<link href="css/addgh.css" rel="stylesheet" type="text/css"/>
		<script language="JavaScript" src="js/manage.js"></script>
		<s:head/>
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
			<div id="ftdiv">
			<s:form onsubmit="return addusercheck();" theme="simple" action="addUser" method="post" enctype="multipart/form-data">
				<fieldset>
					<legend>注册新用户</legend>
					
					<table width="500">
						<tr>
							<td style="width:80px;">用户名:</td>
							<td><s:textfield name="userId" id="userId" cssClass="inputarea"/><span id="userIderr" class="fielderr"></span></td>
				
						</tr>
						<tr>
							<td>密码:</td>
							<td><s:textfield name="userPwd" id="userPwd" cssClass="inputarea"/><span id="userPwderr" class="fielderr"></span></td>
						</tr>
						
					</table>
					</fieldset>
					<s:submit value="提 交" cssClass="tijiao" onmouseover="this.className='tijiaoover';" onmouseout="this.className='tijiao';"/>
				</s:form>
				<s:fielderror cssClass="fielderr"/>
				<script language="JavaScript">
					result="<s:property value="result"/>";
					if(result!="")
					{
						alert(result);
					}
				</script>
			</div>
			
		</div>
	</div>
	</body>
</html>