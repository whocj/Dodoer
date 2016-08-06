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

//-->
</script>

<div class="header-wrapper">
	<header>
		<div class="container">
			<div class="logo-container">
				<!-- Website Logo -->
				<a href="${base }" title="Knowledge Base Theme"> <img
					src="${resRoot}/images/logo.png" alt="Knowledge Base Theme"></a>
				<span class="tag-line">你的问题，我来解决</span>
			</div>
			<!-- Start of Main Navigation -->
			<nav class="main-nav">
				<div class="menu-top-menu-container">
					<ul id="menu-top-menu" class="clearfix">
						<li><a href="${base }/index.htm">首页</a></li>
						<li class="<#if CURRENT_MENU_CODE && CURRENT_MENU_CODE == 1>current-menu-item</#if>" ><a href="${base }/question/ask.htm">提问</a></li>
						<li class="<#if CURRENT_MENU_CODE && CURRENT_MENU_CODE == 2>current-menu-item</#if>" ><a href="${base }/question/listIndex.htm">问题</a></li>
						<li class="current-menu-item" ><a href="${base}">论坛</a></li>
						<li class="<#if CURRENT_MENU_CODE && CURRENT_MENU_CODE == 4>current-menu-item</#if>" ><a href="/blog">博客</a></li>
						<li><a href="#">主题</a>
							<ul class="sub-menu">
								<li><a href="${base }/index.htm?skin=blue">蓝色</a></li>
								<li><a href="${base }/index.htm?skin=green">绿色</a></li>
								<li><a href="${base }/index.htm?skin=red">红色</a></li>
								<li><a href="${base }/index.htm?skin=default">默认</a></li>
							</ul>
						</li>
						<#if sessionUser != null>
						<li><a href="#">${sessionUser.username }</a>
							<ul class="sub-menu">
								<li><a href="${base }/logout.htm">登出</a></li>
							</ul>
						</li>
						</#if>
						<#if sessionUser == null>
							<li><a href="javascript:goLogin()">登录</a></li>
						</#if>
					</ul>
				</div>
			</nav>
			<!-- End of Main Navigation -->
		</div>
	</header>
</div>