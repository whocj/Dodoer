package com.summer.whm.spider.distributed.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicPositiveInteger extends Number {

    private static final long serialVersionUID = -3038533876489105940L;

    private final AtomicInteger value;

    private final int min;

    private final int max;

    public AtomicPositiveInteger() {
        this(0, 0, Integer.MAX_VALUE);
    }

    public AtomicPositiveInteger(int initialValue) {
        this(initialValue, 0, Integer.MAX_VALUE);
    }

    public AtomicPositiveInteger(int initialValue, int min, int max) {
        this.value = new AtomicInteger(initialValue);
        this.min = min;
        this.max = max;
    }

    public final int getAndIncrement() {
        for (; ; ) {
            int current = value.get();
            int next = (current >= max ? min : current + 1);
            if (value.compareAndSet(current, next)) {
                return current;
            }
        }
    }

    public final int getAndDecrement() {
        for (; ; ) {
            int current = value.get();
            int next = (current <= min ? max : current - 1);
            if (value.compareAndSet(current, next)) {
                return current;
            }
        }
    }

    public final int incrementAndGet() {
        for (; ; ) {
            int current = value.get();
            int next = (current >= max ? min : current + 1);
            if (value.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    public final int decrementAndGet() {
        for (; ; ) {
            int current = value.get();
            int next = (current <= min ? max : current - 1);
            if (value.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    public final int get() {
        return value.get();
    }

    public final void set(int newValue) {
        if (newValue < min || newValue > max) {
            throw new IllegalArgumentException("new value " + newValue + " < 0");
        }
        value.set(newValue);
    }

    public final int getAndSet(int newValue) {
        if (newValue < min || newValue > max) {
            throw new IllegalArgumentException("new value " + newValue + " < 0");
        }
        return value.getAndSet(newValue);
    }

    public final int getAndAdd(int delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("delta " + delta + " < 0");
        }
        for (; ; ) {
            int current = value.get();
            int next = (current >= max - delta + 1 ? delta - 1 : current + delta);
            if (value.compareAndSet(current, next)) {
                return current;
            }
        }
    }

    public final int addAndGet(int delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("delta " + delta + " < 0");
        }
        for (; ; ) {
            int current = value.get();
            int next = (current >= max - delta + 1 ? delta - 1 : current + delta);
            if (value.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    public final boolean compareAndSet(int expect, int update) {
        if (update < 0) {
            throw new IllegalArgumentException("update value " + update + " < 0");
        }
        return value.compareAndSet(expect, update);
    }

    public final boolean weakCompareAndSet(int expect, int update) {
        if (update < 0) {
            throw new IllegalArgumentException("update value " + update + " < 0");
        }
        return value.weakCompareAndSet(expect, update);
    }

    public byte byteValue() {
        return value.byteValue();
    }

    public short shortValue() {
        return value.shortValue();
    }

    public int intValue() {
        return value.intValue();
    }

    public long longValue() {
        return value.longValue();
    }

    public float floatValue() {
        return value.floatValue();
    }

    public double doubleValue() {
        return value.doubleValue();
    }

    public String toString() {
        return value.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AtomicPositiveInteger other = (AtomicPositiveInteger) obj;
        if (value == null) {
            if (other.value != null) return false;
        } else if (!value.equals(other.value)) return false;
        return true;
    }

}