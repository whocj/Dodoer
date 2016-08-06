<!-- Start of Page Container -->
<script type="text/javascript">
<!--
	function submitGood(qid,aid){
		if(window.confirm("确认采纳最佳答案?")){
			gotoUrl('${base}/q/submitGoodAnswer.html?qid=' + qid + "&aid=" + aid);
		}else{
			return;
		}
	}
//-->
</script>
<style>
.answer_good{margin:15px auto 0;display:block; width:80px; color:#fff;  margin-right:10px;font-size: inherit; text-align: center;  background-color: #009a61;height: 20px; }
</style>
<div class="page-container">
	<div class="container">
		<div class="row">
			<!-- start of page content -->
			<div class="span8 page-content">
				<article class=" type-post format-standard hentry clearfix">
					
					<h1 class="post-title">
						${question.questionTitle?html }
					</h1>
					<div class="post-meta clearfix">
						<img src="${question.userLogo }"
									class="question_avatar" height="30" width="30">
						<span style="padding-top: 5px;padding-left: 5px;"> 
							<a style="padding-left: 0px;" href="${base }/blog/main/${question.username }.html">${question.username }</a>
						</span>
						<span class="date">${question.createTime?string('yyyy-MM-dd HH:mm') }</span>
						<span class="category">
							${question.tagName }
						</span>
						<span class="comments">
						<a href="#comments-title" title="${question.questionTitle?html }">${question.replyCount } 条回答</a></span>
						<span class="like-count">${question.viewCount }</span>
					</div>
					<!-- end of post meta -->
					${question.questionContent }
				</article>
				
				<#include "/common/baidu_share.ftl"/>

				<section class="widget">
					<h3 class="title">相似文章</h3>
					<ul id="recentcomments">
						<#list likeList as datas>
							<li class="recentcomments">
								<a href="${datas.url }">${datas.title?html }</a></li>
						</#list>
					</ul>
				</section>

				<section id="comments">
					<h3 id="comments-title">所有回答</h3>

					<ol class="commentlist">

						<#list question.answerList as datas>
						<li class="comment even thread-even depth-1" id="li-comment-2" 
						<#if datas.isGood == '1'>
						 	style="border:1px solid #009a61"
						</#if>
						 >
							<article id="comment-2">

								<img src="${datas.userLogo50 }"
									class="avatar avatar-60 photo" height="50" width="50">
								<div class="comment-meta">
									<h5 class="author">
										<cite class="fn"><a href="javascript:void(0);" rel="external nofollow"
											class="url">${datas.nickname }</a>
										</cite> - <a class="comment-reply-link" href="#answerForm">回复</a>
									</h5>
									<p class="date">
										<time>${datas.createTime?string('yyyy-MM-dd HH:mm') }</time>
									</p>
								</div>
								<!-- end .comment-meta -->

								<div class="comment-body">
									${datas.answerTitle?html}
									${datas.answerContent}
								</div>
								<div>
									<#if sessionUser?? && sessionUser.username == question.username && question.hasGood != '1'>
										<input class="answer_submit" type="button" onclick="submitGood(${question.id}, ${datas.id })" value="采纳最佳答案" />
									</#if>
									<#if datas.isGood == '1'>
									 	<span class="answer_good">最佳答案</span>
									</#if>
								</div>
								<!-- end of comment-body -->

							</article> <!-- end of comment -->
						</li>
						</#list>
						
					</ol>
					<#include "/ask/question/single/answer_form.ftl"/>
				</section>
				<!-- end of comments -->

			</div>
			<!-- end of page content -->

			<!-- start of sidebar -->
			<aside class="span4 page-sidebar">
				<#include "/decorators/support.ftl"/>
				<#include "/decorators/latest_question.ftl"/>
				<#include "/decorators/latest_topic.ftl"/>
				<#include "/decorators/latest_blog.ftl"/>
				<#include "/decorators/adsense/index_right.ftl"/>
			</aside>
			<!-- end of sidebar -->
		</div>
	</div>
</div>
<!-- End of Page Container -->