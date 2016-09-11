<style>
<!--
.music-seo{
	display:none;
}
.sidebar {
    width: 180px;
    float: left;
    overflow: hidden;
}
.top_tree .tree_main {
    margin: 10px 0;
}
.top_tree dt {
    height: 30px;
    line-height: 30px;
    font-weight: 700;
    color: #333;
    padding-left: 30px;
}
.top_tree dd {
    position: relative;
}
.top_tree a {
    height: 30px;
    line-height: 30px;
    display: block;
    padding-left: 30px;
}
.main-body {
    width: 920px;
    float: right;
    overflow: hidden;
    border-left: 1px solid #B3B3B3;
    padding-left: 50px;
}
.head {
    margin-bottom: 10px;
}
.head .title {
    font-family: Microsoft YaHei, "幼圆";
    font-weight: 500;
    font-size: 22px;
    line-height: 24px;
}
.index-tag {
    padding: 5px 0 10px;
}
.index-tag h3 {
    padding-right: 7px;
    color: #999;
    float: left;
    line-height: 30px;
    font-size: 14px;
}
.index-tag a, .index-tag span {
    border-radius: 3px;
    display: block;
    float: left;
    font-size: 14px;
    line-height: 30px;
    padding: 0 8px;
}
.module-line {
    background-color: #e4e4e4;
    overflow: hidden;
    height: 1px;
    width: 90%;
}
.author_container {
    margin-top: -5px;
    position: relative;
    font-size: 14px;
    padding-bottom: 40px;
}
.list-item {
    margin-bottom: 15px;
}
.main-body .hot-head {
    margin-bottom: 40px;
}
.hot-head h3 {
    font-weight: bold;
    padding: 15px 0;
}
.hot-head .cover-item {
    height: 163px;
    overflow: hidden;
}
.cover-item {
    float: left;
    margin: 0 35px 10px 0;
    font-size: 14px;
    width: 130px;
}
.cover-img {
    width: 130px;
    height: 130px;
    border-radius: 5px;
    box-shadow: 0px 1px 3px #999;
}
.cover-img .cover {
    float: left;
    overflow: hidden;
}
.cover-item dd {
    overflow: hidden;
    white-space: nowrap;
}
.list-item li {
    float: left;
    width: 130px;
    padding: 5px 0;
    margin-right: 35px;
    overflow: hidden;
    white-space: nowrap;
}
-->
</style>
<div class="box_con">
	<div class="con_top">
		<a href="${base }/index.html">多多儿</a> &gt; 全部作家
	</div>
	<h1 class="music-seo">全部作家</h1>
	<div class="sidebar" monkey="sidebar">
<div class="top_tree">
        <h3 class="tree_index on">
        <a href="/artist">全部作家</a>
    </h3>
    <div class="module-dotted"></div>
       <dl class="tree_main">
                <dt>ALL</dt>
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
	<!--热门歌手-->
		<div class="list-item">		
		<div class="hot-head clearfix"> 
		<h3><a name="top" id="Hot">Hot</a></h3>
			<#list authorList as datas>
				<dl class="cover-item">              				            					                        				            					                        				            					                        				            					            				
					<dt class="cover-img">
						<a class="cover" href="${base }/author/info/${datas.id }.html">
							<img title="${datas.name }" src="http://musicdata.baidu.com/data2/pic/56c7ef8b6895a862a562884947fbf420/246669449/246669449.jpg">
						</a>
					</dt>
					<dd>
						<a href="${base }/author/info/${datas.id }.html" title="${datas.name }">${datas.name }</a>
					</dd>
				</dl>
            	</#list>
            	</div>
        		<ul class="clearfix">
				<#list authorList as datas>
   					<li>
                       <a href="${base }/author/info/${datas.id }.html" title="${datas.name }">${datas.name }</a>
       				</li>
   				</#list>
   				</ul>
            </div>
           </div>																		
	</div>
</div>
