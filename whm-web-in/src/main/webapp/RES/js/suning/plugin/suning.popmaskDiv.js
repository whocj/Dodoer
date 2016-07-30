J_popDivcreat = {	
	open:function(btn,popId,cout){//btn:触发器,popId:弹出层,cout:遮罩透明度值
		width = document.documentElement.clientWidth ;		
		if( document.documentElement.clientHeight < document.body.offsetHeight ){height = document.body.offsetHeight;}else{height = document.documentElement.clientHeight;};
		$(btn).click(function(){		
			var h = $(popId).height();
			var w = $(popId).width();
			var newobj = document.createElement('div');
			newobj.setAttribute('id','bigdiv');
			newobj.style.position = "absolute" ;
			newobj.style.left = "0px" ;	
			newobj.style.top = "0px" ;	
			newobj.style.background = "#000" ;
			newobj.style.width = width + "px" ;
			newobj.style.height = height +"px" ;
			newobj.style.opacity = cout/100 ;
			newobj.style.filter="alpha(opacity="+cout+")";
			newobj.style.zIndex = 10001; 
			document.body.appendChild(newobj);			
			var top = (document.documentElement.clientHeight-h)/2+$(window).scrollTop();	
			var left = (width-w)/2 ;
			$(popId).css({"left":left+"px","top":top+"px"});
			$(popId).css("z-index","10002");
			$(popId).css("display","block");	
		});
	},
	close:function(btn,popId){//btn:触发器,popId:显示层
		$(btn).click(function(e){	
			var obj = document.getElementById("bigdiv");				  
			document.body.removeChild(obj);		
			$(popId).css("z-index","1");
			$(popId).css("display","none");	
			e.preventDefault();
		});
	}
};





