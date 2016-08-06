<html>
<head>
	<#include "/blog/web/post/common/include.ftl"/>
	<meta name="keywords" content="${blogOptions.keywords?html},${siteKeys }" />
	<meta name="description" content="${blogOptions.description?html},${siteDesc }" />
	<title>${blogOptions.title?html} - Blog-${siteTitle }</title>
</head>
<body>
  <#include "/blog/web/post/common/head.ftl"/>
  <div id="content" class="clearfix">
    <div id="left_col" class="clearfix">
      <div class="post_wrap clearfix">
		  <div class="post">
		 	<h3 class="title"><span>${htmlTitle?html }</span></h3>
		    <hr>
		  	<div class="post_content">
		  		<#list page.content as datas>
					<div class="clearfix">
						
						<h3 class="post-title">
							<a href="${base }/b/detail/${datas.id }.html">${datas.title}</a>
						</h3>
						<#if sessionUser != null && sessionUser.username == datas.creator>
							<a href="${base }/blog/backend/post/gotoUpdate.html?id=${datas.id}">修改</a> 
							| <a href="${base }/blog/backend/post/delete.html?id=${datas.id}" onclick="if(!confirm('你确定要删除吗?')) {return false;}" >删除</a>
						</#if>
						<div class="post-meta clearfix">
							<span class="date">${datas.createTime?string('yyyy-MM-dd HH:mm') }</span> <span class="category">
									${datas.tagName }</span> <span class="comments">
									<a href="${base }/b/detail/${datas.id }.html#comments_wrapper" title="${datas.title}">
								${datas.ccount	} 评论</a></span> 
								<span class="like-count">${datas.rcount }</span>
						</div>
						<!-- end of post meta -->

					</div>
					
						<#if datas.excerpt?length gt 200> 
							<#assign questionContentText = datas.excerpt?substring(0, 200) + "…"> 
							${questionContentText?html} 
						<#else> 
							${datas.excerpt?html} 
						</#if>
					
					<a class="readmore-link" href="${base }/b/detail/${datas.id }.html">浏览更多</a> 

				</#list>
				<#include "/common/page.ftl"/> 
		  </div>
		  </div>
		 <#include "/blog/web/post/common/left_menu.ftl"/>
		</div>

    </div>
    <div id="post_mask"></div>
    <div id="right_col">
      <#include "/blog/web/post/common/about_me.ftl"/>

      <#include "/blog/web/post/common/recent.ftl"/>
      <#include "/blog/web/post/common/tagcloud.ftl"/>
      <#include "/blog/web/post/common/link.ftl"/>
      
    </div>
    <#include "/blog/web/post/common/footer.ftl"/>
  </div>
<#include "/common/spider_include.ftl"/>  
</body>
</html>


