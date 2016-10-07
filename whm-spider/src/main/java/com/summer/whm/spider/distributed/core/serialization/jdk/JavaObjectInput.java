package com.summer.whm.spider.distributed.core.serialization.jdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import com.summer.whm.spider.distributed.core.serialization.ObjectInput;

public class JavaObjectInput implements ObjectInput {

    private ObjectInputStream objectInputSteam;

    public JavaObjectInput(InputStream is) throws IOException {
        objectInputSteam = new ObjectInputStream(is);
    }

    @Override
    public Object readObject() throws ClassNotFoundException, IOException {
        return objectInputSteam.readObject();
    }

    @Override
    public Object readObject(Class<?> clazz) throws IOException, ClassNotFoundException {
        return objectInputSteam.readObject();
    }

    @Override
    public int read() throws IOException {
        return objectInputSteam.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return objectInputSteam.read(b, 0, b.length);
    }

    @Override
    public long skip(long n) throws IOException {
        return objectInputSteam.skip(n);
    }

    @Override
    public int available() throws IOException {
        return objectInputSteam.available();
    }

    @Override
    public void close() throws IOException {
        objectInputSteam.close();
    }

    @Override
    public void readFully(byte[] b) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void readFully(byte[] b, int off, int len) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int skipBytes(int n) throws IOException {
        return objectInputSteam.skipBytes(n);
    }

    @Override
    public boolean readBoolean() throws IOException {
        return objectInputSteam.readBoolean();
    }

    @Override
    public byte readByte() throws IOException {
        return objectInputSteam.readByte();
    }

    @Override
    public int readUnsignedByte() throws IOException {
        return objectInputSteam.readByte();
    }

    @Override
    public short readShort() throws IOException {
        return objectInputSteam.readShort();
    }

    @Override
    public int readUnsignedShort() throws IOException {
        return objectInputSteam.readShort();
    }

    @Override
    public char readChar() throws IOException {
        return objectInputSteam.readChar();
    }

    @Override
    public int readInt() throws IOException {
        return objectInputSteam.readInt();
    }

    @Override
    public long readLong() throws IOException {
        return objectInputSteam.readLong();
    }

    @Override
    public float readFloat() throws IOException {
        return objectInputSteam.readFloat();
    }

    @Override
    public double readDouble() throws IOException {
        return objectInputSteam.readDouble();
    }

    @Override
    public String readLine() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String readUTF() throws IOException {
        return objectInputSteam.readUTF();
    }
}
 
