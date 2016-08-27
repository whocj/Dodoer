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
	var url = "${base}/category/submitUser.htm?categoryId="+ categoryId + "&userId="+userId;
	window.location.href=url;
}
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 板块 - 分区管理 - 修改</div>
	<form class="ropt">
		<input type="button" value="返回列表" onclick="gotoURL('${base}/category/list.htm')"/>
	</form>
	<div class="clear"></div>
</div>
<form id="jvForm" action="${base }/category/submit.htm">
	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tbody>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>分区名称:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${category.title }" name="title" class="required" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				上级分区:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${category.parentId }" name="parentId" class="digits" size="20">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				站点:
			</td>
			<td width="80%" class="pn-fcontent">
				<select name="siteId" id="siteId">
					<option value="">-请选择-</option>
					<option value="1" <#if category.siteId == '1'>selected="true"</#if>>BBS</option>
					<option value="2" <#if category.siteId == '2'>selected="true"</#if>>Ask</option>
					<option value="3" <#if category.siteId == '3'>selected="true"</#if>>Blog</option>
					<option value="4" <#if category.siteId == '4'>selected="true"</#if>>Story</option>
				</select>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>访问路径:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${category.path }" name="path" class="required" size="20">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>排列顺序:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${category.priority }" name="priority" class="required digits" size="20"> 从小到大排列
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				分区版主:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${category.moderators }" name="moderators" size="40"> 多个版主用,分割
			</td>
		</tr>

		<#if showUser>
		<tr>
			<td colspan="2" class="pn-fcontent">
				<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
					<thead class="pn-lthead">
					<tr>
						<th width="30%">用户ID</th>
						<th width="40%">用户名</th>
						<th width="30%">操作</th>
					</tr>
					</thead>
					<tbody class="pn-ltbody">
						<#list category.categoryUserList as datas>
						<tr>
						<td>
							${datas.userId }
						</td>
						<td>
							${datas.username }
						</td>
						<td>
							<a href="${base }/category/deleteCategoryUserById.htm?categoryUserId=${datas.id}&categoryId=${category.id }" onclick="if(!confirm('你确定要删除吗?')) {return false;}" class="pn-opt">删除</a>
						</td>
						</tr>
						</#list>
						<tr>
						<td>
							<input type="text" maxlength="20" value="" id="userId" name="userId" size="20">
						</td>
						<td>
							<input type="text" maxlength="20" value="" name="username" size="40">
						</td>
						<td>
							<a href="javascript:addUser()" class="pn-opt">保存</a>
						</td>
						</tr>
					</tbody>

				</table>
			</td>
		</tr>
		</#if>
		<tr>
			<td width="100%" class="pn-fcontent" colspan="2" >
				<input type="hidden" name="id" id="id" value="${category.id }"/>
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