/*  
 * Copyright (c) 2012-2013 �����׹�  Suning.com. 
 * @FileName:JavaSerializer.java 
 * @author:12061770
 * @date:2014-4-10 ����10:03:36   
 *   
 */
package com.summer.whm.spider.distributed.core.serialization.jdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.summer.whm.spider.distributed.core.model.Serialization;
import com.summer.whm.spider.distributed.core.serialization.Serializer;

public class JavaSerializer implements Serializer {

    private static final String CONTENT_TYPE = " x-application/java";

    public static JavaSerializer INSTANCE = new JavaSerializer();

    @Override
    public Serialization getSerialization() {
        return Serialization.OBJ_STREAM;
    }

    @Override
    public String getContentType() {
        return CONTENT_TYPE;
    }

    @Override
    public void serialize(OutputStream os, Object object) throws IOException {
        JavaObjectOutput output = new JavaObjectOutput(os);
        output.writeObject(object);
        output.flush();
    }

    @Override
    public <T> void serialize(OutputStream os, T obj, SerOperator<T> serOperator) throws IOException {
        JavaObjectOutput output = new JavaObjectOutput(os);
        serOperator.operate(output, obj);
        output.flush();
    }

    @Override
    public Object deserialize(InputStream is) throws IOException, ClassNotFoundException {
        JavaObjectInput input = new JavaObjectInput(is);
        return input.readObject();
    }

    @Override
    public Object deserialize(InputStream is, Class clz) throws IOException, ClassNotFoundException {
        JavaObjectInput input = new JavaObjectInput(is);
        return input.readObject(clz);
    }

    @Override
    public <T> T deserialize(InputStream is, DeserOperator<T> deserOperator) throws IOException,
            ClassNotFoundException {
        JavaObjectInput input = new JavaObjectInput(is);
        return deserOperator.operate(input);
    }
}
 
