<style>
<!--
table.grid caption,.gridtop {
	background-color: #E1ECED;
	border: solid 2px #C3DFEA;
	border-bottom: 0px;
	font-size: 14px;
	font-weight: bold;
	margin: auto;
	padding-bottom: 5px;
	padding-top: 5px;
	text-align: center;
	vertical-align: middle;
}

table.grid {
    border: 2px solid #C3DFEA;
    border-collapse: collapse;
    margin: auto;
    padding: 3px;
}
table.grid td {
    background-color: #FFFFFF !important;
    border: 1px solid #C3DFEA;
    padding: 4px;
}
-->
</style>



<div class="box_con">
	<div class="con_top">
		<a href="${base }">多多儿</a> &gt; 我的阅读记录
	</div>
			<div class="newscontent">
			<div class="l" style="width:100%">
			<ul>
				<li>
					<span class="s1"><b>序号</b></span>
					<span class="s1"><b>操作</b></span>
					<span class="s2"><b>小说标题</b></span>
					<span class="s3"><b>阅读章节</b></span>
					<span class="s5"><b>最近阅读时间</b></span>
				</li>
			<#list recordList as datas>
				<li>
					<span class="s1">${datas_index + 1}</span>
					<span class="s1"><a href="${base }/user/bookshelf/del/${datas.id}.html">删除</a></span>
					<span class="s2">
					<a href="${base }/main/${datas.storyId}.html">${datas.title }</a></span>
					<span class="s3"><a href="${base }/detail/${datas.readDetailId}.html">${datas.readDetailTitle }</a></span>
					<span class="s5">${datas.lastUpdate?string('MM-dd HH:mm') }</span>
				</li>
			</#list>
			 </ul>
			</div>
			</div>

</div>


