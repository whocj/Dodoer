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
		<li><a href="${base }/sys/authentication/list.htm" target="rightFrame">认证管理</a></li>
		<li><a href="${base }/sys/friendLink/list.htm" target="rightFrame">友情连接</a></li>
		<li><a href="${base }/sys/limit/list.htm" target="rightFrame">限制访问</a></li>
		<li><a href="${base }/sys/loginLog/list.htm" target="rightFrame">登录日志</a></li>
		<li><a href="${base }/sys/sensitivity/list.htm" target="rightFrame">敏感词管理</a></li>
		<li><a href="${base }/sys/site/list.htm" target="rightFrame">站点管理</a></li>
		<li><a href="${base }/sys/notice/list.htm" target="rightFrame">公告管理</a></li>
		<li><a href="${base }/sys/app/release/main.htm" target="rightFrame">应用管理</a></li>
	</ul>
</body>
</html>