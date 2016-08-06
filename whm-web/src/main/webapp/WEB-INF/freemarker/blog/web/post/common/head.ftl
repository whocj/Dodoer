<script type="text/javascript">
<!--
function openLoginDiv(){
	layer.open({
	    type: 2,
	    title: '用户登录',
	    shadeClose: true,
	    shade: 0.2,
	    area: ['500px', '450px'],
	    content: '${base}/login/indexDialog.html',
	    end : function(){
	    	//document.location.reload();
    	}
	});
}

function logout(url){
	location.href = url;
}

//-->
</script>
<div id="header">
  <div id="menu" class="clearfix">
    <ul>
    	
      <li><a  href="${base }/blog/index.html">首页</a></li>
      <#if sessionUser == null>
      	<li><a id="loginMsg" href="javascript:openLoginDiv()">登录</a></li>
<!-- 			<li id="loginMsg"><span id="qqLoginBtn"></span></li> -->
      <#else>
      	<li>
      	<img src="${sessionUser.userLogo }" class="question_avatar" height="30px" width="30px" style="padding-top: 3px;">
      	<a src="${sessionUser.userLogo }" href="${base }/b/main/${sessionUser.username }.html" style="padding-left: 5px;">${sessionUser.nickname }</a>
        <ul>
			<li onclick="logout('${base }/logout.htm')"><a href="javascript:logout('${base }/logout.htm')">登出</a></li>
	        </ul>
	      </li>	
      </#if>
		
    </ul>
  </div>
  <h1 id="logo"><a href="${base }/blog/main/${blogOptions.creator}.html">${blogOptions.title?html }</a></h1>
  <h2 id="tagline">${blogOptions.subTitle?html } | ${blogOptions.keywords?html }</h2>
</div>