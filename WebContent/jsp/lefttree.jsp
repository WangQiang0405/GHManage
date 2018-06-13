<%@ page contentType="text/html;charset=GBK" %>

<html>
	<head>
		<title>高级链接</title>
		<link href="css/treestyle.css" rel="stylesheet" type="text/css"/>
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
				<li><a href="#" onclick="change(1);" id="li1" class="kai">招聘状况</a></li>
				<li id="li11" style="display:block;" class="son"><a class="sons" href="GhTargetList.action">计划与实际</a></li>
				<li id="li12" style="display:block;" class="son"><a class="sons" href="GhTrack.action">实时跟踪</a></li>
				<li id="li13" style="display:block;" class="son"><a class="sons" href="GhSearchInit.action">更新</a></li>
				
				<li><a href="#" onclick="change(2);" id="li2" class="kai">应聘者信息</a></li>
				<li id="li21" style="display:block;" class="son"><a class="sons" href="GhImport.action">导入</a></li>
			</ul>
		</div>
	</body>
</html>