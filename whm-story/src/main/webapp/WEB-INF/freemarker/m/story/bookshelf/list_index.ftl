<html>
<head>
<meta charset="UTF-8"> 
<title>我的书架_${siteTitleMobile }</title>
<meta name="keywords" content="${siteKeywords }">
<meta name="description" content="${siteDescription }">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"> 
<meta content="yes" name="apple-mobile-web-app-capable"> 
<meta content="black" name="apple-mobile-web-app-status-bar-style"> 
<meta content="telephone=no" name="format-detection"> 
<link rel="stylesheet" href="${resRoot}/m/css/qz_home.css">
<#include "/m/common/include.ftl">
<#include "/m/common/include_js.ftl">
</head>
<body>
<div id="main_body">
	 <header class="s_header">
		<nav>
			<a href="/" class="bg">
				<span>首页</span>
			</a>
			<span id="more">导航</span>
			<span style="font-size: 1.4rem">我的书架</span>
		</nav>
	</header>
	
	<div class="s_toplist" id="toplist" style="display:none">
	<div>
		<#list categoryList as datas>
			<a href="${base }/list/${datas.id}/1.html">${datas.title }</a>
			<#if datas_index + 1 == 4>
				</div>
				<div>
			</#if>
		</#list>
	</div>
	<span></span>
</div>
	

<section class="s_moreread">
	<div class="list_box">
<#list bookshelfList as datas>
	<dl>
		<a href="${base }/main/${datas.storyId}.html">
			<dt><img src="${datas.picPath }" alt="${datas.title }" onerror="nofind()" width="90" height="120"></dt>
			<dd><h3>[${datas.categoryName }]${datas.title }${datas.statusTxt }</h3></dd>
			<dd style="height:70px;">${datas.outline }</dd>
			<dd><span>浏览：${datas.readCount }</span>点攒：${datas.likeCount }</dd>
		</a>
	</dl>
</#list>
	</div>

</section>

</div>
<script type="text/javascript">
$('#more').click(function(){  
	if($('#toplist').css('display')!='none') {
		$('#toplist').hide();
	} else {
		$('#toplist').show();
	}
});
</script>
<#include "/m/common/spider_include.ftl"/>
</body>
</html>