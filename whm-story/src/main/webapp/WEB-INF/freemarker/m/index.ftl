<html>
<head>
<meta name="google-site-verification" content="c1osjSKHNZsIcu0YTloNLOy_DfEj96N6fYUQZVem5Q0" />
<meta charset="UTF-8"> 
<title>首页_${siteTitleMobile }</title>
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
			<h3><a href="javascript:void(0)">热门小说</a></h3>
		</div>
		<div class="list_box">
			<ul>
			<#list hotList as datas>
			<li>
				<a href="${base }/main/${datas.id}.html">
				<img src="${datas.picPath }" width="150" height="180" onerror="nofind()" alt="${datas.title }|${datas.author }">
				<span>${datas.title }|${datas.author }${datas.statusTxt }</span></a>
			</li>
			</#list>
			</ul>
			<br style="clear: both;">
		</div>
	</section>
	<!-- Start of notice -->
	<#include "/m/common/notice.ftl">
	<!-- Start of notice -->

	<!-- start of topic content -->
		
	<#list storyTopicList as topic>
	<section class="s_moreread">
		<div class="module-t">
			<h3><a href="">${topic.title }</a></h3>
		</div>
		<div class="list_box">
		<#list topic.topicDetailList as datas>
			<#if datas_index == 0>
			<dl>
			<a href="${base }/main/${datas.storyId}.html">
				<dt><img src="${datas.picPath }"  width="90" height="120" onerror="nofind()" alt="${datas.title }" ></dt>
				<dd><h3>[${datas.categoryName }]《${datas.title }》${datas.statusTxt }</h3></dd>
				<dd>${datas.outline }</dd>
				<dd><span>阅读：${datas.readCount }</span>点攒：${datas.likeCount }</dd>
				</a>
			</dl>
			<#elseif datas_index &lt; 10>
				<a href="${base }/main/${datas.storyId}.html" class="item">[${datas.categoryName }]${datas.title }${datas.statusTxt }</a>
			</#if>
		</#list>
		</div>
	</section>
	</#list>
	<!-- end of topic content -->
	<section class="s_moreread">
		<div class="module-t">
			<h3><a href="javascript:void(0)">最近上架小说</a></h3>
		</div>
		<div class="list_box">
		<#list newStoryList as datas>
			<#if datas_index == 0>
			<dl>
			<a href="${base }/main/${datas.id}.html">
				<dt><img src="${datas.picPath }"  width="90" height="120" onerror="nofind()" alt="${datas.title }" ></dt>
				<dd><h3>[${datas.categoryName }]《${datas.title }》${datas.statusTxt }</h3></dd>
				<dd>${datas.outline }</dd>
				<dd><span>阅读：${datas.readCount }</span>点攒：${datas.likeCount }</dd>
				</a>
			</dl>
			<#elseif datas_index &lt; 10>
			<a href="${base }/main/${datas.id}.html" class="item">[${datas.categoryName }]${datas.title }${datas.statusTxt }</a>
			</#if>
		</#list>
		</div>
	</section>
	
	<section class="s_moreread">
		<div class="module-t">
			<h3><a href="javascript:void(0)">阅读人数最多小说</a></h3>
		</div>
		<div class="list_box">
		<#list readStoryList as datas>
			<#if datas_index == 0>
			<dl>
			<a href="${base }/main/${datas.id}.html">
				<dt><img src="${datas.picPath }" onerror="nofind()"  width="90"  height="120" alt="${datas.title }" ></dt>
				<dd><h3>[${datas.categoryName }]《${datas.title }》${datas.statusTxt }</h3></dd>
				<dd>${datas.outline }</dd>
				<dd><span>阅读：${datas.readCount }</span>点攒：${datas.likeCount }</dd>
				</a>
			</dl>
			<#elseif datas_index &lt; 10>
			<a href="${base }/main/${datas.id}.html" class="item">[${datas.categoryName }]${datas.title }${datas.statusTxt }</a>
			</#if>
		</#list>
		</div>
	</section>
	
	<section class="s_moreread">
		<div class="module-t">
			<h3><a href="javascript:void(0)">喜爱人数最多小说</a></h3>
		</div>
		<div class="list_box">
		<#list likeStoryList as datas>
			<#if datas_index == 0>
			<dl>
			<a href="${base }/main/${datas.id}.html">
				<dt><img src="${datas.picPath }" onerror="nofind()"   width="90" height="120" alt="${datas.title }" ></dt>
				<dd><h3>[${datas.categoryName }]《${datas.title }》${datas.statusTxt }</h3></dd>
				<dd>${datas.outline }</dd>
				<dd><span>阅读：${datas.readCount }</span>点攒：${datas.likeCount }</dd>
				</a>
			</dl>
			<#elseif datas_index &lt; 10>
			<a href="${base }/main/${datas.id}.html" class="item">[${datas.categoryName }]${datas.title }${datas.statusTxt }</a>
			</#if>
		</#list>
		</div>
	</section>
	
	<section class="s_moreread">
		<div class="module-t">
			<h3><a href="javascript:void(0)">吐槽人数最多小说</a></h3>
		</div>
		<div class="list_box">
		<#list replyStoryList as datas>
			<#if datas_index == 0>
			<dl>
			<a href="${base }/main/${datas.id}.html">
				<dt><img src="${datas.picPath }" onerror="nofind()"  width="90"  height="120" alt="${datas.title }" ></dt>
				<dd><h3>[${datas.categoryName }]${datas.title }${datas.statusTxt }</h3></dd>
				<dd>${datas.outline }</dd>
				<dd><span>阅读：${datas.readCount }</span>点攒：${datas.likeCount }</dd>
				</a>
			</dl>
			<#elseif datas_index &lt; 10>
			<a href="${base }/main/${datas.id}.html" class="item">[${datas.categoryName }]${datas.title }${datas.statusTxt }</a>
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
<!-- 百度站内搜索 -->
<script type="text/javascript">(function(){document.write(unescape('%3Cdiv id="bdcs"%3E%3C/div%3E'));var bdcs = document.createElement('script');bdcs.type = 'text/javascript';bdcs.async = true;bdcs.src = 'http://znsv.baidu.com/customer_search/api/js?sid=8562369014620547317' + '&plate_url=' + encodeURIComponent(window.location.href) + '&t=' + Math.ceil(new Date()/3600000);var s = document.getElementsByTagName('script')[0];s.parentNode.insertBefore(bdcs, s);})();</script>
<#include "/m/common/spider_include.ftl"/>
</body>
</html>