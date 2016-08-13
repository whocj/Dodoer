<script type="text/javascript">
	function replyPost(username){
		CKEDITOR.instances.comment.insertText("@" + username + " ");
	}
</script>
<ol class="commentlist">
  <#list blogPost.blogCommentList as datas>
  
  	<#if datas_index%2 == 0> 
		<#assign varTitle = "even_comment"> 
	<#else> 
		<#assign varTitle = "odd_comment"> 
	</#if>
  
   <li class="comment ${varTitle }" id="comment-${datas.id}">
   <div class="comment-meta">
     <img class="avatar" width="35" height="35" src="${datas.userLogo50 }" />
     <ul class="comment-name-date">
       <li class="comment-name"><a class="url" target="_blank" href="${datas.url}">${datas.creator}</a></li>
       <li class="comment-date">
       	${datas.createTime?string('yyyy-MM-dd HH:mm') }
       </li>
      </ul>
      <div class="comment-reply"><a href='javascript:void(0);' onclick="replyPost('${datas.creator}')">回复</a></div>
     </div>
     <div class="comment-content">
       <p>${datas.content}</p>
     </div>
   </li>
  </#list>
</ol>
