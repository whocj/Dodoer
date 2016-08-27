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
		<input type="button" value="返回列表" onclick="gotoURL('${base}/y/info/list.htm')"/>
	</form>
	<div class="clear"></div>
</div>
<form id="jvForm" action="${base }/story/info/submit.htm">
	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tbody>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>标题:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${storyInfo.title }" name="title" class="required" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>作者:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${storyInfo.author }" name="author" class="required" size="40">
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
						<option value="${datas.id }"  <#if storyInfo.categoryId == datas.id>SELECTED</#if> >${datas.title }</li>
					</#list>
				</select>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				排序:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${storyInfo.sortIndex }" name="sortIndex" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				阅读人数:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${storyInfo.readCount }" name="readCount" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				回复人数:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${storyInfo.replyCount }" name="replyCount" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				点赞人数:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${storyInfo.likeCount }" name="likeCount" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				图片:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${storyInfo.picPath }" name="picPath" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				概要:
			</td>
			<td width="80%" class="pn-fcontent">
				<textarea type="text"  name="outline" style="width:80%;height:100px;"  size="80">${storyInfo.outline?html }</textarea>
			</td>
		</tr>
		<tr>
			<td width="100%" class="pn-fcontent" colspan="2" >
				<input type="hidden" name="id" id="id" value="${storyInfo.id }"/>
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