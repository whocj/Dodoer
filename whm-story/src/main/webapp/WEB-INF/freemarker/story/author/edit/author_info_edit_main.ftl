
<script type='text/javascript' src='${resRoot}/js/jquery.form.js'></script>
<div class="page-container">
	<div class="container">
		<div class="row">
			<div class="span8">
		<form id="authorForm" method="post" action="${base }/author/edit/commit.html">
			<div class="newscontent">
			<div class="l">
			<h2>基本信息</h2>
				<div class="hotstory">
					<div class="main-content">
					<#if sessionUser != null>
							<#if sessionUser.username == 'admin'>
								<input type="button" value="保存" id="btn_save" onclick="submitForm()" />
							</#if>
					</#if>
<div class="basic-info">
<dl class="basicInfo-block basicInfo-left">
<dt class="basicInfo-item name">笔&nbsp;&nbsp;名(*)</dt>
	<dd class="basicInfo-item value">
		<input name="name" id="name" value="${author.name }" maxlength="32">
	</dd>
<dt class="basicInfo-item name">中文名</dt>
<dd class="basicInfo-item value">
	<input name="namezh" value="${author.namezh }" maxlength="64">
</dd>
	<dt class="basicInfo-item name">外文名</dt>
	<dd class="basicInfo-item value">
		<input name="nameen" value="${author.nameen }" maxlength="64">
	</dd>
	<dt class="basicInfo-item name">国&nbsp;&nbsp;籍</dt>
	<dd class="basicInfo-item value">
		<input name="country" value="${author.country }" maxlength="64">
	</dd>
	<dt class="basicInfo-item name">出生地</dt>
	<dd class="basicInfo-item value">
		<input name="homeplace" value="${author.homeplace }" maxlength="64">
	</dd>
	<dt class="basicInfo-item name">出生日期</dt>
	<dd class="basicInfo-item value">
		<input name="birthday" value="${author.birthday }" maxlength="32">
	</dd>
	<dt class="basicInfo-item name">作家分类</dt>
	<dd class="basicInfo-item value">
		<select name="categoryId">
		<#list categoryList as datas>
			<option value="${datas.id }">${datas.title }</option>
		</#list>
		</select>
	</dd>
	<dt class="basicInfo-item name">代表作品</dt>
	<dd class="basicInfo-item value">
		<input name="storyIds" value="${author.storyIds }" maxlength="32">填写小说id,使用英文逗号","分隔。
	</dd>
</dl>
</div>

<dl class="basicInfo-block basicInfo-left">
<dt class="basicInfo-item name">作品列表</dt>
<#list author.detailList as datas>

	
		<dd>[${datas.categoryName }]
		<a href="${base }/main/${datas.storyId}.html">${datas.title }${datas.statusTxt }
		</a></dd>
	
</#list>
</dl>



<input type="hidden" id="id" name="id" value="${author.id }">

<dl class="basicInfo-block basicInfo-left">
<dt class="basicInfo-item name">概要</dt>
<textarea name="outline" style="width:99%;height:200px;overflow:auto;">${author.outline?html }</textarea>

<dt class="basicInfo-item name">描述</dt>
<textarea name="description" style="width:99%;height:200px;overflow:auto;">${author.description?html }</textarea>
</dl>
</div>
	</div>
			</div>
			</div>
</form>
			</div>
			<!-- end of page content -->

			<!-- start of sidebar -->
			<aside class="span4 page-sidebar">
			<dl class="side-box lemma-statistics">
				<dt class="title">词条统计</dt>
				<dd class="description">
				<ul>
				<li>浏览次数：<span id="j-lemmaStatistics-pv">10</span>次</li>
				<li>编辑次数：1145次</li>
				<li>最近更新：<span class="j-modified-time">2016-09-06</span></li>
				<li>创建者：<a class="show-userCard" data-uid="5360586" title="查看此用户资料" target="_blank" nslog-type="1022">Admin</a></li>
				</ul>
				</dd>
				</dl>
			</aside>
			<!-- end of sidebar -->
		</div>
	</div>
</div>
<script type="text/javascript">
function submitForm(){
	if($("#name").val().trim() == ""){
		layer.tips('名字不能为空.', '#name', {time: 4000,tips:[1]});
		$("#name").focus();
		return false;
	}
	
	var queryData = $('#authorForm').formSerialize();
	$.ajax({
		   type: "POST",
		   url: "${base }/author/edit/commit.html",
		   data: queryData,
		   success: function(msg){
			   layer.tips(msg, '#btn_save',{time: 4000,tips:[2]});
		   }
		});
}

</script>