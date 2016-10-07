package com.summer.whm.spider.distributed.core.model;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */
public interface Protocol {

    public String getSchema();

    public String apply(ProviderInfo providerInfo);

    public boolean isPtoP();

}
