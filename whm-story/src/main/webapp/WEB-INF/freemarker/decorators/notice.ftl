<section class="widget">
	<h3 class="title">最新公告</h3>
	<ul id="recentcomments">
		<#list noticeList as datas>
			<li class="recentcomments">
				<a href="${base }/notice/${datas.id}.html">${datas.title }</a></li>
		</#list>
	</ul>
</section>