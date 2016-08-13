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






