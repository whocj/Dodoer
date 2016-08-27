<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>小说专题明细</title>
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
	<div class="rpos">当前位置： 板块 - 小说专题明细 - 修改</div>
	<form class="ropt">
		<input type="button" value="返回列表" onclick="gotoURL('${base}/stopic/detail.htm?topicId=${topicId}')"/>
	</form>
	<div class="clear"></div>
</div>
<form id="jvForm" action="${base }/story/topic/detail/submit.htm">
	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tbody>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>小说ID:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${topicDetail.storyId }" name="storyId" class="required" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				标题:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${topicDetail.title }" name="title" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				排序:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${topicDetail.sortIndex }" name="sortIndex"  size="40">
			</td>
		</tr>
		<tr>
			<td width="100%" class="pn-fcontent" colspan="2" >
				<input type="hidden" name="id" value="${topicDetail.id }"/>
				<input type="hidden" name="topicId" value="${topicId }"/>
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