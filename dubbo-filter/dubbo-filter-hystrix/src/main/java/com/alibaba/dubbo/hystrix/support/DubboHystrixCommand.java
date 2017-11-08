/*
 * Copyright (C), 2014-2015, 江苏乐博国际投资发展有限公司
 * FileName: HystrixCommand
 * Author:   Lucky
 * Date:     2017/11/06 16:56
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.alibaba.dubbo.hystrix.support;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.netflix.hystrix.*;


/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author Lucky
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DubboHystrixCommand extends HystrixCommand<Result> {


    private Invoker<?> invoker;
    private Invocation invocation;

    public DubboHystrixCommand(Invoker<?> invoker, Invocation invocation) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(invoker.getInterface().getName()))
                .andCommandKey(HystrixCommandKey.Factory
                        .asKey(String.format("%s_%d", invocation.getMethodName(), invocation.getArguments() == null ? 0 : invocation.getArguments().length)))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerRequestVolumeThreshold(
                                getHystrixConfigByKey(invoker.getUrl(), Constants.REQUEST_VOLUME_THRESHOLD_KEY, Constants.REQUEST_VOLUME_THRESHOLD))
                        .withCircuitBreakerSleepWindowInMilliseconds(getHystrixConfigByKey(invoker.getUrl(), Constants.SLEEP_MILLISECONDS_KEY, Constants.SLEEP_MILLISECONDS))
                        .withCircuitBreakerErrorThresholdPercentage(
                                getHystrixConfigByKey(invoker.getUrl(), Constants.ERROR_THRESHOLD_PERCENTAGE_KEY, Constants.ERROR_THRESHOLD_PERCENTAGE))
                        .withExecutionTimeoutEnabled(false))// 使用dubbo的超时，禁用这里的超时
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter().withCoreSize(getHystrixConfigByKey(invoker.getUrl(), Constants.CORE_SIZE_KEY, Constants.CORE_SIZE))));
        this.invoker = invoker;
        this.invocation = invocation;
    }

    private static int getHystrixConfigByKey(URL url, String key, int defaultValue) {
        return url.getParameter(key, defaultValue);
    }

    @Override
    protected Result run() throws Exception {
        return invoker.invoke(invocation);
    }

    @Override
    protected Result getFallback() {
        return null;
    }
}
