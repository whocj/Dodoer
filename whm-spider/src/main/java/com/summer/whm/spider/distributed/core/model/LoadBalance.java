package com.summer.whm.spider.distributed.core.model;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */
public interface LoadBalance {

    public enum Type {
        /**
         * RANDOM:由消费方随机选择
         * ROUND_ROBIN:由消费方按轮流的规则选择
         */
        RANDOM, ROUND_ROBIN
    }

    public Type getType();

}
