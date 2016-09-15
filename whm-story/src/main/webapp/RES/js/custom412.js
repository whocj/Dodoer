/*
*
*   Custom JavaScript
*   Product: KnowledgeBase WordPress Theme
*
* */
jQuery(document).ready(function(e) {
	$ = jQuery;
    /*-----------------------------------------------------------------------------------*/
    /*	Menu Dropdown Control
     /*-----------------------------------------------------------------------------------*/
    $('.main-nav li').hover(function(){
        $(this).children('ul').stop(true, true).slideDown(500);
    },function(){
        $(this).children('ul').stop(true, true).slideUp(500);
    });

    $('.sub-menu li').click(function(){
        window.location = $(this).children('a').attr('href');
    });
});

if (!String.prototype.trim) {
	  String.prototype.trim = function () {
	    return this.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, '');
	  };
	}

function gotoUrl(url){
	window.location.href = url;
}

function ajaxGetForLayer(url){
	$.ajax({
	   type: "get",
	   url: url,
	   dataType:"json",
	   success: function(data){
		   if(data.status == "200"){
			   layer.msg(data.msg, {time: 1000});
		   }else{
			  layer.msg(data.msg, {time: 1000});
		   }
	   }
	});
}

function nofind(){
	var img=event.srcElement;
	img.src="/RES/images/default_120_150.jpg";
	img.onerror=null; //控制不要一直跳动
}

function goLogin(){
	location.href = "/login/index.html";
}

function openLoginDiv(){
	layer.open({
	    type: 2,
	    title: '用户登录',
	    shadeClose: true,
	    shade: 0.2,
	    area: ['500px', '450px'],
	    content: '/login/indexDialog.html',
	    end : function(){
	    	document.location.reload();
    	}
	});
}

