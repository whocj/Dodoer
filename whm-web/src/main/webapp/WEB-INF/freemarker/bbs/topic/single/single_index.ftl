<#include "/common/include.ftl"/>
<!doctype html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="${topic.keywords?html},${siteKeys }" />
<meta name="description" content="${topic.description?html},${siteDesc }" />  
<title>${topic.title?html } BBS-${siteTitle }</title>
</head>
<body>
	<!-- Start of Header -->
	<#include "/decorators/header.ftl"/>
	<!-- End of Header -->

	<!-- Start of Page Container -->
	<#include "/bbs/topic/single/single_main.ftl"/>
	<!-- End of Page Container -->

	<!-- Start of Footer -->
	<footer id="footer-wrapper">
	<#include "/decorators/copyright.ftl"/>
	</footer>
	<!-- End of Footer -->

	
</body>
<#include "/common/spider_include.ftl"/>
</html>