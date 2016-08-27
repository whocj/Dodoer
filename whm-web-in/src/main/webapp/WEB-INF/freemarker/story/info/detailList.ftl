<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>小说章节明细</title>
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
	f.action="${base}/spider/crawTemplate/delete.htm";
	f.submit();
}
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 板块 - 小说章节明细 - 列表</div>
	<form class="ropt">
		<input type="submit" value="添加模版" onclick="this.form.action='${base }/story/info/detail/add.htm';"/>
		&nbsp;<input type="button" value="删除" onclick="optDelete();"/>
	</form>
	<div class="clear"></div>
</div>
<form id="pageForm" name="pageForm" method="post" action="${base }/story/info/detail/list.htm">
名称<input type="text" name="title" value="${title }"/>
 <input type="button" value="搜索" onclick="javascript:goPage(1)"/>
<input type="hidden" name="storyId" value="${storyId }"/>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="20px">选择</th>
			<th width="200px">标题</th>
			<th width="200px">crawlUrl</th>
			<th width="70px">更新时间</th>
			<th width="150px">操作</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		<#list detailList as datas>
		<tr>
		<td><input type="checkbox" name="ids" value="${datas.id}"/>
			<input type="hidden" name="wids" value="${datas.id!}"/>
		</td>
		<td>${datas.title}</td>
		<td>${datas.crawlUrl}</td>
		<td>${datas.lastUpdate?string('yyyy-MM-dd HH:mm')}</td>
		<td>
			<a href="${base }/story/detail/edit.htm?id=${datas.id}" class="pn-opt">修改</a> |
			<a href="${base }/story/detail/delete.htm?ids=${datas.id}" onclick="if(!confirm('你确定要删除吗?')) {return false;}" class="pn-opt">删除</a>
		</td>
		</tr>
		</#list>
		</tbody>
		</table>
		<hr>
</form>
<#include "/common/alert_message.ftl"/>
</div>
</body>
</html>