<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录日志</title>
<#include "/common/include.ftl"/>
<script type="text/javascript">
function getTableForm() {
	return document.getElementById('tableForm');
}
function optDelete() {
	if(Pn.checkedCount('ids')<=0) {
		alert("至少选择一条记录.");
		return;
	}
	if(!confirm("确认删除吗？")) {
		return;
	}
	var f = getTableForm();
	f.action="${base}/sys/limit/delete.htm";
	f.submit();
}
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 板块 - 访问限制 - 列表</div>
	<form class="ropt">
		&nbsp;<input type="button" value="删除" onclick="optDelete();"/>
	</form>
	<div class="clear"></div>
</div>
<form id="pageForm" name="pageForm" method="post" action="${base }/sys/limit/list.htm">
	<input type="hidden" name="pageNo" value="${page.pageIndex }"/>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="100px">IP地址</th>
			<th width="100px">用户名</th>
			<th width="200px">备注</th>
			<th width="100px">操作人</th>
			<th width="100px">操作时间</th>
			<th width="200px">操作</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		<#list page.content as datas>
		<tr>
			<input type="hidden" name="wids" value="${datas.id!}"/>
		</td>
		<td>${datas.ip}</td>
		<td>${datas.username }</td>
		<td>${datas.remark }</td>
		<td>${datas.creator }</td>
		<td>${datas.lastUpdate?string('yyyy-MM-dd HH:mm:ss') }</td>
		<td>
			<a href="${base }/sys/limit/edit.htm?id=${datas.id}" class="pn-opt">修改</a> |
			<a href="${base }/sys/limit/delete.htm?ids=${datas.id}" onclick="if(!confirm('你确定要删除吗?')) {return false;}" class="pn-opt">删除</a>
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