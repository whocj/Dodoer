<html><head>
<html>
<head>
<meta charset="UTF-8"> 
<title>用户登录_${siteTitleMobile }</title>
<meta name="keywords" content="${siteKeywords }">
<meta name="description" content="${siteDescription }">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"> 
<meta content="yes" name="apple-mobile-web-app-capable"> 
<meta content="black" name="apple-mobile-web-app-status-bar-style"> 
<meta content="telephone=no" name="format-detection"> 
<link rel="stylesheet" href="${resRoot}/m/css/qz_show.css">
<link rel='stylesheet' id='bootstrap-css-css'
	href='${resRoot}/css/bootstrap5152.css?ver=1.0' type='text/css'
	media='all' />
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101344928" 
data-redirecturi="http://www.dodoer.com/login/QQLogin.html" charset="utf-8"></script>
<#include "/m/common/include.ftl">
<#include "/m/common/include_js.ftl">
<script type="text/javascript">
$(function() {
	$("#loginButton").click(function(event){
		event.preventDefault(); //阻止默认事件
		login();
    });
});	

function login(){
	$("#loginform").submit();
}

if(QC.Login.check()){
	 QC.Login.signOut();
}

//插入按钮的节点qqLoginBtn id
QC.Login({btnId:"qqLoginBtn",size: "B_M"});
</script>
<style type="text/css">
#login{width:80%;margin:0px auto;margin-top: 50px;}
#login div{margin:10px auto;}
#loginform{background-color:#fff;border: 1px solid #e5e5e5;font-weight: normal;padding:24px;
    box-shadow: 0 4px 10px -1px rgba(200, 200, 200, 0.7);}
.message{background-color: #ffffe0;border:1px solid #e6db55; border-radius: 3px;text-align: center;margin-bottom: 10px;}
</style>
</head>
<body>
<div id="main_body">
	 <header class="s_header">
		<nav>
			<a href="/" class="bg">
				<span>首页</span>
			</a>
		</nav>
	</header>
	<div id="login">
		<form action="${base }/login.html" method="post" id="loginform">
			<h3>用户登录</h3>
			<p class="message">${msg }</p>
			<div>
				<label for="username">账号</label> <input type="text" maxlength="32"
					name="username" id="username" style="width:80%">
			</div>
			<div>
				<label for="password">密码 </label> <input maxlength="32"
					type="password" name="password" id="password" style="width:80%">
			</div>
			<div>
					<input class="btn" name="loginButton" type="button" id="loginButton" value="登录">
					
			</div>
			<div>
				<label for="password">第三方登录</label>
				<span id="qqLoginBtn"></span>
			</div>
		</form>
	</div>
</div>

<#include "/m/common/copyright.ftl">
</body>
</html>