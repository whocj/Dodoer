package com.summer.whm.spider.distributed.core.model;

import java.util.List;

/**
 * 服务的配置，一个服务由contract+implCode来标识，
 *
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */
public interface Service {

    /**
     * 服务唯一标识
     *
     * @return 唯一标识
     */
    public String getId();

    /**
     * 服务契约
     *
     * @return 服务契约接口类
     */
    public Class getContract();

    /**
     * 服务契约
     *
     * @return 服务契约接口类的字符串
     */
    public String getContractStr();

    /**
     * 是否系统内部服务，内部服务外系统不能调用
     *
     * @return 是否内部服务
     */
    public boolean internal();

    /**
     * 契约的实现编号，一个契约可能同时存在多种实现
     *
     * @return 实现编号
     */
    public String getImplCode();

    /**
     * 服务所属的系统
     *
     * @return 系统名称
     */
    public String getAppCode();

    /**
     * 服务(契约+implCode)的描述
     *
     * @return 描述信息
     */
    public String getDescription();

    /**
     * 可以为某一个方法指定使用某一种通讯方式（如果该服务提供多种通讯方式）
     *
     * @param methodName 方法名
     * @param argTypes   方法参数类型数组
     * @return communication name
     */
    public String getCommunication(String methodName, Class[] argTypes);

    /**
     * 可以为某一个方法指定使用某一种通讯方式（如果该服务提供多种通讯方式）
     *
     * @param methodName 方法名
     * @param argTypes   方法参数名称数组
     * @return communication name
     */
    public String getCommunication(String methodName, String argTypes);

    /**
     * 得到该服务所有的相关通讯方式（默认+方法指定的）
     *
     * @return 名称列表
     */
    public List<String> getCommunications();

    /**
     * 方法执行的timeout(单次请求，不是包含retry的总时间)，以ms为单位，供各种协议下实现retry使用.
     *
     * @param methodName 方法名
     * @param argTypes   参数类型数组
     * @return timeout，以ms为单位
     */
    public Long getTimeout(String methodName, Class[] argTypes);

    /**
     * 方法是否是幂等的
     *
     * @param methodName 方法名
     * @param argTypes   参数类型数组
     * @return 幂等
     */
    public boolean isIdempotent(String methodName, Class[] argTypes);

    /**
     * 重试次数
     *
     * @param methodName 方法名
     * @param argTypes   参数类型数组
     * @return 重试次数，-1代表不重试
     */
    public int getRetryTimes(String methodName, Class[] argTypes);

    /**
     * Span的log间隔，以毫秒为单位
     *
     * @param methodName 方法名
     * @param argTypes   参数类型数组
     * @return 采样频率
     */
    public long getLogSpanInterval(String methodName, Class[] argTypes);

    /**
     * 方法优先级，当provider资源不够用时，应优先处理哪个方法，优先级影响排队策略
     */
    public enum Priority {
        D/**Disabled**/
        , L, M, H
    }

    /**
     * 优先级
     *
     * @param methodName 方法名
     * @param argTypes   参数类型数组
     * @return 优先级
     */
    public Priority getPriority(String methodName, Class[] argTypes);

    /**
     * 服务的告警手机号
     *
     * @return 告警手机号
     */
    public String getWarningPhones();

    /**
     * invoke消耗时间的告警阀值
     *
     * @param methodName 方法名
     * @param argTypes   参数列表
     * @return 告警阀值(ms)
     */
    public Long getInvokeCostTimeThreshold(String methodName, Class[] argTypes);

    /**
     * invoke消耗时间的告警阀值
     *
     * @param methodName 方法名
     * @param argTypes   参数列表
     * @return 告警阀值(ms)
     */
    public Long getInvokeCostTimeThreshold(String methodName, String[] argTypes);

    /**
     * invoke失败的高级阀值，如果n>0，则当5分钟内调用n次失败，则告警，如果n<0,则表示百分比，达到百分比则告警。
     *
     * @param methodName 方法名
     * @param argTypes   参数列表
     * @return 告警阀值
     */
    public double getInvokeFailThreshold(String methodName, String[] argTypes);

    /**
     * invoke失败的高级阀值，如果n>0，则当5分钟内调用n次失败，则告警，如果n<0,则表示百分比，达到百分比则告警。
     *
     * @param methodName 方法名
     * @param argTypes   参数列表
     * @return 告警阀值
     */
    public double getInvokeFailThreshold(String methodName, Class[] argTypes);

    /**
     * 该方法是否访问受限，访问受限的方法必须经过使用方授权
     *
     * @param methodName 方法名
     * @param argTypes   参数列表
     * @return 是否受限
     */
    public boolean isAccessRestricted(String methodName, String[] argTypes);

    /**
     * 该方法是否访问受限，访问受限的方法必须经过使用方授权
     *
     * @param methodName 方法名
     * @param argTypes   参数列表
     * @return 是否受限
     */
    public boolean isAccessRestricted(String methodName, Class[] argTypes);

}
