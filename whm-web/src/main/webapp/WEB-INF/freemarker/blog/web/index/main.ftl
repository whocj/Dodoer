<div class="page-container">
	<div class="container">
		<div class="row">

			<!-- start of page content -->
			<div class="span8 main-listing">
				<#list page.content as datas>
				<article class="format-standard type-post hentry clearfix">

					<header class="clearfix">

						<h3 class="post-title">
							<a href="${base }/b/detail/${datas.id }.html">${datas.title}</a>
						</h3>

						<div class="post-meta clearfix">
							<span class="date">${datas.createTime?string('yyyy-MM-dd HH:mm') }</span>
							<span style="background: url(${resRoot}/images/user.png) no-repeat left center"> 
							<a href="${base }/blog/main/${datas.creator }.html">${datas.creator }
							</a></span>
							<span class="category">
									${datas.tagName }</span> <span class="comments">
									<a href="${base }/b/detail/${datas.id }.html#comments_wrapper" title="${datas.title}">
								${datas.ccount	} 评论</a></span> 
								<span class="like-count">${datas.rcount }</span>
						</div>
						<!-- end of post meta -->

					</header>
					<p>
						<#if datas.excerpt?length gt 200> 
							<#assign excerpt = datas.excerpt?substring(0, 200) + "…"> 
							${questionContentText} 
							<a class="readmore-link" href="${base }/b/detail/${datas.id }.html">查看更多</a> 
						<#else> 
							${datas.excerpt} 
						</#if>
					</p>

				</article>
				</#list>
				<#include "/common/page.ftl"/> 

			</div>
			<!-- end of page content -->

			<!-- start of sidebar -->
			<aside class="span4 page-sidebar">
				<#include "/blog/web/decorators/blog_tags.ftl"/>
				<#include "/blog/web/decorators/blog_latest_comment.ftl"/>
			</aside>
			<!-- end of sidebar -->
		</div>
	</div>
</div>
