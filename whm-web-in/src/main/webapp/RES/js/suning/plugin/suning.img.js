J_imgscroll = function(next,prev,scrollId,w,list){
	var btn_next = $(next);
	var btn_prev = $(prev)
	var ul = $(scrollId).find('ul');
	var len = ul.find('li').length;
	if (len > list) {
		btn_next.click(function(e) {
			e.preventDefault();	
			if(ul.is(":animated")){return false}
			ul.animate({marginLeft:-w }, 300,function(){
				ul.append(ul.find("li:first"));
				ul.css("marginLeft",0);													  
			})
		});		
		btn_prev.click(function(e) {
			e.preventDefault();					
			if(ul.is(":animated")){return false}
			ul.find("li:first").before(ul.find("li:last"));
			ul.css("marginLeft",-w);
			ul.animate({marginLeft:0 }, 300)
		});
	}else{
		btn_next.css("visibility","hidden");
		btn_prev.css("visibility","hidden");
	}
}



//产品图片
J_picDetail = function(btn,showId,w,addclass) {
	var img = $(showId);
	if (img.length == 0)	return;
	var tq = null;
	var smalls = $(btn);
	var move = true;
	var cur = 0;
	img = img.find("img").eq(0);
	var timer = null;
	smalls.each(function(i) {
		$(this).mouseover(function(e) {
			e.preventDefault();
			var self = this;
			timer = setTimeout(function(){
				if (!move || cur == i) return;
				tq = new Image();
				smalls.eq(cur).removeClass(addclass);
				tq.src = $(self).find("img").attr("src");
				tq = $(tq);
				if (cur > i) {
					img.before(tq);
					tq.css("marginTop", -w);
					tq.animate({marginTop:0}, 500, function() {
						img.remove();
						img = tq;
						tq = null;
						move = true;
					});
				} else {
					img.parent().append(tq);
					img.animate({marginTop:-w}, 500, function() {
						img.remove();
						img = tq;
						tq = null;
						move = true;
					});
				}
				move = false;
				cur = i;
				smalls.eq(cur).addClass(addclass);					
			},300);			
		}).mouseout(function(){
			clearTimeout(timer);	
		}).click(function(){
			return false;	
		});
	});
}

