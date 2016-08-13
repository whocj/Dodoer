<#if env == 'prod'>
<script>
//百度爬虫Push
(function(){
    var bp = document.createElement('script');
    bp.src = '//push.zhanzhang.baidu.com/push.js';
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(bp, s);
})();
//360
(function(){
   var src = document.location.protocol +'//js.passport.qihucdn.com/11.0.1.js?2bbaa1640b767a60b538670242b25982';
   document.write('<script src="' + src + '" id="sozz"><\/script>');
})();
</script>
</#if>