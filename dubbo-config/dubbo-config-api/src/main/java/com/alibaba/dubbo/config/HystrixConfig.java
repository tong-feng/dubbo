package com.alibaba.dubbo.config;


import java.util.Map;

public class HystrixConfig extends AbstractConfig {

    private static final long serialVersionUID = 1L;

    private Integer coreSize;

    private Integer sleepMill;

    private Integer errorThresholdPercentage;
    private Integer requestVolumeThreshold;

    // 自定义参数
    private Map<String, String> parameters;
    // 是否为缺省
    private Boolean isDefault;

    public HystrixConfig() {
    }


    public Integer getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(Integer coreSize) {
        this.coreSize = coreSize;
    }

    public Integer getSleepMill() {
        return sleepMill;
    }

    public void setSleepMill(Integer sleepMill) {
        this.sleepMill = sleepMill;
    }

    public Integer getErrorThresholdPercentage() {
        return errorThresholdPercentage;
    }

    public void setErrorThresholdPercentage(Integer errorThresholdPercentage) {
        this.errorThresholdPercentage = errorThresholdPercentage;
    }

    public Integer getRequestVolumeThreshold() {
        return requestVolumeThreshold;
    }

    public void setRequestVolumeThreshold(Integer requestVolumeThreshold) {
        this.requestVolumeThreshold = requestVolumeThreshold;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        checkParameterName(parameters);
        this.parameters = parameters;
    }

    public Boolean isDefault() {
        return isDefault;
    }

    public void setDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
}