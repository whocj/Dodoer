<div class="box_con white">
	<div class="con_top">
		<a href="${base }">多多儿</a> &gt; ${storyInfo.categoryName }  &gt; ${storyInfo.title }最新章节列表
	</div>
	<div id="maininfo">
		<div id="info">
			<h1>${storyInfo.title }</h1>
			<p>作者：${storyInfo.author }</p>
			<p>最后更新：${storyInfo.lastUpdate?string('yyyy-MM-dd HH:mm') }</p>
			<p>
				操作：<a href="javascript:ajaxGetForLayer('${base }/addLike/${storyInfo.id }.html')" >投推荐票</a>
				<a href="javascript:ajaxGetForLayer('${base }/user/bookshelf/add/${storyInfo.id }.html')" >加入书签</a>
			</p>
		</div>
		<div id="intro">
			<p>
				${storyInfo.outline }</p>
		</div>
	</div>
	<div id="sidebar">
		<div id="fmimg">
			<img alt="${storyInfo.title }" src="${storyInfo.picPath }"
				width="120" height="150">
		</div>
	</div>
<!-- 	<div id="listtj"> -->
<!-- 		&nbsp;推荐阅读：<a target="_blank" href="/" title="活人禁地">活人禁地</a><a -->
<!-- 	</div> -->
</div>