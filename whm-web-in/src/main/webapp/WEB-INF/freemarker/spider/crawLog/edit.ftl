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
	<div class="rpos">当前位置： 板块 - 爬虫日志- 查看</div>
	<form class="ropt">
		<input type="button" value="返回列表" onclick="gotoURL('${base}/spider/crawLog/list.htm')"/>
	</form>
	<div class="clear"></div>
</div>
	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tbody>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				开始时间:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="50" value="${crawLog.beginTime?string('yyyy-MM-dd HH:mm:ss') }" name="name" class="required" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				结束时间:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="50" value="${crawLog.endTime?string('yyyy-MM-dd HH:mm:ss') }" name="type" class="required" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				描述:
			</td>
			<td width="80%" class="pn-fcontent">
				<textarea rows="30" cols="100">${crawLog.log }</textarea>
			</td>
		</tr>
		</tbody>
	</table>
</div>
</body>
</html>