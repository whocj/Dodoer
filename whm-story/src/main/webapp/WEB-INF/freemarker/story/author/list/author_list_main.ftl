<div class="box_con">
	<div class="con_top">
		<a href="${base }/index.html">多多儿</a> &gt; 全部作家
	</div>
	<h1 class="music-seo">全部作家_作家排行榜_作家词条</h1>
	<div class="sidebar" monkey="sidebar">
<div class="top_tree">
        <h3 class="tree_index on">
        <a href="${base }/author/list/0.html">全部作家</a>
    </h3>
    <div class="module-dotted"></div>
       <dl class="tree_main">
         <#list categoryList as datas>
        <dd> 
            <a href="${base }/author/list/${datas.id }.html">${datas.title }</a>
        </dd>
     	</#list>
            </dl>
        </div>		
	</div>
	
	<div monkey="mainbody" class="main-body" style="overflow:hidden">
		<div class="head">
	<h2 class="title">
		全部作家
	</h2>
	<div class="index-tag clearfix">
		<div class="letter-index">
			<#list abcArr as datas>
				<a href="#${datas }">${datas }</a>
			</#list>
				</div>
	</div>	
</div>
<div class="module-line module-line-bottom"></div>
<div class="author_container">
		<!--热门作家-->
		<div class="list-item">		
		<div class="hot-head clearfix"> 
		<#list authorMap?keys as key>
				<h4><a name="top" id="${key }">${key }</a></h4>
        		<ul class="clearfix">
				<#list authorMap[key] as datas>
   					<li>
                       <a href="${base }/author/info/${datas.id }.html" title="${datas.name }" target="_blank">${datas.name }</a>
       				</li>
   				</#list>
   				</ul>
   			</#list>
            	</div>
        		
            </div>
           </div>																		
	</div>
</div>
