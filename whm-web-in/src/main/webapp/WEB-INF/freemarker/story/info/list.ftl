<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>小说基本信息</title>
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
	<div class="rpos">当前位置： 板块 - 小说基本信息 - 列表</div>
	<form class="ropt">
		&nbsp;<input type="button" value="删除" onclick="optDelete();"/>
	</form>
	<div class="clear"></div>
</div>
<form id="pageForm" name="pageForm" method="post" action="${base }/story/info/list.htm">
标题<input type="text" name="title" value="${title }"/>
 <input type="button" value="搜索" onclick="javascript:goPage(1)"/>
<input type="hidden" name="pageNo" value="${page.pageIndex }"/>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="20px">选择</th>
			<th width="100px">标题</th>
			<th width="50px">论坛版块</th>
			<th width="40px">作者</th>
			<th width="70px">排序</th>
			<th width="70px">状态</th>
			<th width="70px">更新时间</th>
			<th width="250px">操作</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		<#list page.content as datas>
		<tr>
		<td><input type="checkbox" name="ids" value="${datas.id}"/>
			<input type="hidden" name="wids" value="${datas.id!}"/>
		</td>
		<td>${datas.title}</td>
		<td>${datas.categoryName}</td>
		<td>${datas.author}</td>
		<td>${datas.sortIndex}</td>
		<td>${datas.status}</td>
		<td>${datas.lastUpdateDetail?string('yyyy-MM-dd HH:mm')}</td>
		<td>
			<a href="${base }/story/info/detail.htm?storyId=${datas.id}" class="pn-opt">章节明细</a> |
			<a href="${base }/story/info/updateStatus.htm?status=2&id=${datas.id}" onclick="if(!confirm('你确定要上架吗?')) {return false;}" class="pn-opt">上架</a> |
			<a href="${base }/story/info/updateStatus.htm?status=3&id=${datas.id}" onclick="if(!confirm('你确定要下架吗?')) {return false;}" class="pn-opt">下架</a> |
			<a href="${base }/story/info/edit.htm?id=${datas.id}" class="pn-opt">修改</a> |
			
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