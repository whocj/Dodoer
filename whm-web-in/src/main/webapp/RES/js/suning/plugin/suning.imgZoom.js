J_snimgzoom = function(id,img,zoomple) {
	var zoompos = {x:0,y:0};//定义一个对象，缓存x,y变量.
	var $pre = $(id);
	var $preimg = $(img);
	var p_w  = parseInt( $preimg.css("width"),10);
	var p_h  = parseInt( $preimg.css("height"),10);
	$(zoomple).bind("mouseenter",function(e){
		var $this  = $(this);
		var $zoom = $this.find(".zoomplePopup");
		$preimg.attr("src",$preimg.attr("src2"));
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
	/*wrap : 当前绑定的元素
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

// J_snimgzoom("#preview","#preview img",".zoomple");    调用

/*
//结构如下
<div class="product_b_image">
	<a href="temp/product/img.jpg" class="zoomple">
		<img src="temp/product/img.jpg" width="300" height="260" />  //小图
		<div class="zoomplePopup"></div>
	</a>
	<div id="preview" >
	    <img src="temp/product/img.jpg"  />  //大图
	</div>
</div>
*/