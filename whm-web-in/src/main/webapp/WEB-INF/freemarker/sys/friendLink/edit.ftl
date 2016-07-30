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

function addUser(){
	var categoryId = document.getElementById("id").value;
	var userId = document.getElementById("userId").value;
	var url = "${base}/sys/friendLink/submitUser.htm?categoryId="+ categoryId + "&userId="+userId;
	window.location.href=url;
}
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 板块 - 友情连接 - 修改</div>
	<form class="ropt">
		<input type="button" value="返回列表" onclick="gotoURL('${base}/sys/friendLink/list.htm')"/>
	</form>
	<div class="clear"></div>
</div>
<form id="jvForm" action="${base }/sys/friendLink/submit.htm">
	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tbody>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>分类:${friendLink.siteId }
			</td>
			<td width="80%" class="pn-fcontent">
				<select name="siteId" class="required">
					<option value="">-请选择-</option>
					<#list siteList as datas>
						<option value="${datas.id }"  <#if friendLink.siteId == datas.id>SELECTED</#if> >${datas.name }</li>
					</#list>
				</select>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>网站名称:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${friendLink.siteName }" name="siteName" class="required" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>网站地址:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${friendLink.domain }" name="domain" class="required" size="20">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				站长邮箱:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${friendLink.email }" name="email"  size="20">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>排列顺序:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${friendLink.priority }" name="priority" class="required digits" size="20"> 从小到大排列
			</td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				点击次数:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${friendLink.views }" name="views" class="digits" size="20">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>是否显示:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${friendLink.isEnabled }" name="isEnabled" class="digits" size="20"> 1显示，0不显示
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				描述:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${friendLink.description }" name="description" size="60">
			</td>
		</tr>
		<tr>
			<td width="100%" class="pn-fcontent" colspan="2" >
				<input type="hidden" name="id" id="id" value="${friendLink.id }"/>
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