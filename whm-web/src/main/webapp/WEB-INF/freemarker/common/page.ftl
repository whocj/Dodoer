<#if page?? && page.totalCount gt 1> 
<div id="pagination">
	<#if page.totalPage gt 10> 
		<#assign totalPage = 10>
	<#else>
		 <#assign totalPage = page.totalPage>
	</#if>
	
	<#if page.pageIndex gte 10>
		<#if page.totalPage gt 10>
			<#list 1..5 as i>
				<a href="${base + listUrl + i}" class="btn">${i }</a>
			</#list>							
			...
			<a href="${base + listUrl + (page.pageIndex - 1)}" class="btn">${page.pageIndex - 1 }</a>
			<a href="${base + listUrl + page.pageIndex}" class="btn active">${page.pageIndex }</a>	

			<#if page.pageIndex lt page.totalPage>
				<a href="${base + listUrl + (page.pageIndex + 1)}" class="btn">${page.pageIndex + 1 }</a>
			</#if>
		</#if>
	<#else>
		<#list 1..totalPage as i>
			<#if i == page.pageIndex>
				<a href="${base + listUrl + i}" class="btn active">${i }</a>
			<#else>
				 <a href="${base + listUrl + i}" class="btn">${i }</a>
			</#if>
		</#list>
	</#if>
	
	<#if page.pageIndex lt page.totalPage>
		<a href="${base + listUrl + (page.pageIndex + 1)}" class="btn">Next »</a>
	</#if>
</div>
</#if>