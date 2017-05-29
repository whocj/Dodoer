package com.summer.whm.spider.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.summer.whm.common.configs.GlobalSystemConfig;
import com.summer.whm.plugin.ApplicationContextUtil;
import com.summer.whm.spider.SpiderConfigs;

public class CustomerHttpClient {

    private static final Logger log = LoggerFactory.getLogger(CustomerHttpClient.class);

    private static final String CHARSET = "UTF-8";

    private static Map<ClientName, HttpClient> clientMap = new HashMap<ClientName, HttpClient>(
            ClientName.values().length);

    static {
        initHttpClients();
    }

    private CustomerHttpClient() {

    }

    /**
     * 创建httpclient
     */
    public static synchronized HttpClient createHttpClient(ClientName clientName) {
        HttpClient httpClient = clientMap.get(clientName);
        if (httpClient != null) {
            return httpClient;
        }

        ClientParam clientParam = getClientParam(clientName);
        HttpParams params = new BasicHttpParams();
        // 设置一些基本参数
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, CHARSET);
        HttpProtocolParams.setUseExpectContinue(params, true);
        HttpProtocolParams.setUserAgent(params, "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) "
                + "AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
        // 超时设置
        /* 从连接池中取连接的超时时间 */
        /* 连接超时 */
        params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, clientParam.getPoolTimeout());
        /* 请求超时 */
        params.setParameter(CoreConnectionPNames.SO_TIMEOUT, clientParam.getSoTimeout());
        // 设置我们的HttpClient支持HTTP和HTTPS两种模式
        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schReg.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
        // 使用线程安全的连接管理来创建HttpClient
        PoolingClientConnectionManager pccm = new PoolingClientConnectionManager(schReg);
        // MAX_ROUTE_CONNECTIONS为要设置的每个路由最大连接数
        pccm.setDefaultMaxPerRoute(clientParam.getMaxRouteConnections());
        pccm.setMaxTotal(clientParam.getMaxTotalConnections());
        HttpClient client = new DefaultHttpClient(pccm, params);
        if (clientParam.isProxy) {
            if (FileUtils.isWindow()) {
                GlobalSystemConfig systemConfig = ApplicationContextUtil.getBean(GlobalSystemConfig.class);
                if(systemConfig != null && GlobalSystemConfig.EVN_NAME_DEV.equals(systemConfig.getEnvName())){
                    HttpHost proxy = new HttpHost(clientParam.getProxyDomain(),
                            clientParam.getProxyPort());
                    client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
                            proxy);
                }
            }
        }
        // HttpClient client = null;
        clientMap.put(clientName, client);
        if (log.isInfoEnabled()) {
            log.info("create {} module httpClient. clientParam: {}", clientName, clientParam.toString());
            log.info("clientMap all size: {}, clientMap: {}", clientMap.size(), clientMap);
        }
        return client;

    }

    /**
     * 根据http客户端名获取相应的连接实例
     * 
     * @param clientName
     * @return
     */
    public static HttpClient getHttpClient(ClientName clientName) {
        HttpClient httpClient = clientMap.get(clientName);
        if (null == httpClient) {
            httpClient = createHttpClient(clientName);
        }
        return httpClient;
    }

    /**
     * 根据http客户端名获取相应的连接实例
     * 
     * @param clientName
     * @return
     */
    public static HttpClient getHttpClient() {
        HttpClient httpClient = clientMap.get(ClientName.DEFAULT_CLIENT);
        if (null == httpClient) {
            httpClient = createHttpClient(ClientName.DEFAULT_CLIENT);
        }
        return httpClient;
    }
    
    /**
     * 根据http client名称获取对应的参数
     * 
     * @param clientName CustomerHttpClient.HTTP_SPES_INTERFACE_CLIENT, CustomerHttpClient.SUNING_DOMAIN_CLIENT
     * @return ClientParam
     */
    private static ClientParam getClientParam(ClientName clientName) {
        ClientParam param = null;

        switch (clientName) {

        // SUNING_DOMAIN httpclient 初始化参数
            case SUNING_DOMAIN_CLIENT:
                param = new ClientParam(SpiderConfigs.SUNING_DOMAIN_POOL_TIMEOUT,
                        SpiderConfigs.SUNING_DOMAIN_CONNECT_TIMEOUT, 
                        SpiderConfigs.SUNING_DOMAIN_SO_TIMEOUT,
                        SpiderConfigs.SUNING_DOMAIN_MAX_ROUTE_CONNECTIONS,
                        SpiderConfigs.SUNING_DOMAIN_MAX_TOTAL_CONNECTIONS);
                break;
            case PROXY_CLIENT:
                param = new ClientParam(
                        SpiderConfigs.SUNING_DOMAIN_POOL_TIMEOUT,
                        SpiderConfigs.SUNING_DOMAIN_CONNECT_TIMEOUT, 
                        SpiderConfigs.SUNING_DOMAIN_SO_TIMEOUT,
                        SpiderConfigs.SUNING_DOMAIN_MAX_ROUTE_CONNECTIONS,
                        SpiderConfigs.SUNING_DOMAIN_MAX_TOTAL_CONNECTIONS);
                
                if (FileUtils.isWindow()) {
                    param.setProxy(true);
                    param.setProxyDomain("10.19.110.55");
                    param.setProxyPort(8080);
                }
                break;
            default:
                param = new ClientParam(SpiderConfigs.HTTP_POOL_TIMEOUT, SpiderConfigs.HTTP_CONNECT_TIMEOUT,
                        SpiderConfigs.HTTP_SO_TIMEOUT, SpiderConfigs.HTTP_MAX_ROUTE_CONNECTIONS,
                        SpiderConfigs.HTTP_MAX_TOTAL_CONNECTIONS);
                break;
        }

        return param;
    }

    public static void initHttpClients() {
        if (clientMap != null && SpiderConfigs.REINIT_HTTP_POOL_SHUTDOWN_ENABLED) {
            for (HttpClient client : clientMap.values()) {
                client.getConnectionManager().closeExpiredConnections();
                client.getConnectionManager().closeIdleConnections(10, TimeUnit.SECONDS);
                client.getConnectionManager().shutdown();
            }
        }
        clientMap.clear();
    }

    /**
     * 发送form类型的post请求
     * 
     * @param url
     * @param map
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String post(String url, Map map, ClientName clientName) {
        try {
            // 编码参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                NameValuePair nvp = new BasicNameValuePair(key, value);
                nvps.add(nvp);
            }

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, CHARSET);
            // 创建POST请求
            HttpPost request = new HttpPost(url);
            addKeepAliveHeader(request);
            request.setEntity(entity);
            // 发送请求;
            HttpResponse response = getHttpClient(clientName).execute(request);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new RuntimeException("请求失败");
            }
            HttpEntity resEntity = response.getEntity();

            String result = null;
            if (resEntity != null) {
                EntityUtils.toString(resEntity, CHARSET);
                // resEntity.consumeContent();
                EntityUtils.consume(resEntity);
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            if (SpiderConfigs.ENABLE_FAIL_LOG == 1) {
                log.error("http encoding error!", e.getMessage());
            }
            return null;
        } catch (ClientProtocolException e) {
            if (SpiderConfigs.ENABLE_FAIL_LOG == 1) {
                log.error("http client protocol error!", e.getMessage());
            }
            return null;
        } catch (Exception e) {
            if (SpiderConfigs.ENABLE_FAIL_LOG == 1) {
                log.error("请求数据失败! url: " + url + ", params: " + map, e.getMessage());
            }
            return null;
        }
    }

    /**
     * 发送字符串做为参数的post请求
     * 
     * @param url
     * @param str
     * @return
     */
    public static String post(String url, String str, ClientName clientName) {
        try {
            HttpPost httppost = new HttpPost(url);
            addKeepAliveHeader(httppost);
            StringEntity reqEntity = new StringEntity(str, CHARSET);
            httppost.setEntity(reqEntity);
            HttpResponse response = getHttpClient(clientName).execute(httppost);
            HttpEntity httpEntity = response.getEntity();
            String result = null;
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity);
                // httpEntity.consumeContent();
                EntityUtils.consume(httpEntity);
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            if (SpiderConfigs.ENABLE_FAIL_LOG == 1) {
                log.error("http encoding error!", e.getMessage());
            }
            return null;
        } catch (ClientProtocolException e) {
            if (SpiderConfigs.ENABLE_FAIL_LOG == 1) {
                log.error("http client protocol error!", e.getMessage());
            }
            return null;
        } catch (Exception e) {
            if (SpiderConfigs.ENABLE_FAIL_LOG == 1) {
                log.error("请求数据失败！url: " + url + ", params: " + str, e.getMessage());
            }
            return null;
        }

    }

    public static String get(String url, ClientName clientName) {
        String result = "";
        try {
            // log.error("请求" + url);
            HttpGet httpget = new HttpGet(url);
            addKeepAliveHeader(httpget);

            HttpResponse response = getHttpClient(clientName).execute(httpget);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity);
                EntityUtils.consume(httpEntity);
            }
        } catch (Exception e) {
            result = "";
            if (SpiderConfigs.ENABLE_FAIL_LOG == 1) {
                log.error("请求数据失败" + e.getMessage() + "url:" + url);
            }
        }
        return result;
    }
    
    public static HttpResp getResponse(String url, ClientName clientName) {
        HttpResp resp = new HttpResp();
        
        try {
            // log.error("请求" + url);
            HttpGet httpget = new HttpGet(url);
            addKeepAliveHeader(httpget);

            HttpResponse response = getHttpClient(clientName).execute(httpget);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                resp.setIs(httpEntity.getContent());
                resp.setContentType(httpEntity.getContentType());
                resp.setContentLength(httpEntity.getContentLength());
                resp.setContentEncoding(httpEntity.getContentEncoding());
                resp.setCode("200");
            }
        } catch (Exception e) {
            if (SpiderConfigs.ENABLE_FAIL_LOG == 1) {
                log.error("请求数据失败" + e.getMessage() + "url:" + url);
            }
            resp.setCode("500");
        }
        return resp;
    }
    
    public static void addKeepAliveHeader(HttpGet httpget) {
        if (SpiderConfigs.DEFALUT_KEEP_ALIVE == SpiderConfigs.HTTP_CLINET_KEEP_ALIVE) {

        } else if (SpiderConfigs.SPECIFIED_KEEP_ALIVE == SpiderConfigs.HTTP_CLINET_KEEP_ALIVE) {
            httpget.addHeader(HTTP.CONN_KEEP_ALIVE, "timeout=" + SpiderConfigs.SPECIFIED_KEEP_ALIIVE_DUR);
        } else if (SpiderConfigs.NEVER_KEEP_ALIVE == SpiderConfigs.HTTP_CLINET_KEEP_ALIVE) {
            httpget.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
        }
    }

    public static void addKeepAliveHeader(HttpPost httppost) {
        if (SpiderConfigs.DEFALUT_KEEP_ALIVE == SpiderConfigs.HTTP_CLINET_KEEP_ALIVE) {

        } else if (SpiderConfigs.SPECIFIED_KEEP_ALIVE == SpiderConfigs.HTTP_CLINET_KEEP_ALIVE) {
            httppost.addHeader(HTTP.CONN_KEEP_ALIVE, "timeout=" + SpiderConfigs.SPECIFIED_KEEP_ALIIVE_DUR);
        } else if (SpiderConfigs.NEVER_KEEP_ALIVE == SpiderConfigs.HTTP_CLINET_KEEP_ALIVE) {
            httppost.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
        }
    }

    public static String simpleGet(String url, ClientName clientName) throws ClientProtocolException, IOException {
        String result = "";
        // log.error("请求" + url);
        HttpGet httpget = new HttpGet(url);
        addKeepAliveHeader(httpget);
        HttpResponse response = getHttpClient(clientName).execute(httpget);
        HttpEntity httpEntity = response.getEntity();
        if (httpEntity != null) {
            result = EntityUtils.toString(httpEntity);
            EntityUtils.consume(httpEntity);
        }
        return result;
    }

    /**
     * http client模块名称的枚举类
     * 
     * @author 14060329
     * 
     */
    public static enum ClientName {
        SUNING_DOMAIN_CLIENT, DEFAULT_CLIENT,PROXY_CLIENT
    }

    public static class ClientParam {
        private int poolTimeout;
        private int connectionTimeout;
        private int soTimeout;
        private int maxRouteConnections;
        private int maxTotalConnections;

        private boolean isProxy = false;

        private String proxyDomain;

        private int proxyPort;

        public ClientParam(int poolTimeout, int connectionTimeout,
                int soTimeout, int maxRouteConnections, int maxTotalConnections) {
            this.poolTimeout = poolTimeout;
            this.connectionTimeout = connectionTimeout;
            this.soTimeout = soTimeout;
            this.maxRouteConnections = maxRouteConnections;
            this.maxTotalConnections = maxTotalConnections;
        }

        public boolean isProxy() {
            return isProxy;
        }

        public void setProxy(boolean isProxy) {
            this.isProxy = isProxy;
        }

        public String getProxyDomain() {
            return proxyDomain;
        }

        public void setProxyDomain(String proxyDomain) {
            this.proxyDomain = proxyDomain;
        }

        public int getProxyPort() {
            return proxyPort;
        }

        public void setProxyPort(int proxyPort) {
            this.proxyPort = proxyPort;
        }

        public int getPoolTimeout() {
            return poolTimeout;
        }

        public void setPoolTimeout(int poolTimeout) {
            this.poolTimeout = poolTimeout;
        }

        public int getConnectionTimeout() {
            return connectionTimeout;
        }

        public void setConnectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
        }

        public int getSoTimeout() {
            return soTimeout;
        }

        public void setSoTimeout(int soTimeout) {
            this.soTimeout = soTimeout;
        }

        public int getMaxRouteConnections() {
            return maxRouteConnections;
        }

        public void setMaxRouteConnections(int maxRouteConnections) {
            this.maxRouteConnections = maxRouteConnections;
        }

        public int getMaxTotalConnections() {
            return maxTotalConnections;
        }

        public void setMaxTotalConnections(int maxTotalConnections) {
            this.maxTotalConnections = maxTotalConnections;
        }

        @Override
        public String toString() {
            return "ClientParam [poolTimeout=" + poolTimeout
                    + ", connectionTimeout=" + connectionTimeout
                    + ", soTimeout=" + soTimeout + ", maxRouteConnections="
                    + maxRouteConnections + ", maxTotalConnections="
                    + maxTotalConnections + "]";
        }

    }
}
