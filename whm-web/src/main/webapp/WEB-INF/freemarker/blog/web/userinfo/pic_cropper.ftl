<html>
<head>
	<link rel='stylesheet' id='bootstrap-css-css'
	href='${resRoot}/css/bootstrap5152.css?ver=1.0' type='text/css'
	media='all' />
	<script type='text/javascript' src='${resRoot}/js/jquery-1.7.2.min.js'></script>
	<script type='text/javascript' src='${resRoot}/js/jquery.form.js'></script>
	<link rel="stylesheet" href="${resRoot}/js/cropper/cropper.css">
	<script type='text/javascript' src="${resRoot}/js/cropper/cropper.js"></script>

	<style type="text/css">
		#submit_comment_wrapper{text-align:center; }
		#submit_comment{margin:15px auto 0; display:block; width:150px; background:#ccc; color:#fff; border:none; padding:10px 20px; cursor:pointer; -moz-border-radius:4px; -khtml-border-radius:4px; -webkit-border-radius:4px; border-radius:4px; }
		#submit_comment:hover{color:#fff;background: #009dc4; }
	</style>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<form id="pageForm" name="pageForm" method="post" ENCTYPE="multipart/form-data"
			action="${base }/blog/backend/userinfo/uploadPic.html">
	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tbody>
		<tr>
			<td width="80%" class="pn-fcontent">
				<div class="img-container">
				  <#if SUCC=='S'>
				  	<img alt="头像"  id="picture" src="${msg }">	
				  <#else>
				    ${msg }
				  </#if>
			    </div>
			</td>
		</tr>
		<tr>
			<td width="100%" class="pn-fcontent" colspan="2" >
				<div id="submit_comment_wrapper">
		        <input id="submit_comment" type="button" value="保存" onclick="submitForm()">
		    </div>
			</td>
		</tr>
			</tbody>
	</table>
	
	</form>
</div>
</div>
<script type="text/javascript">
var cropper;
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
function submitForm(){
	var cropBoxData = cropper.getCropBoxData();
	var filename = document.getElementById("picture").src;
	$.ajax({
		   type: "POST",
		   url: "${base }/blog/backend/userinfo/picCropper.html",
		   dataType:"json",
		   data: {"left":cropBoxData.left, "top":cropBoxData.top, "width":cropBoxData.width, "height":cropBoxData.height, "filename":filename},
		   success: function(data){
			   if(data.succ = "S"){
				   parent.window.showImg(data.path);
				   parent.layer.close(index);
			   }else{
				   layer.tips(data.path, '#submit_comment', {time: 4000,tips:[2]});
			   }
		   }
		});
}

window.addEventListener('DOMContentLoaded', function () {
  var image = document.querySelector('.img-container > img');
  var minAspectRatio = 0.5;
  var maxAspectRatio = 1.5;
  cropper = new Cropper(image, {
	aspectRatio: 1 / 1,
	dragMode:"move",
	modal:true,
	guides:false,
	autoCrop:true,
	zoomable:false,
	zoomOnTouch:false,
	cropBoxResizable:true,
	
	//preview:"overflow: hidden",
    built: function () {
      var cropper = this.cropper;
      var containerData = cropper.getContainerData();
      var cropBoxData = cropper.getCropBoxData();
      
      var newCropBoxWidth = cropBoxData.height * ((minAspectRatio + maxAspectRatio) / 2);
      var newCropBoxHeight = cropBoxData.width * ((minAspectRatio + maxAspectRatio) / 2);
      
      cropper.setCropBoxData({
          left: (containerData.width - newCropBoxWidth + 75) / 2,
          top: (containerData.height - newCropBoxHeight) / 2,
          width: 200,
          height:200
        });
    }
  });
});
</script>
</body>
</html>