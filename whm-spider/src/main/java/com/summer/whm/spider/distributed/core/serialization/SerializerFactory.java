package com.summer.whm.spider.distributed.core.serialization;

import com.summer.whm.spider.distributed.core.model.Serialization;
import com.summer.whm.spider.distributed.core.serialization.jdk.JavaSerializer;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */
public abstract class SerializerFactory {

    public static Serializer getSerializer(Serialization serialization) {

        if (serialization.equals(Serialization.OBJ_STREAM)) {
            return JavaSerializer.INSTANCE;
        }
        throw new IllegalArgumentException("Unsupported serialization.");
    }

}
