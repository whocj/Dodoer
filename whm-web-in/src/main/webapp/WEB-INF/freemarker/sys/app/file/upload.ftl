<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>jeecms-left</title>
<#include "/common/include.ftl"/>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">文件上传</div>
	<div class="clear"></div>
	
	<form id="pageForm" name="pageForm" method="post" ENCTYPE="multipart/form-data"
			action="${base }/sys/app/fileUpload/submit.htm">
			<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tbody>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">*</span>文件:
			</td>
			<td width="80%" class="pn-fcontent">
				<input type="file" name="multipartFile" class="required" size="40">
			</td>
		</tr>
		<tr>
			<td width="100%" class="pn-fcontent" colspan="2" >
				<input type="submit" value="提交"/>
				<input type="reset" value="重置"/>
			</td>
		</tr>
			</tbody>
	</table>
	</form>
</div>
</div>
</body>
</html>