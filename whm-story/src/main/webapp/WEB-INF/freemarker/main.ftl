<div class="page-container">
	<div class="container">
	
		<div class="row">
			<!-- start of page content -->
			<div class="span8">
			<!-- start of hot content -->
			<div class="newscontent">
		<div class="l">
		<h2>热门小说</h2>
			<div class="hotstory">
			<#list hotList as datas>
			  <div class="item">
			        <div class="image"><a href="${base }/main/${datas.id}.html">
			        <img alt="${datas.title }" src="${datas.picPath }" onerror="nofind()"
			         width="120" height="150"></a></div>
			        <dl>
			           <dt><a href="${base }/main/${datas.id}.html">${datas.title }</a>${datas.statusTxt }<span>${datas.author }</span></dt>
			           <dd>
			           	${datas.outline }
			           </dd>
			        </dl>
			        <div class="clear"></div>
			  	</div>
			  </#list>
			</div>
			</div>
			</div>
		<!-- end of hot content -->
		
		<#list storyTopicList as datas>
		<div class="newscontent">
		<div class="l">
		<h2>${datas.title }</h2>
		<div class="channel_center p10">
			<#list datas.topicDetailList as story>
				<#if story_index  &lt; 2>
				<div class="channel_content <#if story_index == 0>ileft<#else>iright</#if>">
				<div class="hot_cover_mini"><a href="${base }/main/${story.storyId}.html">
				<img src="${story.picPath }" alt="${story.title }"
				 onerror="nofind()" width="120" height="150"></a></div>
					<ul class="hot_info" style="padding: 1px;">
					<li class="book">《<a href="${base }/main/${story.storyId}.html" class="f14 c3">${story.title }</a>》${story.statusTxt }</li>
					<li class="author">作者：${story.author }</li>
					<p class="hot_desc c9" style="margin: 0 0 0px;">${story.outline}...</p>
					</ul>
				</div>
				</#if>
			</#list>
			<div class="clearfix"></div>
			<br/>
			<span class="hot_box mt10"><b>精品推荐</b></span>
			<ul class="hot_more" style="padding: 1px;">
			<#list datas.topicDetailList as story>
				<#if story_index  &lt; 12>
					<#if story_index  &gt; 1>
						<li>[${story.categoryName }]&nbsp;<a href="${base }/main/${story.storyId}.html">${story.title }</a>${story.author }${story.statusTxt }</li>
					</#if>
				</#if>
			</#list>
			</ul>
			</div>
		</div>
		<div class="clear"></div>
		</div>
		</#list>
		
		<div class="newscontent">
		<div class="l">
		<h2>最新上架小说</h2>
		<ul>
		<#list newStoryList as datas>
			<li>
				<span class="s1">[${datas.categoryName }]</span>
				<span class="s2">
				<a href="${base }/main/${datas.id}.html">${datas.title }</a>${datas.statusTxt }</span>
				<span class="s3"><a href="${base }/detail/${datas.id }/${datas.lastDetailId}.html" target="_blank">${datas.lastDetailTitle }</a></span>
				<span class="s4">${datas.author }</span>
				<span class="s5">${datas.createTime?string('MM-dd') }</span>
			</li>
		</#list>
		 </ul>
		</div><div class="clear"></div>
		</div>

		<div class="newscontent">
		<div class="l">
		<h2>最近更新小说</h2>
		<ul>
		<#list lastUpdateStoryList as datas>
			<li>
				<span class="s1">[${datas.categoryName }]</span>
				<span class="s2">
				<a href="${base }/main/${datas.id}.html">${datas.title }</a>${datas.statusTxt }</span>
				<span class="s3"><a href="${base }/detail/${datas.id }/${datas.lastDetailId}.html" target="_blank">${datas.lastDetailTitle }</a></span>
				<span class="s4">${datas.author }</span>
				<span class="s5">${datas.lastUpdate?string('MM-dd') }</span>
			</li>
		</#list>
		 </ul>
		</div>
		</div>
			
			</div>
			<!-- end of page content -->

			<!-- start of sidebar -->
			<aside class="span4 page-sidebar">
			<#include "/decorators/support.ftl"/>
			<#include "/decorators/notice.ftl"/>
			<#include "/decorators/top_like.ftl"/>
			<#include "/decorators/top_read.ftl"/>
			<#include "/decorators/top_reply.ftl"/>
			<#include "/decorators/quick_links.ftl"/>
			</aside>
			<!-- end of sidebar -->
		</div>
	</div>
</div>