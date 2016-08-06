<html>
<head>
	<#include "/blog/web/post/common/include.ftl"/>
	<meta name="keywords" content="${blogPost.keywords?html },${blogOptions.keywords},${siteKeys }" />
	<meta name="description" content="${blogPost.description?html },${blogOptions.description},${siteDesc }" />
	<title>${blogPost.title?html} Blog-${siteTitle }</title>
</head>
<body>
  <#include "/blog/web/post/common/head.ftl"/>
  <div id="content" class="clearfix">
    <div id="left_col" class="clearfix">
      <div class="post_wrap clearfix">
		  <div class="post">
		   <h3 class="title"><span>${blogPost.title}</span></h3>
		    <hr>
		    
		   <div class="post_content">
		     ${blogPost.excerpt}
		     <hr>
	         ${blogPost.content}
	         <div style="margin-top: 15px; font-style: italic;">
	           <p style="margin:0;"><strong>原创文章，转载请注明：</strong>转载自<b>${blogOptions.domainPath }</b></p>
	         </div>
	          <#include "/common/baidu_share.ftl"/>
	          <div style="margin-top: 15px;">
	          <ul>
		           	<#if prevPost??>
		           		<li class="recentcomments"><a style="color:#333;" href="${base }/b/detail/${prevPost.id}.html">上一篇:${prevPost.title }</a></li>
		           	</#if>
		           	<#if nextPost??>
		           		<li class="recentcomments"><a style="color:#333;" href="${base }/b/detail/${nextPost.id}.html">下一篇:${nextPost.title }</a></li>
		           	</#if>
	           	</ul>
	         </div>
	         
	          <hr>
	          <div class="side_widget clearfix">
			  <h3 class="headline">我的同类文章</h3>
			  <ul>
			  	<#list meSimilarList as datas>
			     	<li class="recentcomments">
			     	<a style="color:#333;" href="${datas.url}" >${datas.title }</a></li>
			     </#list>
			  </ul>
			</div>

	          <hr>
	          <div class="side_widget clearfix">
				  <h3 class="headline">猜您喜欢的文章 </h3>
				  <ul>
				  	<#list likeList as datas>
				     	<li class="recentcomments">
				     	<a style="color:#333;" href="${datas.url }" >${datas.title }</a></li>
				     </#list>
				  </ul>
				</div>
				
		   </div>
		  </div>
		  <div class="meta">
		    <ul>
		     <li class="post_date clearfix">
				${blogPost.createTime?string('yyyy-MM-dd HH:mm') }
		     </li>
		     <li class="post_read">${blogPost.rcount}人阅读</li>
		     <li class="post_author">
		       <a title="由${blogPost.creator}发布" href="${base }/b/main/${blogPost.creator}.html">${blogPost.creator}</a>
		     </li>
		     <li class="post_comment">
		       <a title="${blogPost.title}" href="#comments_wrapper">发表评论</a>
		     </li>
		    </ul>
		  </div>
		  <#include "/blog/web/post/common/left_menu.ftl"/>
		  
		</div>
      
      
      <div id="comments_wrapper">
        <div id="comment_header" class="clearfix">
          <span class="comments_right">评论 (${blogPost.ccount})</span>
        </div>
        <div id="comments">
          <div id="comment_area">
               <!-- comments_list -->
               <#include "/blog/web/post/comment/comments_list.ftl"/>
          </div>
          <!-- comments_form -->
          <#include "/blog/web/post/comment/comments_form.ftl"/>
        </div>
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
</body>
<#include "/common/spider_include.ftl"/>
</html>
