<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>jeecms-left</title> 
<#include "/common/include.ftl"/>
<script type="text/javascript">
	$(function() {
		Cms.lmenu('lmenu');
	});
</script>
</head>
<body class="lbody">
	<ul id="lmenu">
		<li><a href="${base }/sys/userOnLine/list.htm" target="rightFrame">在线用户</a></li>
		<li><a href="${base }/user/userGroup/list.htm" target="rightFrame">用户组管理</a></li>
		<li><a href="${base }/user/user/list.htm" target="rightFrame">用户管理</a></li>
		<li><a href="${base }/user/friendShip/list.htm" target="rightFrame">好友管理</a></li>
	</ul>
</body>
</html>