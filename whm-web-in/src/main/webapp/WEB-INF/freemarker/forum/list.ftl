<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
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
	f.action="${base}/forum/delete.htm";
	f.submit();
}
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 板块 - 板块管理 - 列表</div>
	<form class="ropt">
		<input type="submit" value="添加板块 " onclick="this.form.action='${base }/forum/add.htm';"/>
		&nbsp;<input type="button" value="删除" onclick="optDelete();"/>
	</form>
	<div class="clear"></div>
</div>
<form id="pageForm" name="pageForm" method="post" action="${base }/forum/list.htm">
<input type="hidden" name="pageNo" value="${page.pageIndex }"/>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="20px">选择</th>
			<th width="100px">标题</th>
			<th width="50px">访问路径</th>
			<th width="20px">顺序</th>
			<th width="70px">版主</th>
			<th width="120px">操作</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		<#list page.content as datas>
		<tr>
		<td><input type="checkbox" name="ids" value="${datas.id}"/>
			<input type="hidden" name="wids" value="${datas.id!}"/>
		</td>
		<td>${datas.title}</td>
		<td>${datas.path }</td>
		<td>${datas.priority }</td>
		<td>${datas.moderators }</td>
		<td>
			<a href="${base }/forum/edit.htm?id=${datas.id}" class="pn-opt">修改</a> |
			<a href="${base }/forum/addUser.htm?id=${datas.id}" class="pn-opt">添加版主</a> |
			<a href="${base }/forum/addGroup.htm?id=${datas.id}" class="pn-opt">添加用户组</a> |
			<a href="${base }/forum/delete.htm?ids=${datas.id}" onclick="if(!confirm('你确定要删除吗?')) {return false;}" class="pn-opt">删除</a>
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