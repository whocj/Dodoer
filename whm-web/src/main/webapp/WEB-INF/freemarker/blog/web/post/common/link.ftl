<div  class="side_widget clearfix">
   <h3 class="headline">友情链接</h3>
   <ul>
     <#list friendLinkList as datas>
     	<li class="recentcomments">
     	<a href="${datas.domain }" target="_blank">${datas.siteName }</a></li>
     </#list>
   </ul>
</div>
