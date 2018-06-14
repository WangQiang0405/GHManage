String.prototype.trim = function()
{
  return this.replace(/(^\s*)|(\s*$)/g, "");
}

function changePic(pic)
{
	var com=document.getElementById(pic);
	var height=175.0;
	var width=200.0;
	var pheight=com.height;
	var pwidth=com.width;
	if(pheight/height>pwidth/width)
	{
		if(pheight>height)
		{
			com.height=pheight/(pheight/height);
			com.width=pwidth/(pheight/height);
		}
	}
	else
	{
		if(pwidth>width)
		{
			com.height=pheight/(pwidth/width);
			com.width=pwidth/(pwidth/width);
		}
	}

}
function changepwd()
{
	var result=true;
	var oldpwd=document.getElementById("oldpwd").value.trim();
	var newpwd=document.getElementById("newpwd").value.trim();
	var renewpwd=document.getElementById("renewpwd").value.trim();
	var pat=/^[0-9a-zA-Z]{6,12}$/;
	if(oldpwd=="")
	{
		result=false;
		document.getElementById("oldpass").style.display="block";
	}
	else
	{
		document.getElementById("oldpass").style.display="none";
	}
	if(newpwd=="")
	{
		result=false;
		document.getElementById("newpass").style.display="block";
	}
	else
	{
		document.getElementById("newpass").style.display="none";
	}
	if(renewpwd=="")
	{
		result=false;
		document.getElementById("repass").style.display="block";
	}
	else
	{
		document.getElementById("repass").style.display="none";
	}
	if(result==true)
	{
		if(newpwd!=renewpwd)
		{
			alert("锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷氩黄ワ拷锟�");
			return false;
		}
		if(!pat.exec(newpwd))
		{
			alert("锟斤拷锟斤拷锟斤拷只锟斤拷锟斤拷6~12位锟斤拷母锟斤拷锟斤拷锟斤拷锟斤拷锟�");
			return false;
		}
	}
	return result;
}
function change()
{
	var randomnumber=Math.random();
	document.getElementById("yanzhengmatu").src="./jsp/yanzhengma.jsp?a="+randomnumber;
}
function getElementPos(elementId) 
{      
	var ua = navigator.userAgent.toLowerCase();     
	var isOpera = (ua.indexOf('opera') != -1);     
	var isIE = (ua.indexOf('msie') != -1 && !isOpera); 
	// not opera spoof
	var el = document.getElementById(elementId);      
	if(el.parentNode == null || el.style.display == 'none')     
 	{	 	         
 	         return false;     
 	}     
 	var parent = null;     
	var pos = [];     
	var box;
 	if(el.getBoundingClientRect)    
	{ // IE
        box = el.getBoundingClientRect(); 
        var scrollTop = Math.max(document.documentElement.scrollTop, document.body.scrollTop); 
        var scrollLeft = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft); 
        return {x:box.left+ scrollLeft, y:box.top + scrollTop,w:box.right-box.left,h:box.bottom-box.top};    
        
	}     
	else if(document.getBoxObjectFor)         
	{ // FireFox
        box = document.getBoxObjectFor(el);                                  
        var borderLeft = (el.style.borderLeftWidth)?parseInt(el.style.borderLeftWidth):0;
        var borderTop = (el.style.borderTopWidth)?parseInt(el.style.borderTopWidth):0;
        pos = [box.x - borderLeft, box.y - borderTop,box.width,box.height];     
	}     
	else     
	{ 
   		// safari & opera
    	pos = [el.offsetLeft,el.offsetTop,el.offsetWidth,el.offsetHeight];
    	parent = el.offsetParent;
    	if (parent != el) 
    	{             
        	while (parent) 
        	{                 
           		pos[0] += parent.offsetLeft;
           		pos[1] += parent.offsetTop;
           		parent = parent.offsetParent;             
        	}         
		}         
		if (ua.indexOf('opera')!=-1||( ua.indexOf('safari')!=-1 && el.style.position == 'absolute' ))
		{                 
			pos[0] -= document.body.offsetLeft;                 
			pos[1] -= document.body.offsetTop;         
		}  
		return {x:pos[0]+2,y:pos[1]+2,w:pos[2],h:pos[3]};      
	}              
	if (el.parentNode) 
	{ 
		parent = el.parentNode; 
	}
	else { parent = null; } 
	while (parent && parent.tagName != 'BODY' && parent.tagName != 'HTML')
	{ // account for any scrolled ancestors
		pos[0] -= parent.scrollLeft;         
		pos[1] -= parent.scrollTop;		       
		if (parent.parentNode) 
		{ 
			parent = parent.parentNode; 
		}          
		else 
		{ 
			parent = null; 
		}     
	}
	return {x:pos[0],y:pos[1],w:pos[2],h:pos[3]}; 
}

function showErrMsg(msg,id)// msg为锟斤拷锟斤拷锟斤拷息锟侥憋拷===id为锟斤拷锟斤拷锟斤拷锟斤拷丶锟絠d
{
   // 锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷丶锟斤拷锟轿伙拷谩锟斤拷叽锟�
   tfpos=getElementPos(id);
   xby=tfpos.y+tfpos.h-1;
   xbx=tfpos.x+tfpos.w/2;
   
   // 锟斤拷示锟斤拷锟斤拷锟斤拷息锟斤拷锟�
   document.all.myerr.style.left=xbx-70;
   document.all.myerr.style.top=xby+18;
   document.all.myerrs.innerHTML=msg; 
   document.all.myerr.style.visibility="visible"; 
   
   // 锟斤拷示锟斤拷锟斤拷系募锟斤拷图片
   document.all.myup.style.left=xbx-40;
   document.all.myup.style.top=xby-2;  
   document.all.myup.style.visibility="visible";
   
   // 锟斤拷取锟斤拷锟斤拷锟斤拷息锟斤拷锟轿伙拷谩锟斤拷叽锟�
   bgpos=getElementPos("myerr");
   
   // 锟斤拷示锟节碉拷span
   document.all.errzd.style.left=xbx-70;
   document.all.errzd.style.top=xby+18;
   document.all.errzd.style.width=bgpos.w;
   document.all.errzd.style.height=bgpos.h;
   document.all.errzd.style.visibility="visible";
   
   // 锟斤拷锟斤拷锟节碉拷锟斤拷锟斤拷
   zdcount=5;
   span=bgpos.h/zdcount;
   setTimeout("xyzd("+span+","+(xby+18)+")",0);
}

// 锟斤拷锟斤拷锟斤拷锟节碉拷锟斤拷
function xyzd(span,top)
{           
   if(zdcount==0)
   {
      document.all.errzd.style.visibility="hidden";
   }
   else
   {
     document.all.errzd.style.height=span*zdcount; 
     document.all.errzd.style.top=top+span*(5-zdcount); 
     zdcount--;           
     setTimeout("xyzd("+span+","+top+")",50);
   }           
}
        
// 锟斤拷锟截达拷锟斤拷锟斤拷息锟斤拷锟斤拷
function hideErr()
{
    document.all.myerr.style.visibility="hidden"; 
    document.all.myup.style.visibility="hidden";
    document.all.errzd.style.visibility="hidden";
}

var zdcount=0;// 锟节碉拷锟斤拷锟斤拷锟截硷拷锟斤拷锟斤拷
	   
function checkmax(ss)
{alert(ss);

	var tiaoye=document.getElementById(inid).value.trim();
	if(tiaoye>totalPage)
	{
		showErrMsg("锟斤拷锟斤拷锟斤拷锟揭筹拷锟�",inid);
	}return false;
} 

function addimpcheck()
{
	var result=true;
	var impId=document.getElementById("impId").value.trim();
	var pat=/^[0-9]{6,12}$/;
	if(impId=="")
	{
		document.getElementById("impIderr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>职锟斤拷锟斤拷挪锟斤拷锟轿拷锟�";
		result=false;
	}
	else
	{
		if(!pat.exec(impId))
		{
			document.getElementById("impIderr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>锟斤拷锟斤拷锟�6锟斤拷10位锟斤拷锟斤拷锟斤拷锟�";
			result=false;
		}
		else
		{
				document.getElementById("impIderr").innerHTML="";
		}
		
	}
	var impName=document.getElementById("impName").value.trim();
	if(impName=="")
	{
		document.getElementById("impNameerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>职锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷为锟斤拷";
		result=false;
	}
	else
	{
			document.getElementById("impNameerr").innerHTML="";
	}
	
	var impEmail=document.getElementById("impEmail").value.trim();
	    pat=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if(impEmail=="")
	{
		document.getElementById("impEmailerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>职锟斤拷锟斤拷锟戒不锟斤拷为锟斤拷";
		result=false;
	}
	else
	{
		if(!pat.exec(impEmail))
		{
			document.getElementById("impEmailerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>锟斤拷锟斤拷锟绞斤拷锟斤拷锟饺�";
			result=false;
		}
		else
		{
				document.getElementById("impEmailerr").innerHTML="";
		}
	}
	
	
	var impTel=document.getElementById("impTel").value.trim();
	    pat=/^[0-9]{6,15}$/;
	if(impTel=="")
	{
		document.getElementById("impTelerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>职锟斤拷锟界话锟斤拷锟斤拷为锟斤拷";
		result=false;
	}
	else
	{
		if(!pat.exec(impTel))
		{
			document.getElementById("impTelerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>锟界话锟斤拷式锟斤拷锟斤拷确";
			result=false;
		}
		else
		{
				document.getElementById("impTelerr").innerHTML="";
		}
	}
	var impRoll=document.getElementById("impRoll").value.trim();
	if(impRoll=="")
	{
		document.getElementById("impRollerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>职锟斤拷职位锟斤拷锟斤拷为锟斤拷";
		result=false;
	}
	else
	{
			document.getElementById("impRollerr").innerHTML="";
	}
	var impSalary=document.getElementById("impSalary").value.trim();
	    pat=/^\d+(\.\d+)?$/;
	if(impSalary=="")
	{
		document.getElementById("impSalaryerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>职锟斤拷锟斤拷锟斤拷薪锟绞诧拷锟斤拷为锟斤拷";
		result=false;
	}
	else
	{
		if(!pat.exec(impSalary))
		{
			document.getElementById("impSalaryerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>锟斤拷锟斤拷薪锟绞革拷式锟斤拷锟斤拷确";
			result=false;
		}
		else
		{
				document.getElementById("impSalaryerr").innerHTML="";
		}
	}
	
	
	var impBio=document.getElementById("impBio").value.trim();
	if(impBio=="")
	{
		document.getElementById("impBioerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>职锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷为锟斤拷";
		result=false;
	}
	else
	{
			document.getElementById("impBioerr").innerHTML="";
	}
	return result;
}

function editimpcheck()
{
	var result=true;
	var impName=document.getElementById("impName").value.trim();
	if(impName=="")
	{
		document.getElementById("impNameerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>职锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷为锟斤拷";
		result=false;
	}
	else
	{
			document.getElementById("impNameerr").innerHTML="";
	}
	
	var impEmail=document.getElementById("impEmail").value.trim();
	    var pat=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if(impEmail=="")
	{
		document.getElementById("impEmailerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>职锟斤拷锟斤拷锟戒不锟斤拷为锟斤拷";
		result=false;
	}
	else
	{
		if(!pat.exec(impEmail))
		{
			document.getElementById("impEmailerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>锟斤拷锟斤拷锟绞斤拷锟斤拷锟饺�";
			result=false;
		}
		else
		{
				document.getElementById("impEmailerr").innerHTML="";
		}
	}
	
	
	var impTel=document.getElementById("impTel").value.trim();
	    pat=/^[0-9]{6,15}$/;
	if(impTel=="")
	{
		document.getElementById("impTelerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>职锟斤拷锟界话锟斤拷锟斤拷为锟斤拷";
		result=false;
	}
	else
	{
		if(!pat.exec(impTel))
		{
			document.getElementById("impTelerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>锟界话锟斤拷式锟斤拷锟斤拷确";
			result=false;
		}
		else
		{
				document.getElementById("impTelerr").innerHTML="";
		}
	}
	var impRoll=document.getElementById("impRoll").value.trim();
	if(impRoll=="")
	{
		document.getElementById("impRollerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>职锟斤拷职位锟斤拷锟斤拷为锟斤拷";
		result=false;
	}
	else
	{
			document.getElementById("impRollerr").innerHTML="";
	}
	var impSalary=document.getElementById("impSalary").value.trim();
	    pat=/^\d+(\.\d+)?$/;
	if(impSalary=="")
	{
		document.getElementById("impSalaryerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>职锟斤拷锟斤拷锟斤拷薪锟绞诧拷锟斤拷为锟斤拷";
		result=false;
	}
	else
	{
		if(!pat.exec(impSalary))
		{
			document.getElementById("impSalaryerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>锟斤拷锟斤拷薪锟绞革拷式锟斤拷锟斤拷确";
			result=false;
		}
		else
		{
				document.getElementById("impSalaryerr").innerHTML="";
		}
	}
	
	
	var impBio=document.getElementById("impBio").value.trim();
	if(impBio=="")
	{
		document.getElementById("impBioerr").innerHTML="<img style=\"width:12px;height:12px;\" src=\"images/cuo.gif\"/>职锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷为锟斤拷";
		result=false;
	}
	else
	{
			document.getElementById("impBioerr").innerHTML="";
	}
	return result;
}


function editdepartcheck()
{
	var departName=document.getElementById("departName").value.trim();
	if(departName=="")
	{
		alert("锟斤拷锟斤拷锟斤拷锟狡诧拷锟斤拷为锟斤拷");
		return false;
	}
	
	var departDescrip=document.getElementById("departDescrip").value.trim();
	if(departDescrip=="")
	{
		alert("锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷为锟斤拷");
		return false;
	}
	return true;
}

function windowpopup(id){
    // var tr=obj.parentNode.parentNode;
    // alert(tr.rowIndex);
    //alert(tr.cells[1].innerText);
    // var name=tr.cells[1].innerText;
    // name="1";
    var top = (screen.height - 200) / 2;
    var left = (screen.width - 1600) / 2;
    window.open ("Ghdetail?id="+id,"_blank",'height=200,width=1600,top='+top+',left='+left+',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no');
}
