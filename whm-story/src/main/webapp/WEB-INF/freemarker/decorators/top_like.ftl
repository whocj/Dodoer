<section class="widget">
	<div>
		<h3 class="title">喜爱小说</h3>
		<ul id="menu-quick-links">
			<#list likeStoryList as datas>
				<li><a href="${base }/main/${datas.id }.html">[${datas.categoryName}]${datas.title }</a></li>
			</#list>
		</ul>
	</div>
</section>