<style>
<!--
.main-content {
    width: 720px;
    padding: 29px 29px 0;
    float: left;
    position: relative;
}
.lemmaWgt-lemmaTitle {
    margin: 0 0 10px;
    width: 700px;
    font-family: arial,tahoma,'Microsoft Yahei','\5b8b\4f53',sans-serif;
}
.lemma-summary {
    clear: both;
    font-size: 14px;
    word-wrap: break-word;
    color: #333;
    margin-bottom: 15px;
    text-indent: 2em;
    line-height: 24px;
    zoom: 1;
}
.edit-lemma{
	float:right;
    margin: 0 5px 0 0;
    padding-left: 0;
    padding-right: 0;
    width: 60px;
    border-color: #c5c5c5;
    border-radius: 3px;
    outline: 0;
    height: 14px;
    line-height: 14px;
    font-size: 12px;
    color: #666;
}
.basic-info {
    margin: 20px 0 35px;
    clear: both;
}

.basic-info .basicInfo-block {
    width: 720px;
    float: left;
}
.basic-info .basicInfo-block .basicInfo-item.name {
    width: 90px;
    padding: 0 5px 0 12px;
    font-weight: 700;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    word-wrap: normal;
    color: #999;
    float: left;
}
.basic-info .basicInfo-block .basicInfo-item.value {
    zoom: 1;
    color: #333;
    width: 200px;
    float: left;
    position: relative;
}

.list_box{
 	margin-right: 10px;
}

.list_box dt {
    float: left;
    margin-right: 10px;
}
.list_box dd h3 {
    font-size: 120%;
    color: #333;
    line-height: 30px;
    text-align: left;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    margin: 0px;
}
.list_box dd:nth-of-type(2) {
    text-align: left;
    color: #999;
}
.list_box dd:nth-of-type(3) {
    color: #999;
}
.list_box img {
	margin: 10px;
}
.list_box dd {
    overflow: hidden;
    text-overflow: ellipsis;
    text-align: right;
    font-size: 12px;
    style:30px;
}
.side-box {
    padding-bottom: 15px;
    margin-bottom: 10px;
    padding: 0 14px 14px;
    border: 1px solid #e6e6e6;
    background: #fcfcfc;
    line-height: 22px;
    color: #555;
}
.lemma-statistics dt.title {
    font-family: arial;
    font-weight: 700;
    color: #555;
}
.side-box dl, dt, dd {
    margin: 10px;
    padding: 0;
    list-style: none;
}
-->
</style>
<div class="page-container">
	<div class="container">
	
		<div class="row">
			<!-- start of page content -->
			<div class="span8">
			<!-- start of hot content -->
			<div class="newscontent">
			<div class="l">
			<h2>基本信息</h2>
				<div class="hotstory">
					<div class="main-content">
					<#if sessionUser != null>
<#if sessionUser.username == 'admin'>
<a href="javascript:;" class="edit-lemma">编辑</a>
</#if>
</#if>

<dl class="lemmaWgt-lemmaTitle lemmaWgt-lemmaTitle-">
<dd class="lemmaWgt-lemmaTitle-title">
<h1>${author.name }</h1>
</dd>
</dl>
<div class="lemma-summary" label-module="lemmaSummary">
<div class="para" label-module="para">
	${author.outline }
</div>
</div>
<div class="basic-info">
<dl class="basicInfo-block basicInfo-left">
<dt class="basicInfo-item name">中文名</dt>
<dd class="basicInfo-item value">
${author.namezh }
</dd>
<dt class="basicInfo-item name">外文名</dt>
<dd class="basicInfo-item value">
${author.nameen }
</dd>
<dt class="basicInfo-item name">笔&nbsp;&nbsp;名</dt>
<dd class="basicInfo-item value">
${author.name }
</dd>
<dt class="basicInfo-item name">国&nbsp;&nbsp;籍</dt>
<dd class="basicInfo-item value">
${author.country }
</dd>
<dt class="basicInfo-item name">出生地</dt>
<dd class="basicInfo-item value">
${author.homeplace }
</dd>
<dt class="basicInfo-item name">出生日期</dt>
<dd class="basicInfo-item value">
${author.birthday }
</dd>
</dl>

</div>
</div>
				</div>
			</div>
			</div>
			
		<div class="newscontent">
			<div class="l">
			<h2>作品列表</h2>
				<div class="hotstory">
						<div class="list_box">
					<#list author.detailList as datas>
						<dl>
							<a href="${base }/main/${datas.id}.html">
								<dt><img src="${datas.picPath }" alt="${datas.title }" onerror="nofind()" width="90" height="120"></dt>
								<dd><h3>[${datas.categoryName }]${datas.title }${datas.statusTxt }</h3></dd>
								<dd style="height:70px;">&nbsp;&nbsp;&nbsp;&nbsp;${datas.outline }</dd>
								<dd>浏览：${datas.readCount }&sp;&nbsp;点攒：${datas.likeCount }</dd>
							</a>
						</dl>
					</#list>
						</div>
				</div>
			</div>
		</div>

			</div>
			<!-- end of page content -->

			<!-- start of sidebar -->
			<aside class="span4 page-sidebar">
			<dl class="side-box lemma-statistics">
				<dt class="title">词条统计</dt>
				<dd class="description">
				<ul>
				<li>浏览次数：<span id="j-lemmaStatistics-pv">10</span>次</li>
				<li>编辑次数：1145次</li>
				<li>最近更新：<span class="j-modified-time">2016-09-06</span></li>
				<li>创建者：<a class="show-userCard" data-uid="5360586" title="查看此用户资料" target="_blank" nslog-type="1022">Admin</a></li>
				</ul>
				</dd>
				</dl>
			</aside>
			<!-- end of sidebar -->
		</div>
	</div>
</div>