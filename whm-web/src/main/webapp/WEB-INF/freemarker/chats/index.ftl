<!doctype html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />

<link href="${resRoot }/js/dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${resRoot }/js/dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${resRoot }/js/dwz_jui-master/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${resRoot }/js/dwz_jui-master/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>

<link rel='stylesheet' id='main-css-css'
	href='${resRoot}/css/main5152.css?ver=1.0' type='text/css' media='all' />
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->
<!--[if lt IE 9]><script src="${resRoot }/js/dwz_jui-master/js/speedup.js" type="text/javascript"></script><script src="${resRoot }/js/dwz_jui-master/js/jquery-1.11.3.min.js" type="text/javascript"></script><![endif]-->
<!--[if gte IE 9]><!--><script src="${resRoot }/js/dwz_jui-master/js/jquery-2.1.4.min.js" type="text/javascript"></script><!--<![endif]-->
<script src="${resRoot }/js/dwz_jui-master/xheditor/xheditor-1.2.2.min.js" type="text/javascript"></script>
<script src="${resRoot }/js/dwz_jui-master/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="${resRoot }/js/dwz_jui-master/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<script src="${resRoot }/js/dwz_jui-master/bin/dwz.min.js" type="text/javascript"></script>
<script src="${resRoot }/js/dwz_jui-master/js/dwz.regional.zh.js" type="text/javascript"></script>

<script src="${resRoot }/js/base64.js" type="text/javascript"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="聊天室 ,WebScoket,${siteKeywords }" />
<meta name="description" content="${siteDescription }" />  
<title>聊天室 - ${siteTitle }</title>
<script type="text/javascript">
$(function(){
	DWZ.init("dwz.frag.xml", {
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
		}
	});
});

function addFriend(){
	var  friendname = document.getElementById("friendname").value; 
	$.ajax({
		   type: "POST",
		   url: "${base }/chats/friend/add.html",
		   data: "friendname=" + friendname,
		   dataType:"json",
		   success: function(data){
			   if(data == "1"){
				   alert('添加好友成功.');
			   }else{
				   alert('添加好友失败.');
			   }
		   }
		});
}
</script>
</head>
<body>
		<div id="layout">
		<div id="header">
			<#include "/chats/common/header.ftl"/>
			<!-- navMenu -->
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>菜单</h2><div>收缩</div></div>
				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>我的好友</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="main.html" target="navTab" rel="main">热门话题</a></li>
							<#list chatsFriendList as data>	
								<li><a href="${base }/chats/friend/index.html?friendName=${data.friendname }&type=User&chatkey=${data.chatkey}" target="navTab" id="${data.chatkey}" rel="friend">${data.friendNickname }</a></li>
							</#list>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>我的讨论组</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<#list chatsUserList as data>
								<#if data.type == 'Group'>
									<li><a href="${base }/chats/group/index.html?id=${data.retaId }&type=${data.type }&chatkey=${data.chatkey}" target="navTab" id="${data.chatkey}" rel="${data.chatkey}">${data.retaName }</a></li>
								</#if>
							</#list>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>我参与的话题</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<#list chatsUserList as data>
								<#if data.type == 'Topic'>
									<li><a href="${base }/chats/topic/index.html?friendName=${data.retaId }&type=${data.type }&chatkey=${data.chatkey}" target="navTab" id="${data.chatkey}" rel="${data.chatkey}">${data.retaName }</a></li>
								</#if>
							</#list>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>友情链接</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${base }/q/list.html" target="_blank">问题</a></li>
							<li><a href="${base }/bbs/index.html" target="_blank">论坛</a></li>
							<li><a href="${base }/blog/index.html"target="_blank">博客</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">热门话题</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">热门话题</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">
							添加好友
							<input type="text" id="friendname">
							<input type="button" id="addFriend" value="添加好友" onclick="addFriend()">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<div id="footer">版权所有 © 2017 谁帮我-WHOHELPME | 苏ICP备16010569号-1</div>
</body>
</html>
