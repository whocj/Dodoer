<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>爬虫模版</title>
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
	<div class="rpos">当前位置： 板块 - 爬虫模版 - 列表</div>
	<form class="ropt">
		<input type="submit" value="添加模版" onclick="this.form.action='${base }/spider/crawTemplate/add.htm';"/>
		&nbsp;<input type="button" value="删除" onclick="optDelete();"/>
	</form>
	<div class="clear"></div>
</div>
<form id="pageForm" name="pageForm" method="post" action="${base }/spider/crawTemplate/list.htm">
<input type="hidden" name="pageNo" value="${page.pageIndex }"/>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="20px">选择</th>
			<th width="100px">名称</th>
			<th width="50px">论坛版块</th>
			<th width="40px">过虑词</th>
			<th width="70px">描述</th>
			<th width="250px">操作</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		<#list page.content as datas>
		<tr>
		<td><input type="checkbox" name="ids" value="${datas.id}"/>
			<input type="hidden" name="wids" value="${datas.id!}"/>
		</td>
		<td>${datas.name}</td>
		<td>${datas.forumId }</td>
		<td></td>
		<td>${datas.description }</td>
		<td>
			<a href="${base }/spider/crawTemplate/start.htm?id=${datas.id}" class="pn-opt">开始</a> |
			<a href="${base }/spider/crawTemplate/pause.htm?id=${datas.id}" class="pn-opt">暂停</a> |
			<a href="${base }/spider/crawTemplate/stop.htm?id=${datas.id}" class="pn-opt">结束</a> |
			<a href="${base }/spider/crawTemplate/view.htm?id=${datas.id}" class="pn-opt">进度</a> |
			
			<a href="${base }/spider/listTemplate/editByCrawTemplateId.htm?crawTemplateId=${datas.id}&forumId=${datas.forumId}" class="pn-opt">列表模版</a> |
			<a href="${base }/spider/detailTemplate/editByCrawTemplateId.htm?crawTemplateId=${datas.id}&forumId=${datas.forumId}" class="pn-opt">详情模版</a> |
			
			<a href="${base }/spider/crawTemplate/edit.htm?id=${datas.id}" class="pn-opt">修改</a> |
			<a href="${base }/spider/crawTemplate/delete.htm?ids=${datas.id}" onclick="if(!confirm('你确定要删除吗?')) {return false;}" class="pn-opt">删除</a>
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