<div class="page-container">
	
	<div class="container">
		<div class="row">
			<!-- start of page content -->
			<div class="span8 main-listing">
				<div class="top_question_select">
				<table>
				<tr>
				<td width="15%" style="border-bottom:2px solid gray;"><h2><a href="${base }/q/list.html?type=new">最新的</a></h2></td>
				<td width="15%"><h2><a href="${base }/q/list.html?type=hot">热门的</a></h2></td>
				<td width="15%"><h2><a href="${base }/q/list.html?type=no">未解决</a></h2></td>
				<td width="40%">
					<input id="submit_comment" style="margin-top:2px;margin-right:10px;font-size: inherit;" type="button" onclick="gotoUrl('${base}/q/ask.html')" value="我要提问?" /></td>
				</tr>
				</table>
				</div>
				<#list latestList as datas>
				<div class="stream-list__item">
                <div class="qa-rank">
            <div class="answers answered">
                ${datas.replyCount }<small>回答</small>
            </div>
            <div class="views hidden-xs">
                ${datas.viewCount }<small>浏览</small>
            </div>
        </div>
        <div class="summary">
           		 <h4 class="title">
            	<img class="question_avatar"  src="${datas.userLogo }" alt="${datas.username }" height="20px" width="20px">
            	<a href="${base }/q/detail/${datas.id}.html">${datas.questionTitle?html}</a>
            	</h4>${datas.createTime?string('MM-dd HH:mm') }  
           		<#list datas.tagNameList as tag>
	             	<a class="tag tag-sm" href="${base }/search.html?keyword=${tag}">${tag }</a>
	             </#list>
	             <br><span>${datas.description?html}<span>
	       </div>
	    </div>
				</#list>
			</div>
			<!-- end of page content -->

			<!-- start of sidebar -->
			<aside class="span4 page-sidebar">
			<#include "/decorators/support.ftl"/>
			<#include "/decorators/tags.ftl"/>
			<#include "/decorators/latest_topic.ftl"/>
			<#include "/decorators/latest_blog.ftl"/>
			<#include "/decorators/quick_links.ftl"/>
			<#include "/decorators/adsense/index_right.ftl"/>
			</aside>
			<!-- end of sidebar -->
		</div>
	</div>
</div>