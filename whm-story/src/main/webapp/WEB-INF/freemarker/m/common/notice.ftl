<div class="module module-margin">
	<#list noticeList as datas>
		<div class="topic">
			<ul class="cont-list">
				<li>
					<span class="live-icon">公告</span><a href="${base }/notice/${datas.id}.html">${datas.title }</a>
				</li>
			</ul>
		</div>
	</#list>
</div>