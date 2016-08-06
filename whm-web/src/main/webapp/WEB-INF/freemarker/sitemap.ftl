<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="application-name" content="Help Me - WHM" />
<link rel='stylesheet' id='bootstrap-css-css'
	href='${resRoot}/css/bootstrap5152.css?ver=1.0' type='text/css'
	media='all' />
<title>sitemap - WHO HELP ME</title>
</head>
<body>
	<h2>Question</h2>
	<#list sitemap.question as data>
		${data }<br>
	</#list>
	<h2>Topic</h2>
	<#list sitemap.topic as data>
		${data }<br>
	</#list>
	<h2>Blog</h2>
	<#list sitemap.blog as data>
		${data }<br>
	</#list>
</body>
</html>