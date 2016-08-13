<div class="side_widget clearfix">
   <h3 class="headline">标签</h3>
   <div id="tagcloud">
   	<ul>
  		<#list tagList as datas>
  		<li>
		<a href="${base }/search.html?keyword=${datas.name }" class="btn btn-mini">${datas.name }</a>
		</li>
	</#list>
	</ul>
   </div>
</div>