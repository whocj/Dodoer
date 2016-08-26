<#include "/common/include.ftl"/>
<!doctype html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="${siteKeywords }" />
<meta name="description" content="${siteDescription }" />
<meta name="mobile-agent" content="format=xhtml;url=${mobileDomain }/detail/${storyDetail.id}.html">
<meta name="mobile-agent" content="format=html5;url=${mobileDomain }/detail/${storyDetail.id}.html">
<title>${storyDetail.title }-${storyInfo.title }|${storyInfo.author } - ${siteTitle }</title>
</head>

<body>
	<!-- Start of Header -->
	<#include "/decorators/header.ftl"/>
	<!-- End of Header -->

	<!-- Start of Search Wrapper -->
	<!-- End of Search Wrapper -->

	<!-- Start of Page Container -->
	<#include "/story/detail/detail_main.ftl"/>
	<!-- End of Page Container -->

	<!-- Start of Footer -->
	<#include "/decorators/copyright.ftl"/>
	<!-- End of Footer -->

<!-- 	<a href="#top" id="scroll-top"></a> -->
<#include "/common/spider_include.ftl"/>
</body>
</html>
