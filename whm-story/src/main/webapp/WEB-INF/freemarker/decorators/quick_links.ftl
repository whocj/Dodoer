<section class="widget">
	<div>
		<h3 class="title">快速连接</h3>
		<ul id="menu-quick-links">
			<#list friendLinkList as datas>
				<li><a href="${datas.domain }" target="_blank">${datas.siteName }</a></li>
			</#list>
		</ul>
	</div>
</section>