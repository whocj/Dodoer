<section class="widget">
	<div>
		<h3 class="title">最爱吐槽</h3>
		<ul id="menu-quick-links">
			<#list replyStoryList as datas>
				<li><a href="${base }/main/${datas.id }.html">
				[${datas.categoryName}]《${datas.title }》</a>${datas.statusTxt }</li>
			</#list>
		</ul>
	</div>
</section>