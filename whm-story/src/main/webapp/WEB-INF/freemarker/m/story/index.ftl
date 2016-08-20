<html>
<head>
<meta charset="UTF-8"> 
<title>首页 - ${siteTitle }</title>
<meta name="keywords" content="${siteKeywords }">
<meta name="description" content="${siteDescription }">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"> 
<meta content="yes" name="apple-mobile-web-app-capable"> 
<meta content="black" name="apple-mobile-web-app-status-bar-style"> 
<meta content="telephone=no" name="format-detection"> 
<link rel="stylesheet" href="${resRoot}/m/css/common.css">
<#include "/m/common/include.ftl">
<#include "/m/common/include_js.ftl">
</head>
<body>
<div id="main_body">
	 <#include "/m/common/header.ftl">
	 <#include "/m/common/menu.ftl">
	
	<section class="s_moreread">
		<div class="module-t">
			<h3><a href="liebiao.html">热门小说</a></h3>
		</div>
		<div class="list_box">
			<ul>
			<#list hotList as datas>
			<li>
				<a href="${base }/main/${datas.id}.html">
				<img src="${datas.picPath }" width="150" height="180" onerror="nofind()" alt="${datas.title }|${datas.author }">
				<span>${datas.title }|${datas.author }</span></a>
			</li>
			</#list>
			</ul>
			<br style="clear: both;">
		</div>
	</section>
	<!-- Start of notice -->
	<#include "/m/common/notice.ftl">
	<!-- Start of notice -->

	<section class="s_moreread">
		<div class="module-t">
			<h3><a href="">最近上架小说</a></h3>
		</div>
		<div class="list_box">
		<#list newStoryList as datas>
			<#if datas_index == 0>
			<dl>
			<a href="${base }/main/${datas.id}.html">
				<dt><img src="${datas.picPath }"  width="120" height="150" onerror="nofind()" alt="${datas.title }" ></dt>
				<dd><h3>[${datas.categoryName }]${datas.title }</h3></dd>
				<dd>${datas.outline }</dd>
				<dd><span>阅读：${datas.readCount }</span>点攒：${datas.likeCount }</dd>
				</a>
			</dl>
			<#elseif datas_index &lt; 10>
			<a href="${base }/main/${datas.id}.html" class="item">[${datas.categoryName }]${datas.title }</a>
			</#if>
		</#list>
		</div>
	</section>
	
	<section class="s_moreread">
		<div class="module-t">
			<h3><a href="">阅读人数最多小说</a></h3>
		</div>
		<div class="list_box">
		<#list readStoryList as datas>
			<#if datas_index == 0>
			<dl>
			<a href="${base }/main/${datas.id}.html">
				<dt><img src="${datas.picPath }" onerror="nofind()"  width="120"  height="150" alt="${datas.title }" ></dt>
				<dd><h3>[${datas.categoryName }]${datas.title }</h3></dd>
				<dd>${datas.outline }</dd>
				<dd><span>阅读：${datas.readCount }</span>点攒：${datas.likeCount }</dd>
				</a>
			</dl>
			<#elseif datas_index &lt; 10>
			<a href="${base }/main/${datas.id}.html" class="item">[${datas.categoryName }]${datas.title }</a>
			</#if>
		</#list>
		</div>
	</section>
	
	<section class="s_moreread">
		<div class="module-t">
			<h3><a href="">喜爱人数最多小说</a></h3>
		</div>
		<div class="list_box">
		<#list likeStoryList as datas>
			<#if datas_index == 0>
			<dl>
			<a href="${base }/main/${datas.id}.html">
				<dt><img src="${datas.picPath }" onerror="nofind()"   width="120" height="150" alt="${datas.title }" ></dt>
				<dd><h3>[${datas.categoryName }]${datas.title }</h3></dd>
				<dd>${datas.outline }</dd>
				<dd><span>阅读：${datas.readCount }</span>点攒：${datas.likeCount }</dd>
				</a>
			</dl>
			<#elseif datas_index &lt; 10>
			<a href="${base }/main/${datas.id}.html" class="item">[${datas.categoryName }]${datas.title }</a>
			</#if>
		</#list>
		</div>
	</section>
	
	<section class="s_moreread">
		<div class="module-t">
			<h3><a href="">吐槽人数小说</a></h3>
		</div>
		<div class="list_box">
		<#list replyStoryList as datas>
			<#if datas_index == 0>
			<dl>
			<a href="${base }/main/${datas.id}.html">
				<dt><img src="${datas.picPath }" onerror="nofind()"  width="120"  height="150" alt="${datas.title }" ></dt>
				<dd><h3>[${datas.categoryName }]${datas.title }</h3></dd>
				<dd>${datas.outline }</dd>
				<dd><span>阅读：${datas.readCount }</span>点攒：${datas.likeCount }</dd>
				</a>
			</dl>
			<#elseif datas_index &lt; 10>
			<a href="${base }/main/${datas.id}.html" class="item">[${datas.categoryName }]${datas.title }</a>
			</#if>
		</#list>
		</div>
	</section>

	
	
	


	<#include "/m/common/copyright.ftl">

	<div id="favtip" style="display: none; left: 549px; ">
		<a id="closetip" href="javascript:void(0);" style=""><span></span></a>
	</div>
	<div id="gotop" class="gotop" style="display:none">
		<img src="${resRoot}/m/img/gotop.png" width="100%" height="100%">
	</div>
</div>
<script type="text/javascript">
$('#closetip').click(function(){
	$('#favtip').hide();
})
function hideGoTop(){
	var top = document.body.scrollTop;
	if (top > 250) {
		$('#gotop').show();
	} else {
		$('#gotop').hide();
	}
}
window.onscroll=hideGoTop;
$('#gotop').click(function(){
	document.body.scrollTop=0;
});
var dwidth = $('body').width();
$('#favtip').css('left', (dwidth-210)/2);
var ua = navigator.userAgent;
if(/iPhone|iPad/g.test(ua) && ua.indexOf('Safari') > 0) {
	$('#favtip').show();
	window.setTimeout("$('#favtip').hide();", 6000);
}
</script>
<#include "/m/common/spider_include.ftl"/>
</body>
</html>