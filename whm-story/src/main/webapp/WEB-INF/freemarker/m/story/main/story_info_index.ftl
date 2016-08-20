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
    width: 50%;
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
			<span id="more" onclick="javascript:location.href='${base }/list/${storyInfo.categoryId}/1.html';">
				${storyInfo.categoryName }
			</span>
			<span id="more">
				${storyInfo.title }
			</span>
		</nav>
	</header>

<section class="s_floor">
<div class="pr">
	<h2>${storyInfo.title}</h2>
	<p class="s_p">
		<span>阅读：<span id="viewnum">${storyInfo.readCount}</span></span>
		<span>点攒：<span id="commentnum">${storyInfo.likeCount }</span></span>
		<span>${storyInfo.lastUpdate?string('yyyy-MM-dd HH:mm') }</span>
	</p>
</div>
<#include "/common/baidu_share.ftl"/>
<div class="img topic_txt"><p>${storyInfo.outline }</p>
<p align="center">
	<img alt="${storyInfo.title}" src="${storyInfo.picPath }">
</p>
</div>
</section>
<h2 style="margin-left: 10px;">全部章节列表</h2>
<div id="list">
	<dl>
	<#list storyInfo.storyDetailList as datas>
	<dd>
		<a href="${base }/detail/${datas.id }.html">${datas.title }</a>
		</dd>
		</#list>
		&nbsp;&nbsp;
	</dl>
</div>

<div id="gotop" class="gotop" style="display:none">
	<img src="${resRoot}/m/img/gotop.png" width="100%" height="100%">
</div>

</div>
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
<#include "/m/common/spider_include.ftl"/>
</body>
</html>