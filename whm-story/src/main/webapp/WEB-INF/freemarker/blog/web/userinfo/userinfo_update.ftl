<html>
<head>
	<#include "/blog/web/post/common/include.ftl"/>
	<meta name="keywords" content="${siteKeywords }" />
	<meta name="description" content="${siteDescription }" />
	<title>用户基本信息修改 - Blog - ${siteTitle }</title>
	
	<script type="text/javascript">
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		function submitForm(){
			
			if($("#username").val() == ""){
				layer.tips('用户名不能为空.', '#username', {time: 4000,tips:[2]});
				$("#username").focus();
				return false;
			}
			
			if(!(/^[a-zA-Z0-9_]+$/).test($("#username").val())){
				layer.tips('用户名只能包含字母数字和下划线.', '#username', {time: 4000,tips:[2]});
				$("#username").focus();
				return false;
			}
			
			if($("#nickname").val() == ""){
				layer.tips('昵称不能为空.', '#nickname', {time: 4000,tips:[2]});
				$("#nickname").focus();
				return false;
			}
			
			$("#comment_form").submit();
		}
		
		function openUploadDiv(){
			layer.open({
			    type: 2,
			    title: '头像上传',
			    shadeClose: true,
			    shade: 0.2,
			    area: ['521px', '400px'],
			    content: '${base}/blog/backend/userinfo/gotoUploadPic.html',
			    end : function(){
			    	//document.location.reload();
		    	}
			});
		}

		function showImg(url){
			layer.tips('头像上传成功！', '#userLogoImg', {
				time : 4000,
				tips : [ 2 ]
			});
			document.getElementById("userLogoImg").src = url;
		}
	</script>
	
</head>
<body>
  <#include "/blog/web/post/common/head.ftl"/>
  <div id="content" class="clearfix">
    <div id="left_col" class="clearfix">
      <div class="post_wrap clearfix">
		  <div class="post">
		 	<h3 class="title"><span>用户管理</span></h3>
		    <hr>
		    &nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${msg }</font>
		  	<div class="post_content">
		  		<form method="post" action="${base }/blog/backend/userinfo/commit.html" id="comment_form">
					<div class="clearfix">
						<div id="guest_info">
					      <div id="guest_name">
					       <label for="username"><span>用户名</span>(必填且唯一，如果是第三方认证的，请进行修改，并且只能修改一次。 只能包含字母数字和下划线。)</label>
					       <#if user.password?? && user.password!''>
					       		<input  type="text" id="username" value="${user.username }" readonly="readonly" maxlength="32">
					       <#else>
							    <input  type="text" id="username" value="" name="username"  maxlength="32">
				  			</#if>
					      </div>
					       <div id="guest_email">
					        <label for="nickname"><span>昵称</span></label>
					        <input type="text" id="nickname" value="${user.nickname }" name="nickname" maxlength="32">
					      </div>
					      <div id="guest_email">
					        <label for="realname"><span>真实姓名</span></label>
					        <input type="text" value="${user.realname }" name="realname" maxlength="32">
					      </div>
					      <div id="guest_url">
					        <label for="email"><span>Email</span></label>
					        <input id="email" type="text" value="${user.email }" name="email" maxlength="64"  style="width:400px;">
					      </div>
					      <div id="guest_url">
					        <label for="description"><span>描述</span></label>
					        <input id="description" type="text" value="${user.description }" name="description" maxlength="256"  style="width:600px;">
					      </div>
					      <div id="guest_url">
					        <label for="userLogo"><span>头像</span></label>
					        <#if user.userLogo??>
								<img alt="头像" id="userLogoImg" src="${user.userLogo100 }">
							<#else>
							    <img alt="头像" id="userLogoImg" src="${resRoot }/css/img/avatar.png">
				  			</#if>
					        <input id="submit_comment" type="button" value="上传头像" onclick="openUploadDiv()">
					      </div>
					     </div>
					     <div id="submit_comment_wrapper">
					     	<input type="hidden" name="id" id="id" value="${user.id }"/>
					     	<input id="submit_comment" type="button" value="保存" onclick="submitForm()">
					    </div>
					</div>
				</form>
		  </div>
		  </div>
		 <#include "/blog/web/post/common/left_menu.ftl"/>
		</div>

    </div>
    <div id="post_mask"></div>
    <div id="right_col">
      <#include "/blog/web/post/common/about_me.ftl"/>

      <#include "/blog/web/post/common/recent.ftl"/>
      <#include "/blog/web/post/common/tagcloud.ftl"/>
      <#include "/blog/web/post/common/link.ftl"/>
      
    </div>
    <#include "/blog/web/post/common/footer.ftl"/>
  </div>
</body>
</html>




