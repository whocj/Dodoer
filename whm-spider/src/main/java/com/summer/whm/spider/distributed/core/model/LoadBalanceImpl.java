package com.summer.whm.spider.distributed.core.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */
public class LoadBalanceImpl implements LoadBalance, Serializable {

    private static final long serialVersionUID = 6399927901411647182L;

    private Type type;

    public static LoadBalance DEFAULT = new LoadBalanceImpl(Type.RANDOM);

    public LoadBalanceImpl(Type type) {
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoadBalanceImpl that = (LoadBalanceImpl) o;
        if (type != that.type) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }
}
