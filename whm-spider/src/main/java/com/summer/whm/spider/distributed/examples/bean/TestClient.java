package com.summer.whm.spider.distributed.examples.bean;

public class TestClient {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        try {
            NettyClient nettyClient = new NettyClient();
            nettyClient.connect("127.0.0.1", 7397);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
