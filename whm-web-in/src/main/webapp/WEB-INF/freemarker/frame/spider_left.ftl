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
		<li><a href="${base }/spider/crawTemplate/list.htm" target="rightFrame">爬虫模版</a></li>
		<li><a href="${base }/spider/listTemplate/list.htm" target="rightFrame">列表模版</a></li>
		<li><a href="${base }/spider/detailTemplate/list.htm" target="rightFrame">详情模版</a></li>
		<li><a href="${base }/spider/crawLog/list.htm" target="rightFrame">爬虫日志</a></li>
		<li><a href="${base }/spider/crawInfo/list.htm" target="rightFrame">爬虫信息</a></li>
		
		<li><a href="${base }/spider/story/template/list.htm" target="rightFrame">小说模版</a></li>
		<li><a href="${base }/spider/story/job/list.htm" target="rightFrame">小说抓取任务</a></li>
	</ul>
</body>
</html>