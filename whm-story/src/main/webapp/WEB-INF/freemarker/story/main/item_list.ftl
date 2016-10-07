<div class="box_con white">
<div class="con_top">
		《${storyInfo.title }》全部章节列表
	</div>
	<div id="main_list">
		<dl>
			<#list storyInfo.storyDetailList as datas>
			<dd>
				<a href="${base }/detail/${datas.storyId }/${datas.id }.html">${datas.title }</a>
			</dd>
			</#list>
			
			&nbsp;&nbsp;

		</dl>
	</div>
	<div id="listtj">
	&nbsp;新书推荐：
	<#list recNewStoryList as datas>
		<a target="_blank" href="${base }/main/${datas.id}.html" title="${datas.title }">${datas.title }</a>
	</#list>
	</div>
</div>
