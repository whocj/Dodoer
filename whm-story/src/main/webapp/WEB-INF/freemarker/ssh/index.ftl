<#include "/common/include.ftl"/>
<!doctype html>
<html lang="en-US">
<head>
<meta property="qc:admins" content="134441654767070540556375" />
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="${siteKeywords }" />
<meta name="description" content="${siteDescription }" />
<title>SSH - ${siteTitle }</title>
</head>
<body>
	<div style="margin: auto;width:80%;">
		Welcome to SSH Console.
	</div>
	<div style="margin: auto;width:80%;height:400px;border:solid 1px #b8d0d6;overflow:auto">
		<div id="message" style="width:100%;overflow:hidden;"></div>
		<div style="margin-bottom: 0px;"><a style="width:1px;height:1px;text-decoration:none;" id="msg_end" name="1" href="#1">&nbsp;</a></div>
	</div>
	<div style="margin: 2px auto;width:80%;">
		<input id="text" type="text" onkeypress="textKeypress()" onkeydown="textKeydown()" style="width:100%;"/>
	</div>
<script type="text/javascript">
	var websocket = null;
	var refreshCount = 0;
	//判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket(
				"ws://${domain}/ssh/admin");
	} else {
		alert('Not support websocket');
	}

	//连接发生错误的回调方法
	websocket.onerror = function() {
		setMessageInnerHTML("SSH连接服务器失败!</br>");
	};

	//连接成功建立的回调方法
	websocket.onopen = function(event) {
		setMessageInnerHTML("SSH连接服务器成功!</br>");
	}

	//接收到消息的回调方法
	websocket.onmessage = function() {
		try{
			//设置回调，如果不出异常，则回调成功，否则走catch逻辑
			eval(event.data);
		}catch(e){
			document.getElementById('text').value = "";
			setMessageInnerHTML(event.data);
		}
	}

	//连接关闭的回调方法
	websocket.onclose = function() {
		setMessageInnerHTML("SSH连接关闭!</br>");
	}

	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	window.onbeforeunload = function() {
		websocket.close();
	}

	//将消息显示在网页上
	function setMessageInnerHTML(innerHTML) {
		var message = document.getElementById('message');
		if(refreshCount < 50000){
			refreshCount++;
			message.innerHTML += innerHTML;
		}else{
			refreshCount=0;
			message.innerHTML = innerHTML;
		}
		
		document.getElementById('msg_end').click();
		document.getElementById('text').focus();
	}

	//关闭连接
	function closeWebSocket() {
		websocket.close();
	}

	//发送消息
	function send(message) {
		if(message.length > 0){
			websocket.send(message);
		}
	}
	
	var textInput = document.getElementById("text");
	function textKeypress() {
       // 回车键事件  
       if(window.event.keyCode == 13) {
    	   send(textInput.value);
    	   return false;
       }
	}
	
	function textKeydown() {
		
		//处理Tab键
		if(window.event.keyCode == 9){
			stopEvent(window.event);
			var val = textInput.value;
			var message = "";
			
			if(val.length > 0){
				var vals = val.split(" ");
				if(vals.length == 2){
					message = "ls " + vals[1];
				}

				send(message + "#setText");
			}
			
			document.getElementById('text').focus();
			return false;
		}
	}

	function stopEvent (evt) { 
		var evt = evt || window.event; 
		if (evt.preventDefault) { 
			evt.preventDefault(); 
			evt.stopPropagation(); 
		} else { 
			evt.returnValue = false; 
			evt.cancelBubble = true; 
		} 
	}

	function str2DOMElement(html) {
	    var frame = document.createElement('iframe');
	    frame.style.display = 'none';
	    document.body.appendChild(frame);             
	    frame.contentDocument.open();
	    frame.contentDocument.write(html);
	    frame.contentDocument.close();
	    var el = frame.contentDocument.body.firstChild;
	    document.body.removeChild(frame);
	    return el;
	}

	function setText(str){
		var dom = str2DOMElement(str);
		var trDom = dom.getElementsByTagName("tr");
		if(trDom.length == 1){
			var path = trDom[0].children[0].innerText;
			if(path.length > 0){
				var textVal = textInput.value;
				var textVals = textVal.split(" ");
				if(textVals.length == 2){
					var tempPath = "";
					if(textVals[1].lastIndexOf("/") != -1){
						tempPath = textVals[1].substring(0, textVals[1].lastIndexOf("/") + 1);
					}
					
					document.getElementById('text').value = textVals[0] + " " + tempPath + path;
				}
			}
		}else if(trDom.length > 1){
			//document.getElementById('text').value = "";
			setMessageInnerHTML(str);
		}
	}
</script>
</body>
</html>
