package com.summer.whm.spider.distributed.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.summer.whm.spider.distributed.utils.ClassUtils;
import com.summer.whm.spider.distributed.utils.MethodIdUtils;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */
public class ServiceImpl implements Service, Serializable {

    private static final long serialVersionUID = -8566388637781882575L;

    private static final int DEFAULT_RETRY_TIMES = 3;

    private static final long DEFAULT_TIMEOUT = 5000;

    private static final boolean DEFAULT_IDEMPOTENT = true;

    private static final long DEFAULT_SPAN_LOG_INTERVAL = 1000;

    private static final Priority DEFAULT_PRIORITY = Priority.H;

    private static final long DEFAULT_INVOKE_COST_TIME_THRESHOLD = 1000;

    private static final long DEFAULT_INVOKE_FAIL_THRESHOLD = 5;

    private String contract;

    private String implCode;

    private String appCode;

    private String description;

    private boolean internal;

    private String warningPhones;

    private String defaultCommunication;

    private Map<String, String> methodCommunicationMap = new HashMap<String, String>();

    private Map<String, Long> methodTimeoutMap = new HashMap<String, Long>();

    private Map<String, Boolean> methodIdempotentMap = new HashMap<String, Boolean>();

    private Map<String, Integer> methodRetryTimesMap = new HashMap<String, Integer>();

    private Map<String, Priority> methodPriorityMap = new HashMap<String, Priority>();

    private Map<String, Long> methodLogSpanIntervalMap = new HashMap<String, Long>();

    private Map<String, Long> methodInvokeCostThresholds = new HashMap<String, Long>();

    private Map<String, Double> methodInvokeFailThresholds = new HashMap<String, Double>();

    private Map<String, Boolean> methodAccessRestrictedInfos = new HashMap<String, Boolean>();

    public ServiceImpl(String contract, String implCode) {
        this.contract = contract;
        this.implCode = implCode;
    }

    @Override
    public String getId() {
        return getContractStr() + (getImplCode() == null ? "" : "/" + getImplCode());
    }

    @Override
    public Class getContract() {
        try {
            return ClassUtils.forName(this.contract);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getContractStr() {
        return this.contract;
    }

    @Override
    public String getImplCode() {
        return implCode;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean internal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public void setDefaultCommunication(String name) {
        this.defaultCommunication = name;
    }

    @Override
    public List<String> getCommunications() {
        List<String> result = new ArrayList<String>();
        if (this.defaultCommunication != null) {
            result.add(this.defaultCommunication);
        }
        for (String commuName : this.methodCommunicationMap.values()) {
            if (!result.contains(commuName)) {
                result.add(commuName);
            }
        }
        return result;
    }

    @Override
    public String getCommunication(String methodName, Class[] argTypes) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        String name = this.methodCommunicationMap.get(methodId);
        if (name == null) {
            name = this.defaultCommunication;
        }
        return name;
    }

    @Override
    public String getCommunication(String methodName, String argTypes) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        String name = this.methodCommunicationMap.get(methodId);
        if (name == null) {
            name = this.defaultCommunication;
        }
        return name;
    }

    public void setCommunication(String methodName, Class[] argTypes, String name) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodCommunicationMap.put(methodId, name);
    }

    public void setCommunication(String methodName, String[] argTypes, String name) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodCommunicationMap.put(methodId, name);
    }

    @Override
    public Long getTimeout(String methodName, Class[] argTypes) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        Long result = this.methodTimeoutMap.get(methodId);
        if (result == null) {
            return DEFAULT_TIMEOUT;
        }
        return result;
    }

    public void setTimeout(String methodName, Class[] argTypes, Long timeout) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodTimeoutMap.put(methodId, timeout);
    }

    public void setTimeout(String methodName, String[] argTypes, Long timeout) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodTimeoutMap.put(methodId, timeout);
    }

    @Override
    public boolean isIdempotent(String methodName, Class[] argTypes) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        Boolean idempotent = this.methodIdempotentMap.get(methodId);
        return idempotent != null ? idempotent : DEFAULT_IDEMPOTENT;
    }

    public void setIdempotent(String methodName, Class[] argTypes, boolean idempotent) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodIdempotentMap.put(methodId, idempotent);
    }

    public void setIdempotent(String methodName, String[] argTypes, boolean idempotent) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodIdempotentMap.put(methodId, idempotent);
    }

    @Override
    public int getRetryTimes(String methodName, Class[] argTypes) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        Integer result = this.methodRetryTimesMap.get(methodId);
        if (result == null) {
            return DEFAULT_RETRY_TIMES;
        }
        return result;
    }

    public void setRetryTimes(String methodName, Class[] argTypes, int times) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodRetryTimesMap.put(methodId, times);
    }

    public void setRetryTimes(String methodName, String[] argTypes, int times) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodRetryTimesMap.put(methodId, times);
    }

    @Override
    public Priority getPriority(String methodName, Class[] argTypes) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        Priority result = this.methodPriorityMap.get(methodId);
        if (result == null) {
            return DEFAULT_PRIORITY;
        }
        return result;
    }

    public void setPriority(String methodName, Class[] argTypes, Priority priority) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodPriorityMap.put(methodId, priority);
    }

    public void setPriority(String methodName, String[] argTypes, Priority priority) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodPriorityMap.put(methodId, priority);
    }

    @Override
    public long getLogSpanInterval(String methodName, Class[] argTypes) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        Long interval = methodLogSpanIntervalMap.get(methodId);
        if (interval == null) {
            return DEFAULT_SPAN_LOG_INTERVAL;
        }
        return interval;
    }

    public void setLogSpanInterval(String methodName, Class[] argTypes, long interval) throws Exception {
        if (interval < 0) {
            throw new Exception("Log span interval can not < 0");
        }
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        methodLogSpanIntervalMap.put(methodId, interval);
    }

    public void setLogSpanInterval(String methodName, String[] argTypes, long interval) throws Exception {
        if (interval < 0) {
            throw new Exception("Log span interval can not < 0");
        }
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        methodLogSpanIntervalMap.put(methodId, interval);
    }

    public String getWarningPhones() {
        return warningPhones;
    }

    public void setWarningPhones(String warningPhones) {
        this.warningPhones = warningPhones;
    }

    @Override
    public Long getInvokeCostTimeThreshold(String methodName, Class[] argTypes) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        Long result = this.methodInvokeCostThresholds.get(methodId);
        if (result == null) {
            return DEFAULT_INVOKE_COST_TIME_THRESHOLD;
        }
        return result;
    }

    @Override
    public Long getInvokeCostTimeThreshold(String methodName, String[] argTypes) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        Long result = this.methodInvokeCostThresholds.get(methodId);
        if (result == null) {
            return DEFAULT_INVOKE_COST_TIME_THRESHOLD;
        }
        return result;
    }

    public void setInvokeCostTimeThreshold(String methodName, Class[] argTypes, long threshold) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodInvokeCostThresholds.put(methodId, threshold);
    }

    public void setInvokeCostTimeThreshold(String methodName, String[] argTypes, long threshold) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodInvokeCostThresholds.put(methodId, threshold);
    }

    @Override
    public double getInvokeFailThreshold(String methodName, Class[] argTypes) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        Double result = this.methodInvokeFailThresholds.get(methodId);
        if (result == null) {
            return DEFAULT_INVOKE_FAIL_THRESHOLD;
        }
        return result;
    }

    @Override
    public double getInvokeFailThreshold(String methodName, String[] argTypes) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        Double result = this.methodInvokeFailThresholds.get(methodId);
        if (result == null) {
            return DEFAULT_INVOKE_FAIL_THRESHOLD;
        }
        return result;
    }

    public void setInvokeFailThreshold(String methodName, Class[] argTypes, double threshold) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodInvokeFailThresholds.put(methodId, threshold);
    }

    public void setInvokeFailThreshold(String methodName, String[] argTypes, double threshold) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodInvokeFailThresholds.put(methodId, threshold);
    }

    @Override
    public boolean isAccessRestricted(String methodName, Class[] argTypes) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        Boolean result = this.methodAccessRestrictedInfos.get(methodId);
        if (result == null) {
            return false;
        }
        return result;
    }

    @Override
    public boolean isAccessRestricted(String methodName, String[] argTypes) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        Boolean result = this.methodAccessRestrictedInfos.get(methodId);
        if (result == null) {
            return false;
        }
        return result;
    }

    public void setAccessRestricted(String methodName, Class[] argTypes, boolean restricted) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodAccessRestrictedInfos.put(methodId, restricted);
    }

    public void setAccessRestricted(String methodName, String[] argTypes, boolean restricted) {
        String methodId = MethodIdUtils.getMethodId(methodName, argTypes);
        this.methodAccessRestrictedInfos.put(methodId, restricted);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceImpl service = (ServiceImpl) o;
        if (internal != service.internal) return false;
        if (appCode != null ? !appCode.equals(service.appCode) : service.appCode != null) return false;
        if (!contract.equals(service.contract)) return false;
        if (defaultCommunication != null ? !defaultCommunication.equals(service.defaultCommunication) : service
                .defaultCommunication != null)
            return false;
        if (description != null ? !description.equals(service.description) :
                service.description != null) return false;
        if (implCode != null ? !implCode.equals(service.implCode) :
                service.implCode != null) return false;
        if (!methodAccessRestrictedInfos.equals(service.methodAccessRestrictedInfos)) return false;
        if (!methodCommunicationMap.equals(service.methodCommunicationMap)) return false;
        if (!methodIdempotentMap.equals(service.methodIdempotentMap)) return false;
        if (!methodInvokeCostThresholds.equals(service.methodInvokeCostThresholds)) return false;
        if (!methodInvokeFailThresholds.equals(service.methodInvokeFailThresholds)) return false;
        if (!methodLogSpanIntervalMap.equals(service.methodLogSpanIntervalMap)) return false;
        if (!methodPriorityMap.equals(service.methodPriorityMap)) return false;
        if (!methodRetryTimesMap.equals(service.methodRetryTimesMap)) return false;
        if (!methodTimeoutMap.equals(service.methodTimeoutMap)) return false;
        if (warningPhones != null ? !warningPhones.equals(service.warningPhones) :
                service.warningPhones != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = contract.hashCode();
        result = 31 * result + (implCode != null ? implCode.hashCode() : 0);
        result = 31 * result + (appCode != null ? appCode.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (internal ? 1 : 0);
        result = 31 * result + (warningPhones != null ? warningPhones.hashCode() : 0);
        result = 31 * result + (defaultCommunication != null ? defaultCommunication.hashCode() : 0);
        result = 31 * result + methodCommunicationMap.hashCode();
        result = 31 * result + methodTimeoutMap.hashCode();
        result = 31 * result + methodIdempotentMap.hashCode();
        result = 31 * result + methodRetryTimesMap.hashCode();
        result = 31 * result + methodPriorityMap.hashCode();
        result = 31 * result + methodLogSpanIntervalMap.hashCode();
        result = 31 * result + methodInvokeCostThresholds.hashCode();
        result = 31 * result + methodInvokeFailThresholds.hashCode();
        result = 31 * result + methodAccessRestrictedInfos.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ServiceImpl{" +
                "contract='" + contract + '\'' +
                ", implCode='" + implCode + '\'' +
                '}';
    }

}
