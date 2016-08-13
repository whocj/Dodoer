
	<div class="main-wrap"  >
		<#include "/bbs/decorators/statistic_info.ftl"/>
    <div id="main">
    <div id="content">
    <div class="contentwrap z">
    
    	<div class="t z">
            <table width="100%" cellspacing="0" cellpadding="0">
            <tbody>
            <tr>
	            <th class="h" colspan="6"><a class="closeicon fr"><img alt="" src="${resRoot }/images/cate_fold.gif"></a>
	            <span>» <a>≡论坛快讯≡</a></span>
	            </th>
            </tr>
            </tbody>
            <tbody><tr class="tr3">
            <td bgcolor="#FFFFFF">
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tbody><tr>
              <td width="35%" height="30" align="center" bgcolor="#f1f1f1"><span style="color:#4284ce; font-weight:bold;">最新主题</span></td>
              <td width="35%" align="center" bgcolor="#f1f1f1"><span style="color:#4284ce; font-weight:bold;">最新回复</span></td>
              <td align="center" bgcolor="#f1f1f1"><span style="color:#4284ce; font-weight:bold;">今日热门话题</span></td>
            </tr>
            <tr>
              <td valign="top">
              <table width="100%" border="0" cellspacing="1" cellpadding="1" style="border-right:1px solid #d4eff7;">
                <tbody><tr>
                  <td style="line-height:24px;font-size:13px;">
                  
                  <#list topicList as datas>
                  ${datas_index + 1}.
                  <a href="${base }/t/detail/${datas.id}.html" style="color:#4284ce;" title="${datas.title }">
	                  <#if datas.title?length gt 30> 
	                  	<#assign title = datas.title?substring(0, 30) + "…"> 
	                  	${title?html }
	                  <#else> 
	                  	${datas.title?html }
	                  </#if>
                  </a>
                  [${datas.username }]
                  <br>
                  </#list>
                  
                  </td>
                </tr>
              </tbody></table>
              </td>
              <td valign="top">
              <table width="100%" border="0" cellspacing="1" cellpadding="1" style="border-right:1px solid #d4eff7;">
                <tbody><tr>
                  <td style="line-height:24px;font-size:13px;">
                  <#list replyTopicList as datas>
                  ${datas_index + 1}.
                  <a href="${base }/t/detail/${datas.id}.html" style="color:#4284ce;" title="${datas.title }">
	                  <#if datas.title?length gt 30> 
	                  	<#assign title = datas.title?substring(0, 30) + "…"> 
	                  	${title?html }
	                  <#else> 
	                  	${datas.title?html }
	                  </#if>
                  </a>
                  [${datas.username }]
                  <br>
                  </#list>
                  </td>
                </tr>
              </tbody></table>
              </td>
              <td valign="top">
              <table width="100%" border="0" cellspacing="1" cellpadding="1">
                <tbody><tr>
                  <td style="line-height:24px;font-size:13px;">
                   <#list hotTopicList as datas>
                  ${datas_index + 1}.
                  <a href="${base }/t/detail/${datas.id}.html" style="color:#4284ce;" title="${datas.title }">
	                  <#if datas.title?length gt 30> 
	                  	<#assign title = datas.title?substring(0, 30) + "…"> 
	                  	${title?html }
	                  <#else> 
	                  	${datas.title?html }
	                  </#if>
                  </a>
                  [${datas.username }]
                  <br>
                  </#list>
                  </td>
                </tr>
              </tbody></table>
              </td>
            </tr>
            </tbody></table>
            
            </td>
            </tr>
            </tbody></table>
        </div>
        
<#list categoryList as datas>
	<#if datas.view == 1> 
    <div class="t z">
    <table width="100%" cellspacing="0" cellpadding="0">
      <tbody>
        <tr>
          <th class="h" colspan="6"><a class="closeicon fr"><img alt="" src="${resRoot }/images/cate_fold.gif"></a>
          <span>» <a class="cfont">≡${datas.title }≡</a></span>
          </th>
        </tr>
      </tbody>
        <tbody>
     <tr class="tr3">
     <#list datas.forumList as forums>
      <td bgcolor="#FFFFFF" width="33%">
	  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tbody><tr>
        <td width="30" rowspan="2" valign="top">
		<table width="30" border="0" cellspacing="0" cellpadding="0" style="margin-top:12px !important; margin-top:5px;">
          <tbody><tr>
            <td valign="top">
              <div> <img src="${resRoot }/images/top/tie001.gif"> </div>
			  </td>
          </tr>
        </tbody></table>
		</td>
        <td> </td></tr>
      <tr>
        <td valign="top">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tbody><tr>
        <td>
		</td>
	  </tr>
      <tr>
        <td>
          <span class="b"><a href="${base }/t/list/${forums.id}.html">${forums.title?html }</a></span>
		  </td>
          </tr>
          <tr>
            <td class="tal y-style  e">
			<span style="color:#838383;">主题:</span><span class="gray2 ">${forums.topicTotal}</span> &nbsp;
			<span style="color:#838383;">贴数:</span> <span class="gray2 ">${forums.postTotal }</span></td></tr>
          <tr>
            <td>
          <span style="color:#838383;">标题:</span>
          <a href="${base }/t/detail/${forums.postId}.html" title="${forums.postTitle?html }">
          	${forums.postTitle?html }
          </a> &nbsp;</td></tr>
         <tr>
           <td> <span style="color:#838383;">最后发表:</span>
          <span class="f9">[${forums.lastTime?string('yyyy-MM-dd HH:mm') }]</span>&nbsp;</td></tr>
          <tr>
            <td>
          <span style="color:#838383;">作者:</span><span class="gray2">${forums.username }
                 </span></td></tr>
          </tbody></table>         </td>
          
          
          </tr>
          </tbody></table>
          </td>
  		</#list>
        </tr>


      
    </tbody></table>
    </div>
   </#if>
</#list>

    </div>
    </div>
    </div>
</div>
