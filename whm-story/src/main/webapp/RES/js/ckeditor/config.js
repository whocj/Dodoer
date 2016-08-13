CKEDITOR.editorConfig = function(config) {
	// Define changes to default configuration here. For example:
	config.language = 'zh-cn';
	// config.uiColor = '#BEFEC1';
	config.width = 750;
	// 皮肤
	config.skin = 'v2';
	// 表情显示每行个数
	config.smiley_columns = 5;
	// 加入代码插件
	config.extraPlugins = "syntaxhighlight";
	// 去掉左下角的body和p标签
	config.removePlugins = 'elementspath';
	// 表情自定义
	config.smiley_images = [ 'yociexp1.gif', 'yociexp10.gif', 'yociexp11.gif',
			'yociexp12.gif', 'yociexp13.gif', 'yociexp14.gif', 'yociexp15.gif',
			'yociexp16.gif', 'yociexp17.gif', 'yociexp18.gif', 'yociexp19.gif',
			'yociexp2.gif', 'yociexp20.gif', 'yociexp3.gif', 'yociexp4.gif',
			'yociexp5.gif', 'yociexp6.gif', 'yociexp7.gif', 'yociexp8.gif',
			'yociexp9.gif' ];

	config.image_previewText = '';
	config.filebrowserUploadUrl = "/ckeditor/upload.html";
	// 加入中文
	config.font_names = '宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'
			+ config.font_names; // 设置编辑器里字体下拉列表里的字体
	// 设置工具栏里面的工具 最主要的
	config.toolbar = [ [ 'Cut', 'Copy', 'Paste', '-', 'Undo', 'Redo','-',
							'Bold', 'Italic', 'TextColor', 'RemoveFormat','-', 
							'Smiley','Image', 'syntaxhighlight' ] ];
	
	

};
