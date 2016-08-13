<section class="widget">
	<h3 class="title">友情连接</h3>
	<ul id="recentcomments">
		<#list friendLinkList as datas>
			<li class="recentcomments">
				<a href="${datas.domain }" target="_blank">${datas.siteName }</a></li>
		</#list>
	</ul>
</section>