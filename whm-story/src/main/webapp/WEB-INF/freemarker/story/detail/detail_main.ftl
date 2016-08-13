<div class="box_con">
	<div class="con_top">
		<a href="${base }">多多儿</a> &gt; ${storyInfo.categoryName } &gt; ${storyInfo.title }
	</div>
	<div class="bookname">
		<h1>《${storyInfo.title}》${storyDetail.title }</h1>
		<div class="bottem1">
			    <a href="javascript:ajaxGetForLayer('${base }/story/addLike/${storyInfo.id }.html')" >投推荐票</a>
				<#if prevStoryDetail!''>
					<a href="${base }/detail/${prevStoryDetail.id}.html">上一章←</a>
				</#if>
				<a href="${base }/main/${storyInfo.id}.html">章节列表</a>
				<#if nextStoryDetail!''>
					<a href="${base }/detail/${nextStoryDetail.id}.html">→下一章</a>
				</#if>
				<a href="javascript:ajaxGetForLayer('${base }/user/bookshelf/add/${storyInfo.id }.html')" >加入书签</a>
		</div>
		<div class="lm">
<!-- 			&nbsp;推荐阅读：<a target="_blank" href="/kan_61/" title="末日之城">末日之城</a><a -->
		</div>
	</div>

	<br>
		${storyDetail.content }
		<div class="bottem2">
		    <a href="javascript:ajaxGetForLayer('${base }/story/addLike/${storyInfo.id }.html')" >投推荐票</a>
			<#if prevStoryDetail!''>
				<a href="${base }/detail/${prevStoryDetail.id}.html">上一章←</a>
			</#if>
			<a href="${base }/main/${storyInfo.id}.html">章节列表</a>
			<#if nextStoryDetail!''>
				<a href="${base }/detail/${nextStoryDetail.id}.html">→下一章</a>
			</#if>
			<a href="javascript:ajaxGetForLayer('${base }/story/user/bookshelf/add/${storyInfo.id }.html')" >加入书签</a>
	</div>
</div>