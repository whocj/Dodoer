<script src="${resRoot }/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!--
function submitForm(){
	if($("#questionTitle").val() == ""){
		layer.tips('主题为必填项.', '#questionTitle', {time: 4000,tips:[2]});
		$("#questionTitle").focus();
		return;
	}
	
	if($("#askName").val() == ""){
		layer.tips('名字为必填项.', '#askName', {time: 4000,tips:[2]});
		$("#askName").focus();
		return;
	}
	
	if($("#tagName").val() == ""){
		layer.tips('标签为必填项.', '#tagName', {time: 4000,tips:[2]});
		$("#tagName").focus();
		return;
	}
	
	var stemTxt = CKEDITOR.instances.questionContent.document.getBody().getText(); //取得纯文本 
	
	if(stemTxt == "" || stemTxt.length < 10){
		layer.tips('内容不可为空，长度10个字符以上.', '#subButton', {time: 2000,tips:[1]});
		return;
	}
	$("#questionForm").submit();
}

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
//-->
</script>

<div class="page-container">
	<div class="container">
		<div class="row">

			<!-- start of page content -->
			<div class="span8 main-listing">
				<div id="respond">
				<h3>我有一个问题</h3>
				<form action="${base }/q/submit.html" method="post" id="questionForm">
					<p class="comment-notes"></p>
					<div>
						<label for="answerContent">主题*</label>
							 <input class="span4" type="text" name="questionTitle" id="questionTitle" value="" size="40" maxlength="100">
							
					</div>
					<div>
						<label for="categoryId">分类</label>
							<select name="categoryId" id="categoryId">
								<option value="">-请选择-</option>
								<#list categoryList as datas>
									<option value="${datas.id }">${datas.title }</li>
								</#list>
							</select>
					</div>
					<#if sessionUser != null>
					<div>
						<label for="author">名字 *</label> <input class="required span4" type="text"
							name="username" id="username" value="${sessionUser.nickname }" size="22" readonly="readonly" maxlength="32">
					</div>
					</#if>
					<#if sessionUser == null>
						<div>
							<label for="askName">名字 *</label> <input class="span4" type="text"
								name="askName" id="askName" value="" size="22" maxlength="32">
						</div>
						<div>
							<label for="askEmail">信箱 *</label> <input class="span4" type="text"
								name="askEmail" id="askEmail" value="" size="22" maxlength="64">
						</div>
						<div>
							<label for="askWebSite">网址</label> <input class="span4" type="text"
								name="askWebSite" id="askWebSite" value="" size="22" maxlength="64">
						</div>
					</#if>
					<div>						<label for="tagName">标签</label>
							<input class="span4" type="text"
								name="tagName" id="tagName" value="" size="22" maxlength="32">
						<div class="tagcloud">
							<#list tagList as datas>
								<a href="javascript:tagManager('${datas.name }')" class="btn btn-mini">${datas.name }</a>
							</#list>
						</div>
					</div>
					<div>
						<label id="questionContentDiv" for="questionContent">内容</label>
						<textarea class="span8" name="questionContent" id="questionContent" cols="58"
							rows="10"></textarea>
						<script type="text/javascript">
							CKEDITOR.replace('questionContent');
						</script>
					</div>
			
					<div>
						<br> 
						<input type="hidden" name="questionId" value="${question.id }">
						<input class="btn" id="subButton" name="button" type="button" id="button" onclick="submitForm()"
							value="提交">
					</div>
				</form>
			</div>
			<#include "/decorators/adsense/ask_bottom.ftl"/>
			</div>
			<!-- end of page content -->

			<!-- start of sidebar -->
			<aside class="span4 page-sidebar">
				<#include "/decorators/support.ftl"/>
				<#include "/decorators/latest_question.ftl"/>
				<#include "/decorators/latest_topic.ftl"/>
				<#include "/decorators/latest_blog.ftl"/>
				<#include "/decorators/adsense/index_right.ftl"/>
			</aside>
			<!-- end of sidebar -->
		</div>
	</div>
</div>