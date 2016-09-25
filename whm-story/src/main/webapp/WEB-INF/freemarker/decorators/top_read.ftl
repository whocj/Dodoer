<section class="widget">
	<div>
		<h3 class="title">热门小说</h3>
		<ul id="menu-quick-links">
			<#list readStoryList as datas>
				<li><a href="${base }/main/${datas.id }.html">
				[${datas.categoryName}]《${datas.title }》</a>${datas.statusTxt }</li>
			</#list>
		</ul>
	</div>
</section>