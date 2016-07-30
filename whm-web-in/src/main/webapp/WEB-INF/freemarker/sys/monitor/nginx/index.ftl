<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>
         nginx日志统计 
    </title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <style>
        .demo ul,
        .demo li {
            margin: 0;
            padding: 0;
        }
        .demo ul {
            list-style: none;
        }
        .demo {
            border: 1px solid  #ccc;
            background-color: #fff;
            margin-bottom: 20px; 
        }
        .demo-nav {
            height: 30px;
            border-bottom: 1px solid  #ccc;
            line-height: 30px;
            background-color: #f0f0f0;
        }
        .demo-nav li {
            float: left;
            padding: 0 10px; 
            border-right: 1px solid  #ccc;
            text-align: center;
            cursor: pointer;
        }
        /*导航高亮*/
        .demo-nav li.ui-tab-active {
            height: 31px;
            background-color: #fff;
        }
        #content {
            height: 400px;
            background: #fff url("/whm-web-in/RES/images/loading-16-16.gif") center center no-repeat;
        }
        #content.ok{
            background: none; 
        }
    </style>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0">
</head>
<body>
    <h1 style="text-align:center;">博客抽样访问统计</h1>
    <hr>
    <div class="demo" id="J-demo">
        <div class="demo-nav">
            <ul>
                <li class="ui-tab-active" data-type="browser">
                     浏览器统计 
                </li>
                <li data-type="os">
                     系统统计 
                </li>
                <li data-type="status">
                     状态码 
                </li>
                <li data-type="robot">
                    蜘蛛
                </li>
                <li data-type="chromever">
                    谷歌浏览器版本
                </li>
                <li data-type="iever">
                    IE浏览器版本
                </li>
            </ul>
        </div>
        <div class="demo-cnt" style="padding:20px;">
            <div id="content"></div>
        </div>
    </div>

    <hr>
    <p style="text-align:center;">
        &copy; <a href="https://xuexb.com">https://xuexb.com/ 前端小武</a>
    </p>
    <script src="${resRoot}/js/echarts/echarts.js" type="text/javascript"></script>
    <script src="${resRoot}/common/js/jquery.js" type="text/javascript"></script>
    <script src="${resRoot}/js/monitor/nginx/log.js" type="text/javascript"></script>
</body>
</html>