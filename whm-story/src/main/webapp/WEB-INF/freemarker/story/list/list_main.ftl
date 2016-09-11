<div id="hotcontent">
<div class="ll">
      <#list hotList as datas>
       <div class="item">
        <div class="image"><a href="${base }/main/${datas.id}.html">
        <img src="${datas.picPath }" onerror="nofind()"
        	alt="${datas.title }" style="width:120px;height:150px;"></a></div>
        <dl>
           <dt><span>${datas.author }</span><a href="${base }/main/${datas.id}.html">${datas.title }</a></dt>
           <dd>${datas.outline }</dd>
        </dl>
        <div class="clear"></div>
      </div>
	</#list>
</div>
<div class="clear"></div>
</div>
<div class="page-container">
	<div class="container" style="padding: 0px">
		<div class="row">
			<!-- start of page content -->
			<div class="span8">

			<div class="newscontent">
			<div class="l">
			<h2>最近更新小说列表</h2>
			<ul>
			<#list page.content as datas>
				<li>
					<span class="s1">[${datas.categoryName }]</span>
					<span class="s2">
					<a href="${base }/main/${datas.id}.html">${datas.title }</a>${datas.statusTxt }</span>
					<span class="s3"><a href="${base }/detail/${datas.id }/${datas.lastDetailId}.html">${datas.lastDetailTitle }</a></span>
					<span class="s4">${datas.author }</span>
					<span class="s5">${datas.lastUpdate?string('MM-dd') }</span>
				</li>
			</#list>
			 </ul>
			 <#include "/common/page.ftl"/>
			</div>
			</div>
			
			</div>
			<!-- end of page content -->

			<!-- start of sidebar -->
			<aside class="span4 page-sidebar">
			<#include "/decorators/top_like.ftl"/>
			<#include "/decorators/top_read.ftl"/>
			</aside>
			<!-- end of sidebar -->
		</div>
	</div>
</div>