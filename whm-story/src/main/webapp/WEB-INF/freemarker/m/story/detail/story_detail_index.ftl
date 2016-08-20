<html><head>
<html>
<head>
<meta charset="UTF-8"> 
<title>${storyInfo.title}|${storyInfo.author}|${storyInfo.categoryName} - ${siteTitle }</title>
<meta name="keywords" content="${siteKeywords }">
<meta name="description" content="${siteDescription }">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"> 
<meta content="yes" name="apple-mobile-web-app-capable"> 
<meta content="black" name="apple-mobile-web-app-status-bar-style"> 
<meta content="telephone=no" name="format-detection"> 
<link rel="stylesheet" href="${resRoot}/m/css/qz_show.css">
<#include "/m/common/include.ftl">
<#include "/m/common/include_js.ftl">
<style type="text/css">
<!--
#list {
    padding: 2px;
}
#list dl {
    float: left;
    overflow: hidden;
    padding-bottom: 1px;
    margin: auto;
    width: 100%;
}
#list dt {
    background: none repeat scroll 0 0 #C3DFEA;
    display: inline;
    float: left;
    font-size: 14px;
    line-height: 28px;
    overflow: hidden;
    text-align: center;
    vertical-align: middle;
    width: 98%;
    margin: auto auto 5px;
    padding: 5px 10px;
}
#list dd {
    border-bottom: 1px dashed #CCC;
    display: inline;
    float: left;
    height: 25px;
    line-height: 200%;
    margin-bottom: 5px;
    overflow: hidden;
    text-align: left;
    text-indent: 10px;
    vertical-align: middle;
    width: 100%;
}
-->
</style>
</head>
<body>
<div id="main_body">
	 <header class="s_header">
		<nav>
			<a href="/" class="bg">
				<span>首页</span>
			</a>
			<span id="more" onclick="javascript:location.href='${base }/main/${storyInfo.id}.html';">
				${storyInfo.title }
			</span>
		</nav>
	</header>
<section class="s_floor">
<h2 style="text-align: center;">${storyDetail.title }</h2>
<div class="img topic_txt"><p>${storyDetail.content }</p>
</div>
</section>
<#include "/common/baidu_share.ftl"/>
<div id="list">
	<dl>
			<#if prevStoryDetail!''>
			<dd>
				<a href="${base }/detail/${prevStoryDetail.id}.html">←上一章:${prevStoryDetail.title }</a>
			</dd>
			</#if>
			<#if nextStoryDetail!''>
			<dd>
				<a href="${base }/detail/${nextStoryDetail.id}.html">→下一章:${nextStoryDetail.title }</a>
			</dd>
			</#if>
			<dd>
				<a href="${base }/main/${storyInfo.id}.html">章节列表:${storyInfo.title }</a>
			</dd>
			<dd>
				<a href="javascript:ajaxGetForLayer('${base }/addLike/${storyInfo.id }.html')" >投推荐票</a>
			</dd>
			<dd>
				<a href="javascript:ajaxGetForLayer('${base }/user/bookshelf/add/${storyInfo.id }.html')" >加入书签</a>
			</dd>
		</dd>
		&nbsp;&nbsp;
	</dl>
</div>

</div>
<div id="gotop" class="gotop" style="display:none">
	<img src="${resRoot}/m/img/gotop.png" width="100%" height="100%">
</div>

<#include "/m/common/copyright.ftl">
<#include "/m/common/spider_include.ftl"/>

<script type="text/javascript">
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
</script>
</body>
</html>