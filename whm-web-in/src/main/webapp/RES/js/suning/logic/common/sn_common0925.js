snNavMneu = function(){
	var timer = 0;
	$("#allsort").hover(function(){
		$("#allsortlist").show();
		$(this).find("h2 i").addClass("up");
	},function(){
		$("#allsortlist").hide();
		$(this).find("h2 i").removeClass("up");
	});	
	$(".navlist").hover(function(){
		$(this).find("h3").addClass("hover");
		var ref = $(this);
		//timer = setTimeout(function(){
			ref.find(".navmore").show();
		//},10);		
		$(this).css("z-index",2);
	},function(){
		//clearTimeout(timer);	
		$(this).find("h3").removeClass("hover");	
		$(this).find(".navmore").hide();
		$(this).css("z-index",0);
	});
};


function addhover(o){
	$(o).find(".menuHd").eq(0).addClass("hover");
	$(o).find("div").show();
	$(o).css("z-index",2);
};
function delhover(o){
	$(o).find(".menuHd").eq(0).removeClass("hover");
	$(o).find("div").hide();
	$(o).css("z-index",1);
};
snInputhover = function(id){
	$(id).focus(function(){
		$(this).addClass("focus");				 
		if( $(this).val() == $(this)[0].defaultValue ){  
			$(this).val("");   
		};					 
	});	
	$(id).blur(function(){
		$(this).removeClass("focus");	
		if ($(this).val() == '') {
			$(this).val($(this)[0].defaultValue);
		}
	});	
};
snProductCategory = function(sLink,sInput){var link = $("#" + sLink);var input = $("#" + sInput);var data = link.parent().next();link.parent().parent().mouseleave(function(e){data.addClass("datanone");link.parent().removeClass("hover");e.preventDefault();});data.find("a").click(function(e){e.preventDefault();link.html(this.innerHTML);input.value = this.innerHTML;data.addClass("datanone");});link.mouseover(function(e){data.removeClass("datanone");link.parent().addClass("hover");e.preventDefault();}).click(function(e){link.parent().removeClass("hover");e.preventDefault();});}

$(function(){	
	snInputhover("#searchKeywords");
	snNavMneu();
	$("#seachSumit,.telSubmit,#snweiboSubmit").hover(function(){
		$(this).addClass("hover");							
	},function(){
		$(this).removeClass("hover");		
	});	
	new snProductCategory("sn_category", "product_category");
});