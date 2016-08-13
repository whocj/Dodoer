<#include "/common/include.ftl"/>
<!doctype html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ask - WHO HELP ME</title>
<style>
<!--
#div1,#div2{
    position: fixed;
    top: 50%;
    left: 50%;
    background:red;
    width:100px;
    height:100px;
    margin:-50px 0 0 -50px;
    display:none;
}
-->
</style>
<script type="text/javascript">
<!--
	$(function() {
		// 点击按钮，DIV弹出隐藏
		$('#btn1').toggle(function() {
			$('#div1').show();
		}, function() {
			$('#div1').hide();
		});

		// 点击按钮，DIV弹出，点击body任意地方隐藏div
		$('#btn2').click(function() {
			if (event && event.stopPropagation) {
				// this code is for Mozilla and Opera 
				event.stopPropagation();
			} else if (window.event) {
				// this code is for IE 
				window.event.cancelBubble = true;
			}
			$('#div2').show();
		});
		$(document).click(function() {
			$('#div2').hide();
		});
	});
	
//批量上传
function showBatchUpload(){
	$("#loginDiv").dialog({hide:"fadeIn",modal:true,minWidth:500,minHeight:300,
		close:function(){
		}
	});
}
//-->
</script>
</head>
<body>
	<input type="button" id="btn1" value="btn1">
	<input type="button" id="btn2" value="btn2">
	
	<input type="button" id="btn2" onclick="showBatchUpload()" value="弹出层">
	<div id="div1">div1</div>
	<div id="div2">div2</div>
	
<div id="loginDiv" style="width:100%;margin:0px auto;margin-top: 0px;display:none;" title="用户登录">
		<form action="#" method="post" id="loginform">
			<p style="background-color: #ffffe0;border:1px solid #e6db55; border-radius: 3px;text-align: center;margin-bottom: 10px;">${msg }</p>
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
				<label><input type="checkbox" name="remeber" /> 记住我的登录信息</label> <input
					class="btn" name="button" type="button" id="button" value="提交">
			</div>
		</form>
	</div>
</body>
</html>