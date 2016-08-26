<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101344928" 
data-redirecturi="http://www.dodoer.com/login/QQLogin.html" charset="utf-8"></script>
<script type="text/javascript">
<!--
	function goLogin(){
		var currentUrl = location.href;
		var url = new Base64().encodes(currentUrl);
		if(currentUrl != ""){
			location.href = "${base }/login/index.htm?url=" + url;
		}else{
			location.href = "${base }/login/index.htm";
		}
	}
	
	function openLoginDiv(){
		layer.open({
		    type: 2,
		    title: '用户登录',
		    shadeClose: true,
		    shade: 0.2,
		    area: ['500px', '450px'],
		    content: '${base}/login/indexDialog.html',
		    end : function(){
		    	//document.location.reload();
	    	}
		});
	}

	function logout(url){
		location.href = url;
	}
	
	if(QC.Login.check()){
   		//QC.Login.signOut();
    }

	 //插入按钮的节点qqLoginBtn id
	 QC.Login({btnId:"qqLoginBtn",size: "B_M"});
	//-->
</script>

<div class="header-wrapper">
	<header>
		<div class="container">
			<div class="logo-container">
				<!-- Website Logo -->
				<a href="${base }/index.html">
				<span class="tag-line" style="color:#FFF;font-size:18px;">
				多多儿小说网Dodoer
				</span>
				</a>
				&nbsp;&nbsp;
				<span class="tag-line">多多儿小说网</span>
			</div>
			
			<!-- Start of Main Navigation -->
			<nav class="main-nav">
				<div class="menu-top-menu-container">
					<ul id="menu-top-menu" class="clearfix">
						<#list categoryList as datas>
							<li><a href="${base }/list/${datas.id}/1.html">${datas.title }</a></li>
						</#list>
						<#if sessionUser != null>
						<li><a href="${base }/user/bookshelf/list.html">我的书架</a></li>
						<li>
							<span id="qqLoginBtn"></span>
<!-- 							<img class="question_avatar" src="${sessionUser.userLogo }"/> --!>
<!-- 							<a href="javascript:void(0)">${sessionUser.nickname }</a> --!>
<!-- 							<ul class="sub-menu"> -->
<!-- 								<li onclick="logout('${base }/logout.htm')"><a href="javascript:logout('${base }/logout.htm')">登出</a></li> --!>
<!-- 							</ul> -->
						</li>
						</#if>
						<#if sessionUser == null>
<!-- 							<li ><a id="loginMsg" href="javascript:openLoginDiv()">登录</a></li> -->
								<li ><span id="qqLoginBtn"></span></li>
						</#if>
					</ul>
				</div>
			</nav>
			<!-- End of Main Navigation -->
		</div>
	</header>
</div>