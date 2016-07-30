<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>列表模版</title>
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
	<div class="rpos">当前位置： 板块 - 列表模版 - 修改</div>
	<form class="ropt">
		<input type="button" value="返回列表" onclick="gotoURL('${base}/spider/listTemplate/list.htm')"/>
	</form>
	<div class="clear"></div>
</div>
<form id="jvForm" action="${base }/spider/listTemplate/submit.htm">
	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tbody>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>模版ID:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${listTemplate.crawTemplateId }" name="crawTemplateId" readonly="readonly" class="required" size="40">
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
						<option value="${datas.id }"  <#if listTemplate.forumId == datas.id>SELECTED</#if> >${datas.title }</li>
					</#list>
				</select>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>URL:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="200" value="${listTemplate.url }" name="url" class="required" size="80">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				类型:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${listTemplate.topicType }" name="topicType" class="required digits" size="20">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				（列表）listXPath:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${listTemplate.listXPath }" name="listXPath" size="60">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				（总页数）totalPageXPath:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${listTemplate.totalPageXPath }"  name="totalPageXPath" size="60">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				（标题）titleXPath:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${listTemplate.titleXPath }"  name="titleXPath" size="60">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				（下一页）nextXPath:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${listTemplate.nextXPath }"  name="nextXPath" size="60">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				（明细）detailXPath:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${listTemplate.detailXPath }"  name="detailXPath" size="60">
			</td>
		</tr>

		<tr>
			<td width="100%" class="pn-fcontent" colspan="2" >
				<input type="hidden" name="id" id="listTemplateId" value="${listTemplate.id }"/>
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