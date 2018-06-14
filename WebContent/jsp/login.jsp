<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<title><s:text name="login.title"/></title>
		<link href="css/style.css" rel="stylesheet" type="text/css"/>
		<link href="css/loginStyle.css" rel="stylesheet" type="text/css"/>
		<script language="JavaScript" src="js/manage.js">
		
		</script>
	</head>
	<body>
		<div id="container">
			<div id="headcontainer">
				<h2><s:text name="system.name"/></h2>
				<h4><s:text name="system.englishname"/></h4>
			</div>
			<div id="mainbody">
				<div id="pic">
					<img src="images/login.jpg">
				</div>
				
				<div id="operate">
					<s:form action="login" theme="simple" method="post" onsubmit="return check();" validate="true">
						<p><s:text name="login.info"/></p>
						<span class="uid"><s:text name="login.name"/></span>
						<s:textfield id="uid" name="uid" cssClass="inuid"/>
						<span class="pwd"><s:text name="login.pwd"/></span>
						<s:password id="pwd" name="pwd" cssClass="inpwd"/>
						<span class="yanzhengma"><s:text name="login.yanzheng"/></span>
						<s:textfield id="yanzhengma" name="yanzhengma" cssClass="inyanzhengma"/>
						<img id="yanzhengmatu" src="./jsp/yanzhengma.jsp">
						<a href="#" onclick="change();"><s:text name="login.change"/></a>
						<s:submit id="submit" value="%{getText(\"botton.login\")}" cssClass="logins" 
							onmouseover="this.className='loginsover';" onmouseout="this.className='logins';"/>
						<s:textfield id="result" name="result" cssStyle="display:none"/>
					</s:form>
				</div>
			</div>
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
				showErrMsg(result,"yanzhengma");
			}
			function check() {
				var uidstr=document.getElementById("uid").value.trim();
				if(uidstr=="")
				{
					showErrMsg("请输入用户名！","uid");
					return false;
				}
				var pwdstr=document.getElementById("pwd").value.trim();
				if(pwdstr=="")
				{
					showErrMsg("请输入密码！","pwd");
					return false;
				}
				var yanzhengmastr=document.getElementById("yanzhengma").value.trim();
				if(yanzhengmastr=="")
				{
					showErrMsg("请输入验证码！","yanzhengma");
					return false;
				}
				return true;
			}
		</script>
	</body>
</html>