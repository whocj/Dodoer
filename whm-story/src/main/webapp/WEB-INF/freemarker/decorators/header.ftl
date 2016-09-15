<div class="header-wrapper">
	<header>
		<div class="container">
			<div class="logo-container">
				<!-- Website Logo -->
				<a href="${base }/index.html">
				<span class="tag-line" style="color:#FFF;font-size:18px;">
				多多儿Dodoer
				</span>
				</a>
				&nbsp;&nbsp;
				<span class="tag-line">看小说，上多多儿小说网！</span>
			</div>
			
			<!-- Start of Main Navigation -->
			<nav class="main-nav">
				<div class="menu-top-menu-container">
					<ul id="menu-top-menu" class="clearfix">
						<#list categoryList as datas>
							<li><a href="${base }/list/${datas.id}/1.html">${datas.title }</a></li>
						</#list>
						<#if sessionUser != null>
						<li>
<!-- 						<span id="qqLoginBtn"></span> -->
 							<img class="question_avatar" src="${sessionUser.userLogo }"/>
							<a href="${base }/user/bookshelf/list.html">${sessionUser.nickname }的书架</a>
						</li>
						<li>
							<a href="${base }/logout.html">退出</a>
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