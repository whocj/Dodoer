/*
*手机版
*/
function nofind(){
	var img=event.srcElement;
	img.src="/RES/images/default_120_150.jpg";
	img.onerror=null; //控制不要一直跳动
}

function ajaxGetForLayer(url){
	$.ajax({
	   type: "get",
	   url: url,
	   dataType:"json",
	   success: function(data){
		   if(data.status == "200"){
			   layer.open({
				   content: data.msg,
				   time: 1 //1秒后自动关闭
				 });
		   }else{
			   layer.open({
				   content: data.msg,
				   time: 1 //1秒后自动关闭
				 });
		   }
	   }
	});
}

