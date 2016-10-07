package com.summer.whm.spider.distributed.core.model;

/**
 * 通讯配置，一个app可能有多个通讯配置
 *
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */
public interface Communication {

    /**
     * 该通讯配置对象的标识,在一个服务配置中，通讯配置对象的标识应唯一。
     *
     * @return 标识
     */
    public String getName();

    /**
     * 协议配置
     *
     * @return 协议配置
     */
    public Protocol getProtocol();

    /**
     * 序列化方式，JSON or hessian etc
     *
     * @return 序列化方式
     */
    public Serialization getSerialization();

    /**
     * loadBalance相关配置
     *
     * @return loadBalance相关配置
     */
    public LoadBalance getLoadBalance();
}
