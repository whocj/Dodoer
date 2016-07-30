<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>详情模版</title>
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
	<div class="rpos">当前位置： 板块 - 详情模版 - 修改</div>
	<form class="ropt">
		<input type="button" value="返回列表" onclick="gotoURL('${base}/spider/detailTemplate/list.htm')"/>
	</form>
	<div class="clear"></div>
</div>
<form id="jvForm" action="${base }/spider/detailTemplate/submit.htm">
	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tbody>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>模版ID:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${detailTemplate.crawTemplateId }" name="crawTemplateId" readonly="readonly" class="required" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>论坛版块ID:
			</td>
			<td width="80%" class="pn-fcontent">
				<select name="forumId" class="required">
					<option value="">-请选择-</option>
					<#list forumList as datas>
						<option value="${datas.id }"  <#if detailTemplate.forumId == datas.id>SELECTED</#if> >${datas.title }</li>
					</#list>
				</select>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				标题（titleXPath）:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${detailTemplate.titleXPath }"  name="titleXPath" size="60">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				标题排除，多个使用','分隔（titleExcludeXPath）:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${detailTemplate.titleExcludeXPath }"  name="titleExcludeXPath" size="60">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				内容（contentXPath）:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${detailTemplate.contentXPath }"  name="contentXPath" size="60">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				内容排除，多个使用','分隔（contentExcludeXPath）:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${detailTemplate.contentExcludeXPath }"  name="contentExcludeXPath" size="60">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				下一页（nextXPath）:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${detailTemplate.nextXPath }"  name="nextXPath" size="60">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				上一页（prevXPath）:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${detailTemplate.prevXPath }"  name="prevXPath" size="60">
			</td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				内容下一页（contentNextXPath）:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${detailTemplate.contentNextXPath }"  name="contentNextXPath" size="60">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				内容上一页（contentPrevXPath）:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${detailTemplate.contentPrevXPath }"  name="contentPrevXPath" size="60">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				评论（commentsXPath）:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${detailTemplate.commentsXPath }"  name="commentsXPath" size="60">
			</td>
		</tr>

		<tr>
			<td width="100%" class="pn-fcontent" colspan="2" >
				<input type="hidden" name="id" id="detailTemplateId" value="${detailTemplate.id }"/>
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