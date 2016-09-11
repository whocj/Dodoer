<script type="text/javascript">
<!--

//-->
</script>

<header class="s_header">
	<nav>
		<span class="navt qq">多多儿小说网Dodoer</span>
		<span class="userspan">
		
		<#if sessionUser != null>
			<a href="${base }/user/bookshelf/list.html">${sessionUser.nickname }的书架</a>
			<a href="${base }/logout.html">退出</a>
		</#if>
		<#if sessionUser == null>
			<a href="${domain }${base }/login/index.html">登录</a>
		</#if>
		</span>
	</nav>
</header>