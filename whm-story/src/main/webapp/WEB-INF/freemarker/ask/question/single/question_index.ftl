<#include "/common/include.ftl"/>
<!doctype html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="${question.keywords?html},${siteKeys }" />
<meta name="description" content="${question.description?html},${siteDesc }" />
<title>${question.questionTitle?html } Ask-${siteTitle?html }</title>
</head>

<body>
	<!-- Start of Header -->
	<#include "/decorators/header.ftl"/>
	<!-- End of Header -->

	<!-- Start of Page Container -->
	<#include "/ask/question/single/question_main.ftl"/>
	<!-- End of Page Container -->

	<!-- Start of Footer -->
	<#include "/decorators/copyright.ftl"/>
	<!-- End of Footer -->

<!-- 	<a href="#top" id="scroll-top"></a> -->
</body>
<#include "/common/spider_include.ftl"/>
</html>
