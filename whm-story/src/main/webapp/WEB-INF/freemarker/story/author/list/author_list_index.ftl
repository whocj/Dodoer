<#include "/common/include.ftl"/>
<!doctype html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="${siteKeywords }" />
<meta name="description" content="${siteDescription }" />
<title>全部作家_作家大全_作家排行榜_${category.title}_${siteTitle }</title>
<link rel='stylesheet' href='${resRoot}/css/story_author.css' type='text/css' media='all' />
</head>

<body>
	<!-- Start of Header -->
	<#include "/decorators/header.ftl"/>
	<!-- End of Header -->

	<!-- Start of Page Container -->
	<#include "/story/author/list/author_list_main.ftl"/>
	<!-- End of Page Container -->

	<!-- Start of Footer -->
	<#include "/decorators/copyright.ftl"/>
	<!-- End of Footer -->

<!-- 	<a href="#top" id="scroll-top"></a> -->
<#include "/common/spider_include.ftl"/>
</body>
</html>
