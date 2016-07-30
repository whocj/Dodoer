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
	var forumId = $("#forumId").val();
	var userId = $("#userId").val();
	var username = $("#username").val();
	
	var url = "${base}/forum/submitUser.htm?forumId="+ forumId + "&userId="+userId + "&username=" + username;
	window.location.href=url;
}

function addGroup(){
	var forumId = $("#forumId").val();
	var groupId = $("#groupId").val();
	var groupName = $("#groupName").val();
	var type = $("#type").val();
	
	var url = "${base}/forum/submitGroup.htm?forumId="+ forumId + "&groupId="+groupId + "&groupName=" + groupName + "&type=" + type;
	window.location.href=url;
}
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 板块 - 版块管理 - 修改</div>
	<form class="ropt">
		<input type="button" value="返回列表" onclick="gotoURL('${base}/forum/list.htm')"/>
	</form>
	<div class="clear"></div>
</div>
<form id="jvForm" action="${base }/forum/submit.htm">
	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tbody>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>版块名称:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${forum.title }" name="title" class="required" size="40">
			</td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>分类:
			</td>
			<td width="80%" class="pn-fcontent">
				<select name="categoryId" id="categoryId">
					<option value="">-请选择-</option>
					<#list categoryList as datas>
						 <#if datas.id == forum.categoryId >
						 	<option selected="true" value="${datas.id }">${datas.title }</li>
						 <#else>
						 	<option value="${datas.id }">${datas.title }</li>
						 </#if>
					
						
					</#list>
				</select>
			</td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>访问路径:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${forum.path }" name="path" class="required" size="20">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>排列顺序:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${forum.priority }" name="priority" class="required digits" size="20"> 从小到大排列
			</td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				关键词:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${forum.keywords }" name="keywords"  size="50">
			</td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				版规:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${forum.forumRule }" name="forumRule"  size="50">
			</td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				积分是否启用:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${forum.pointAvailable }" name="pointAvailable" class="digits" size="20"> 1为启用，0为未启用
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				发贴加分:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${forum.pointTopic }" name="pointTopic" class="digits" size="20">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				回帖加分:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${forum.pointReply }" name="pointReply" class="digits" size="20">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				精华加分:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${forum.pointPrime }" name="pointPrime" class="digits" size="20">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				锁定主题（天）:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${forum.topicLockLimit }" name="topicLockLimit" class="digits" size="20">
			</td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				描述:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${forum.description }" name="description" size="50">
			</td>
		</tr>

		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				分区版主:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${forum.moderators }" readonly="readonly" name="moderators" size="40"> 多个版主,用","分割
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				发帖会员组:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${forum.groupTopics }" readonly="readonly" name="groupTopics" size="40"> 多个用户组,用","分割
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				回复会员组:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${forum.groupReplies }" readonly="readonly" name=groupReplies size="40"> 多个用户组,用","分割
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				访问会员组:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="20" value="${forum.groupViews }" readonly="readonly" name="groupViews" size="40"> 多个用户组,用","分割
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
						<#list forum.forumUserList as datas>
						<tr>
						<td>
							${datas.userId }
						</td>
						<td>
							${datas.username }
						</td>
						<td>
							<a href="${base }/forum/deleteForumUserById.htm?forumUserId=${datas.id}&forumId=${forum.id }" onclick="if(!confirm('你确定要删除吗?')) {return false;}" class="pn-opt">删除</a>
						</td>
						</tr>
						</#list>
						<tr>
						<td>
							<input type="text" maxlength="20" value="" id="userId" name="userId" size="20">
						</td>
						<td>
							<input type="text" maxlength="20" value="" id="username" name="username" size="40">
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
		
		<#if showGroup>
		发贴用户组
		<tr>
			<td colspan="2" class="pn-fcontent">
				<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
					<thead class="pn-lthead">
					<tr>
						<th width="30%">用户组ID</th>
						<th width="40%">用户组名</th>
						<th width="30%">操作</th>
					</tr>
					</thead>
					<tbody class="pn-ltbody">
						<#list forum.groupTopicList as datas>
						<tr>
						<td>
							${datas.groupId }
						</td>
						<td>
							${datas.groupName }
						</td>
						<td>
							<a href="${base }/forum/deleteForumGroupById.htm?forumGroupId=${datas.id}&forumId=${forum.id }" onclick="if(!confirm('你确定要删除吗?')) {return false;}" class="pn-opt">删除</a>
						</td>
						</tr>
						</#list>
					</tbody>

				</table>
			</td>
		</tr>
		
		回贴用户组
		<tr>
			<td colspan="2" class="pn-fcontent">
				<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
					<thead class="pn-lthead">
					<tr>
						<th width="30%">用户组ID</th>
						<th width="40%">用户组名</th>
						<th width="30%">操作</th>
					</tr>
					</thead>
					<tbody class="pn-ltbody">
						<#list forum.groupReplyList as datas>
						<tr>
						<td>
							${datas.groupId }
						</td>
						<td>
							${datas.groupName }
						</td>
						<td>
							<a href="${base }/forum/deleteForumGroupById.htm?forumGroupId=${datas.id}&forumId=${forum.id }" onclick="if(!confirm('你确定要删除吗?')) {return false;}" class="pn-opt">删除</a>
						</td>
						</tr>
						</#list>
					</tbody>

				</table>
			</td>
		</tr>
		
		查看用户组
		<tr>
			<td colspan="2" class="pn-fcontent">
				<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
					<thead class="pn-lthead">
					<tr>
						<th width="30%">用户组ID</th>
						<th width="40%">用户组名</th>
						<th width="30%">操作</th>
					</tr>
					</thead>
					<tbody class="pn-ltbody">
						<#list forum.groupViewList as datas>
						<tr>
						<td>
							${datas.groupId }
						</td>
						<td>
							${datas.groupName }
						</td>
						<td>
							<a href="${base }/forum/deleteForumGroupById.htm?forumGroupId=${datas.id}&forumId=${forum.id }" onclick="if(!confirm('你确定要删除吗?')) {return false;}" class="pn-opt">删除</a>
						</td>
						</tr>
						</#list>
					</tbody>

				</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="2" class="pn-fcontent">
				<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
				<tr>
						<td>
							<input type="text" maxlength="10" value="" id="groupId" name="groupId" size="10">
						</td>
						<td>
							<input type="text" maxlength="20" value="" id="groupName" name="groupName" size="20">
								<select name="type" id="type" name="type">
								<option>请选择</option>
								<option value="Topic">发帖会员组</option>
								<option value="Reply">回复会员组</option>
								<option value="View">访问会员组</option>
							</select>
						</td>
						<td>
							<a href="javascript:addGroup()" class="pn-opt">保存</a>
						</td>
						</tr>
				</table>
				</td>
		</tr>				
		</#if>
		
		<tr>
			<td width="100%" class="pn-fcontent" colspan="2" >
				<input type="hidden" name="id" id="forumId" value="${forum.id }"/>
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