package com.summer.whm.spider.distributed.core.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */
public class CommunicationImpl implements Communication, Serializable {

    private static final long serialVersionUID = -6774867682983998476L;

    private String name;

    private Protocol protocol;

    private Serialization serialization = Serialization.JSON;

    private LoadBalance loadBalance = LoadBalanceImpl.DEFAULT;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public Serialization getSerialization() {
        return serialization;
    }

    public void setSerialization(Serialization serialization) {
        this.serialization = serialization;
    }

    public LoadBalance getLoadBalance() {
        return loadBalance;
    }

    public void setLoadBalance(LoadBalance loadBalance) {
        this.loadBalance = loadBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommunicationImpl that = (CommunicationImpl) o;
        if (loadBalance != null ? !loadBalance.equals(that.loadBalance) :
                that.loadBalance != null) return false;
        if (!name.equals(that.name)) return false;
        if (!protocol.equals(that.protocol)) return false;
        if (serialization != that.serialization) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + protocol.hashCode();
        result = 31 * result + (serialization != null ? serialization.hashCode() : 0);
        result = 31 * result + (loadBalance != null ? loadBalance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CommunicationImpl@" + hashCode() + "[" +
                "name='" + name + '\'' +
                ']';
    }
}
