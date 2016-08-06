<section class="widget">
	<h3 class="title">最新回答</h3>
	<ul id="recentcomments">
		<#list answerList as datas>
			<li class="recentcomments">
				${datas.askName } on <a href="${base }/q/detail/${datas.questionId}.html#comments-title">${datas.answerContent }</a></li>
		</#list>
	</ul>
</section>