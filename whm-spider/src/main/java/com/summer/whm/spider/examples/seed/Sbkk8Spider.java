package com.summer.whm.spider.examples.seed;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.spider.client.WebClientPool;
import com.summer.whm.spider.model.seed.StorySeed;
import com.summer.whm.spider.service.StorySeedService;

public class Sbkk8Spider {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @throws Exception
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) throws Exception {

        String[] urls = new String[] {
                "http://www.sbkk8.cn/shige/tangshishangxi/",
                "http://www.sbkk8.cn/shige/songci/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhuzibaijia/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/shishu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/gudaigonganxiaoshuo/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/gudianwuxia/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/fujingdaquan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/daojiaoshuji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/yijingshuji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/gudaibingshu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/mengxuejingdian/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/gudaiyishu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/wenyanwen/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/sidamingzhu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/sanyanerpaiheji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/cifu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/qianzexiaoshuo/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/hongloumengpancipingxi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/chanzhenhoushi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhuzixuetigang/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/haishanghuameiying/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/hongloufumeng/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/hongloumengyan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/hongloumengxinzheng/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/wangshourenquanji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/songbai/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/baijiashihuazongguihouji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/shihuazonggui/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/shirenyuxie/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/tianbaotu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/qimendunjiatongzong/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/rudiyanquanshu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/huangjijingshishu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/qianliminggao/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/houheixue/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/fansanguoyanyi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhouzaoji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/nianzaifanhuameng/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/chaoyeqianzai/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/luandanshuihu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/duanhonglingyanji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/tangzhiyan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/xijingzaji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/qijingshisanpian/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/nancunchuogenglu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/wulinjiushi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/lishigongjulei/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/yuanbendaxueweiyan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/hangongqiu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/kaiyuanshijiaolu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/jieyuhui/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/hushanji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/yizhoushu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhixinjing/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/jingyuanxiaoyu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/sunvmiaolun/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/qianbainianyan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhifuqishu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/gujinxianwen/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/maqiankexiangjie/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/datangsancangqujingshihua/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/shuangfengqiyuan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/gujindaojianlu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/bowuzhi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/yuejueshu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhaijing/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhongguodihouzhimi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhongguowanglingzhimi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhonghuaguobaozhimi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhongguogongtingzhimi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/daqingwangchaozhimi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/damingwangchaozhimi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/dasongwangchaozhimi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/datangwangchaozhimi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/guoxuechangshi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/weiqirumenjiaocheng/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/tuibeitu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/meiluconghua/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/beimengsuoyan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/shangjiexianxingji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/rongzhaixubi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/yehangchuan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/qingpingshantanghuaben/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/jiayixinshu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/yinshanzhengyao/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/songzhiyunjieshuoci/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/tangzhiyun/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhongguolidaimingnv/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/huajianji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/mingshengliandaquan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/yinglianconghua/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/yinglianconghuaquanbian/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/jiangnanjiangji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/ershisishipin/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/mingruxuean/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/guanyinpusachuanqi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/suepian/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/yesoupuyan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/doupengxianhua/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/xianqingoujifanyi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/xianqingouji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/sishuzhangjujizhu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/yizhongtianpinsanguowenziban/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/songdaigongweishi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/xinyu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/chanzhenyishi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/qingdaimingrendieshi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/xinshitouji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/xiaojingbaihuawen/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/qiminyaoshu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhinanquanji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/yuxiangpiaomiaolu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/qidongyeyu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/mingyidaifanglu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/renwuzhi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/nvwashi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/renjiancihua/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/_woxianping/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/youyangzazu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/linquangaozhi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/tangcaizichuan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/chajing/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/chuanxilu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhuzijiaxun/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/xiyuanjilu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/yuanshishifan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/dayijuemilu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/taipingyulan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/nvjie/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/chuci/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhiyanzhaizhongpingshitouji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/dadailiji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/chunqiufanlu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/jiuzhangsuanshu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/xiaochuangyouji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/yueweicaotangbiji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zibuyu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/qunshuzhiyao360/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/wangyangmingquanji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/huliyuanquanchuan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/luoyangjialanji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/qianlongxiajiangnan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/shuijingzhu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/yili/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhouli/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/tiangongkaiwu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/rongzhaisuibi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/suiyuanshidan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/nalancishangxi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/shunzhichujia/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/suiyuanshihua/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/soushenji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/fengshenyanyi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/jigongquanchuan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/xingshiyinyuanchuan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/taipingguangji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/huangdisijing/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/shangjunshu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/liaozhaizhiyi2/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/shishuoxinyu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhongguogudaiguanzhi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/mengxibitan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/daxue/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/sushu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/liji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/lunheng/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zhenguanzhengyao/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/lefushiji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/xiaolinguangji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/xuxiakeyouji/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zengguofanjiashu/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/shuoyuequanchuan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/lienvchuan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/xiaojing/",
                "http://www.sbkk8.cn/mingzhu/ruyijunzhuan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/mengxibitan/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/suitangyanyi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/rulinwaishi/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/liaozhaizhiyi/",
                "http://www.sbkk8.cn/mingzhu/wenxindiaolong/",
                "http://www.sbkk8.cn/mingzhu/zhonghuashangxiawuqiannian/",
                "http://www.sbkk8.cn/mingzhu/guwenguanzhi/",
                "http://www.sbkk8.cn/mingzhu/shanhaijing/",
                "http://www.sbkk8.cn/mingzhu/fushengliuji/",
                "http://www.sbkk8.cn/mingzhu/tangshisanbaishou/",
                "http://www.sbkk8.cn/mingzhu/yanshijiaxun/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/zengguangxianwen/",
                "http://www.sbkk8.cn/mingzhu/gudaicn/digongan/"
               };
        
        List<StorySeed> list = new ArrayList<StorySeed>();
        for (String url : urls) {
            List<StorySeed> storySeedList = buildStorySeed(url);
            if (storySeedList != null && storySeedList.size() > 0) {
                list.addAll(storySeedList);
                Thread.sleep(2000);
            }
        }

        StorySeedService.saveFile(list);
    }

    public static List<StorySeed> buildStorySeed(String url) {
        try {

            WebClientPool webClientPool = new WebClientPool();
            final HtmlPage htmlpage = webClientPool.borrowWebClient().getPage(url);
            List<HtmlAnchor> anchorList = (List<HtmlAnchor>) htmlpage.getByXPath("//ul[@class='bookIndex']/li/a[2]");
            List<StorySeed> seedList = new ArrayList<StorySeed>();
            String baseUrl = "http://www.sbkk8.cn";
            if (anchorList != null && anchorList.size() > 0) {
                String href = "";
                String id = "";
                for (HtmlAnchor htmlAnchor : anchorList) {
                    System.out.println(htmlAnchor.getTextContent() + " url=" + htmlAnchor.getAttribute("href"));

                    href = htmlAnchor.getAttribute("href");
                    if (href != null) {
                        href = baseUrl + href;
                        seedList.add(new StorySeed(htmlAnchor.getTextContent(), "40", "9", href, "* 1 2 * * * *"));
                    }
                }
            }
            return seedList;
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
