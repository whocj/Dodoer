<div class="side_widget clearfix">
  <h3 class="headline">近期文章</h3>
  <ul>
  	<#list blogPostTop as datas>
  	
  		<#if datas.title?length gt 20> 
			<#assign varTitle = datas.title?substring(0, 20) + "…"> 
		<#else> 
			<#assign varTitle = datas.title> 
		</#if>
     	<li class="recentcomments">
     	<a href="${base}/b/detail/${datas.id }.html" >${varTitle }</a></li>
     </#list>
  </ul>
</div>