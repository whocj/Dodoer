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





