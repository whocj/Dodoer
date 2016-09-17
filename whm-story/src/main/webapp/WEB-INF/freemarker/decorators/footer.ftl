<footer id="footer-wrapper">
		<div id="footer" class="container">
			<div class="row">

				<div class="span3" style="width:270px;">
					<section class="widget">
						<h3 class="title">理念</h3>
						<div class="textwidget">
							<p>&nbsp;&nbsp;&nbsp;&nbsp;本站所有小说为转载作品，所有章节均由网友上传，版权归原作者， 转载至本站只是为了宣传本书让更多读者欣赏。</p>
						</div>
					</section>
				</div>

				<div class="span3" style="width:270px;">
					<section class="widget">
						<h3 class="title">频道</h3>
						<ul>
							<li><a target="_blank" href="${base }/author/list/0.html">作家排行</a></li>
							<li><a target="_blank" href="${base }/finish/0/1.html">完结小说</a></li>
							<li><a target="_blank" href="${base }/story/register/list/0.html">收录列表</a></li>
							<#if sessionUser != null>
								<li><a target="_blank" href="javascript:openStoryRegisterDiv()">申请收录</a></li>
							</#if>
						</ul>
					</section>
				</div>

				<div class="span3" style="width:270px;">
					<section class="widget">
						<h3 class="title">热点</h3>
						<div id="title">
							<ul>
								<li>宝宝心里苦</li>
								<li>但宝宝不说</li>
								<li>加油！加油！加油！</li>
							</ul>
						</div>

					</section>
				</div>

				<div class="span3" style="width:270px;">
					<section class="widget">
						<h3 class="title">联系我们</h3>
						<div class="flickr-photos" id="basicuse">
							<ul>
								<li><a target="_blank" href="http://weibo.com/whohelome?is_all=1" title="博客">站长博客</a></li>
							</ul>
						</div>
					</section>
				</div>

			</div>
		</div>
		<!-- end of #footer -->

		<!-- Footer Bottom -->
		<#include "/decorators/copyright.ftl"/>
		<!-- End of Footer Bottom -->

	</footer>