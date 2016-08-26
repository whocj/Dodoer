<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="application-name" content="多多儿小说-Dodoer" />
<title>用户认证 - 多多儿小说-Dodoer</title>
<#include "/common/global_include.ftl">
<script type='text/javascript' src='${resRoot}/js/jquery-1.7.2.min.js'></script>
<script type='text/javascript' src='${resRoot}/js/jquery.form.js'></script>
<script type='text/javascript' src='${resRoot}/js/layer/layer.js'></script>
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101296555" 
data-redirecturi="http://www.whohelpme.com/login/QQLogin.html" charset="utf-8"></script>

<script type="text/javascript">
	if(QC.Login.check()){
		QC.Login.getMe(function(openId, accessToken){
			
			 $.post("http://www.dodoer.com/login/publicAuthLogin.html", {
				 key:openId,
				 source:"QQ",
				 token:accessToken
				}, function(data) {
					try{
						//opener.window.closeWin(data.user.username, "登录成功！");
						opener.window.location.reload(true);
		//				window.close();
					}catch(e){}
				}, "json");

		})
	}

</script>
</head>
<body>
</body>
</html>