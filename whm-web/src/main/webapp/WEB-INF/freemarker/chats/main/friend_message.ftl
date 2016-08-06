<script type="text/javascript">
	  //var chatkey = "${chatkey}";
	  //var friendName = "${friendName}";
	  //var type = "${type}";
	  //var username = "${sessionUser.username}";

	  //var acceptMessageStr = "<div class=\"messageAcceptContainer\"> <img class=\"question_avatar\" src=\"${acceptUser.userLogo }\"> <p class=\"messageAcceptUsername\"> 	<b>${acceptUser.nickname }</b> <p> <p class=\"messageAcceptContent\">@content<p> </div>";

	  //var sendMessageStr = "<div class=\"messageSendContainer\">  <img class=\"question_avatar\" src=\"${sessionUser.userLogo }\" style=\"float: right;\">  <p class=\"messageSendUsername\">  	<b>${sessionUser.nickname }</b>  <p>  <p class=\"messageSendContent\">@content<p> </div>";

	  function buildMessage(msg){
		  var chatkey = "${chatkey}"
		  var friendName = "${friendName}";
		  var type = "${type}";
		  var username = "${sessionUser.username}";

		  msg = new Base64().encodes(msg);
		  var jsonStr = 
				 "{"+  "chatkey:\"" + chatkey +  "\",acceptName:\"" + friendName + 
				  "\",type:\"" + type +  
				  "\",username:\"" + username + 
				  "\",userChatkey:\"" + "${sessionUser.chatkey}" + 
				  "\",content:\"" + msg + "\"}";
		  return jsonStr;
	  }

      var websocket = null;

      //判断当前浏览器是否支持WebSocket
      if('WebSocket' in window){
          websocket = new WebSocket("ws://${domain}/charts/friend/${sessionUser.chatkey}");
      }else{
          alert('Not support websocket');
      }

      //连接发生错误的回调方法
      websocket.onerror = function(){
          //setMessageInnerHTML("error");
      };

      //连接成功建立的回调方法
      websocket.onopen = function(event){
          //setMessageInnerHTML("open");
      }

      //接收到消息的回调方法
      websocket.onmessage = function(event){
         try{
   		 	  var acceptMessageStr = "<div class=\"messageAcceptContainer\"> <img class=\"question_avatar\" src=\"${acceptUser.userLogo }\"> <p class=\"messageAcceptUsername\"> 	<b>${acceptUser.nickname }</b> <p> <p class=\"messageAcceptContent\">@content<p> </div>";
		  	  var sendMessageStr = "<div class=\"messageSendContainer\">  <img class=\"question_avatar\" src=\"${sessionUser.userLogo }\" style=\"float: right;\">  <p class=\"messageSendUsername\">  	<b>${sessionUser.nickname }</b>  <p>  <p class=\"messageSendContent\">@content<p> </div>";
		  	  var friendName = "${friendName}";
		  	  
        	  var msg = "";
        	  var messageJson = eval( "(" + event.data + ")");
        	  var messageObj = messageJson;
        	  if("1" == messageObj.type){//接受消息
        		  if(messageObj.username == friendName){
        			  msg = acceptMessageStr;
        		  }else{
        			 $("#" + messageObj.userChatkey).attr("style");
        		  }
        	  }else if("0" == messageObj.type){//发送消息
        		  msg = sendMessageStr;
        	  }
        	  msg = msg.replace("@content", messageObj.content);
        	  setMessageInnerHTML(msg);
           }catch(e){
         	  alert(e);
           }
      }

      //连接关闭的回调方法
      websocket.onclose = function(){
          //setMessageInnerHTML("close");
      }

      //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
      window.onbeforeunload = function(){
          websocket.close();
      }

      //将消息显示在网页上
      function setMessageInnerHTML(innerHTML){
          document.getElementById('${friendName}_msgDiv').innerHTML += innerHTML + "</br>";
          document.getElementById('${friendName}_msgDiv').scrollIntoView(false);
      }

      //关闭连接
      function closeWebSocket(){
          websocket.close();
      }
	  
      //发送消息
      function send(){
          var message = document.getElementById('${friendName}_editor').value;
          if(Trim(message) == ""){
        	  return;
          }
          
          message = buildMessage(message);
          websocket.send(message);
          
          $("#${friendName}_editor").val("");
          $("#${friendName}_editor").focus();
      }
      
  	jQuery(function($) {
		  // 你可以在这里继续使用$作为别名...
		$("#${friendName}_editor").keypress(function(e) {  
		    // 回车键事件  
	       if(e.which == 13) {
	    	   send();
	    	   //document.getElementById('msg_end').click();
	           return false;
	       }  
		});
		$("#${friendName}_editor").keyup(function(e) {  
		    // 回车键事件  
	       if(e.which == 13) {
	    	   $("#${friendName}_editor").val("");
	    	   return false;
	       }  
		});
		
		document.getElementById('${friendName}_msgDiv').scrollIntoView(false);
	});

  	function Trim(str){   
  		return str.replace(/(^\s*)|(\s*$)/g, "");   
  	}
</script>
<style>
	.messageContainer{width:100%;border:solid 1px #b8d0d6;}
	.messageContent{ width:100%;margin: 0px; }
	.messageSendContainer{width:98%;margin-top: 0px;}
	.messageSendUsername{font-size:15px;text-align:right;height:30px;padding-top:6px;margin-right:35px;}
	.messageSendContent{width:90%;margin-left: 50px;margin-top: 3px;text-align:right;background-color: antiquewhite;}
	
	.messageAcceptContainer{width:98%;margin: 0px;margin-left:15px;}
	.messageAcceptUsername{font-size:15px;height:30px;padding-top: 6px;margin-left:35px;}
	.messageAcceptContent{width:90%;margin-left: 30px;margin-top: 3px;    background-color: aliceblue;}
</style>
<div class="messageContainer">
<input type="hidden" id="chatkey_${friendName}" value="${chatkey}" />
<input type="hidden" id="friendName_${friendName}" value="${friendName}" />
<input type="hidden" id="type_${friendName}" value="${type}" />
<input type="hidden" id="username_${friendName}" value="${username}" />
<input type="hidden" id="acceptMessageStr_${friendName}" value='<div class="messageAcceptContainer"> <img class="question_avatar" src="${acceptUser.userLogo }"> <p class="messageAcceptUsername"> 	<b>${acceptUser.nickname }</b> <p> <p class="messageAcceptContent">@content<p> </div>' />
<input type="hidden" id="sendMessageStr_${friendName}" value='<div class="messageSendContainer">  <img class="question_avatar" src="${sessionUser.userLogo }" style="float: right;">  <p class="messageSendUsername">  	<b>${sessionUser.nickname }</b>  <p>  <p class="messageSendContent">@content<p> </div>' />
<dir id="msgDivContainer" style="width:100%;padding-left: 0px;margin-top: 0px;margin-bottom: 0px;overflow:auto;height:300px;">
<div class="messageContent" id="${friendName}_msgDiv">
<#list chatsFriendMessageList as data>	
	<#if data.username == sessionUser.username>
		<div class="messageSendContainer">
			<img class="question_avatar" src="${sessionUser.userLogo }" style="float: right;">
			<p class="messageSendUsername">
				<b>${sessionUser.nickname }</b>
			<p>
			<p class="messageSendContent">
				${data.content }
			<p>
		</div>
	<#else>
		<div class="messageAcceptContainer">
			<img class="question_avatar" src="${acceptUser.userLogo }">
			<p class="messageAcceptUsername">
				<b>${acceptUser.nickname }</b>
			<p>
			<p class="messageAcceptContent">
				${data.content }
			<p>
		</div>
	</#if>
	<br/>
</#list>
</div>
</dir>
<div style="width:100%;border:solid 1px #b8d0d6;">
	<textarea id="${friendName}_editor" name="description" rows="10" 
	style="width:99%;height:80px;"></textarea>
</div>
<input type="button" value="发送" onclick="send()"/>
<div>
