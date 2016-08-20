<nav class="s_nav">
	<section id="jt">
		<div id="nav">
			<ul>
				<li>
				<#list categoryList as datas>
					<a href="${base }/list/${datas.id}/1.html">${datas.title }</a>
					<#if datas_index + 1 == 4>
						</li>
						<li style="margin-top: -10px;">
					</#if>
				</#list>
				
				</li>
			</ul>
		</div>
	</section>
</nav>