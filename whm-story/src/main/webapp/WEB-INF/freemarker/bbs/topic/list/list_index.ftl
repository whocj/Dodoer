<#include "/common/include.ftl"/>
<!doctype html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="${forum.keywords?html},${siteKeys }" />
<meta name="description" content="${forum.description?html},${siteDesc }" />  
<title>${forum.title?html}-BBS-${siteTitle }</title>
</head>
<body>
	<!-- Start of Header -->
	<#include "/decorators/header.ftl"/>
	<!-- End of Header -->
	
	<!-- Start of Page Container -->
	<#include "/bbs/topic/list/list_main.ftl"/>
	<!-- End of Page Container -->

	<!-- Start of Footer -->
	<footer id="footer-wrapper">
	<#include "/decorators/copyright.ftl"/>
	</footer>
	<!-- End of Footer -->

<!-- 	<a href="#top" id="scroll-top"></a> -->
<#include "/common/spider_include.ftl"/>
</body>
</html>