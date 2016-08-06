<div class="page-container">
	<div class="container">
		<div class="row">

			<!-- start of page content -->
			<div class="span8 page-content">

				<!-- Basic Home Page Template -->
				<div class="row separator">
					<section class="span4 articles-list">
						<h3>精选问题</h3>
						<ul class="articles">
						<#list featuredList as datas>
							<li class="article-entry standard">
								<h4>
									<a href="${base }/q/detail/${datas.id }.html">
									  <#if datas.questionTitle?length gt 25>
								         <#assign questionTitle = datas.questionTitle?substring(0, 25) + "…">
								         ${questionTitle}
								      <#else>
								         ${datas.questionTitle}
								      </#if>
									</a>
								</h4> <span class="article-meta">${datas.createTime?string('yyyy-MM-dd HH:mm') }&nbsp;&nbsp;
								<#if datas.tagName?length gt 10>
							         <#assign tagName = datas.tagName?substring(0, 10) + "…">
							         ${tagName}
							      <#else>
							         ${datas.tagName}
							      </#if>
								</span> <span class="like-count">${datas.viewCount}</span>
							</li>
						</#list>
						</ul>
					</section>

					<section class="span4 articles-list">
						<h3>最新问题</h3>
						<ul class="articles">
							<#list latestList as datas>
								<li class="article-entry standard">
								<h4>
									<a href="${base }/q/detail/${datas.id }.html">
										<#if datas.questionTitle?length gt 25>
								         <#assign questionTitle = datas.questionTitle?substring(0, 25) + "…">
								         ${questionTitle}
								      <#else>
								         ${datas.questionTitle}
								      </#if>
									</a>
								</h4> <span class="article-meta">${datas.createTime?string('yyyy-MM-dd HH:mm') }&nbsp;&nbsp;
									<#if datas.tagName?length gt 10>
							         <#assign tagName = datas.tagName?substring(0, 10) + "…">
							         ${tagName}
							      <#else>
							         ${datas.tagName}
							      </#if>
							      </span> <span class="like-count">${datas.viewCount}</span>
							</li>
							</#list>
						</ul>
					</section>
				
					<section class="span4 articles-list">
						<h3>精选主题</h3>
						<ul class="articles">
						<#list hotTopicList as datas>
							<li class="article-entry standard">
								<h4>
									<a href="${base }/t/detail/${datas.id }.html">
									  <#if datas.title?length gt 25>
								         <#assign title = datas.title?substring(0, 25) + "…">
								         ${title}
								      <#else>
								         ${datas.title}
								      </#if>
									</a>
								</h4> <span class="article-meta">${datas.createTime?string('yyyy-MM-dd HH:mm') }&nbsp;&nbsp;
								<a href="${base }/b/main/${datas.username }.html">${datas.username }</a>
									</span> <span class="like-count">${datas.viewCount}</span>
							</li>
						</#list>
						</ul>
					</section>

					<section class="span4 articles-list">
						<h3>最新主题</h3>
						<ul class="articles">
							<#list topicList as datas>
								<li class="article-entry standard">
								<h4>
									<a href="${base }/t/detail/${datas.id }.html">
										<#if datas.title?length gt 25>
								         <#assign title = datas.title?substring(0, 25) + "…">
								         ${title}
								      <#else>
								         ${datas.title}
								      </#if>
									</a>
								</h4> <span class="article-meta">${datas.createTime?string('yyyy-MM-dd HH:mm') }&nbsp;&nbsp;
								<a href="${base }/b/main/${datas.username }.html">${datas.username }</a>
									</span> <span class="like-count">${datas.viewCount}</span>
							</li>
							</#list>
						</ul>
					</section>
					
					<section class="span4 articles-list">
						<h3>精选博客</h3>
						<ul class="articles">
						<#list hotBlogPostList as datas>
							<li class="article-entry standard">
								<h4>
									<a href="${base }/b/detail/${datas.id }.html">
									  <#if datas.title?length gt 25>
								         <#assign title = datas.title?substring(0, 25) + "…">
								         ${title}
								      <#else>
								         ${datas.title}
								      </#if>
									</a>
								</h4> <span class="article-meta">${datas.createTime?string('yyyy-MM-dd HH:mm') }&nbsp;&nbsp;
									<a href="${base }/b/main/${datas.creator }.html">
									${datas.creator }
									</a>
								</span> <span class="like-count">${datas.rcount}</span>
							</li>
						</#list>
						</ul>
					</section>

					<section class="span4 articles-list">
						<h3>最新博客</h3>
						<ul class="articles">
							<#list blogPostList as datas>
								<li class="article-entry standard">
								<h4>
									<a href="${base }/b/detail/${datas.id }.html">
										<#if datas.title?length gt 25>
								         <#assign title = datas.title?substring(0, 25) + "…">
								         ${title}
								      <#else>
								         ${datas.title}
								      </#if>
									</a>
								</h4> <span class="article-meta">${datas.createTime?string('yyyy-MM-dd HH:mm') }&nbsp;&nbsp;
									<a href="${base }/b/main/${datas.creator }.html">
									${datas.creator }
									</a>
							</span> <span class="like-count">${datas.rcount}</span>
							</li>
							</#list>
						</ul>
					</section>
				</div>
				
			</div>
			<!-- end of page content -->

			<!-- start of sidebar -->
			<aside class="span4 page-sidebar">
			<#include "/decorators/support.ftl"/>
			<#include "/decorators/tags.ftl"/>
			<#include "/decorators/quick_links.ftl"/>
			
			</aside>
			<!-- end of sidebar -->
		</div>
	</div>
</div>