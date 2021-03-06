<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<title><s:text name="import.title"/></title>
		<link href="css/style.css" rel="stylesheet" type="text/css"/>
		<link href="css/mainstyle.css" rel="stylesheet" type="text/css"/>
		<link href="css/commonStyle.css" rel="stylesheet" type="text/css"/>
		<script language="JavaScript" src="js/manage.js"></script>
		<script language="JavaScript">
		function openFile(){
			var filepath = document.getElementById("file");
			filepath.click();
			document.getElementById("importPath").value = filepath.value;
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
		<div id="maincontent" style="text-align:center">
			<s:form action="GhImportExe" theme="simple" method="post" onsubmit="" validate="true">
			<H3 style="margin-top:30px"><s:text name="import.head"/></H3>
			<div id="operate" style="text-align:center">
				<input type="file" id="file" accept=".xls,.xlsx" style="display:none"> 
				<s:textfield id="importPath" name="importPath" cssClass="inPath"/>
				<input type="button" name="inPath" value="..." onclick="openFile()"
				class="inbutton" onmouseover="this.className='inbuttonover';" onmouseout="this.className='inbutton';"/>
				<s:textfield id="result" name="result" cssStyle="display:none"/>
			</div>
			<div id="foot">
				<table border="0" align="center">
					<tr>
						<td>
							<s:submit id="submit" value="%{getText(\"botton.import\")}"
								cssClass="import" onmouseover="this.className='importover';" onmouseout="this.className='import';"/>
						</td>
					</tr>
				</table>
			</div>
			</s:form>
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
			if (errMsg != "") {//若有错误消息则调用错误信息气球显示
				showErrMsg(errMsg, "importPath");
			}
			var result = document.getElementById("result").value;
			if (result == "success") {
			    alert("导入成功。");
			} else if (result == "fail") {
			    alert("导入失败！");
			} else if (result != "") {
				showErrMsg(result, "importPath");
			}
		</script>
	</body>
</html>