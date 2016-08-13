  <script src="${resRoot }/js/ckeditor/ckeditor.js"></script>
  <form method="post" action="${base }/blog/comment/commit.html" id="comment_form">
    <input type="hidden" id="postId" name="postId" value="${blogPost.id}" />
    <input type="hidden" id="comment_parent" name="parent" />
    <#if sessionUser != null>
      <div id="guest_info">登录身份:${sessionUser.username}</div>
    </#if>
    <#if sessionUser == null>
     <div id="guest_info">
      <div id="guest_name">
       <label for="author"><span>昵称</span>( 必须 )</label>
       <input id="author" autocomplete="off" type="text" aria-required="true" size="22" name="creator">
      </div>
      <div id="guest_email">
        <label for="email"><span>E-MAIL</span>( 必须 ) - 不会公开 -</label>
        <input id="email" autocomplete="off" type="text" aria-required="true" size="22" name="email">
      </div>
      <div id="guest_url">
        <label for="url"><span>网址</span></label>
        <input id="url" type="text" tabindex="3" size="22" placeholder="http://" name="url">
      </div>
     </div>
    </#if>
    <div id="comment_textarea">
		<textarea id="comment" tabindex="4" rows="10" cols="50" name="content"></textarea>
		<script type="text/javascript">
			CKEDITOR.replace('comment');
		</script>
    </div>
    <div id="submit_comment_wrapper">
      <input id="submit_comment" type="submit" value="发表评论" >
    </div>
  </form>
<script src="${resRoot}/js/zblog.js"></script>
