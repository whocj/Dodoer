<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>友情连接</title>
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
	f.action="${base}/sys/friendLink/delete.htm";
	f.submit();
}
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 板块 - 友情连接 - 列表</div>
	<form class="ropt">
		<input type="submit" value="添加地址" onclick="this.form.action='${base }/sys/friendLink/add.htm';"/>
		&nbsp;<input type="button" value="删除" onclick="optDelete();"/>
	</form>
	<div class="clear"></div>
</div>
<form id="pageForm" name="pageForm" method="post" action="${base }/sys/friendLink/list.htm">
	<input type="hidden" name="pageNo" value="${page.pageIndex }"/>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="50px">网站名称</th>
			<th width="100px">网站地址</th>
			<th width="100px">站长邮箱</th>
			<th width="200px">描述</th>
			<th width="40px">点击次数</th>
			<th width="40px">是否显示</th>
			<th width="40px">排列顺序</th>
			<th width="200px">操作</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		<#list page.content as datas>
		<tr>
			<input type="hidden" name="wids" value="${datas.id!}"/>
		</td>
		<td>${datas.siteName}</td>
		<td>${datas.domain }</td>
		<td>${datas.email }</td>
		<td>${datas.description }</td>
		<td>${datas.views}</td>
		<td>${datas.isEnabled }</td>
		<td>${datas.priority }</td>
		
		<td>
			<a href="${base }/sys/friendLink/edit.htm?id=${datas.id}" class="pn-opt">修改</a> |
			<a href="${base }/sys/friendLink/delete.htm?ids=${datas.id}" onclick="if(!confirm('你确定要删除吗?')) {return false;}" class="pn-opt">删除</a>
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