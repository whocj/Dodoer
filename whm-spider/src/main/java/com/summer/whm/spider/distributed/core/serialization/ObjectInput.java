package com.summer.whm.spider.distributed.core.serialization;

import java.io.IOException;

public interface ObjectInput extends java.io.ObjectInput {

    public Object readObject(Class<?> clazz) throws ClassNotFoundException, IOException;

}
