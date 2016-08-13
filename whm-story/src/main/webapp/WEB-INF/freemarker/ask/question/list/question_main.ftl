
<div class="page-container">
	<div class="container">
		<div class="row">
			<!-- start of page content -->
			<div class="span8 main-listing">
				<div class="top_question_select">
				<table>
				<tr>
				<td width="15%" <#if type=='new'>style="border-bottom:2px solid gray;"</#if> >
					<h2><a href="${base }/q/list.html?type=new">最新的</a></h2></td>
				<td width="15%" <#if type=='hot'>style="border-bottom:2px solid gray;"</#if> ><h2><a href="${base }/q/list.html?type=hot">热门的</a></h2></td>
				<td width="15%" <#if type=='no'>style="border-bottom:2px solid gray;"</#if> ><h2><a href="${base }/q/list.html?type=no">未解决</a></h2></td>
				<td width="40%">
					<input id="submit_comment" style="margin-top:2px;margin-right:10px;font-size: inherit;" type="button" onclick="gotoUrl('${base}/q/ask.html')" value="我要提问?" /></td>
				</tr>
				</table>
				</div>
				<#list page.content as datas>
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
		            	<a href="${base }/q/detail/${datas.id}.html">${datas.questionTitle?html}</a>
		            </h4>${datas.createTime?string('MM-dd HH:mm') }<img class="question_avatar" 
		            	src="${datas.userLogo }" alt="${datas.username }" height="20px" width="20px">
			             <br><span>${datas.description?html}<span>
			             <#list datas.tagNameList as tag>
			             	<a class="tag tag-sm" href="${base }/search.html?keyword=${tag}">${tag }</a>
			             </#list>
			       </div>
			    </div>
				</#list>
				<#include "/common/page.ftl"/> 
			</div>
			<!-- end of page content -->

			<!-- start of sidebar -->
			<aside class="span4 page-sidebar">
				<#include "/decorators/support.ftl"/> 
				<#include "/decorators/tags.ftl"/>
				<#include "/decorators/latest_topic.ftl"/>
				<#include "/decorators/latest_blog.ftl"/>
				<#include "/decorators/adsense/index_right.ftl"/>
			<!-- end of sidebar -->
		</div>
	</div>
</div>
