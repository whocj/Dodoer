<#include "/common/include.ftl"/>
<!doctype html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ask - WHO HELP ME</title>
<style type="text/css">
#login{width:450px;margin:0px auto;margin-top: 100px;}
#loginform{background-color:#fff;border: 1px solid #e5e5e5;font-weight: normal;padding:24px;
    box-shadow: 0 4px 10px -1px rgba(200, 200, 200, 0.7);}
.message{background-color: #ffffe0;border:1px solid #e6db55; border-radius: 3px;text-align: center;margin-bottom: 10px;}
</style>
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
</script>
</head>
<body>
	<#include "/decorators/header.ftl"/>
	<div id="login" style="height:550px;">
		<form action="${base }/login.htm" method="post" id="loginform">
			<h3>用户登录</h3>
			<p class="message">${msg }</p>
			<div>
				<label for="username">用户名 </label> <input class="span4" type="text"
					name="username" id="username" value="" size="22">
			</div>
			<div>
				<label for="password">密码 </label> <input class="span4"
					type="password" name="password" id="password" value="" size="22">
			</div>
			<div style="display: none">
				<label for="author">验证码</label> <input class="span4" type="text"
					name="author" id="author" value="" size="22"> <img alt="" />
			</div>
			<div>
				<label><input type="checkbox" name="remeber" value="true" /> 记住我的登录信息</label> 
					<input class="btn" name="loginButton" type="button" id="loginButton" value="登录">
					<input type="hidden" name="url" value="${url }">
			</div>
		</form>
	</div>

	<!-- Footer Bottom -->
	<#include "/decorators/copyright.ftl"/>
	<!-- End of Footer Bottom -->
</body>
</html>