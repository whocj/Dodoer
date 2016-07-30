<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
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
	<div class="rpos">当前位置： 板块 - 标签管理 - 修改</div>
	<form class="ropt">
		<input type="button" value="返回列表" onclick="gotoURL('${base}/ask/tag/list.htm')"/>
	</form>
	<div class="clear"></div>
</div>
<form id="jvForm" action="${base }/ask/tag/submit.htm">
	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tbody>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>名称:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${tag.name }" name="name" class="required" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				站点:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${tag.siteId }" name="siteId" class="digits" size="20">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				粗体:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${tag.styleBold }" name="styleBold"  size="20">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				斜体:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${tag.styleItalic }" name="styleItalic"  size="20">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				颜色:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${tag.styleColor }" name="styleColor"  size="20">
			</td>
		</tr>
		<tr>
			<td width="100%" class="pn-fcontent" colspan="2" >
				<input type="hidden" name="id" id="id" value="${tag.id }"/>
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