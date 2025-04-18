package com.dite.znpt.monitor.domain;

/**
 * @Author: cuizhibin
 * @Date: 2023/1/16 14:36:36
 * @Description:
 */
public interface CustomFunction<T> {
    /**
     * 执行的方法
     * @return
     */
    T get();
}
