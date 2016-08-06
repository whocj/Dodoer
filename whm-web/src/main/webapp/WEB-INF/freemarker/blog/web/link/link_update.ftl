<html>
<head>
	<link rel='stylesheet' id='bootstrap-css-css'
	href='${resRoot}/css/bootstrap5152.css?ver=1.0' type='text/css'
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
			
			if($("#siteName").val() == ""){
				layer.tips('网站名称不能为空.', '#siteName', {time: 4000,tips:[2]});
				$("#siteName").focus();
				return false;
			}
			
			if($("#domain").val() == ""){
				layer.tips('网站地址不能为空.', '#domain', {time: 4000,tips:[2]});
				$("#domain").focus();
				return false;
			}
			
			if($("#priority").val() == ""){
				layer.tips('网站排名不能为空.', '#priority', {time: 4000,tips:[2]});
				$("#priority").focus();
				return false;
			}
			
			var queryData = $('#jvForm').formSerialize();
			$.ajax({
				   type: "POST",
				   url: "${base }/blog/backend/link/commit.html",
				   data: queryData,
				   success: function(msg){
					   parent.layer.close(index);
				   }
				});
		}
	</script>
</head>
<body>
<div id="link_div">
<form id="jvForm" action="${base }/blog/backend/link/commit.html">
	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tbody>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>网站名称:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="40" value="${friendLink.siteName }" id="siteName" 
				name="siteName" class="required" size="40">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>网站地址:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="100" value="${friendLink.domain }" id="domain" 
				name="domain" class="required" size="20">
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>排列顺序:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" maxlength="6" value="${friendLink.priority }" id="priority" 
				name="priority" class="required digits" size="20"> 从小到大排列
			</td>
		</tr>
		<tr>
			<td width="100%" class="pn-fcontent" colspan="2" >
			<input type="hidden" name="id" id="id" value="${friendLink.id }"/>
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