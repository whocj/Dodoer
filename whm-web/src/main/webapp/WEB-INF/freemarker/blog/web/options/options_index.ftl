<html>
<head>
	<#include "/blog/web/post/common/include.ftl"/>
	<meta name="keywords" content="${siteKeywords }" />
	<meta name="description" content="${siteDescription }" />
	<title>选项 - Blog - ${siteTitle }</title>
	
	<script type="text/javascript">
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		function submitForm(){
			
			if($("#name").val() == ""){
				layer.tips('名称不能为空.', '#name', {time: 4000,tips:[2]});
				$("#name").focus();
				return false;
			}
			
			var queryData = $('#jvForm').formSerialize();
			$.ajax({
				   type: "POST",
				   url: "${base }/blog/backend/tag/commit.html",
				   data: queryData,
				   success: function(msg){
					   parent.layer.close(index);
					   parent.document.location.reload();
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
		 	<h3 class="title"><span>选项管理</span></h3>
		 	
		    <hr>
		  	<div class="post_content">
		  		<form method="post" action="${base }/blog/backend/options/commit.html" id="comment_form">
					<div class="clearfix">
						<div id="guest_info">
					      <div id="guest_name">
					       <label for="title"><span>标题</span>(必须 )</label>
					       <input id="title" autocomplete="off" type="text" value="${blogOptions.title }" name="title" 
					       	maxlength="100" style="width:400px;">
					      </div>
					      <div id="guest_email">
					        <label for="subTitle"><span>子标题</span>( 必须 )</label>
					        <input id="subTitle" autocomplete="off" type="text" value="${blogOptions.subTitle }" name="subTitle" maxlength="100" style="width:600px;">
					      </div>
					      <div id="guest_url">
					        <label for="description"><span>描述</span></label>
					        <input id="description" type="text" tabindex="3" value="${blogOptions.description }" name="description" maxlength="256" style="width:600px;">
					      </div>
					      <div id="guest_url">
					        <label for="keywords"><span>关键词</span></label>
					        <input id="keywords" type="text" tabindex="3"value="${blogOptions.keywords }" name="keywords" maxlength="100" style="width:600px;">
					      </div>
					      <div id="guest_url">
					        <label for="domainPath"><span>网站</span></label>
					        <input id="domainPath" type="text" tabindex="3" value="${blogOptions.domainPath }" placeholder="http://"  maxlength="100" style="width:600px;" name="domainPath">
					      </div>
					     </div>
					     <div id="submit_comment_wrapper">
					     	<input type="hidden" name="id" id="id" value="${blogOptions.id }"/>
					      <input id="submit_comment" type="submit" value="保存" >
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




