<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Admin Administrator's Control Panel</title>
<#include "/common/include.ftl"/>
<style type="text/css">
*{margin:0;padding:0}
html{height:100%;overflow:hidden;}
body{height:100%;}
#logout{color:#FFF;padding-left:5px;}
#welcome{color:#FFF;padding:0 10px 0 5px; }
#view_index{color:#FFF;}

.menu{padding-left:1em;font-size:12px;font-weight:700;float:left;margin:4px 4px 0 0;list-style:none;}
.menu li{float:left;width:60px;}
.menu li.sep{float:left;height:35px;width:10px;background:url(${resRoot}/jeecms/img/admin/sep.jpg) left 3px no-repeat;}
.menu li a{display:block;height:35px;float:left;line-height:35px;padding:0 14px;color:#000;outline:none;hide-focus:expression(this.hideFocus=true);}
.menu li.current{background:url(${resRoot}/jeecms/img/admin/nav_current.jpg) left top no-repeat;}
.menu li.current a{color:#fff;}

.undis{display:none;}
.dis{display:block;}
</style>

<script type="text/javascript">
function g(o){
	return document.getElementById(o);
}
function HoverLi(m,n,counter){
	for(var i=1;i<=counter;i++){
		g('tb_'+m+i).className='';
	}
	g('tb_'+m+n).className='current';
}
function changeSite(siteId) {
	
}
</script>
</head>

<body>
<div id="top">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="223">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><img src="${resRoot}/jeecms/img/admin/logo.jpg"></td>
      </tr>
      <tr>
        <td height="33" align="center" background="${resRoot}/jeecms/img/admin/time_bg.jpg">
		<img src="${resRoot}/jeecms/img/admin/ico3.jpg">&nbsp;现在时间：
       <script language="javascript">
		var day="";
		var month="";
		var ampm="";
		var ampmhour="";
		var myweekday="";
		var year="";
		mydate=new Date();
		myweekday=mydate.getDay();
		mymonth=mydate.getMonth()+1;
		myday= mydate.getDate();
		year= mydate.getFullYear();
		if(myweekday == 0)
		weekday=" 星期日 ";
		else if(myweekday == 1)
		weekday=" 星期一 ";
		else if(myweekday == 2)
		weekday=" 星期二 ";
		else if(myweekday == 3)
		weekday=" 星期三 ";
		else if(myweekday == 4)
		weekday=" 星期四 ";
		else if(myweekday == 5)
		weekday=" 星期五 ";
		else if(myweekday == 6)
		weekday=" 星期六 ";
		document.write(year+"年"+mymonth+"月"+myday+"日 "+weekday);
	   </script>
       </td>
      </tr>
    </table></td>
    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="54"><img src="${resRoot}/jeecms/img/admin/top_bg.jpg"></td>
        <td background="${resRoot}/jeecms/img/admin/top_bg.jpg"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="260" height="30">
			<img src="${resRoot}/jeecms/img/admin/ico1.gif"><span id="welcome">欢迎&nbsp;${sessionUser.nickname }</span>
			<img src="${resRoot}/jeecms/img/admin/ico2.jpg"><a href="${base }/logout.htm" target="_top" id="logout");">退出</a></td>
            <td align="right">
            </td>
            <td width="100">
			&nbsp;<a id="view_index" href="${base}/" target="_blank"></a>
			</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><img src="${resRoot}/jeecms/img/admin/top_07.jpg"></td>
        <td background="${resRoot}/jeecms/img/admin/nav_bg.jpg">
		   <ul class="menu">
			<li class="current" id="tb_11" onclick="HoverLi(1,1,6);"><a href="${base}/index/main.htm" target="mainFrame">首页</a></li>
			<li class="sep"></li><li id="tb_12" onclick="HoverLi(1,2,6);"><a href="${base}/category/main.htm" target="mainFrame">分区</a></li>
			<li class="sep"></li><li id="tb_13" onclick="HoverLi(1,3,6);"><a href="${base}/ask/main.htm" target="mainFrame">问答</a></li>
			<li class="sep"></li><li id="tb_14" onclick="HoverLi(1,4,6);"><a href="${base}/user/user/main.htm" target="mainFrame">会员</a></li>
			<li class="sep"></li><li id="tb_15" onclick="HoverLi(1,5,6);"><a href="${base}/spider/main.htm" target="mainFrame">爬虫</a></li>
			<li class="sep"></li><li id="tb_16" onclick="HoverLi(1,6,6);"><a href="${base}/sys/main.htm" target="mainFrame">维护</a></li>
			</ul>
		</td>
      </tr>
    </table></td>
  </tr>
</table>
</div>
</body>
</html>