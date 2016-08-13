
<div class="page-container">
	<div class="container">
		<div class="row">

			<!-- start of page content -->
			
			<#if page.totalCount gt 0>
				<div class="span8 main-listing">
					<#list page.content as datas>
					<article class="format-standard type-post hentry clearfix">
	
						<header class="clearfix">
							<h3 class="post-title">
								<a href="${datas.url }">${datas.title}</a>
							</h3>
	
							<div class="post-meta clearfix">
								<span class="date">${datas.createTime }</span> <span class="category">
										${datas.tags }
									</span>
									<span style="background: url(${resRoot}/images/user.png) no-repeat left center"> 
										${datas.author }
									</span>
							</div>
							<!-- end of post meta -->
						</header>
						<p>	
					         ${datas.content}
						</p>
					</article>
					</#list>
					<#include "/common/search_page.ftl"/> 
				</div>
			<#else>
				<div class="span8 main-listing">
					没有你到你的答案,请点击<a href="${base }/ask/question/ask.html">提问</a>,让更多的人来帮你解决!
				</div>
			</#if>
			
			<!-- end of page content -->

			<!-- start of sidebar -->
			<aside class="span4 page-sidebar">
				<#include "/decorators/support.ftl"/>
				<#include "/decorators/latest_topic.ftl"/>
				<#include "/decorators/latest_blog.ftl"/>
			</aside>
			<!-- end of sidebar -->
		</div>
	</div>
</div>