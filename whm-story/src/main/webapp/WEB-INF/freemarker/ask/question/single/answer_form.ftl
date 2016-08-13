
<script src="${resRoot }/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!--
function submitForm(){

	if($("#askName").val() == ""){
		layer.tips('名字为必填项.', '#askName', {time: 4000,tips:[2]});
		$("#askName").focus();
		return;
	}
	
	var stemTxt = CKEDITOR.instances.answerContent.document.getBody().getText(); //取得纯文本 
	
	if(stemTxt == "" || stemTxt.length < 10){
		layer.tips('内容不可为空，长度10个字符以上.', '#subButton', {time: 2000,tips:[1]});
		return;
	}
	
	$("#answerForm").submit();
}
//-->
</script>


<div id="respond">
	<h3>留下我的答案</h3>
	<form action="${base }/q/reply.html" method="post" id="answerForm">
		<p class="comment-notes"></p>
		<#if sessionUser != null>
		<div>
			<label for="author">名字 *</label> <input class="span4" type="text"
				name="username" id="username" value="${sessionUser.username }" readonly="readonly" size="22">
		</div>
		</#if>
		<#if sessionUser == null>
			<div>
				<label for="askName">名字 *</label> <input class="span4" type="text"
					name="askName" id="askName" value="" size="22">
			</div>
			<div>
				<label for="askEmail">信箱 *</label> <input class="span4" type="text"
					name="askEmail" id="askEmail" value="" size="22">
			</div>
			<div>
				<label for="askWebSite">网址</label> <input class="span4" type="text"
					name="askWebSite" id="askWebSite" value="" size="22">
			</div>
		</#if>
		
		<div>
			<label for="answerContent">内容</label>
			<textarea class="span8" name="answerContent" id="answerContent" cols="58"
				rows="10"></textarea>
			<script type="text/javascript">
				CKEDITOR.replace('answerContent');
			</script>
		</div>

		<div>
			<br> 
			<input type="hidden" name="questionId" value="${question.id }">
			<input class="btn" name="button" type="button" id="subButton" onclick="submitForm()"
				value="提交">
		</div>
	</form>
</div>