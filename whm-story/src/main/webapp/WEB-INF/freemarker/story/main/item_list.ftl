<style>
<!--
#list {
    padding: 2px;
}
#list dl {
    float: left;
    overflow: hidden;
    padding-bottom: 1px;
    margin: auto;
    width: 100%;
}
#list dt {
    background: none repeat scroll 0 0 #C3DFEA;
    display: inline;
    float: left;
    font-size: 14px;
    line-height: 28px;
    overflow: hidden;
    text-align: center;
    vertical-align: middle;
    width: 98%;
    margin: auto auto 5px;
    padding: 5px 10px;
}
#list dd {
    border-bottom: 1px dashed #CCC;
    display: inline;
    float: left;
    height: 25px;
    line-height: 200%;
    margin-bottom: 5px;
    overflow: hidden;
    text-align: left;
    text-indent: 10px;
    vertical-align: middle;
    width: 32%;
}
-->
</style>

<div class="box_con white">
<div class="con_top">
		《${storyInfo.title }》全部章节列表
	</div>
	<div id="list">
		<dl>
			<#list storyInfo.storyDetailList as datas>
			<dd>
				<a href="${base }/detail/${datas.id }.html">${datas.title }</a>
			</dd>
			</#list>
			
			&nbsp;&nbsp;

		</dl>
	</div>
</div>