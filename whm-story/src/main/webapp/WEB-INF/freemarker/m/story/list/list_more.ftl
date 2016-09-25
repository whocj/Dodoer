<#list page.content as datas>
	<dl>
		<a href="${base }/main/${datas.id}.html">
			<dt><img src="${datas.picPath }" alt="${datas.title }" onerror="nofind()" width="90" height="120"></dt>
			<dd><h3>[${datas.categoryName }]《${datas.title }》${datas.statusTxt }</h3></dd>
			<dd style="height:70px;">${datas.outline }</dd>
			<dd><span>浏览：${datas.readCount }</span>点攒：${datas.likeCount }</dd>
		</a>
	</dl>
</#list>
