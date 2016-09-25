<#include "/common/include.ftl"/>
<!doctype html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<!--必填-->
<meta property="og:type" content="novel"/>
<meta property="og:title" content="${storyInfo.title }"/>
<meta property="og:description" content="${storyInfo.outline }"/>
<meta property="og:image" content="${storyInfo.picPath }"/>
<meta property="og:novel:category" content="${storyInfo.categoryName }"/>
<meta property="og:novel:author" content="${storyInfo.author }"/>
<meta property="og:novel:book_name" content="${storyInfo.title }"/>
<meta property="og:novel:read_url" content="${domain }/main/${storyInfo.id }.html"/>

<meta property="og:novel:click_cnt" content="${storyInfo.readCount }"/>
<meta property="og:novel:update_time" content="${storyInfo.lastUpdateDetail?string('yyyy-MM-dd HH:mm') }"/>
<meta property="og:novel:latest_chapter_name" content="${storyInfo.lastDetailTitle }"/>
<meta property="og:novel:latest_chapter_url" content="${domain }/detail/${storyInfo.id }/${storyInfo.lastDetailId }.html"/>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="${siteKeywords }" />
<meta name="description" content="${storyInfo.outline?html }" />
<meta name="mobile-agent" content="format=xhtml;url=${mobileDomain }/main/${storyInfo.id}.html">
<meta name="mobile-agent" content="format=html5;url=${mobileDomain }/main/${storyInfo.id}.html">
<title>${storyInfo.title }_${storyInfo.author }_${storyInfo.categoryName }_${siteTitle }</title>
</head>

<body>
	<!-- Start of Header -->
	<#include "/decorators/header.ftl"/>
	<!-- End of Header -->

	<!-- Start of Search Wrapper -->
	<!-- End of Search Wrapper -->

	<!-- Start of Page Container -->
	<#include "/story/main/main_info.ftl"/>
	<#include "/story/main/item_list.ftl"/>
	<!-- End of Page Container -->
	
	<!-- 多说评论框 start -->
<div class="ds-thread" style="margin: 5px auto;width: 1170px;"
	data-thread-key="story_${storyInfo.id }" 
	data-title="${storyInfo.title }" data-url="${domain}${base}/main/${storyInfo.id }.html"></div>
<!-- 多说评论框 end -->
<!-- 多说公共JS代码 start (一个网页只需插入一次) -->
<script type="text/javascript">
var duoshuoQuery = {short_name:"dodoer"};
	(function() {
		var ds = document.createElement('script');
		ds.type = 'text/javascript';ds.async = true;
		ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
		ds.charset = 'UTF-8';
		(document.getElementsByTagName('head')[0] 
		 || document.getElementsByTagName('body')[0]).appendChild(ds);
	})();
</script>
<!-- 多说公共JS代码 end -->
	
	<!-- Start of Footer -->
	<#include "/decorators/footer.ftl"/>
	<!-- End of Footer -->

<!-- 	<a href="#top" id="scroll-top"></a> -->
<#include "/common/spider_include.ftl"/>
</body>
</html>
