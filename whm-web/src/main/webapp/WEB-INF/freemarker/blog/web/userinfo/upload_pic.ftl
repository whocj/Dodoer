<html>
<head>
<link rel='stylesheet' id='bootstrap-css-css'
	href='${resRoot}/css/bootstrap5152.css?ver=1.0' type='text/css'
	media='all' />
<script type='text/javascript' src='${resRoot}/js/jquery-1.7.2.min.js'></script>
<script type='text/javascript' src='${resRoot}/js/jquery.form.js'></script>
<script type='text/javascript' src='${resRoot}/js/ajaxfileupload.js'></script>
<script type='text/javascript' src='${resRoot}/js/layer/layer.js'></script>
<style type="text/css">
#submit_comment_wrapper {
	text-align: center;
}

#submit_comment {
	margin: 15px auto 0;
	display: block;
	width: 150px;
	background: #ccc;
	color: #fff;
	border: none;
	padding: 10px 20px;
	cursor: pointer;
	-moz-border-radius: 4px;
	-khtml-border-radius: 4px;
	-webkit-border-radius: 4px;
	border-radius: 4px;
}

#submit_comment:hover {
	color: #fff;
	background: #009dc4;
}
</style>
<script type="text/javascript">
	function submitForm() {
		if ($("#upload").val() == "") {
			layer.tips('请选择上传文件.', '#upload', {
				time : 4000,
				tips : [ 2 ]
			});
			$("#upload").focus();
			return false;
		}
		
		$("#pageForm").submit();
	}
	
	function ajaxUpload(){
		$.ajaxFileUpload({
			url : "${base }/blog/backend/userinfo/uploadPic.html", //需要链接到服务器地址  
			secureuri : true,
			fileElementId : 'upload', //文件选择框的id属性  
			dataType : "json",
			success : function(data, status) {
				//{"SUCC":"S","msg":"http://imgdev.whohelpme.com/temp/20160419/1461067008743.jpg"}
				if (data.SUCC == "S") {
					parent.window.showImg(data.msg);
				} else {
					layer.tips('文件上传失败.' + data.msg, '#upload', {
						time : 4000,
						tips : [ 2 ]
					});
				}
			},
			error : function(data, status, e) {
				layer.tips('文件上传失败.', '#upload', {
					time : 4000,
					tips : [ 2 ]
				});
			}
		});
	}

</script>
</head>
<body>
	<div class="post_content">
		<form method="post"
			action="${base }/blog/backend/userinfo/uploadPic.html"
			ENCTYPE="multipart/form-data" id="pageForm">
			<div class="clearfix">
				<div id="guest_info">
					<div id="guest_name">
						<label for="title"><span>头像</span>(必须 )（文件格式必须为.jpg/.gif/.bmp/.png/.icon文件，小于2MB。)</label> 
						<input id="upload" type="file" name="upload" class="required" size="40">
					</div>
					<div id="guest_name">
						<div id="submit_comment_wrapper">
							<input id="submit_comment" type="button" value="保存"
								onclick="submitForm()">
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

</body>
</html>