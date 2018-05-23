<%@ page contentType="text/html;charset=GBK" %>

<html>
	<head>
		<title>高级链接</title>
		<link href="images/treestyle.css" rel="stylesheet" type="text/css"/>
		<script language="JavaScript">
			function change(num)
			{
				var s=document.getElementById("li"+num+1).style.display;
				if(s=="none")
				{
				
					document.getElementById("li"+num).className="kai";
					for(var i=1;i<10;i++)
					{
						var obj=document.getElementById("li"+num+i);
						if(obj==null)
						{
							return;
						}
						else
						{
							obj.style.display="block";
						}
					}
				}
				else
				{
					document.getElementById("li"+num).className="he";
					for(var i=1;i<10;i++)
					{
						var obj=document.getElementById("li"+num+i);
						if(obj==null)
						{
							return;
						}
						else
						{
							obj.style.display="none";
						}
					}
				}
			}
		</script>
	</head>
	<body>
		<div id="leftTree">
			<ul>	
				<li><a href="#" onclick="change(1);" id="li1" class="kai">信息管理</a></li>
				<li id="li11" style="display:block;" class="son"><a class="sons" href="UserAuthList.action">用户权限变更</a></li>
				<li id="li12" style="display:block;" class="son"><a class="sons" href="GhTrack.action">学生信息追加</a></li>
				<li id="li13" style="display:block;" class="son"><a class="sons" href="GhJhSjGap.action">学生信息修改</a></li>
								
				<li><a href="#" onclick="change(2);" id="li2" class="kai">扩展用</a></li>
				<li id="li21" style="display:block;" class="son"><a class="sons" href="departmentList.action">扩展用</a></li>
				<li id="li22" style="display:block;" class="son"><a class="sons" href="addDepartment.jsp">扩展用</a></li>
				
				<li><a href="#" onclick="change(3);" id="li3" class="kai">扩展用</a></li>
				<li id="li31" style="display:block;" class="son"><a class="sons" href="todayAttend.action">扩展用</a></li>
				<li id="li32" style="display:block;" class="son"><a class="sons" href="kaoQinSearch.action?departId=55555&attendType=-1&startTime=2008-8-8&endTime=2008-8-8">扩展用</a></li>
				<li id="li33" style="display:block;" class="son"><a class="sons" href="QianDaoOption.jsp">扩展用</a></li>
				
				<li><a href="#" onclick="change(4);" id="li4" class="kai">扩展用</a></li>
				<li id="li41" style="display:block;" class="son"><a class="sons" href="departEW.action?departId=55555&startTime=2008-8-8&endTime=2008-8-8">扩展用</a></li>
				<li id="li42" style="display:block;" class="son"><a class="sons" href="personEW.action?impId=999&startTime=2008-8-8&endTime=2008-8-8">扩展用</a></li>
				<li id="li43" style="display:block;" class="son"><a class="sons" href="AddExtraWork.jsp">扩展用</a></li>
				
				<li><a href="#" onclick="change(5);" id="li5" class="kai">扩展用</a></li>
				<li id="li51" style="display:block;" class="son"><a class="sons" href="ComSalary.jsp">扩展用</a></li>
				
				<li><a href="#" onclick="change(6);" id="li6" class="kai">扩展用</a></li>
				<li id="li61" style="display:block;" class="son"><a class="sons" href="dualApply.action?departId=0000&holsState=0&currenPage=1">扩展用</a></li>
				<li id="toperson"><a href="personInfo.action">扩展用</a></li>
			</ul>
		</div>
	</body>
</html>