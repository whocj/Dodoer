package com.summer.whm.spider.distributed.core.model;


import java.io.Serializable;

import com.summer.whm.spider.distributed.utils.StringUtils;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */
public class ProviderInfo implements Serializable {

    private static final long serialVersionUID = 5722129487821553866L;

    private String schema;

    private String host;

    private int port;

    public ProviderInfo(String schema, String hostAndPort) {
        this.schema = schema;
        this.host = StringUtils.substringBefore(hostAndPort, ":");
        this.port = Integer.valueOf(StringUtils.substringAfter(hostAndPort, ":"));
    }

    public ProviderInfo(String schema, String host, int port) {
        this.schema = schema;
        this.host = host;
        this.port = port;
    }

    public ProviderInfo(String str) {
        this(StringUtils.substringBefore(str, "@"), StringUtils.substringAfter(str, "@"));
    }

    public String getSchema() {
        return schema;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String toString() {
        return this.schema + "@" + host + ":" + port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProviderInfo that = (ProviderInfo) o;
        if (port != that.port) return false;
        if (!host.equals(that.host)) return false;
        if (!schema.equals(that.schema)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = schema.hashCode();
        result = 31 * result + host.hashCode();
        result = 31 * result + port;
        return result;
    }
}
