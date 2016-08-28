<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="application-name" content="多多儿小说-Dodoer" />
<title>用户登陆 - 多多儿小说网-Dodoer</title>
<#include "/common/global_include.ftl">
<script type='text/javascript' src='${resRoot}/js/jquery-1.7.2.min.js'></script>
<script type='text/javascript' src='${resRoot}/js/jquery.form.js'></script>
<script type='text/javascript' src='${resRoot}/js/layer/layer.js'></script>
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101344928" 
data-redirecturi="http://www.dodoer.com/login/QQLogin.html" charset="utf-8"></script>
<link href="${resRoot }/css/login.min.css" rel="stylesheet">

<script type="text/javascript">

	function showRegister(){
		$(".lr-log").hide();
		$(".lr-reg").show();
	}
	
	function showLogin(){
		$(".lr-log").show();
		$(".lr-success").hide();
		$(".lr-reg").hide();
	}
	
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function submitForm(){
		var formData = $('#loginform').formSerialize();
		$.ajax({
			   type: "POST",
			   url: "${base }/login/dialogLogin.html",
			   data: formData,
			   dataType:"json",
			   success: function(data){
				   if(data.succ == "F"){
					   layer.tips('用户名或密码错误.',  '#username', {time: 4000,tips:[1]});
				   }else if(data.succ == "S"){
					   //parent.$('#showUsernameA').attr("innerText",msg);
					   closeWin(data.user.username, '您好！' + data.user.username + '，登录成功。');
				   }
			   }
			});
	}
	
	function closeWin(username, msg){
		parent.window.location.reload(true);
	}

	function submitRegister(){
		checkUsername();
		checkPasswordSame();
		
		var formData = $('#nameregform').formSerialize();
		$.ajax({
			   type: "POST",
			   url: "${base }/register.html",
			   data: formData,
			   success: function(msg){
				   if(msg == "S"){
					   $(".lr-reg").hide();
					   $(".lr-success").show();
				   }else if(msg == "F"){
					   layer.tips('注册失败，用户名已存在。', '#regusername', {time: 4000,tips:[1]});
				   }
			   }
			});
	}
    
     if(QC.Login.check()){
    	 QC.Login.signOut();
     }

	 //插入按钮的节点qqLoginBtn id
	 QC.Login({btnId:"qqLoginBtn",size: "B_M"});

	 function checkPassword(u){
		var un = $(u).val();
		if(un.length < 5){
			layer.tips('密码至少6个字符.', '#regpassword', {time: 4000,tips:[1]});
			return false;
		}
		checkPasswordSame();
	 }

	 function checkPasswordSame(){
		var p =  $("#regpassword").val();
		var p1 =  $("#regpassword1").val();
		if(p.length > 0 && p1.length > 0){
			if(p != p1){
				layer.tips('两次输入密码不一致', '#regpassword', {time: 4000,tips:[1]});
				return false;
			}
			
			return true;
		}
		return false;
	 }
	 
	function checkUsername(){
		var un = $("#regusername").val();
		if(un.length < 4){
			layer.tips('用户名至少5个字符.', '#regusername', {time: 4000,tips:[1]});	
		}
		if(!(/^[a-zA-Z0-9_]+$/).test(un)){
			layer.tips('用户名只能包含字母数字和下划线.', '#regusername', {time: 4000,tips:[1]});
			$("#regusername").focus();
			return false;
		}

		$.post("${base}/login/queryByUser.html", {
			 username:un,
			}, function(data) {
				//绑定用户
				if(data.succ == 'S'){
					layer.tips('用户名已存在。', '#regusername', {time: 4000,tips:[1]});
				}
			}, "json");
	}
</script>
</head>
<body>
<div class="wrapper">
    <div class="lr-log ">
        <h1 class="title">登录</h1>
        <form id="loginform" class="lg-box" action="${base }/login.htm">
            <ul>
                <li class="list-name">
                    <input name="username" id="username" maxlength="30" class="text-input" type="text" placeholder="用户名" tabindex="1">
                </li>
                <li class="list-pass">
                    <input name="password" id="password" maxlength="30" class="text-input" type="password" placeholder="密码" tabindex="2">
                </li>
                <li class="list-check hidden"></li>
                <li class="list-ckbox">
                    <label><input name="remeber" class="" type="checkbox" tabindex="3" checked="checked">自动登录</label>
<!--                     <a href="http://passport.pptv.com/fetchpassword.aspx" target="_blank">忘记密码？</a> -->
                </li>
                <li class="list-submit">
                    <input id="loginSubmit" name="Submit" class="submit-input" type="button" onclick="submitForm()" tabindex="4" value="登录">
                </li>
                <span id="qqLoginBtn"></span>
<!--                 <li class="list-third"> -->
<!--                     <div class="tt"> -->
<!--                         <i></i> -->
<!--                         <span>使用第三方账号登录</span> -->
<!--                     </div> -->
<!--                     <div class="icons"> -->
<!--                         <a class="icon icon-qq" data-app="qq" id="qqLoginBtn" href="javascript:QQLogin()" title="QQ账号">QQ账号</a> -->
<!--                         <a class="icon icon-wechat" data-app="wx" href="javascript:;" title="微信登录">微信登录</a> -->
<!--                         <a class="icon icon-weibo" data-app="sina" href="javascript:;" title="新浪微博">新浪微博</a> -->
<!--                         <a class="icon icon-alipay" data-app="alipay" href="javascript:;" title="使用支付宝登录">使用支付宝登录</a> -->
<!--                     </div> -->
<!--                 </li> -->
            </ul>
        </form>
        <p class="bottom-text">没有账号?<br><a href="javascript:showRegister();">创建一个新账号</a></p>
    </div>
    <div class="lr-reg hidden">
        <h1 class="title">注册</h1>
        <div class="tabs cf">
            <a class="now" href="javascript:;">用户名/邮箱<i class="icon"></i></a>
            <a href="javascript:void(0);"><i class="icon"></i></a>
        </div>
        <form id="nameregform" class="rg-box rg-box-u" action="">
            <ul>
                <li class="list-name">
                    <input name="username" id="regusername" maxlength="30" class="text-input" type="text" 
                    placeholder="只能包含字母数字和下划线，至少5个字符" tabindex="5" onblur="checkUsername()">
                </li>
                <li class="list-pass">
                    <input id="regpassword" name="password" maxlength="30" class="text-input" type="password"
                     placeholder="创建密码，至少6个字符" tabindex="6" onblur="checkPassword(this)">
                </li>
                <li class="list-repass">
                    <input id="regpassword1" name="password1" maxlength="30" class="text-input" type="password" 
                    placeholder="确认密码，至少6个字符" tabindex="7" onblur="checkPassword(this)">
                </li>
                <li class="list-check"></li>
                <li class="list-text">
                    <p>点击创建账户，即表示同意相关<a href="javascript:void(0)" target="_blank">服务条款</a></p>
                    <input name="checkboxreaded" type="checkbox" checked="checked" class="hidden">
                </li>
                <li class="list-submit">
                    <input name="Submit" class="submit-input" type="button" tabindex="8" onclick="submitRegister()" value="注册">
                </li>
            </ul>
        </form>
<!--         <p class="bottom-text">已有账号?<br><a href="javascript:showLogin();">立即登录</a></p> -->
    </div>
    
    <div class="lr-mailConfirm hidden">
        <span class="logo"></span>
        <p class="tt"><i class="ico"></i>信息提交成功！</p>
        <div class="confirmtext">
            <p class="tt-1">请激活您的账号以完成注册</p>
            <p class="tt-mail">验证邮件已发送至您的邮箱：<span>sys@sys.com</span></p>
            <p>您的激活链接在48小时内有效</p>
            <p>抓紧时间激活账号，尽情享受在线高清视听吧</p>
        </div>
        <a href="javascript:;" class="loginbtn">点击这里登录邮箱</a>
        <a href="javascript:;" class="resendbtn">再次发送激活邮件</a>
        <span class="tips tip-success">发送成功</span>
        <span class="tips tip-fail">发送失败，请稍后再试</span>
        <span class="tips tip-send">请前往您的邮箱激活帐号</span>
    </div>

    <div class="lr-success hidden">
        <p class="tt"><i class="ico"></i>注册成功，感谢您使用WHM</p>
        <p class="tt-1">您的登录账号是：<span id="usernameSpan"></span></p>
        <a href="javascript:showLogin();" class="btn">登录</a>
    </div>

</div>

</body>
</html>