<script src="${resRoot }/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!--
function StringHas(srcStr, childStr){
	var strs = srcStr.split(",");
	for(var i=0; i<strs.length;i++ ){
		if(strs[i] == childStr){
			return true;
		}
	}
	false;
}

function tagManager(tagValue){
	var tagV = $("#tagName").val();
	if(tagV != ""){
		if(StringHas(tagV, tagValue)){
			return;
		}
		
		tagV = tagV + ",";
	}
	tagV = tagV + tagValue;
	$("#tagName").val(tagV);
}

	function checkContent(){
	  if($("#title").val() == ""){
			layer.tips('标题不能为空.', '#title', {time: 4000,tips:[2]});
			$("#title").focus();
			return false;
		}
		
		var stemTxt = CKEDITOR.instances.topicContent.document.getBody().getText(); //取得纯文本 
		
		if(stemTxt == "" || stemTxt.length < 10){
			layer.tips('内容不能为空，长度10个字符以上.', '#subButton', {time: 2000,tips:[1]});
			return false;
		}
	  
	  return true;
	}

	function submitForm(){
		if(checkContent()){
			$("#jvForm").submit();
		}
	}

//-->
</script>
<div class="main-wrap">
    <div id="main">
    <div class="topInfo" id="breadCrumb">
    <img align="absMiddle" src="${resRoot }/images/home.gif">
    	<a href="${base }/bbs/index.htm">BBS</a> » 发表新主题</div>
    <div class="t z" style="margin:5px auto;">
    <form id="jvForm" action="${base }/t/topicCommit.htm" method="post" enctype="multipart/form-data" validate="true" onsubmit="return checkContent();">
      <table cellspacing="0" cellpadding="0" width="100%" align="center">
        <tbody>
          <tr style="color: #666; line-height: 23px; height: 23px">
            <td class="h"><b>发表新话题</b></td>
            <td class="h"></td>
          </tr>
          <tr class="tr2">
            <td colspan="99" style="border-bottom-width: 0px"></td>
          </tr>
          <tr>
            <td class="f_one" valign="top" style="padding-right: 7px; padding-left: 7px; padding-bottom: 7px; padding-top: 7px">
            <strong>标题*</strong>
            </td>
            <td class="f_one" valign="top" style="padding-right: 7px; padding-left: 7px; padding-bottom: 7px; padding-top: 7px">
            	<input class="required s_ipt" maxlength="100" size="80" id="title" name="title" type="text">
            </td>
          </tr>
          <tr >
            <td class="f_one" valign="top" style="padding-right: 7px; padding-left: 7px; padding-bottom: 7px; padding-top: 7px">
            <strong>标签</strong>
            </td>
            <td class="f_one" valign="top" style="padding-right: 7px; padding-left: 7px; padding-bottom: 7px; padding-top: 7px">
            	<input class="s_ipt" type="text"
								name="tagName" id="tagName" value="" maxlength="100" size="80">
				<dir style="margin: 5 0;padding-left: 5px;">
	            	<#list tagList as datas>
						<a href="javascript:tagManager('${datas.name }')" class="btn btn-mini">${datas.name }</a>
					</#list>
				</dir>
            </td>
          </tr>
          <tr height="400px">
            <td class="f_one" valign="top" width="20%" style="padding-right: 7px; padding-left: 7px; padding-bottom: 7px; padding-top: 7px"><b>内容*</b><br>
            </td>
            <td class="f_one" valign="top" style="padding-right: 7px; padding-left: 7px; padding-bottom: 7px; padding-top: 7px">
            <div><textarea id="topicContent" rows="30" cols="80" 
            	vld="{rangelength:[2,4096]}" style="width: 96%" name="content"></textarea>
            			<script type="text/javascript">
							CKEDITOR.replace('topicContent');
						</script>
					</div>
            <div class="fr gray" id="atc_content_warn" style="padding-right: 15px; padding-left: 15px; padding-bottom: 0px; padding-top: 0px">限 2,4096 字符</div>
            <div id="_file_container"></div>
            <div style="margin: 5px 0px">
            <input type="hidden" name="forumId" value="${forumId }">
            <input class="btn" type="button" id="subButton" onclick="submitForm()" value="提 交 ">
			</div>
            </td>
          </tr>
        </tbody>
      </table>
    </form>
    </div>
    </div>
    </div>