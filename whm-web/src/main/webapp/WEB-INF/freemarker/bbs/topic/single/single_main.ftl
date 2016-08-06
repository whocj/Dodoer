<script type="text/javascript">
function checkContent(){
	var stemTxt = $("#atc_content").val();
	if(stemTxt == "" || stemTxt.length < 10){
		$("#atc_content").focus();
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

function checkedCount(name) {
	var batchChecks = document.getElementsByName(name);
	var count = 0;
	for (var i = 0;i < batchChecks.length; i++) {
		if (batchChecks[i].checked) {
			count++;
		}
	}
	return count;
}

</script>
<<style>
<!--

-->
</style>

<div class="main-wrap">
	<div class="topInfo" id="breadCrumb">
    	<img align="absMiddle" style="cursor: pointer; pointer;padding-left: 4px;" src="${resRoot }/images/home.gif">
    	<a href="${base }/bbs/index.htm">BBS</a> » 
    	<a href="${base }/t/list/${forum.id}.html">${forum.title }</a> » 
    	<a href="javascript:void(0)">${topic.title }</a>
    </div>
	
    <div id="main">
    
    <div class="t3">
	<span class="fr" style="margin-left: 0.5em">
	<a href="${base}/t/newTopic/${forum.id }.htm"><img alt="发帖" src="${resRoot }/images/post.png"></a>
	</span> 
	<span class="fr"><a href="${base }/t/reply.html?topicId=${topic.id}"><img alt="回复" src="${resRoot }/images/reply.png"></a></span> 
	<span class="fl">
	<#include "/bbs/decorators/search.ftl"/>
    </span></div>
	
    <div class="c"></div>
	<!--帖子列表_Begin-->


<div class="t" style="border-bottom-width: 0pt; margin:3 auto 0">
    <table cellspacing="0" cellpadding="0" width="100%">
      <tbody>
        <tr>
          <td class="tal h b">主题 :${topic.title }</td>
        </tr>
      </tbody>
    </table>
    </div>

	<#list topic.postPage.content as post>
    <div class="t5" style="border-top-width: 0pt">
    <table cellspacing="0" cellpadding="0" width="100%" style="table-layout: fixed">
      <tbody>
        <tr class="tr1">
          <th class="r_two" rowspan="2" style="width: 180px;">
          <div>
          	${post.username }
          </div>
          <div class="user-pic">
         	<img height="60" width="60" border="0" class="topic_avatar" src="${post.userLogo100 }">
          </div>
          <div style="padding-left: 12px; padding-bottom: 8px">
          <span class="user-info2" id="showface_0">
          <span class="user-infoWrap2">
          <div class="c" style="width: 120px; height: 5px"></div>
          </span></span></div>
          </th>
          <th class="r_one" id="td_tpc" valign="top" height="100%" style="border-top-width: 0pt; padding-right: 0px; padding-left: 0px; border-left-width: 0pt; border-bottom-width: 0pt; padding-bottom: 0px; overflow: hidden; padding-top: 0px; border-right-width: 0pt">
          <div class="tiptop"><span class="fl"><span class="b">${(post_index + 1) + (topic.postPage.pageIndex - 1) * topic.postPage.pageSize }#</span></span> 
          <span class="fl gray" style="white-space: nowrap">&nbsp; 发表于: ${post.createTime?string('yyyy-MM-dd HH:mm') }
		  </span>
		  <#if post_index == -1> 
          	<div class="fr black"><a href="#">显示全部</a> <a href="#">只看作者</a></div>
          </#if>
          <div class="c"></div>
          </div>
          <div class="c"></div>
          <div class="vote" style="font-size:14px;">
          <#if post_index == 0> 
          	<div id="related_topics">
          		<h5>相似文章</h5>
          		<ul id="recentcomments">
          		<#list likeList as datas>
          			<#if datas.title?length gt 25>
				         <#assign varTitle = datas.title?substring(0, 25) + "…">
				         <li><a href="${datas.url }">${varTitle?html }</a></li>
				      <#else>
				        <li><a href="${datas.url }">${datas.title?html }</a></li>
				      </#if>
				</#list>
				</ul>
          	</div>
		  </#if>
          	${post.content }
          	<#if post_index == 0> 
          		<#include "/common/baidu_share.ftl"/>
          	</#if>
          </div>
		  </th>
        </tr>
        <tr class="tr1 r_one">
          <th style="border-top-width: 0pt; padding-right: 0px; padding-left: 0px; border-left-width: 0pt; border-bottom-width: 0pt; padding-bottom: 0px; vertical-align: bottom; padding-top: 30px; border-right-width: 0pt">
          <div class="c" id="w_tpc"></div>
          <div class="sigline">
          </div>
          <div class="tipad black" style="padding-bottom:10px;">
          <div class="fl readbot" >
          <a class="r-reply" title="引用此楼" href="${base }/t/reply.htm?topicId=${topic.id}&postId=${post.id}&type=1">引用</a> 
          <a class="r-reply" title="回复此楼" href="${base }/t/reply.htm?topicId=${topic.id}&postId=${post.id}&type=2&indexCount=${post_index + 1}">回复</a>
          </div>
          <div class="c"></div>
          </div>
          </th>
        </tr>
      </tbody>
    </table>
    </div>
    </#list>


	<div style="margin-top:5px;">
	  <div class="pg-3 fl">
  	 <#assign page = topic.postPage>
	 <#include "/common/page.ftl"/>
</div>
	<span class="fr" style="margin-left: 0.5em">
	    <a href="${base}/bbs/topic/newTopic/${forum.id }.html">
	    <img alt="发帖" src="${resRoot }/images/post.png"></a></span>
	<span class="fr"><a href="${base }/t/reply.htm?topicId=${topic.id}">
	<img alt="回复" src="${resRoot }/images/reply.png"></a></span>
	<span class="fl"></span>
    <div class="c"></div>
    </div>
<form id="jvForm" action="${base }/t/replyCommit.htm" method="post" enctype="multipart/form-data" validate="true" >
      <div class="t" style="margin-top: 5px">
      <table cellspacing="0" cellpadding="0" width="100%" align="center">
        <tbody>
          <tr style="color: rgb(102,102,102); line-height: 23px; height: 23px">
            <td class="h"><b>快速回复</b></td>
            <td class="h">&nbsp;</td>
            <td class="h" style="text-align: right"></td>
          </tr>
          <tr class="tr2">
            <td colspan="100" style="border-bottom-width: 0pt"></td>
          </tr>
          <tr>
            <td class="f_one" valign="top" width="20%" style="padding-right: 7px; padding-left: 7px; padding-bottom: 7px; padding-top: 7px"><b>内容</b><br>
            HTML 代码不可用 <br>
            <input type="hidden" name="anonymous" value="0"> <br>
            <input type="hidden" name="isHidden" value="0"> <br>
            <td class="f_one" valign="top" width="60%" style="padding-right: 7px; padding-left: 7px; padding-bottom: 7px; padding-top: 7px">
            <input name="postTypeId" value="1" type="hidden">
            <textarea id="atc_content" rows="8" style="width: 96%" name="content"></textarea>
            <div class="fr gray" id="atc_content_warn" style="padding-right: 15px; padding-left: 15px; padding-bottom: 0px; padding-top: 0px">限 50,000 字符</div>
            <div style="margin: 5px 0pt">
            <input type="hidden" name="topicId" value="${topic.id }"> 
            <input class="btn fl b" type="button" onclick="submitForm()" 
            style="padding-right: 1em; padding-left: 1em; padding-bottom: 0pt; padding-top: 0pt; height: 30px" id="subButton" name="Submit" value="提 交 "></div>
            </td>
            <td class="f_one" width="20%"></td>
          </tr>
        </tbody>
      </table>
      </div>
    </form>
    
	<!--帖子列表_End-->
    <div class="c"></div>
    </div>
    </div>