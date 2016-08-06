
<!-- Start of Page Container -->
<div class="page-container">
	<div class="container">
		<div class="row">

			<!-- start of page content -->
			<div class="span8 page-content">

				<article class="type-page hentry clearfix">
					<h1 class="post-title">
						<a href="#">联系我们</a>
					</h1>
					<hr>
					<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit,
						sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna
						aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud
						exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea
						commodo consequat.</p>
				</article>


				<form id="contact-form" class="row"
					action="http://inspirythemes.com/templates/knowledgebase-html/contact_form_handler.php"
					method="post">

					<div class="span2">
						<label for="name">名字 <span>*</span>
						</label>
					</div>
					<div class="span6">
						<input type="text" name="name" id="name"
							class="required input-xlarge" value=""
							title="* Please provide your name">
					</div>

					<div class="span2">
						<label for="email">信箱<span>*</span></label>
					</div>
					<div class="span6">
						<input type="text" name="email" id="email"
							class="email required input-xlarge" value=""
							title="* Please provide a valid email address">
					</div>

					<div class="span2">
						<label for="reason">主题</label>
					</div>
					<div class="span6">
						<input type="text" name="reason" id="reason" class="input-xlarge"
							value="">
					</div>

					<div class="span2">
						<label for="message">内容<span>*</span>
						</label>
					</div>
					<div class="span6">
						<textarea name="message" id="message" class="required span6"
							rows="6" title="* Please enter your message"></textarea>
					</div>

					<div class="span6 offset2 bm30">
						<input type="submit" name="submit" value="Send Message"
							class="btn btn-inverse"> <img src="images/loading.gif"
							id="contact-loader" alt="Loading...">
					</div>

					<div class="span6 offset2 error-container"></div>
					<div class="span8 offset2" id="message-sent"></div>

				</form>
			</div>
			<!-- end of page content -->

			<!-- start of sidebar -->
			<aside class="span4 page-sidebar">
				<#include "/decorators/support.ftl"/>
				
				<#include "/decorators/categories.ftl"/>
			</aside>
			<!-- end of sidebar -->
		</div>
	</div>
</div>
<!-- End of Page Container -->