function realtime()
{
	var com=document.getElementById("time");
	var date=new Date();
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	var day=date.getDate();
	var hour=date.getHours();
	var min=date.getMinutes();
	var second=date.getSeconds();
	
	com.innerHTML=year+"-"+month+"-"+day+" "+hour+":"+min+":"+second;
	
	setTimeout('realtime()',1000);
	
}