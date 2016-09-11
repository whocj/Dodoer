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
</div>