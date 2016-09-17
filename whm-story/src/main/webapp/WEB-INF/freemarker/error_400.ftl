<#include "/common/include.ftl"/>
<!doctype html>
<html lang="en-US">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>400 HTTP ERROR_${siteTitle }</title>
<style type="text/css">
#login{width:450px;margin:0px auto;margin-top: 100px;}
#loginform{background-color:#fff;border: 1px solid #e5e5e5;font-weight: normal;padding:24px;
    box-shadow: 0 4px 10px -1px rgba(200, 200, 200, 0.7);}
.message{background-color: #ffffe0;border:1px solid #e6db55; border-radius: 3px;text-align: center;margin-bottom: 10px;}
</style>
</head>
<body>
	<#include "/decorators/header.ftl"/>
	<div id="login"  style="height:550px;">
		<div class="error-container">
			<div class="well">
				<h1 class="grey lighter smaller">
					<span class="blue bigger-125">
						400 HTTP ERROR
					</span>
					
				</h1>
				<hr>
				<div class="space"></div>
				<div>
					<h4 class="lighter smaller">亲, 你可以尝试以下操作:</h4>

					<ul class="list-unstyled spaced inline bigger-110 margin-15">
						<li>
							<i class="icon-hand-right blue"></i>
							请检查您输入的URL是否正确。
						</li>
						<br>
						<li>
						<i class="icon-hand-right blue"></i>
							请联系我们，<a href="http://weibo.com/whohelome">微博</a>
						</li>
					</ul>
				</div>
				<hr>
				<div class="space"></div>
				<div class="center">
					<a href="${domain}/index.html" class="btn btn-grey">
						<i class="icon-arrow-left"></i>
						返回首页
					</a>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer Bottom -->
	<#include "/decorators/copyright.ftl"/>
	<!-- End of Footer Bottom -->
</body>
</html>