<#include "/common/include.ftl"/>
<!doctype html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="${siteKeywords },${author.namezh }" />
<meta name="description" content="${siteDescription }" />
<title>${author.name }_${author.nameen }_小说全集_${author.categoryName}_${siteTitle }</title>
<link rel='stylesheet' href='${resRoot}/css/story_author.css' type='text/css' media='all' />
</head>

<body>
	<!-- Start of Header -->
	<#include "/decorators/header.ftl"/>
	<!-- End of Header -->

	<!-- Start of Search Wrapper -->
	<!-- End of Search Wrapper -->

	<!-- Start of Page Container -->
	<#include "/story/author/info/author_info_main.ftl"/>
	<!-- End of Page Container -->

	<!-- Start of Footer -->
	<#include "/decorators/footer.ftl"/>
	<!-- End of Footer -->

<!-- 	<a href="#top" id="scroll-top"></a> -->
<#include "/common/spider_include.ftl"/>
</body>
</html>
