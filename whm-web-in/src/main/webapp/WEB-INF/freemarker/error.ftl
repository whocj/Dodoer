<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Admin-Error</title>
<#include "/common/include.ftl"/>
</head>
<body>
<h2>CODE:${error.code }</h2>
<p>
<hr/>
<h4>URL:${error.url} </h4>
<p>
<h4>Class:${error.class }</h4>
<p>
<pre>
Message:${error.message }
<pre>
</body>
</html>
