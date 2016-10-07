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

public class ShuyuewuFinishSpider {
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

        String[] urls = new String[] { "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=1",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=2",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=3",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=4",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=5",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=6",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=7",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=8",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=9",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=10",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=11",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=12",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=13",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=14",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=15",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=16",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=17",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=18",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=19",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=20",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=21",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=22",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=23",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=24",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=25",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=26",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=27",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=28",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=29",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=30",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=31",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=32",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=33",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=34",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=35",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=36",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=37",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=38",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=39",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=40",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=41",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=42",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=43",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=44",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=45",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=46",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=47",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=48",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=49",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=50",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=51",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=52",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=53",
                "http://www.09xs.com/modules/article/articlelist.php?fullflag=1&page=7&page=54",
 };
        List<StorySeed> list = new ArrayList<StorySeed>();
        for (String url : urls) {
            List<StorySeed> storySeedList = buildStorySeed(url);
            if (storySeedList != null && storySeedList.size() > 0) {
                list.addAll(storySeedList);
            }
        }

        StorySeedService.saveFile(list);
    }

    public static List<StorySeed> buildStorySeed(String url) {
        try {

            WebClientPool webClientPool = new WebClientPool();
            final HtmlPage htmlpage = webClientPool.borrowWebClient().getPage(url);
            List<HtmlAnchor> anchorList = (List<HtmlAnchor>) htmlpage.getByXPath("//div[@class='l']/ul/li/span[1]/a");
            List<StorySeed> seedList = new ArrayList<StorySeed>();
            if (anchorList != null && anchorList.size() > 0) {
                String href = "";
                String id = "";
                for (HtmlAnchor htmlAnchor : anchorList) {
                    System.out.println(htmlAnchor.getTextContent() + " url=" + htmlAnchor.getAttribute("href"));

                    href = htmlAnchor.getAttribute("href");
                    if (href != null) {
                        Integer categoryId = null;
                        try{
                            Thread.sleep(2000);
                            HtmlPage page =  webClientPool.borrowWebClient().getPage(href);
                            categoryId = getCategoryId(page);
                        }catch(Exception e){
                        }
                        
                        if(categoryId != null){
                            seedList.add(new StorySeed(htmlAnchor.getTextContent(), categoryId + "",  "1", href, ""));
                        }
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
    
    public static Integer getCategoryId(HtmlPage page){
        java.util.List elementList = (java.util.List) page.getByXPath("//div[@class='con_top']");
        java.lang.Integer ret = null; 
        if (elementList != null && elementList.size() > 0) {
            com.gargoylesoftware.htmlunit.html.HtmlElement element = (com.gargoylesoftware.htmlunit.html.HtmlElement) elementList.get(0);
            String categoryName = element.asText();
            //书阅屋 > 玄幻魔法 > 武临九霄最新章节列表
            String[] categoryNames =  categoryName.split(">");
            if(categoryNames.length == 3){
                categoryName = categoryNames[1];
            }
            categoryName = categoryName.trim();
            System.out.println(categoryName);
            
            if("玄幻魔法".equals(categoryName)){
                ret = 30;
            }else if("仙侠修真".equals(categoryName)){
                ret = 29;
            }else if("都市言情".equals(categoryName)){
                ret = 28;
            }else if("穿越小说".equals(categoryName)){
                ret = 32;
            }else if("网游竞技".equals(categoryName)){
                ret = 26;
            }else if("科幻恐怖".equals(categoryName)){
                ret = 25;
            }else if("悬疑小说".equals(categoryName)){
                ret = 32;
            }else if("历史军事".equals(categoryName)){
                ret = 27;
            }
            return ret;
        }

        return ret;
    }


}
