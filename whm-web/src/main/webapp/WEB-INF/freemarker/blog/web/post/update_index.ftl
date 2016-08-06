<html>
<head>
	<#include "/blog/web/post/common/include.ftl"/>
	<meta name="keywords" content="${blogOptions.keywords},${siteKeys }" />
	<meta name="description" content="${blogOptions.description},${siteDesc }" />
	<title>发贴 - Blog-${siteTitle }</title>
	 <script src="${resRoot }/js/ckeditor/ckeditor.js"></script>
	 <script type="text/javascript">

function StringHas(srcStr, childStr){
	var strs = srcStr.split(",");
	for(var i=0; i<strs.length;i++ ){
		if(strs[i] == childStr){
			return true;
		}
	}
	false;
}

function tagManager(tagValue){
	var tagV = $("#tagName").val();
	if(tagV != ""){
		if(StringHas(tagV, tagValue)){
			return;
		}
		
		tagV = tagV + ",";
	}
	tagV = tagV + tagValue;
	$("#tagName").val(tagV);
}

function checkContent(){
	  if($("#title").val() == ""){
			layer.tips('标题不能为空.', '#title', {time: 4000,tips:[2]});
			$("#title").focus();
			return false;
		}
	  
	  if($("#tagName").val() == ""){
			layer.tips('标签不能为空.', '#tagName', {time: 4000,tips:[2]});
			$("#tagName").focus();
			return false;
		}
		
		
		var stemTxt = CKEDITOR.instances.blogContent.document.getBody().getText(); //取得纯文本 
		
		if(stemTxt == "" || stemTxt.length < 10){
			layer.tips('内容不能为空，长度10个字符以上.', '#submit_comment', {time: 2000,tips:[1]});
			return false;
		}
	  
	  return true;
	}

	function submitForm(){
		if(checkContent()){
			$("#comment_form").submit();
		}
	}
</script>
</head>
<body>
  <#include "/blog/web/post/common/head.ftl"/>
  <div id="content" class="clearfix">
    <div id="left_col" class="clearfix">
      <div class="post_wrap clearfix">
      <form method="post" action="${base }/blog/backend/post/commit.html" id="comment_form">
		  <div class="post">
		  	<h3 class="title"><span>博客编辑</span></h3>
		    <hr>
		  	<div class="post_content">
    			<div>
						<label for="title">主题</label>
							<input class="span4" type="text"
								name="title" id="title" value="${blogPost.title }" size="80" maxlength="100">
					</div>
    			<div>
						<label for="tagName">标签</label>
							<input class="span4" type="text"
								name="tagName" id="tagName" value="${blogPost.tagName }" size="80" maxlength="100">
						<div class="tagcloud">
							<#list tagList as datas>
								<a href="javascript:tagManager('${datas.name }')" class="btn btn-mini">${datas.name }</a>
							</#list>
						</div>
					</div>
    				<div id="comment_textarea">
    				<br>
						<label for="blogContent">内容</label>
						<textarea name="content" id="blogContent">${blogPost.content }</textarea>
						<script type="text/javascript">
							CKEDITOR.replace('blogContent');
						</script>
					</div>
    			
	    		<div id="submit_comment_wrapper">
	    		  <input  type="hidden" name="id" value="${blogPost.id }" >
			      <input id="submit_comment" type="button" onclick="submitForm()" value="保存" >
			    </div>
		   </div>
		  </div>
		 </form>
		  
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
