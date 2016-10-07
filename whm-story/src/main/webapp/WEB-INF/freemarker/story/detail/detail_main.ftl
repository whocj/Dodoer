<div class="box_con">
	<div class="con_top">
		<a href="${base }/index.html">多多儿</a> &gt; ${storyInfo.categoryName } &gt; ${storyInfo.title }
	</div>
	<div class="bookname">
		<h1>《${storyInfo.title}》${storyDetail.title }</h1>
		<div class="bottem1">
			    <a href="javascript:ajaxGetForLayer('${base }/addLike/${storyInfo.id }.html')" >投推荐票</a>
				<#if prevStoryDetail!''>
					<a href="${base }/detail/${storyDetail.storyId}/${prevStoryDetail.id}.html">上一章←</a>
				</#if>
				<a href="${base }/main/${storyInfo.id}.html">章节列表</a>
				<#if nextStoryDetail!''>
					<a href="${base }/detail/${storyDetail.storyId}/${nextStoryDetail.id}.html">→下一章</a>
				</#if>
				<a href="javascript:ajaxGetForLayer('${base }/user/bookshelf/add/${storyInfo.id }.html')" >加入书签</a>
		</div>
		
		<div id="listtj">
			&nbsp;热门推荐：
			<#list recHotStoryList as datas>
				<a target="_blank" href="${base }/main/${datas.id}.html" title="${datas.title }">${datas.title }</a>
			</#list>
		</div>
	</div>
		<div id="content">
		${storyDetail.content }
		</div>
		<div class="bottem2">
		    <a href="javascript:ajaxGetForLayer('${base }/addLike/${storyInfo.id }.html')" >投推荐票</a>
			<#if prevStoryDetail!''>
				<a href="${base }/detail/${storyDetail.storyId}/${prevStoryDetail.id}.html">上一章←</a>
			</#if>
			<a href="${base }/main/${storyInfo.id}.html">章节列表</a>
			<#if nextStoryDetail!''>
				<a href="${base }/detail/${storyDetail.storyId}/${nextStoryDetail.id}.html">→下一章</a>
			</#if>
			<a href="javascript:ajaxGetForLayer('${base }/user/bookshelf/add/${storyInfo.id }.html')" >加入书签</a>
	</div>
	<div id="listtj">
	&nbsp;新书推荐：
	<#list recNewStoryList as datas>
		<a target="_blank" href="${base }/main/${datas.id}.html" title="${datas.title }">${datas.title }</a>
	</#list>
	</div>
</div>