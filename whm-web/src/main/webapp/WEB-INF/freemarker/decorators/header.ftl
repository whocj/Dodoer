<script type="text/javascript">
<!--
	function goLogin(){
		var currentUrl = location.href;
		var url = new Base64().encodes(currentUrl);
		if(currentUrl != ""){
			location.href = "${base }/login/index.htm?url=" + url;
		}else{
			location.href = "${base }/login/index.htm";
		}
	}
	
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

<div class="header-wrapper">
	<header>
		<div class="container">
			<div class="logo-container">
				<!-- Website Logo -->
				<a href="${base }/index.html">
				<span class="tag-line" style="color:#FFF;font-size:18px;">
				谁帮我 WHOHELPME
				</span>
				</a>
				&nbsp;&nbsp;
				<span class="tag-line">您的问题，我们来解决！</span>
			</div>
			
			
			<!-- Start of Main Navigation -->
			<nav class="main-nav">
				
				<div class="menu-top-menu-container">
					<ul id="menu-top-menu" class="clearfix">
						<#if configs.SITE_ASK_ENABLE == true>
						<li>
						<div style="position: relative;top:9px;">
						<script type="text/javascript">(function(){document.write(unescape('%3Cdiv id="bdcs"%3E%3C/div%3E'));var bdcs = document.createElement('script');bdcs.type = 'text/javascript';bdcs.async = true;bdcs.src = 'http://znsv.baidu.com/customer_search/api/js?sid=489190037201838139' + '&plate_url=' + encodeURIComponent(window.location.href) + '&t=' + Math.ceil(new Date()/3600000);var s = document.getElementsByTagName('script')[0];s.parentNode.insertBefore(bdcs, s);})();</script>
							<form action="${base }/search.html" name="jvForm" method="get" submit="search()">
								<input type="text" name="keyword" id="searchText"  placeholder="输入关键词"
								style="width:300px;height:26px;" value="${keyword }">
							</form>
						</div>
						</li>
						</#if>
						<#if configs.SITE_ASK_ENABLE == true>
						<li class="<#if CURRENT_MENU_CODE && CURRENT_MENU_CODE == 2>current-menu-item</#if>" ><a href="${base }/q/list.html">问题</a></li>
						</#if>
						<#if configs.SITE_BBS_ENABLE == true>
						<li class="<#if CURRENT_MENU_CODE && CURRENT_MENU_CODE == 3>current-menu-item</#if>"><a href="${base }/bbs/index.html">论坛</a></li>
 						</#if>
						<#if configs.SITE_BLOG_ENABLE == true>
 						<li class="<#if CURRENT_MENU_CODE && CURRENT_MENU_CODE == 4>current-menu-item</#if>"><a href="${base }/blog/index.html">博客</a></li>
 						</#if>
						<#if configs.SITE_CHART_ENABLE == true>
 						<li class="<#if CURRENT_MENU_CODE && CURRENT_MENU_CODE == 4>current-menu-item</#if>"><a href="${base }/chats/index.html">聊天室</a></li>
						</#if>
						<#if sessionUser != null>
						<li>
						<img class="question_avatar" src="${sessionUser.userLogo }">
						<a href="${base }/blog/main/${sessionUser.username}.html">${sessionUser.nickname }</a>
							<ul class="sub-menu">
								<li onclick="logout('${base }/logout.htm')"><a href="javascript:logout('${base }/logout.htm')">登出</a></li>
							</ul>
						</li>
						</#if>
						<#if sessionUser == null>
							<li ><a id="loginMsg" href="javascript:openLoginDiv()">登录</a></li>
						</#if>
					</ul>
				</div>
			</nav>
			<!-- End of Main Navigation -->
		</div>
	</header>
</div>