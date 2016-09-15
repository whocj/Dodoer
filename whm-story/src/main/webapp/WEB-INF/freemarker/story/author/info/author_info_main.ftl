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
<a href="${base }/author/edit/info/${author.id}.html" class="edit-lemma">编辑</a>
</#if>
</#if>

<dl class="lemmaWgt-lemmaTitle lemmaWgt-lemmaTitle-">
<dd class="lemmaWgt-lemmaTitle-title">
<h1>${author.name?html }</h1>
</dd>
</dl>
<div class="lemma-summary" label-module="lemmaSummary">
<div class="para" label-module="para">
	${author.outline?html }
</div>
</div>
<div class="basic-info">
<dl class="basicInfo-block basicInfo-left">

<dt class="basicInfo-item name">中文名</dt>
<dd class="basicInfo-item value">
${author.namezh?html }
</dd>

<#if author.nameen??>
	<dt class="basicInfo-item name">外文名</dt>
	<dd class="basicInfo-item value">
	${author.nameen?html }
	</dd>
</#if>

<#if author.name??>
	<dt class="basicInfo-item name">笔&nbsp;&nbsp;名</dt>
	<dd class="basicInfo-item value">
	${author.name?html }
	</dd>
</#if>

<#if author.country??>
	<dt class="basicInfo-item name">国&nbsp;&nbsp;籍</dt>
	<dd class="basicInfo-item value">
	${author.country?html }
	</dd>
</#if>
<#if author.homeplace??>
	<dt class="basicInfo-item name">出生地</dt>
	<dd class="basicInfo-item value">
	${author.homeplace?html }
	</dd>
</#if>
<#if author.birthday??>
	<dt class="basicInfo-item name">出生日期</dt>
	<dd class="basicInfo-item value">
	${author.birthday?html }
	</dd>
</#if>
</dl>
</div>

<div class="lemma-summary" style="text-indent: 0em;" label-module="lemmaSummary">
<div class="para" label-module="para">
<pre>
	${author.description?html }
</pre>
</div>
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
							<a href="${base }/main/${datas.storyId}.html">
								<dt><img src="${datas.picPath }" alt="${datas.title }" onerror="nofind()" width="90" height="120"></dt>
								<dd><h3>[${datas.categoryName }]${datas.title }${datas.statusTxt }</h3></dd>
								<dd style="height:70px;">&nbsp;&nbsp;&nbsp;&nbsp;${datas.outline }</dd>
							</a>
							<dd>浏览：${datas.readCount }&nbsp;&nbsp;点攒：${datas.likeCount }</dd>
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