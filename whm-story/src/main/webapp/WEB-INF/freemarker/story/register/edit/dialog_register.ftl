<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="application-name" content="多多儿小说-Dodoer" />
<title>小说申请收录 _${siteTitle }</title>
<#include "/common/global_include.ftl">
<script type='text/javascript' src='${resRoot}/js/jquery-1.7.2.min.js'></script>
<script type='text/javascript' src='${resRoot}/js/jquery.form.js'></script>
<script type='text/javascript' src='${resRoot}/js/layer/layer.js'></script>
<script type='text/javascript' src='${resRoot}/js/custom412.js'></script>
<link href="${resRoot }/css/login.min.css" rel="stylesheet">

<script type="text/javascript">
	function submitForm(){
		
		if(!checkTitle()){
			return false;
		}
		
		if(!checkAuthor()){
			return false;
		}
		
		var formData = $('#loginform').formSerialize();
		$.ajax({
		   type: "POST",
		   url: "${base }/story/register/commit.html",
		   data: formData,
		   dataType:"json",
		   success: function(data){
			   if(data.status == "F"){
				   layer.tips('申请错误.',  '#title', {time: 4000,tips:[1]});
			   }else if(data.status == "S"){
				   //layer.tips('申请成功.',  '#title', {time: 4000,tips:[1]});
				   $("#registerDiv").hide();
				   $("#successDiv").show();
			   }
		   }
		});
	}
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function closeWin(){
		//layer.closeAll(); //疯狂模式，关闭所有层
		parent.window.location.reload(true);
		//window.close();
	}
	
	function checkTitle(){
		var un = $("#title").val();
		if(un.trim().length == 0){
			 layer.tips('小说标题必填.',  '#title', {time: 4000,tips:[1]});
			 return false;
		}
		return true;
	}
	
	function checkAuthor(){
		var un = $("#author").val();
		if(un.trim().length == 0){
			 layer.tips('小说作者必填.',  '#author', {time: 4000,tips:[1]});
			 return false;
		}
		return true;
	}

</script>
</head>
<body>
<div class="wrapper" style="height: 300px;">
    <div id="registerDiv" class="lr-log">
        <h1 class="title">小说申请收录</h1>
        <form id="loginform" class="lg-box" action="${base }/story/register/commit.html">
            <ul>
                <li class="list-name">
                    <input name="title" id="title" maxlength="64" class="text-input" type="text" placeholder="小说标题" tabindex="1">
                </li>
                <li class="list-pass">
                    <input name="author" id="author" maxlength="64" class="text-input" type="text" placeholder="作家" tabindex="2">
                </li>
                 <li class="list-pass" placeholder="分类" tabindex="3">
                    <select name="categoryId">
                    	<#list categoryList as datas>
                    	<option value="${datas.id }">${datas.title }</option>
                    	</#list>
                    </select>
                </li>
                <li class="list-submit">
                    <input id="loginSubmit" name="Submit" class="submit-input" type="button" 
                    onclick="submitForm()" tabindex="4" value="提交">
                </li>
            </ul>
        </form>
    </div>

    <div id="successDiv" class="lr-success hidden">
        <p class="tt"><i class="ico"></i>小说收录申请成功。</p>
        <a href="javascript:closeWin();" class="btn">关闭</a>
    </div>

</div>

</body>
</html>