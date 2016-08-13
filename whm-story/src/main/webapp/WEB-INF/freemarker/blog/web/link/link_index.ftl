<html>
<head>
	<#include "/blog/web/post/common/include.ftl"/>
	<meta name="keywords" content="${siteKeywords }" />
	<meta name="description" content="${siteDescription }" />
	<title>友情连接- Blog - ${siteTitle }</title>
	<script type='text/javascript' src='${resRoot}/js/layer/layer.js'></script>
	<script type="text/javascript">
	//iframe层
	function openDiv(id){
		layer.open({
		    type: 2,
		    title: '友情连接编辑',
		    shadeClose: true,
		    shade: 0.2,
		    area: ['580px', '300px'],
		    content: '${base}/blog/backend/link/update.html?id='+id,
		    end : function(){
		    	document.location.reload();
		    }
		});
	}
	</script>
</head>
<body>
  <#include "/blog/web/post/common/head.ftl"/>
  <div id="content" class="clearfix">
    <div id="left_col" class="clearfix">
      <div class="post_wrap clearfix">
		  <div class="post">
		 	<h3 class="title"><span>友情连接</span></h3>
		 	
		    <hr>
		  	<div class="post_content">
					<div class="clearfix">
						<a href="javascript:openDiv(0)" class="pn-opt">添加</a>
						<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
							<thead class="pn-lthead">
							<tr>
								<th width="50px">名称</th>
								<th width="300px">地址</th>
								<th width="40px">顺序</th>
								<th width="60px">操作</th>
							</tr>
							</thead>
							<tbody class="pn-ltbody">
							<#list friendLinkList as datas>
							<tr>
								<input type="hidden" name="wids" value="${datas.id!}"/>
							</td>
							<td>${datas.siteName}</td>
							<td>${datas.domain }</td>
							<td>${datas.priority }</td>
							<td>
								<a href="javascript:openDiv(${datas.id})" class="pn-opt">修改</a> 
								<a href="${base }/blog/backend/link/delete.htm?id=${datas.id}" onclick="if(!confirm('你确定要删除吗?')) {return false;}" class="pn-opt">删除</a>
							</td>
							</tr>
							</#list>
							</tbody>
							</table>
					</div>
				<#include "/common/page.ftl"/> 
		  </div>
		  </div>
		 <#include "/blog/web/post/common/left_menu.ftl"/>
		</div>

    </div>
    <div id="post_mask"></div>
    <div id="right_col">
      <#include "/blog/web/post/common/about_me.ftl"/>
      <#include "/blog/web/post/common/recent.ftl"/>
      <#include "/blog/web/post/common/tagcloud.ftl"/>
      <#include "/blog/web/post/common/link.ftl"/>
      
    </div>
    <#include "/blog/web/post/common/footer.ftl"/>
  </div>
</body>
</html>




