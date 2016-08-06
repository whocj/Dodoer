<script src="${resRoot }/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!--
function checkContent(){
  var stemTxt = CKEDITOR.instances._editor_textarea.getData();

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
    	<a href="${base }/bbs/index.htm">BBS</a>  »
    	<a href="${base }/t/detail/${topic.id}.html">${topic.title }</a>
    	 » 回复主题</div>
    <div class="c"></div>
    <div class="t z" style="margin:5px auto;">
    <form id="jvForm" action="${base }/t/replyCommit.htm" method="post" enctype="multipart/form-data" validate="true">
      <table cellspacing="0" cellpadding="0" width="100%" align="center">
        <tbody>
          <tr style="color: #666; line-height: 23px; height: 23px">
            <td class="h"><b>回复主题</b></td>
            <td class="h"></td>
          </tr>
          <tr class="tr2">
            <td colspan="99" style="border-bottom-width: 0px"></td>
          </tr>
          <tr height="450px">
            <td class="f_one" valign="top" width="20%" style="padding-right: 7px; padding-left: 7px; padding-bottom: 7px; padding-top: 7px"><b>内容*</b><br>
            </td>
            <td class="f_one" valign="top" style="padding-right: 7px; padding-left: 7px; padding-bottom: 7px; padding-top: 7px">
            <div><textarea id="_editor_textarea" rows="30" cols="80"
            vld="{rangelength:[2,4096]}" style="width: 96%" name="content">${content }
            	</textarea>
            			<script type="text/javascript">
							CKEDITOR.replace('_editor_textarea');
						</script>
					</div>
            <div class="fr gray" id="atc_content_warn" style="padding-right: 15px; padding-left: 15px; padding-bottom: 0px; padding-top: 0px">限 2,4096 字符</div>
            <div id="_file_container"></div>
            <div style="margin: 5px 0px">
            <input type="hidden" name="topicId" value="${topic.id }">
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
