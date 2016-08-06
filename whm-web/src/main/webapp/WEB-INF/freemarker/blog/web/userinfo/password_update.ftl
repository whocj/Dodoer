<html>
<head>
	<#include "/blog/web/post/common/include.ftl"/>
	<meta name="keywords" content="${siteKeywords }" />
	<meta name="description" content="${siteDescription }" />
	<title>用户密码修改 - Blog - ${siteTitle }</title>
	
	<script type="text/javascript">
		function submitForm(){
			
			if($("#newpassword").val() == ""){
				layer.tips('新密码不能为空.', '#newpassword', {time: 4000,tips:[2]});
				$("#newpassword").focus();
				return false;
			}
			
			if($("#newpassword2").val() == ""){
				layer.tips('新密码不能为空.', '#newpassword2', {time: 4000,tips:[2]});
				$("#newpassword2").focus();
				return false;
			}
			
			if($("#newpassword").val() != $("#newpassword2").val() || $("#newpassword2").val().length < 6){
				layer.tips('两次新密码不一致或长度少于6位.', '#newpassword2', {time: 4000,tips:[2]});
				$("#newpassword2").focus();
				return false;
			}
			
			var queryData = $('#jvForm').formSerialize();
			$.ajax({
				   type: "POST",
				   url: "${base }/blog/backend/userinfo/passwordcommit.html",
				   data: queryData,
				   dataType:"json",
				   success: function(data){
					   if(data.succ == "S"){
						   layer.tips('密码更新成功.', '#submit_comment', {time: 4000,tips:[2]});						   
					   }else{
						   layer.tips('密码更新失败.', '#submit_comment', {time: 4000,tips:[2]});
					   }
				   }
				});
		}
	</script>
	
</head>
<body>
  <#include "/blog/web/post/common/head.ftl"/>
  <div id="content" class="clearfix">
    <div id="left_col" class="clearfix">
      <div class="post_wrap clearfix">
		  <div class="post">
		 	<h3 class="title"><span>密码管理</span></h3>
		    <hr>
		  	<div class="post_content">
		  		<form method="post" action="${base }/blog/backend/userinfo/passowrdcommit.html" id="jvForm">
					<div class="clearfix">
						<div id="guest_info">
					      <div>
					        <label for="newpassword"><span>新密码(必填)</span></label>
					        <input id="newpassword"  type="password"   name="newpassword" maxlength="32">
					      </div>
					      <div>
					        <label for="newpassword2"><span>新密码(必填)</span></label>
					        <input id="newpassword2" type="password"   name="newpassword2" maxlength="32">
					      </div>
					      
					     </div>
					     <div id="submit_comment_wrapper">
					      <input id="submit_comment" type="button" onclick="submitForm()" value="保存" >
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




