
package com.summer.whm.spider.distributed.core.serialization.jdk;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class JavaObjectOutput implements ObjectOutput {

    private ObjectOutputStream objectOutputStream;

    public JavaObjectOutput(OutputStream os) throws IOException {
        objectOutputStream = new ObjectOutputStream(os);
    }

    @Override
    public void writeBoolean(boolean v) throws IOException {
        objectOutputStream.writeBoolean(v);
    }

    @Override
    public void writeByte(int v) throws IOException {
        objectOutputStream.writeByte(v);
    }

    @Override
    public void writeShort(int v) throws IOException {
        objectOutputStream.writeShort(v);
    }

    @Override
    public void writeChar(int v) throws IOException {
        objectOutputStream.writeChar(v);
    }

    @Override
    public void writeInt(int v) throws IOException {
        objectOutputStream.writeInt(v);
    }

    @Override
    public void writeLong(long v) throws IOException {
        objectOutputStream.writeLong(v);
    }

    @Override
    public void writeFloat(float v) throws IOException {
        objectOutputStream.writeFloat(v);
    }

    @Override
    public void writeDouble(double v) throws IOException {
        objectOutputStream.writeDouble(v);
    }

    @Override
    public void writeBytes(String s) throws IOException {
        objectOutputStream.writeBytes(s);
    }

    @Override
    public void writeChars(String s) throws IOException {
        objectOutputStream.writeChars(s);
    }

    @Override
    public void writeUTF(String s) throws IOException {
        objectOutputStream.writeUTF(s);
    }

    @Override
    public void writeObject(Object obj) throws IOException {
        objectOutputStream.writeObject(obj);
    }

    @Override
    public void write(int b) throws IOException {
        objectOutputStream.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        objectOutputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        objectOutputStream.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        objectOutputStream.flush();
    }

    @Override
    public void close() throws IOException {
        objectOutputStream.close();
    }
}
 
