<section class="widget">
	<h3 class="title">标签</h3>
	<div class="tagcloud">
		<#list tagList as datas>
			<a href="${base }/search.html?keyword=${datas.name }" class="btn btn-mini">${datas.name }</a>
		</#list>
	</div>	
</section>