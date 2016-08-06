<div class="meta">
	<ul>
		<#if sessionUser != null>
			<li class="post_author"><a title="发贴" href="${base }/blog/backend/post/gotoUpdate.html">发贴</a></li>
			<li class="post_author"><a title="草稿" href="${base }/blog/post/main/${sessionUser.username }/0/1.html">草稿</a></li>
			
			<li class="post_author"><a title="友情连接" href="${base }/blog/backend/link/index.html">友情连接</a></li>
			<li class="post_author"><a title="标签" href="${base }/blog/backend/tag/index.html">标签编辑</a></li>
			<li class="post_author"><a title="选项" href="${base }/blog/backend/options/index.html">博客选项</a></li>
			
			<li class="post_author"><a title="选项" href="${base }/blog/backend/userinfo/index.html">用户信息</a></li>
			<li class="post_author"><a title="选项" href="${base }/blog/backend/userinfo/passwordupdate.html">密码修改</a></li>
		</#if>
	</ul>
</div>