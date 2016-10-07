package com.summer.whm.spider.distributed.core;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.summer.whm.spider.distributed.utils.ClassUtils;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */

public class InvocationImpl implements Invocation, Serializable {

    private static final long serialVersionUID = -4355285085441097045L;

    private String id;

    private int retryTimes = 0;

    private boolean idempotent;

    private long requestTimeout;

    private String contract;

    private String implCode;

    private String methodName;

    private String[] parameterTypes;

    private transient Class[] parameterTypeClasses;

    private Object[] arguments;

    private Map<String, String> attachments;

    public InvocationImpl() {
    }

    public InvocationImpl(String id, String contract, String implCode, Method method, Object[] arguments) {
        this(id, contract, implCode, method.getName(), method.getParameterTypes(), arguments, null);
    }

    public InvocationImpl(String id, String contract, String implCode, Method method,
                          Object[] arguments, Map<String, String> attachment) {
        this(id, contract, implCode, method.getName(), method.getParameterTypes(), arguments, attachment);
    }

    public InvocationImpl(String id, String contract, String implCode, String methodName,
                          Class<?>[] parameterTypes, Object[] arguments) {
        this(id, contract, implCode, methodName, parameterTypes, arguments, null);
    }

    public InvocationImpl(String id, String contract, String implCode, String methodName,
                          Class<?>[] parameterTypes, Object[] arguments,
                          Map<String, String> attachments) {
        this.id = id;
        this.contract = contract;
        this.implCode = implCode;
        this.methodName = methodName;
        this.parameterTypeClasses = parameterTypes == null ? new Class<?>[0] : parameterTypes;
        this.parameterTypes = toStrArray(this.parameterTypeClasses);
        this.arguments = arguments == null ? new Object[0] : arguments;
        this.attachments = attachments == null ? new HashMap<String, String>() : attachments;
    }

    public String[] getParameterTypes() {
        return this.parameterTypes;
    }

    public void setParameterTypes(String[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public boolean isIdempotent() {
        return idempotent;
    }

    public void setIdempotent(boolean idempotent) {
        this.idempotent = idempotent;
    }

    public long getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(long timeout) {
        this.requestTimeout = timeout;
    }

    public String getContract() {
        return contract;
    }

    public String getImplCode() {
        return implCode;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class<?>[] makeParameterTypes() {
        if (this.parameterTypeClasses != null) {
            return this.parameterTypeClasses;
        }
        parameterTypeClasses = new Class[this.parameterTypes.length];
        for (int i = 0; i < this.parameterTypes.length; i++) {
            try {
                parameterTypeClasses[i] = ClassUtils.forName(this.parameterTypes[i]);
            } catch (Throwable e) {
                throw new DistributedException("Class is not found for parameter type: " + parameterTypeClasses[i], e);
            }
        }
        return parameterTypeClasses;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments == null ? new Object[0] : arguments;
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments == null ? new HashMap<String, String>() : attachments;
    }

    public void setAttachment(String key, String value) {
        if (attachments == null) {
            attachments = new HashMap<String, String>();
        }
        attachments.put(key, value);
    }

    public void setAttachmentIfAbsent(String key, String value) {
        if (attachments == null) {
            attachments = new HashMap<String, String>();
        }
        if (!attachments.containsKey(key)) {
            attachments.put(key, value);
        }
    }

    public void addAttachments(Map<String, String> attachments) {
        if (attachments == null) {
            return;
        }
        if (this.attachments == null) {
            this.attachments = new HashMap<String, String>();
        }
        this.attachments.putAll(attachments);
    }

    public void addAttachmentsIfAbsent(Map<String, String> attachments) {
        if (attachments == null) {
            return;
        }
        for (Map.Entry<String, String> entry : attachments.entrySet()) {
            setAttachmentIfAbsent(entry.getKey(), entry.getValue());
        }
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public void setImplCode(String implCode) {
        this.implCode = implCode;
    }

    public String getAttachment(String key) {
        if (attachments == null) {
            return null;
        }
        return attachments.get(key);
    }

    public String getAttachment(String key, String defaultValue) {
        if (attachments == null) {
            return defaultValue;
        }
        String value = attachments.get(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        }
        return value;
    }

    private static String[] toStrArray(Class[] classes) {
        if (classes == null || classes.length == 0) {
            return new String[0];
        }
        String[] s = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            s[i] = classes[i].getName();
        }
        return s;
    }

    @Override
    public String toString() {
        return "InvocationImpl{" +
                "id='" + id + '\'' +
                ", contract='" + contract + '\'' +
                ", implCode='" + implCode + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", attachments=" + attachments +
                '}';
    }
}