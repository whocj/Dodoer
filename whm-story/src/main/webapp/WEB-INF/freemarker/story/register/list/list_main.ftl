<div class="page-container">
	<div class="container">
		<div class="row">
			<!-- start of page content -->
			<div class="span8">

			<div class="newscontent">
			<div class="l">
			<h2>收录列表</h2>
			<ul>
			<#list page.content as datas>
				<li>
					<span class="s1">[${datas.categoryName }]</span>
					<span class="s2" style="width: 320px;">
					<#if datas.status == '2'>
						<a href="${base }/main/${datas.storyId}.html">${datas.title?html }</a>
					<#else>
						${datas.title?html }
					</#if>
						${datas.statusTxt }
					</span>
					<span class="s4">${datas.author?html }</span>
					<span class="s5">${datas.remark }</span>
				</li>
			</#list>
			 </ul>
			 <#include "/common/page.ftl"/>
			</div>
			</div>
			
			</div>
			<!-- end of page content -->

			<!-- start of sidebar -->
			<aside class="span4 page-sidebar">
			<#include "/decorators/top_like.ftl"/>
			<#include "/decorators/top_read.ftl"/>
			</aside>
			<!-- end of sidebar -->
		</div>
	</div>
</div>