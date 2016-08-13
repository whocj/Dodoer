
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="application-name" content="Help Me - WHM" />
<title>绑定用户-WHO HELP ME</title>
<#include "/common/global_include.ftl">
<script type='text/javascript' src='${resRoot}/js/jquery-1.7.2.min.js'></script>
<script type='text/javascript' src='${resRoot}/js/jquery.form.js'></script>
<script type='text/javascript' src='${resRoot}/js/layer/layer.js'></script>
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101296555" 
data-redirecturi="http://www.whohelpme.com/login/QQLogin.html" charset="utf-8"></script>
<link href="${resRoot }/css/login.min.css" rel="stylesheet">
<script type="text/javascript">
	function checkUsername(u){
		var un = $(u).val();
		$.post("${base}/login/queryByUser.html", {
			 username:un,
			}, function(data) {
				//绑定用户
				if(data.succ == 'S'){
					layer.tips('已经存在用户，输入密码，绑定账户', '#username', {time: 4000,tips:[1]});
				}else{
					layer.tips('新用户，请输入密码，完成账户操作', '#username', {time: 4000,tips:[1]});
		        }
			}, "json");
	}
	
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function submitForm(){
		var formData = $('#bindform').formSerialize();
		$.ajax({
			   type: "POST",
			   url: "${base }/auth/submit.html",
			   data: formData,
			   dataType:"json",
			   success: function(data){
				   if(data.succ == "F"){
					   layer.tips('用户名或密码错误.','#username',  {time: 4000,tips:[1,'#0FA6D8']});
				   }else{
					   opener.window.closeWin(data.user.username, "登录成功！");
					   window.close();
				   }
			   }
			});
	}
</script>
</head>
<body>
<div class="wrapper">
    <div class="lr-log ">
    	<br>
        <h1 class="title">绑定用户-QQ用户登录成功</h1>
        <form id="bindform" class="lg-box" action="${base }/auth/submit.html">
            <ul>
                <li class="list-name">
                    <input name="username" id="username" maxlength="30" class="text-input"  
                    	type="text" placeholder="用户名/邮箱/手机号" tabindex="1" onblur="checkUsername(this)">
                </li>
                <li class="list-pass">
                    <input name="password" id="password" maxlength="30" class="text-input" 
                    type="password" placeholder="密码" tabindex="2">
                </li>
                <li class="list-submit">
                	<input name="key" type="hidden" value="${key }">
                	<input name="source" type="hidden" value="${source }">
                	<input name="token" type="hidden" value="${token }">
                    <input id="loginSubmit" name="Submit" class="submit-input" type="button" onclick="submitForm()" tabindex="3" value="保存">
                </li>
            </ul>
        </form>
    </div>
</div>
</body>
</html>