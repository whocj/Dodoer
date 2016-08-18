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
	<div class="rpos">当前位置： 板块 - 爬虫模版 - 修改</div>
	<form class="ropt">
		<input type="button" value="返回列表" onclick="gotoURL('${base}/spider/story/template/list.htm')"/>
	</form>
	<div class="clear"></div>
</div>
<form id="jvForm" action="${base }/spider/story/template/submit.htm" method="post">
	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tbody>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>名称:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="128" value="${spiderStoryTemplate.name }" name="name" class="required" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>小说标题:
			</td>
			<td width="80%" class="pn-fcontent">
				<textarea rows="8" cols="80" name="titleXPath" maxlength="1500" class="required">${spiderStoryTemplate.titleXPath?html }</textarea>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>作者:
			</td>
			<td width="80%" class="pn-fcontent">
				<textarea rows="8" cols="80" name="authorXPath" maxlength="1500" class="required">${spiderStoryTemplate.authorXPath?html }</textarea>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				概要:
			</td>
			<td width="80%" class="pn-fcontent">
				<textarea rows="8" cols="80" name="outlineXPath" maxlength="1500">${spiderStoryTemplate.outlineXPath?html }</textarea>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				图片:
			</td>
			<td width="80%" class="pn-fcontent">
			 	<textarea rows="8" cols="80" name="picPathXPath" maxlength="1500">${spiderStoryTemplate.picPathXPath?html }</textarea>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				小说下一页:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="128" value="${spiderStoryTemplate.nextXPath }" name="nextXPath"  size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>明细地址:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="128" value="${spiderStoryTemplate.detailXPath }" name="detailXPath" class="required" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				明细标题:
			</td>
			<td width="80%" class="pn-fcontent">
				<textarea rows="8" cols="80" name="detailTitleXPath" maxlength="1500">${spiderStoryTemplate.detailTitleXPath?html }</textarea>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>明细内容:
			</td>
			<td width="80%" class="pn-fcontent">
				<textarea rows="8" cols="80" name="detailContentXPath" maxlength="2000">${spiderStoryTemplate.detailContentXPath?html }</textarea>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				内容下一页:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="128" value="${spiderStoryTemplate.detailNextXPath }" name="detailNextXPath"  size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				执行模块:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="128" value="${spiderStoryTemplate.execModel }" name="execModel" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				过滤词:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="200" value="${spiderStoryTemplate.filterWord }" name="filterWord" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				过滤行:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${spiderStoryTemplate.filterItem }" name="filterItem" size="40">
			</td>
		</tr>

		<tr>
			<td width="100%" class="pn-fcontent" colspan="2" >
				<input type="hidden" name="id" id="spiderStoryTemplate" value="${spiderStoryTemplate.id }"/>
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