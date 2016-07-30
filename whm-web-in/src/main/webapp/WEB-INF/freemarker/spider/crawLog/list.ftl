<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>爬虫日志</title>
<#include "/common/include.ftl"/>
<script type="text/javascript">
function getTableForm() {
	return document.getElementById('tableForm');
}
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 板块 - 爬虫日志 - 列表</div>
	<form class="ropt">
		&nbsp;
	</form>
	<div class="clear"></div>
</div>
<form id="pageForm" name="pageForm" method="post" action="${base }/spider/crawLog/list.htm">
<input type="hidden" name="pageNo" value="${page.pageIndex }"/>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="20px">选择</th>
			<th width="100px">模版</th>
			<th width="100px">开始时间</th>
			<th width="100px">结束时间</th>
			<th width="100px">状态</th>
			<th width="100px">操作</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		<#list page.content as datas>
		<tr>
		<td><input type="checkbox" name="ids" value="${datas.id}"/>
			<input type="hidden" name="wids" value="${datas.id!}"/>
		</td>
		<td>${datas.crawTemplateId}</td>
		<td>/</td>
		<td>/</td>
		<td>${datas.status }</td>
		<td>
			<a href="${base }/spider/crawLog/edit.htm?id=${datas.id}" class="pn-opt">查看日志</a>
		</td>
		</tr>
		</#list>
		</tbody>
		</table>
		<hr>
		<#include "/common/page.ftl" />
</form>
<#include "/common/alert_message.ftl"/>
</div>
</body>
</html>