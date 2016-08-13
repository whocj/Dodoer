<div class="main-wrap">
	 <div class="topInfo" id="breadCrumb">
	 	<img align="absmiddle" style="cursor: pointer; pointer;padding-left: 4px;" src="${resRoot }/images/home.gif" alt=""> 
		<a href="${base }/bbs/index.html">BBS</a> » <a href="${base }/t/list/${forum.id}.html">${forum.title }</a>
	</div>
	
    <div id="main">
    </div>
    <div class="t3">
	    <span class="fr"> 	
	    <a href="${base }/t/newTopic/${forum.id}.html"><img id="td_post" src="${resRoot }/images/post.png" alt=""></a>
	    </span> 
	    <#include "/bbs/decorators/search.ftl"/>
    </div>
    <div class="c"></div>
    <div style="margin: auto;" class="t z">
	<form method="post" action="" id="jvForm">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tbody><tr>
	  <th class="h" colspan="5">
	  【<a href="${base }/t/list/${forum.id}.html">全部主题</a>】 
	  【<a href="${base }/t/list/${forum.id}.html?jh=1">精华</a>】</th></tr>
	   <tr>
	  <th class="h" colspan="5">
	  </th></tr>
	  
	  </tbody><tbody style="table-layout:fixed;">
        <tr class="tr2" style="height:30px;">
          <td class="tac y-style" style="width:">&nbsp;</td>
          <td class="tac">标 题</td>
          <td class="y-style" style="width:12em">作者/时间</td>
          <td class="tal y-style" style="width:10em">回 复 / 人 气</td>
<!--           <td class="y-style" style="width:13em">最后发表</td>	   -->
        </tr>
		<!--置顶主题列表_Begin-->
		
		<!--置顶主题列表_End-->
		<tr class="tr2" style="height:30px;"><td colspan="6" bgcolor="f2f9ff" class="tac">普通主题</td>
		</tr>
		<!--普通主题列表_Begin-->
		<#list page.content as topic>
			<tr align="center" class="tr33 t_one" style="height:40px;">
				<td  style="width:60px;">		
					<img align="absmiddle" src="${resRoot }/images/topic.gif">
				</td>
				<td style="text-align: left; line-height: 23px;font-size: 12px;" >
				<a id="link3377" href="${base }/t/detail/${topic.id }.html">${topic.title?html }</a>
				&nbsp;
				</td>
				<td class="tal y-style" style="font-size: 12px;">${topic.username }
				<div class="f10 gray2">
				<span style="color:#838383;font-size: 12px;">${topic.createTime?string('yyyy-MM-dd HH:mm') }</span>
				</div></td>
				<td class="tal y-style f10" style="font-size: 12px;"><span class="s8">${topic.replyCount }</span> / ${topic.viewCount }</td>
			</tr>
		</#list>
		<!--普通主题列表_End-->
    </tbody></table>
	<input type="hidden" value="3" name="forumId">
	<div class="fl" style="margin-top: 5px;margin-left: 5px;">
	<#include "/common/page.ftl"/> 
	</div>
    </form>
    </div>
    
    </div>
</div>