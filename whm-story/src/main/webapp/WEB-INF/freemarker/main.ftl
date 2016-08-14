<style>

</style>
<div class="page-container">
	<div class="container">
		<div class="row">
			<!-- start of page content -->
			<div class="span8">
			<!-- start of hot content -->
			<div class="hotstory">
			<#list hotList as datas>
			  <div class="item">
			        <div class="image"><a href="${base }/main/${datas.id}.html">
			        <img src="${datas.picPath }" 
			         alt="${datas.title }" width="120" height="150"></a></div>
			        <dl>
			           <dt><a href="${base }/main/${datas.id}.html">${datas.title }</a><span>${datas.author }</span></dt>
			           <dd>
			           	${datas.outline }
			           </dd>
			        </dl>
			        <div class="clear"></div>
			  	</div>
			  </#list>
			</div>
			<!-- end of hot content -->



		<div class="newscontent">
		<div class="l">
		<h2>最近更新小说列表</h2>
		<ul>
		<#list lastUpdateStoryList as datas>
			<li>
				<span class="s1">[${datas.categoryName }]</span>
				<span class="s2">
				<a href="${base }/main/${datas.id}.html">${datas.title }</a></span>
				<span class="s3"><a href="${base }/detail/${datas.lastDetailId}.html" target="_blank">${datas.lastDetailTitle }</a></span>
				<span class="s4">${datas.author }</span>
				<span class="s5">${datas.lastUpdate?string('MM-dd') }</span>
			</li>
		</#list>
		 </ul>
		</div>
		</div>
		
		
		<div class="newscontent">
		<div class="l">
		<h2>最新入库小说</h2>
		<ul>
		
		<#list newStoryList as datas>
			<li>
				<span class="s1">[${datas.categoryName }]</span>
				<span class="s2">
				<a href="${base }/main/${datas.id}.html">${datas.title }</a></span>
				<span class="s3"><a href="${base }/detail/${datas.lastDetailId}.html" target="_blank">${datas.lastDetailTitle }</a></span>
				<span class="s4">${datas.author }</span>
				<span class="s5">${datas.createTime?string('MM-dd') }</span>
			</li>
		</#list>
		       
		 </ul>
		</div><div class="clear"></div>
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