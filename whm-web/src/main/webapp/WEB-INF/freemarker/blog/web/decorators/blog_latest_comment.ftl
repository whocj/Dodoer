<section class="widget">
	<h3 class="title">最新文章</h3>
	<ul id="recentcomments">
		<#list blogPostList as datas>
			<li class="recentcomments">
				<a href="${base }/b/detail/${datas.id}.html">${datas.title }</a></li>
		</#list>
	</ul>
</section>