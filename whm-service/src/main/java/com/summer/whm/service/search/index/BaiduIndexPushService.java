package com.summer.whm.service.search.index;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.entiry.search.SearchPost;
import com.summer.whm.mapper.search.SearchPostMapper;
import com.summer.whm.service.search.model.PostType;

@Service
public class BaiduIndexPushService {
    private static final Logger logger = LoggerFactory.getLogger(BaiduIndexPushService.class);

    @Autowired
    private SearchPostMapper searchPostMapper;

    private static boolean running = true;

    synchronized public void startup() {
        logger.info("索引初始化开始...");
        int count = 0;
        try {
            while (running) {
                List<SearchPost> searchPostList = searchPostMapper.queryByIsIndexTop100(1);
                if (searchPostList != null && searchPostList.size() > 0) {
                    String[] strArr = new String[searchPostList.size()];
                    int index = 0;
                    for (SearchPost post : searchPostList) {

                        try {
                            if (SearchPost.POST_MODEL_INDEX.equals(post.getModel())) {
                                count++;
                                if (PostType.POST_TYPE_BLOG.equals(post.getType())) {
                                    strArr[index++] = GlobalConfigHolder.DOMAIN_URL + "/blog/post/detail/"
                                            + post.getDocId() + ".html";
                                } else if (PostType.POST_TYPE_TOPIC.equals(post.getType())) {
                                    strArr[index++] = GlobalConfigHolder.DOMAIN_URL + "/bbs/topic/detail/"
                                            + post.getDocId() + ".html";
                                } else if (PostType.POST_TYPE_QUESTION.equals(post.getType())) {
                                    strArr[index++] = GlobalConfigHolder.DOMAIN_URL + "/ask/question/detail/"
                                            + post.getDocId() + ".html";
                                }
                            } else if (SearchPost.POST_MODEL_DELETE.equals(post.getModel())) {
                                // 暂不处理
                            } else if (SearchPost.POST_MODEL_UPDATE.equals(post.getModel())) {
                                // 暂不处理
                            }
                            searchPostMapper.updateIsIndex(post.getId());
                            if (count > GlobalConfigHolder.BAIDU_POST_COUNT) {
                                running = false;
                                break;
                            }
                        } catch (Exception e) {
                            logger.error("SearchPost索引创建失败" + post, e);
                        }
                    }
                    if (count > 0) {
                        post(strArr);
                    }

                    if (searchPostList.size() != 100) {
                        running = false;
                    }
                } else {
                    running = false;
                }
            }
        } catch (Exception e) {
            running = true;
            logger.error("索引初始化失败...", e);
        } finally {
            // 状态改回true,确保下次可以正常起动
            running = true;
        }

        logger.info("索引初始化结束...");
    }

    /**
     * 百度链接实时推送
     * 
     * @param PostUrl
     * @param Parameters
     * @return
     */
    public static String post(String[] Parameters) {
        if (null == Parameters || Parameters.length == 0) {
            return null;
        }
        String result = "";
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            // System.setProperty("http.proxyHost", "10.19.110.31");
            // System.setProperty("http.proxyPort", "8080");

            // 建立URL之间的连接
            URLConnection conn = new URL(GlobalConfigHolder.BAIDU_POST_URL).openConnection();

            // 设置通用的请求属性
            conn.setRequestProperty("Host", "data.zz.baidu.com");
            conn.setRequestProperty("User-Agent", "curl/7.12.1");
            conn.setRequestProperty("Content-Length", "83");
            conn.setRequestProperty("Content-Type", "text/plain");

            // 发送POST请求必须设置如下两行
            conn.setDoInput(true);
            conn.setDoOutput(true);

            // 获取conn对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            String param = "";
            for (String s : Parameters) {
                if (s != null) {
                    param += s + "\n";
                }
            }
            out.print(param.trim());
            // 进行输出流的缓冲
            out.flush();
            // 通过BufferedReader输入流来读取Url的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

        } catch (Exception e) {
            logger.error("发送post请求出现异常！" + e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] param = { "http://www.whohelpme.com/ask/question/detail/6.html" };
        // 需要推送的网址

        String json = post(param);// 执行推送方法
        System.out.println("结果是" + json); // 打印推送结果

    }

}
