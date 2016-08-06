<script type="text/javascript">
<!--
	function logout(url){
		location.href = url;
	}
	//-->
</script>

<div class="header-wrapper">
		<div style="margin-left:10px">
			<div class="logo-container" >
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
			<div class="main-nav" style="margin-right: 10px;">
				
				<div class="menu-top-menu-container" style="">
					<ul id="menu-top-menu" class="clearfix">
						<li class="<#if CURRENT_MENU_CODE && CURRENT_MENU_CODE == 4>current-menu-item</#if>"><a href="${base }/blog/index.html">系统设置</a></li>
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
			</div>
			<!-- End of Main Navigation -->
		</div>
</div>