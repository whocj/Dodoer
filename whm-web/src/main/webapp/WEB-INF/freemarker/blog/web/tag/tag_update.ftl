<html>
<head>
	<link rel='stylesheet' id='bootstrap-css-css' href='${resRoot}/css/bootstrap5152.css?ver=1.0' type='text/css'
	media='all' />
	<script type='text/javascript' src='${resRoot}/js/jquery-1.7.2.min.js'></script>
	<script type='text/javascript' src='${resRoot}/js/jquery.form.js'></script>
	<script type='text/javascript' src='${resRoot}/js/layer/layer.js'></script>
	<style type="text/css">
		#submit_comment_wrapper{text-align:center; }
		#submit_comment{margin:15px auto 0; display:block; width:150px; background:#ccc; color:#fff; border:none; padding:10px 20px; cursor:pointer; -moz-border-radius:4px; -khtml-border-radius:4px; -webkit-border-radius:4px; border-radius:4px; }
		#submit_comment:hover{color:#fff;background: #009dc4; }
	</style>
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
<div id="link_div">
<form id="jvForm" action="${base }/blog/backend/tag/commit.html">
	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tbody>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>名称:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="32" value="${tag.name }" id="name" name="name" class="required" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				粗体:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="4" value="${tag.styleBold }" name="styleBold" class="required" size="16">(0否，1是)
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				斜体:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="4" value="${tag.styleItalic }" name="styleItalic" class="required" size="16">(0否，1是)
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				颜色:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="16" value="${tag.styleColor }" name="styleColor" class="required" size="16">(red)
			</td>
		</tr>
		
		<tr>
			<td width="100%" class="pn-fcontent" colspan="2" >
			<input type="hidden" name="id" id="id" value="${tag.id }"/>
			<div id="submit_comment_wrapper">
		      <input id="submit_comment" type="button" value="保存" onclick="submitForm()">
		    </div>
			</td>
		</tr>
			</tbody>
	</table>
</form>
</div>
</body>