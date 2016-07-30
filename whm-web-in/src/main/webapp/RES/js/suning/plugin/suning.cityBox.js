J_cityBox = function(btn,cityId) {
	var city = $(cityId);
	var btn,isopen = false,ref, cityc,offset,poffset, active = false, timeout, tempProvince;
	var url = 'cityjson.json';
	var nowprovince = "";
	var onChange;
	return {
		init: function(fn) {
			onChange = fn || (function(cname,ccode){ alert(cname+'--'+ccode) });
			if (city.length == 0) {
				return;
			};
			ref = this;				
			btn = $(btn).click(function(e) {
				if(city.css('display')=="none"){
					$(this).addClass('select');	
					city.show();
				}else{
					$(this).removeClass('select');	
					city.hide();
				}											   
				e.preventDefault();				
			});			
			city.mouseover(function(){
				$(this).show();		
				$(btn).addClass('select');
			}).mouseleave(function(){
				$(this).hide();
				$(btn).removeClass('select');
			});			

			city.find("table a").click(function(e) {	
				tempProvince=$(this);			
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
		},
		changeView: function() {
			city.css("display", isopen ? "none" : "block");
			if (!offset && !isopen) {
				offset = city.offset();
			}
			isopen = !isopen;
		},
		addCity: function(str,obj) {
		        var o = {};
				var code={} ;
				str =  eval(str);
				
				$(str).each(function(){ 
				   // alert(this.name+" "+this.code+this.title); 
					if (!o[this.title]) {
					o[this.title] = [this.name];
					code[this.title] = [this.code];
					} else {
					o[this.title].push(this.name);
					code[this.title].push(this.code);
					} 
				}); 
				
				var loc = 0;
				var template = '<table class="citys"><tr>';
				var temp = "";
				for (var i in o) {
					var temp = "";
					var temparr = o[i];
					var temparrValue = code[i];
					for (var j=0 ;j<temparr.length; j++) {
						temp += '<a name=' + temparrValue[j] +' href="#">' + temparr[j] + '</a>'
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
					onChange(this.innerHTML,this.name);						   
					setTimeout(function(){
						city.hide();
						$(btn).removeClass('select');
						city.css({"height":"auto"});
					},300);		
					$(btn).find(".ctext").html(this.innerHTML);
					e.preventDefault();
				});
				cityc.css({left:10+"px" ,top:tempProvince.offset().top+20+"px" });
		}
	}
}


/*加载调用*/
$(function(){
	J_cityBox("#citybox_btn","#citybox").init();  //城市切换
});