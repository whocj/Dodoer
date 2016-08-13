<script type="text/javascript">
<!--
	function search() {
		if ($("#searchText").val() == "") {
			return false;
		}

		$("#search-form").submit();
	}
//-->
</script>
<style>
<!--
/* autoComplete */
.g-ac-results{position:absolute;left:0px;top:48px;width:674px;border:1px solid #E4E4E4;background:#FFF;}
.g-ac-results ul{width:100%;list-style-position:outside;list-style:none;padding:0;margin:0;float:left;}
.g-ac-results iframe{display:block;position:;top:0;left:0;z-index:-1;filter:mask();width:3000px;height:3000px;background:#fff;}
.g-ac-results li{clear:both;margin:0;cursor:default;display:block;height:24px;line-height:24px;padding:0 8px;text-align:left;font-weight:bold;color:#666;}
.g-ac-results li p{float:left;margin:0;padding:0;}
.g-ac-results li span{margin:0;padding:0;float:right;font-size:12px;}
.g-ac-results li.ac_over{background-color:#EFEFEF;}
.g-ac-results li.cateSearch{line-height:24px;padding-left:20px;font-weight:normal;}
.g-ac-results li.cateSearch b{color:#F60;}
.g-ac-results li.cateSearch.bottom{border-bottom:1px solid #EFEFEF;}
.g-ac-results li .keyname{display:block;float:left;}
.g-ac-results li .keyname b{font-weight:normal;}
.g-ac-results li .keyextend{color:green;display:block;float:right;}
-->
</style>

<div class="search-area-wrapper">
	<div class="search-area container">
		<h3 class="search-header">有一个问题 ？</h3>
		<p class="search-tag-line">如果你有任何问题，你可以问我们或直接寻找！</p>
		<form id="search-form" class="search-form clearfix" method="get"
			action="${base }/search.htm" autocomplete="off">
			<div style="width:116px;"></div>
			<div style="position:relative;float:right;width:940px;">
				<input class="search-term" type="text" id="searchText"
					name="keyword" value="${keyword }" placeholder="请输入您搜索的关键词！" />
				<input type="hidden" name="category" value="${category }" />
				<input class="search-btn" type="button" id="search-btn" value="Search" onclick="search()" />
				<div id="search-error-container"></div>
				<div id="ac_results" class="g-ac-results hide">
				</ul>
			</div>
			</div>
			
		</form>
	</div>

</div>
<script type="text/javascript">
<!--
var resultBox = $("#ac_results");
var obj = $("#searchText");
var assUrl = "${base}/search/ass/search.htm?keyword=";
var timer = 10;
var delay = 200;

// 向服务端发送请求
obj.keyup(function (event) {
	if (event.which == 13 || event.which == 38 || event.which == 40) {
		return false;
	}
	clearTimeout(timer);
	timer = setTimeout(function () {
		var keyword = $.trim($("#searchText").val());
		if (keyword.length == 0) {
			resultBox.hide();
			return false;
		}

		
		$.ajax({
			url: assUrl + encodeURIComponent(keyword),
			dataType: "jsonp",
			cache: true,	
			jsonpCallback:"callback",
			success: function (data) {

				resultBox.show();
				var resultDom = '<ul>';
				$(data).each(function (key, value) {
					resultDom += '<li><span class="keyname">' + value.keywordView + '</span></li>'
				});

				resultDom += '</ul>';
				resultBox.html(resultDom);
				resultBox.find(".cateSearch:last").addClass("bottom");
				resultListCurrentIndex = -1;
			}
		});
	}, delay);
}).keydown(function (event) {  // 按键按下，检测是否为上下方向键
		if (event.which == 13) { // 回车键
			resultBox.hide();
			resultListCurrentIndex = -1;
			$("#search-btn").click();
			return false;
		}
		if (event.which == 38) { // 上方向键
			selectKeywordByKey(-1);
		}
		if (event.which == 40) { // 下方向键
			selectKeywordByKey(1);
		}
	}).click(function () {
		return false;
	});
$(document).click(function () {
	resultBox.hide();
	resultListCurrentIndex = -1;
});

// 联想条目鼠标滑过及点击事件
resultBox.delegate("li", "hover",function () {
	$(this).addClass("ac_over").siblings().removeClass("ac_over");
}).delegate("li", "click", function () {

		if($(this).hasClass("g-ac-store")){
			searchStoreFlag = true;
		} else {
			searchStoreFlag = false;
		}
		obj.val($(this).find(".keyname").text());
		resultBox.hide();
		resultListCurrentIndex = -1;
		$("#searchSubmit").click();
	});
	
// 通过键盘选择搜索词
var resultListCurrentIndex = -1;
var selectKeywordByKey = function (n) {
	var resultBox = $("#ac_results"),
		results = resultBox.find("li"),
		maxCount = results.size();
	if (resultBox.is("hidden") || results.size() == 0 || Math.abs(n) != 1) {
		return;
	}
	resultListCurrentIndex += n;
	if (resultListCurrentIndex < 0) {
		resultListCurrentIndex = maxCount - 1;
	}
	if (resultListCurrentIndex == maxCount) {
		resultListCurrentIndex = 0;
	}
	var currentKeywords = results.eq(resultListCurrentIndex);
	results.removeClass("ac_over");
	currentKeywords.addClass("ac_over");

	if(currentKeywords.hasClass("g-ac-store")){
		searchStoreFlag = true;
	} else {
		searchStoreFlag = false;
	}
	obj.val(currentKeywords.find(".keyname").text());
	return false;
};
//-->
</script>
