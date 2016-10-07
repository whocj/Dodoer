package com.summer.whm.spider.distributed.core.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutput;
import java.io.OutputStream;

import com.summer.whm.spider.distributed.core.model.Serialization;

public interface Serializer {

    public String getContentType();

    public Serialization getSerialization();

    public void serialize(OutputStream os, Object object) throws IOException;

    public <T> void serialize(OutputStream os, T obj, SerOperator<T> serOperator) throws IOException;

    public <T> Object deserialize(InputStream is) throws IOException, ClassNotFoundException;

    public <T> Object deserialize(InputStream is, Class clz) throws IOException, ClassNotFoundException;

    public <T> T deserialize(InputStream is, DeserOperator<T> deserOperator)
            throws IOException, ClassNotFoundException;

    public interface SerOperator<T> {
        public void operate(ObjectOutput output, T obj) throws IOException;
    }

    public interface DeserOperator<T> {
        public T operate(ObjectInput input) throws IOException, ClassNotFoundException;
    }
}
