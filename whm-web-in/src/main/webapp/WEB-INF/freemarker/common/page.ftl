当前页${page.pageIndex}/${page.totalPage}  
<#if page.hasPreviousPage>
<a HREF="javascript:goPage(1)">&lt;&lt;</a>  
<a HREF="javascript:goPage(${page.previousPage})">&lt;</a>  
<#else>
&lt;&lt;  &lt;  
</#if>

<#if page.hasNextPage>
<a HREF="javascript:goPage(${page.nextPage})">&gt;</a>  
<a HREF="javascript:goPage(${page.totalPage})">&gt;&gt;</a>  
<#else>
&gt;  &gt;&gt;  
</#if>

每页${page.pageSize}条  
共${page.totalCount}条  
<input type="hidden" name="currentPage" id="currentPage" />
<script language="JavaScript">
// 分页跳转
function goPage(pageNum) {
	var page = document.getElementById("currentPage");
	page.value = pageNum;
	if(page.value.length > 0) {
		var form = document.pageForm;
		form.submit();
	}
}
</script>