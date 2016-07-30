/* 缓动插件 */
// t: current time, b: begInnIng value, c: change In value, d: duration
jQuery.extend( jQuery.easing,
{
	def: 'easeOutQuad',
	swing: function (x, t, b, c, d) {
		//alert(jQuery.easing.default);
		return jQuery.easing[jQuery.easing.def](x, t, b, c, d);
	},
	easeOutQuad: function (x, t, b, c, d) {
		return -c *(t/=d)*(t-2) + b;
	}
});

(function() {
	$.suning || ($.suning = {});
	$.suning.productView = {};
	
	//继承
	$.suning.productView.extend = function (subClass, superClass) {
		var F = function() {};
		F.prototype = superClass.prototype;
		subClass.prototype = new F();
		superClass.prototype.constructor = superClass;
		subClass.prototype.constructor = subClass;
		subClass.base = superClass.prototype;
	}
})();

//快捷方式
$PV = $.suning.productView;//快捷方式

//产品图片
$PV.PicDetail = function() {
	var img = $("#product_imgs_box");
	if (img.length == 0)	return;
	var tq = null;
	var smalls = img.find(".product_s_images ul a");
	var move = true;
	var cur = 0;
	var preview = $('#preview');
	img = img.find("img").eq(0);
	var timer = null;
	smalls.each(function(i) {
		$(this).mouseover(function(e) {
			e.preventDefault();
			var self = this;
			timer = setTimeout(function(){
				if (!move || cur == i) return;
				tq = new Image();
				smalls.eq(cur).parent().removeClass("on");
				tq.src = self.href;
				preview.find('img').attr('src',tq.src);
				tq = $(tq);
				if (cur > i) {
					img.before(tq);
					tq.css("marginTop", -304);
					tq.animate({marginTop:0}, 500, function() {
						img.remove();
						img = tq;
						tq = null;
						move = true;
					});
				} else {
					img.parent().append(tq);
					img.animate({marginTop:-304}, 500, function() {
						img.remove();
						img = tq;
						tq = null;
						move = true;
					});
				}
				move = false;
				cur = i;
				smalls.eq(cur).parent().addClass("on");					
			},300);
			
		}).mouseout(function(){
			clearTimeout(timer);	
		}).click(function(){
			return false;	
		});
	});
}

$PV.imgscroll = function(){
	var btn_next = $('#btn_next');
	var btn_prev = $('#btn_prev')
	var ul = btn_next.next().find('ul');
	var len = ul.find('li').length;
	var i = 0 ;
	if (len > 4) {
		ul.css("width", 62 * len)
		btn_next.click(function(e) {
			e.preventDefault();					
			if(ul.is(":animated")){return false}
			ul.animate({marginLeft:-60 }, 500,function(){
				ul.append(ul.find("li:first"));
				ul.css("marginLeft",0);													  
			})
		});
		
		btn_prev.click(function(e) {
			e.preventDefault();					
			if(ul.is(":animated")){return false}
			ul.find("li:first").before(ul.find("li:last"));
			ul.css("marginLeft",-62);
			ul.animate({marginLeft:0 }, 500)
		});
	}else{
		btn_next.css("visibility","hidden");
		btn_prev.css("visibility","hidden");
	}
}

// 放大镜效果
$PV.imgzoom = function() {
	var zoompos = {x:0,y:0};//定义一个对象，缓存x,y变量.
	var $pre = $("#preview");
	var $preimg = $("#preview img");
	var p_w  = parseInt( $preimg.css("width"),10);
	var p_h  = parseInt( $preimg.css("height"),10);
	$(".zoomple").bind("mouseenter",function(e){
		var $this  = $(this);
		var $zoom = $this.find(".zoomplePopup");
		$zoom.show();
		$pre.show();
		PositionPopupZoom( $this , $zoom , e.pageX , e.pageY , p_w , p_h );
		$this.bind("mousemove",function(e){
			setTimeout(function(){
				PositionPopupZoom( $this , $zoom , e.pageX , e.pageY , p_w , p_h);
			},10);
		});
		
	}).bind("mouseleave",function(){
		var $this  = $(this);
		var $zoom = $this.find(".zoomplePopup");
		$zoom.hide();
		$pre.hide();
		$this.unbind("mousemove");
	}).click(function(){
		return false;
	});
	
	
	
	
	/*
		wrap : 当前绑定的元素
		zoom : 当前绑定的元素里的popupzoom元素
		x, y : 当前鼠标在页面上的位置
		w, h : 放大的图片的高度和宽度,用来计算比例
	*/
	function PositionPopupZoom( wrap , zoom , x , y , w , h){
		var wrapLeft = wrap.offset().left;
		var wrapTop =  wrap.offset().top;
		var zoomWidth = zoom.width();
		var zoomHeight = zoom.height();
		var wrapWidth = wrap.width();
		var wrapHeight = wrap.height();
		//计算半透明层的x,y.确定它不超出图片
		zoompos.x =  x -wrapLeft - (zoomWidth/2);
		zoompos.y =  y -wrapTop- (zoomHeight/2);
		if( zoompos.x <= 0 ){
			zoompos.x  =  0;
		}
		if( zoompos.y <= 0 ){
			zoompos.y  =  0;
		}
		if( zoompos.x + zoomWidth >= wrapWidth){
			zoompos.x  = wrapWidth  -  zoomWidth; 
		}
		if( zoompos.y + zoomHeight >= wrapHeight){
			zoompos.y  = wrapHeight  -  zoomHeight; 
		}
		//放大比例
		var xRatio = w / wrapWidth;  
		var yRatio = h / wrapHeight;
		//设置位置
		zoom.css({left:zoompos.x,top:zoompos.y});
		$preimg.css({left: -(zoompos.x * parseInt(xRatio)) , top:  -(zoompos.y * parseInt(yRatio)) }); 
	}		

}

//选择城市
$PV.cityBox = function() {
	var city = $("#citybox");
	var btn,isopen = false,ref, cityc,offset,poffset, active = false, timeout;
	var url = 'city.htm';
	var nowprovince = "";
	var onChange;
	return {
		init: function(fn) {
			onChange = fn || (function(cname) {alert(cname)})
			if (city.length == 0) {
				return;
			}
			ref = this;
			
			
			btn = $("#citybox_btn").click(function(e) {
				if(city.css('display')=="none"){
					$(this).addClass('select');	
					city.show();
				}else{
					$(this).removeClass('select');	
					city.hide();
				}											   
				e.preventDefault();
				
			})
			
			city.mouseover(function(){
				$(this).show();		
				$("#citybox_btn").addClass('select');
				$("#citybox_btn02").addClass('select');
			}).mouseleave(function(){
				$(this).hide();
				$("#citybox_btn").removeClass('select');
				$("#citybox_btn02").removeClass('select');
			});
			
			city.find("table a").click(function(e) {
				ref.x=parseInt($(this).offset().left-$(".province").offset().left);
				ref.y=parseInt($(this).offset().top-$(".province").offset().top);
				e.preventDefault();
				if (cityc != null) {
					cityc.remove();
					cityc = null;
				}
				active = false;
				clearTimeout(timeout);
				nowprovince = this.innerHTML;
				poffset = $(this).offset();
				var _this=this;
				(function() {
					var qp = nowprovince;
					$.get(url, function(data) {
						if (qp == nowprovince) {
							ref.addCity(data,$(_this).parent());
						}
				})})()
				city.find("table a").css({"z-index":0,"position":"relative"});
				$(this).css("z-index",100000);
				//$(this).css("border","1px solid #999999");
				$(this).css("position","relative");
				$(this).addClass('on').parent().parent().siblings().find('a').removeClass('on');
				$(this).addClass('on').parent().siblings().find('a').removeClass('on');
				$(this).addClass('on').siblings().removeClass('on');
				
			}).mouseleave(function(e) {
				timeout = setTimeout(function() {
					if (!active && cityc != null) {
						cityc.remove();
						$(".province").find('a').removeClass('on');
						cityc = null;
						city.css({"height":"auto"});
					}
				},200)
			});
			
			/* 关闭按钮 */
			city.find(".closeIco").click(function(event){
				city.hide();
				event.preventDefault();
			});
		},
		changeView: function() {
			city.css("display", isopen ? "none" : "block");
			if (!offset && !isopen) {
				offset = city.offset();
			}
			isopen = !isopen;
		},
		addCity: function(str,obj) {
			var re = /([A-Za-z]+)(.[^\|]+)/g;
				var o = {}
				while(r = re.exec(str)) {
					
					if (!o[r[1]]) {
						o[r[1]] = [r[2]];
					} else {
						o[r[1]].push(r[2]);
					}
				}
				var loc = 0;
				var template = '<table class="citys"><tr>';
				for (var i in o) {
					var temp = "";
					var temparr = o[i];
					for (var j=0 ;j<temparr.length; j++) {
						temp += '<a href="#">' + temparr[j] + '</a>'
					}
					template += '<td class="td03">' + temp + '</td>';
					template += "</tr><tr>";
					
					loc++;
				}
				template = template.substring(0, template.length-5) + "</table>";
				cityc = $(template);
				city.append(cityc);
				city.css({"height":this.y+cityc.height()+30});
				cityc.mouseover(function(e) {
					active = true;
				}).mouseleave(function(e) {
					active = false;
					cityc.remove();
					cityc = null;
					city.css({"height":"auto"});
					$(".province").find('a').removeClass('on');
				});
				cityc.find("a").click(function(e) {
					//onChange(this.innerHTML);
					setTimeout(function(){
						city.hide();
						$("#citybox_btn").removeClass('select');
						$("#citybox_btn02").removeClass('select');	
						city.css({"height":"auto"});
					},300);		
					$('#citybox_btn .ctext').html(this.innerHTML);
					$('#citybox_btn02').html(this.innerHTML);
					e.preventDefault();
				});
				cityc.css({left:10+"px" ,top:this.y+20+"px" });
				//cityc.css({left:this.x+"px" ,top:this.y+20+"px" });
		}
	}
}

/* 增加减少商品数量 */
var ChangeProNums = function(p,n,maxN){
	/* 商品单价 */
	var sprice = p;
	/* 初始商品数量 */
	var num = n;
	/* 商品数量上限 */
	var maxNum = maxN;
	var oldNum = n;
	var _this = this;
	if(!ChangeProNums._init){
		ChangeProNums.prototype.init=function(){
			_this.cangeNums(num);
			$("#proNumInp").find(".add").click(function(event){
				if(!(num>=maxNum)){
					num++;
					_this.cangeNums(num);
				}
				event.preventDefault();
			});
			$("#proNumInp").find(".subtract").click(function(event){
				if(!(num<=1)){
					num--;
					_this.cangeNums(num);
					$(this).parent().siblings(".errormsg").hide();
				}											 
				event.preventDefault();											 
			});
			$("#proNumInp").find("input").keyup(function(){
				var val = $(this).val();
				if(!/^\d*$/.test(val)){num = oldNum;_this.cangeNums(num);return false;}
				if(val>maxNum){
					num = maxNum;
					$(this).parent().siblings(".errormsg").show();
				}else if(val<=0&&val!=""){
					num = 1;
				}else{
					num = val;
					$(this).parent().siblings(".errormsg").hide();
				}
				_this.cangeNums(num);
			}).blur(function(){
				if(num==""){
					num=1;
					_this.cangeNums(num);	
				}
			});
		}
		ChangeProNums._init = 1;
	}
	
	this.cangeNums = function(num){
		$("#proNumInp").find("input").val(num);
		$("#proNumResult").html(sprice*num);
		oldNum = num;
	}
}

/*弹出层*/
function ObjOutshow(showId,o){
	var width = document.documentElement.clientWidth - 3;
	if( window.screen.availHeight < document.body.scrollHeight ){
		 height = document.body.scrollHeight;
	 }else{
		 height = window.screen.availHeight;
	 }
	var newobj = document.createElement('div');
	var p=$(o).offset();
	var top=p.top;
	var left=p.left;
	var h=$("#"+showId).height();
	var w=$("#"+showId).width();

	newobj.setAttribute('id','bigdiv');
	newobj.style.position = "absolute" ;
	newobj.style.left = "0px" ;	
	newobj.style.top = "0px" ;	
	newobj.style.background = "#666666" ;
	newobj.style.width = width + "px" ;
	newobj.style.height = height +"px" ;
	newobj.style.opacity = 0 ;
	newobj.style.filter="alpha(opacity=0)";
	newobj.style.zIndex = 100001; 
	newobj.innerHTML = '<iframe id="ss" name="ss" style="position:absolute;top:-5px;left:0;border:none;width:100%;height:100%;background:#666666;filter:alpha(opacity=0);" ></iframe>';
	document.body.appendChild(newobj);	
	$("#"+showId).css({"top":top+"px","left":left+"px",height:0,width:0});
    $("#"+showId).attr("t",top);
	$("#"+showId).attr("l",left);
	$('#bigdiv').animate({opacity:0.65},300,function(){
		$("#"+showId).animate({height:h+"px",width:w+"px",top:(document.documentElement.clientHeight-h)/2+$(window).scrollTop()+"px",left:(width-w)/2+"px"},function(){
			$("#"+showId).height("auto");																								  
			$("#"+showId).width(w);																							  
		})	
	});	
	$("#"+showId).css('z-index','100002');
}
function ObjOuthide(o){
	var h=$("#"+o).height();
	var w=$("#"+o).width();
	$("#"+o).animate({height:0,width:0,left:$("#"+o).attr("l")+"px",top:$("#"+o).attr("t")+"px"},300,function(){
		$("#"+o).hide();																										  
		$("#"+o).height("auto");																								  
		$("#"+o).width(w);																							  
	});
	$("#"+o).parent().css('z-index',0);
	var addId = document.getElementById('bigdiv');
	$('#bigdiv').animate({opacity:0},300,function(){
		document.body.removeChild(addId);											 
	});	
}
function wrongShow(o){
	var text = $(o).parents().find('td').eq(0).text();							  
	$('#seachtext').text(text);	
	var width = document.documentElement.clientWidth - 3;
	if( window.screen.availHeight < document.body.clientHeight ){
		 height = document.body.clientHeight;
	 }else{
		 height = window.screen.availHeight;
	 }
	var newobj = document.createElement('div');
	newobj.setAttribute('id','bigdiv');
	newobj.style.position = "absolute" ;
	newobj.style.left = "0px" ;	
	newobj.style.top = "0px" ;	
	newobj.style.background = "#666666" ;
	newobj.style.width = width + "px" ;
	newobj.style.height = height +"px" ;
	newobj.style.opacity = 0.6 ;
	newobj.style.filter="alpha(opacity=60)";
	newobj.style.zIndex = 100001; 
	document.body.appendChild(newobj);	
	var selfW = (document.documentElement.clientWidth - $('.pro_wrongOut').width())/2 ;
	var selfH = (document.documentElement.clientHeight - $('.pro_wrongOut').height())/2 ;
	$('.pro_wrongOut').css('z-index',100002);
	$('.pro_wrongOut').css('top',$(window).scrollTop()+selfH);
	$('.pro_wrongOut').css('left',selfW);
	$('.pro_wrongOut').css("display","block");
	
}
function wrongRemove(){
	$('.pro_wrongOut').hide();
	document.body.removeChild( document.getElementById("bigdiv") );
}

/* 设置选中颜、色类型 */
function changeResultType(){
	var $colors = $("#colors");
	var $sizes = $("#sizes");
	var $color_text = $("#result_type").find(".color_text");
	var $size_text = $("#result_type").find(".size_text");
	$colors.find(">li").click(function(e){
		e.preventDefault();
		setText($(this).find("a").html(),$color_text,$(this));
	});
	$sizes.find(">li").click(function(e){
		e.preventDefault();
		setText($(this).find("a").html(),$size_text,$(this));
	});
	function setText(v,textcon,o){
		if(o.hasClass("unable")){return false}
		o.addClass("on").siblings().removeClass("on");
		textcon.html(v);	
	}
	$color_text.html($colors.find(">li.on").find("a").html());
	$size_text.html($sizes.find(">li.on").find("a").html());
}
/* 同类热卖 */
function change_similar(){
	$("#similar_prolist>li").mouseover(function(){
		$(this).addClass("on").siblings().removeClass("on");											
	});	
}
/* 服务显示\隐藏 */
function change_serve_show(){
	var $serve_show = $("#serve_show");
	var $serve_show_btn = $serve_show.find("dt>a");
	var $serve_show_msg = $serve_show.find("dd>div");
	var $toggle_line_btn = $("#toggle_line>a");
	var tag = 0;
	$serve_show_btn.click(function(){
		$(this).addClass("curr").siblings().removeClass("curr");						   
		$serve_show_msg.eq($(this).index()).show().siblings().hide();
		$toggle_line_btn.removeClass("on");
		tag = 0;
		return false;
	});
	$toggle_line_btn.click(function(){
		if(!tag){
			$(this).addClass("on");
			$serve_show_msg.show();	
			$serve_show_btn.removeClass("curr");
			tag=1;
		}else{
			$(this).removeClass("on");
			$serve_show_msg.hide().eq(0).show();
			$serve_show_btn.eq(0).addClass("curr");
			tag=0;
		}
		return false;
	});
}
/* 套餐商品明细 */
function change_groups(){
	var $con = $("#groups_out");
	var $lbtn = $con.find(".lbtn");
	var $rbtn = $con.find(".rbtn");
	var $ul = $con.find(".groups_out_ul");
	var t = Math.ceil($ul.find("li").not(".icos").length/4);
	var i = 0;
	$ul.css("width",t*744);
	$rbtn.click(function(){
		if(i<t-1){i++}
		$ul.animate({"left":-i*744},400);					 
	});
	$lbtn.click(function(){
		if(i>0){i--}
		$ul.animate({"left":-i*744},400);					 
	});
}

/* 商品参数 */
function change_detail(){
	$("#detail_table tr").hover(function(){
		$(this).find(".erro_recovery").show();									 
	},function(){
		$(this).find(".erro_recovery").hide();	
	});	
}
function pack_show(obj){
	$(obj).find(".fond_wrong a").show();
}
function pack_hide(obj){
	$(obj).find(".fond_wrong a").hide();
}

/* 评价、咨询、晒单tab切换 */
function change_tab(id){
	var $con = $("#"+id);
	var $lis = $con.find(".pro_tab>ul>li");
	var isloaded = [];
	$lis .click(function(event){
		event.preventDefault();	
		var self = $(this);
		var i = self.index();
		self.addClass("on").siblings().removeClass("on");
		if(!isloaded[i]&&i!=0){
			var url = "data/"+id+i+".htm";
			isloaded[i] = 1;
			$.get(url,function(data){
				addHtml(data,$con.find(".bd"+i));
			});
		}
		$con.find(".bd"+i).show().siblings().not(".pro_tab").hide();
	});	
}
/* 评价、咨询、晒单加载 */
function loadDate(id){
	var $con = $("#"+id);
	var url = "data/"+id+"0.htm";
	var wh = $(window).height(),h;
	$(window).scroll(function(){
		h = $(window).scrollTop()+wh;
		if(checkScroll(h,$con)){
			loaddata();
		}
	});
	function loaddata(){
		if($con.loaded){return;};
		$con.loaded = 1;
		$.get(url,function(data){
			addHtml(data,$con.find(".bd0"));
		});
	};
	//检查DOM是否可见
	function checkScroll(h,o) {	
		if(!o.offset()){return;};
		return (h >= o.offset().top);
	}
}
//评价、咨询、晒单内容添加
function addHtml(data,$con){
	$con.html(data);	
	stopA();
	if($con.parent().attr("id")=="pro_reviews"){
		//设置百分比进度条
		setPercent(37.8,20,60,$con);
	}
}
/* 图片懒加载 */
function lazyImg(){
	var imgs = $(".lazy_load").find("img[loadsrc]");
	var len = imgs.length;
	var loaded = 0,h,y,loadsrc;
	$(window).scroll(function(){
		if(loaded){return;};				  
		loadimg();
	});
	function loadimg(){
		h = $(window).scrollTop()+$(window).height();				  
		for(var i=0;i<len;i++){
			y = $(imgs[i]).offset().top;
			loadsrc = $(imgs[i]).attr("loadsrc");
			if(y<h&&loadsrc){
				$(imgs[i]).attr("src",loadsrc).removeAttr("loadsrc");
				if(i==len-1){
					loaded = 1;	
				}
			}
		}	
	}
}
/* 去结算提示 */
function change_go2cart(){
	var $go2shoppingcart = $("#go2shoppingcart");
	var $close_btn = $go2shoppingcart.find(".close_btn") 
	$(".add_cart_btn").click(function(e){
		e.preventDefault();							  
		$go2shoppingcart.show();
		$go2shoppingcart.parent().css("z-index",999);
	});	
	$close_btn.click(function(e){
		e.preventDefault();	
		$go2shoppingcart.hide();
		$go2shoppingcart.parent().css("z-index",1);
	});
}
/* 回到顶部 */ 
function backTop(o){
	var isIE=!!window.ActiveXObject; 
	var isIE6=isIE&&!window.XMLHttpRequest;
	return{
		init:function(){
			var w = $(window).width();
			var left = w/2+490;
			backTop.oTop = o.offset().top;
			o.css({"left":left});
			if($(window).scrollTop()>0){
				o.show();
			}
		},
		scrollTops:function(){
			var oScrollTop = $(window).scrollTop();
			var clientHeight = $(window).height(); 
			var top = $(window).scrollTop()+clientHeight-100;
			if(isIE6){
				o.css({"top":top});	
			}
			if($(window).scrollTop()>0){
				o.fadeIn();
			}else{
				o.fadeOut();	
			}
		}
		,start:function(){
			$(document.body).append('<div id="back_top" class="back_top" title="回顶部">回顶部</div>');
			var obackTop = backTop($("#back_top"));
			$("#back_top").click(function(event){$(window).scrollTop(0);event.preventDefault();});
			backTop($("#back_top")).init();	
			$(window).resize(function(){
				if(!isIE6){
					obackTop.init();
				}
			})
			$(window).scroll(function(){
				if(!isIE6){
					obackTop.init();
				}
				obackTop.scrollTops();	
			});		
		}
	}
}
/* 配件推荐 */
function accessory(){
	var $accessory = $("#accessory");
	var $acc_ul = $accessory.find(".acc_ul");
	var $lbtn = $accessory.find(".lbtn");
	var $rbtn = $accessory.find(".rbtn");
	var $checkbox = $acc_ul.find(">li>.checkbox>input");
	var $checkNum = $("#checkNumBox>a");
	var len = $acc_ul.find("li").length;
	var currlen = len;
	var i = 0;
	var endi = currlen>5?Math.floor(currlen/5):0;
	var yigou_money = $("#yigou_money"),end_money = $("#end_money"),save_money = $("#save_money"),acc_num = $("#acc_num");
	var yigouM = +(yigou_money.text().split("￥").join("")),endM = +(end_money.text().split(",").join("")),accN = 0;
	$acc_ul.css("width",len*110);
	$("#pacc_bd_tab>ul>li").click(function(){
		var index = $(this).index();
		$checkNum.removeClass("on");
		$(this).addClass("on").siblings().removeClass("on");
		$acc_ul.css("left",0);
		$acc_ul.find("li").hide();
		$acc_ul.find("li.type"+index).show();
		i = 0;
		currlen = $acc_ul.find("li.type"+index).length;
		if(index ==0 ){
			$acc_ul.css("left",0);
			$acc_ul.find("li").show();
			i = 0;
			currlen = $acc_ul.find("li").length;
		}
		endi = currlen>5?Math.floor(currlen/5):0;
	});
	$rbtn.click(function(){
		if(i<endi){i++}
		var scrollnum = i*5*110;
		$acc_ul.animate({"left":-scrollnum},400);
	});
	$lbtn.click(function(){
		if(i>0){i--}
		var scrollnum = i*5*110;
		$acc_ul.animate({"left":-scrollnum},400);
	});
	$checkbox.each(function(){
		if($(this).attr("checked")==true){
			$(this).parent().siblings(".img").addClass("imgon");
			var arrM = getMoney($(this).parent().siblings(".price"));
			yigouM = yigouM+arrM[0];
			endM = endM+arrM[1];
			accN++;
		}
	});
	setMoney(yigouM,endM,accN);
	$checkbox.click(function(){
		if($(this).attr("checked")==true){											   
			$(this).parent().siblings(".img").addClass("imgon");
			var arrM = getMoney($(this).parent().siblings(".price"));
			yigouM = yigouM+arrM[0];
			endM = endM+arrM[1];
			accN++;
			changeCheckNum($checkNum,accN);
			setMoney(yigouM,endM,accN);
		}else{
			$(this).parent().siblings(".img").removeClass("imgon");	
			var arrM = getMoney($(this).parent().siblings(".price"));
			yigouM = yigouM-arrM[0];
			endM = endM-arrM[1];
			accN--;
			changeCheckNum($checkNum,accN);
			setMoney(yigouM,endM,accN);
		}
	});
	$checkNum.click(function(){
		$("#pacc_bd_tab>ul>li").removeClass("on");
		$acc_ul.css("left",0);
		i=0;
		$(this).addClass("on");	
		currlen = 0;
		$acc_ul.find("li").hide();
		$checkbox.each(function(){
			if($(this).attr("checked")==true){
				$(this).parents("li").show();
				currlen++;
			}
		});
		endi = currlen>5?Math.floor(currlen/5):0;
	});
	function getMoney(con){
		var m1 = +(con.find(".yigou_p").text().split("￥").join("")),
			m2 = +(con.find(".end_p").text().split("￥").join(""));
		return [m1,m2];
	}
	function setMoney(m1,m2,n){
		yigou_money.text("￥"+m1);
		end_money.text(m2);
		save_money.text(m1-m2);
		acc_num.text(n);
	}
	function changeCheckNum(numBox,num){
		numBox.find(".num").html(num);
	}
}
//切换套餐商品参数内容
function showDetail(e,items){
	var ev=e||window.event;
	var tar=ev.target||ev.srcElement;
	if(tar.tagName.toLowerCase()!="a"||$(tar).parents(".detail").length!=0||$(tar).parent().next(".detail").css("display")!="none") return;
	$(items).siblings("table").css("display","none");
	$(items).find("li>a").each(function(i){
		if(this==tar){
			$(tar).addClass("on").parent().next(".detail").show("fast");
			$($("#package_items").siblings("table")[i]).css("display","block");
		}else{
			$(this).removeClass("on").parent().next(".detail").slideUp("fast");
		}
	});
}
//评价百分比
function setPercent(n1,n2,n3,con){
	var arr = Array.prototype.slice.call(arguments);
	for(var i=0;i<3;i++){
		arr[i] = -(100-arr[i])/100*200;
		con.find(".percent_chart li").eq(i).find(".chart").css("background-position",arr[i]+"px -150px");
	}
}

//阻止链接默认事件
function stopA(){
	$("a").click(function(e){
		if($(this).attr("href")=="javascript:void(0)"){e.preventDefault();};				  
	})	
}
/*用户登录弹出框*/
function SNuserRegitLogo(){
	var width = document.documentElement.clientWidth;
	if( window.screen.availHeight < document.body.offsetHeight ){
		height = document.body.scrollHeight;
	}else{
		height = window.screen.availHeight;
	}
	var h = $("#userRegitLogo").height();
	var w = $("#userRegitLogo").width();
	var newobj = document.createElement('div');
	newobj.setAttribute('id','bigdiv');
	newobj.style.position = "absolute" ;
	newobj.style.left = "0px" ;	
	newobj.style.top = "0px" ;	
	newobj.style.background = "#000" ;
	newobj.style.width = width + "px" ;
	newobj.style.height = height +"px" ;
	newobj.style.opacity = 0.15 ;
	newobj.style.filter="alpha(opacity=15)";
	newobj.style.zIndex = 100001; 
	document.body.appendChild(newobj);								
	var top = (document.documentElement.clientHeight-h)/2+$(window).scrollTop();
	var left = (width-w)/2 ;
	$("#userRegitLogo").css({"left":left+"px","top":top+"px"});
	$("#userRegitLogo").show();		
};
function SNbanksubmitclose(){
	$("#userRegitLogo").hide();
	$("#bankSubmitout").hide();
	var addId = document.getElementById('bigdiv');	
	document.body.removeChild(addId);										 
};
/*输入框焦点*/
function inputfocus(o){
	$(o).addClass("focus");
	if($(o).val() == $(o)[0].defaultValue ){  
		$(o).val("");           
	};	
};
function inputblur(o){
	$(o).removeClass("focus");	
	if ($(o).val() == '') {
        $(o).val($(o)[0].defaultValue);
    };
};
$(function(){
	$PV.PicDetail();
	$PV.imgscroll();
	$PV.imgzoom();	
	$PV.cityBox().init();
	/* 改变选中的颜色、尺寸文本 */
	changeResultType();
	/* 产品数量修改 */
	new ChangeProNums(11,1,120).init();
	/* 购物车提示 */
	change_go2cart();
	/* 同类热卖 */
	change_similar();
	/* 特色服务 */
	change_serve_show();
	/* 套餐 */
	change_groups();
	/* 配件 */
	accessory()
	/* 商品参数 */
	change_detail();
	/* 评价、咨询、晒单tab切换 */
	change_tab("pro_reviews");
	change_tab("pro_ask");
	/* 图片懒加载 */
	lazyImg();
	/* 评价、咨询、晒单内容动态载入 */
	loadDate("pro_reviews");
	loadDate("pro_ask");
	loadDate("pro_billshow");
	/* 回到顶部 */
	backTop().start();
	/* 阻止链接默认事件 */
	stopA();
})