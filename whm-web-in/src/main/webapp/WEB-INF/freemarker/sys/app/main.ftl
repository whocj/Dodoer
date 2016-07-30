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
	<div class="rpos">应用管理</div>
	<div class="clear"></div>
	${msg }
	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="0" border="0">
		<tbody>
			<tr height="30px">
				<td width="100%" class="pn-fcontent" colspan="2" >
					<a href="${base }/sys/app/release/restart.htm">重起Server</a>
				</td>
			</tr>
			<tr height="30px">
				<td width="100%" class="pn-fcontent" colspan="2" >
					<a href="${base }/sys/app/release/upload.htm">上传War文件</a>
				</td>
			</tr>
			<tr height="30px">
				<td width="100%" class="pn-fcontent" colspan="2" >
					<a href="${base }/sys/app/release/initAsswordFile.htm">初始化联想词文件</a>
				</td>
			</tr>
			<tr height="30px">
				<td width="100%" class="pn-fcontent" colspan="2" >
					<a href="${base }/sys/app/release/initIndex.htm">初始化联想词索引</a>
				</td>
			</tr>
	    </tbody>
	</table>
</div>
</div>
</body>
</html>