<html>
<head>
<meta charset="UTF-8"> 
<title>${category.title } - ${siteTitle }</title>
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
			<span style="font-size: 1.4rem">${category.title }</span>
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
<#list page.content as datas>
	<dl>
		<a href="${base }/main/${datas.id}.html">
			<dt><img src="${datas.picPath }" alt="${datas.title }" onerror="nofind()" width="90" height="120"></dt>
			<dd><h3>[${datas.categoryName }]${datas.title }</h3></dd>
			<dd style="height:70px;">${datas.outline }</dd>
			<dd><span>浏览：${datas.readCount }</span>点攒：${datas.likeCount }</dd>
		</a>
	</dl>
</#list>
	</div>
	<div class="load_more">
		查看更多
	</div>
</section>

</div>

<script type="text/javascript">
$('a[name=index]').attr('href', '').attr('class', 'aactive');
$('#more').click(function(){  
	if($('#toplist').css('display')!='none')
	{
		$('#toplist').hide();
	}
	else
	{
		$('#toplist').show();
	}
});

var curr_page=1, nomore=false, islock=false;
function load_more() {
	if(nomore) {
		return;
	}
	if(islock) {
		return;
	}
	islock = true;
	curr_page++;
 	$(".load_more").html('加载中...');
    $.get('${base}/more/${category.id}/'+curr_page + ".html",function(data){
    	islock = false;
    	if($.trim(data).length > 0){
			$('.list_box').append(data);
			$(".load_more").html('查看更多');
		}else{
			$(".load_more").remove();
			nomore=true;
		}
    });
}
$(".load_more").click(load_more);


</script>
<#include "/m/common/spider_include.ftl"/>
</body>
</html>