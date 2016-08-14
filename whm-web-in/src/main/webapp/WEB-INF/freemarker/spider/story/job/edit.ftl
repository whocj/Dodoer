<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>小说抓取任务</title>
<#include "/common/include.ftl"/>
<script type="text/javascript">
$(function() {
	$("#jvForm").validate();
});
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 板块 - 小说抓取任务 - 修改</div>
	<form class="ropt">
		<input type="button" value="返回列表" onclick="gotoURL('${base}/spider/story/job/list.htm')"/>
	</form>
	<div class="clear"></div>
</div>
<form id="jvForm" action="${base }/spider/story/job/submit.htm">
	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tbody>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>名称:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="128" value="${spiderStoryJob.title }" name="title" class="required" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>分类:
			</td>
			<td width="80%" class="pn-fcontent">
				<select name="categoryId" class="required">
					<option value="">-请选择-</option>
					<#list categoryList as datas>
						<option value="${datas.id }"  <#if spiderStoryJob.categoryId == datas.id>SELECTED</#if> >${datas.title }</li>
					</#list>
				</select>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>小说抓取模版:
			</td>
			<td width="80%" class="pn-fcontent">
				<select name="templateId" class="required">
					<option value="">-请选择-</option>
					<#list templateList as datas>
						<option value="${datas.id }"  <#if spiderStoryJob.templateId == datas.id>SELECTED</#if> >${datas.name }</li>
					</#list>
				</select>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>URL:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="128" value="${spiderStoryJob.url }" name="url" class="required" size="40">
			</td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				状态:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="128" value="${spiderStoryJob.status }" name="status" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				抓取时间规则:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="128" value="${spiderStoryJob.qtRule }" name="qtRule" size="40">
			</td>
		</tr>

		<tr>
			<td width="100%" class="pn-fcontent" colspan="2" >
				<input type="hidden" name="id" id="spiderStoryJobId" value="${spiderStoryJob.id }"/>
				<input type="submit" value="提交"/>
				<input type="reset" value="重置"/>
			</td>
		</tr>
	</tbody>
	</table>
</form>
</div>
</body>
</html>