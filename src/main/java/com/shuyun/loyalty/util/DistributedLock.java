package com.shuyun.loyalty.util;

import java.util.function.Supplier;

public interface DistributedLock {

    /**
     * 加锁
     * 
     * @param key 锁Key
     * @param waitTime 尝试加锁，等待时间 (ms)
     * @param leaseTime 上锁后的失效时间 (ms)
     * @param success 锁成功执行的逻辑
     * @param fail 锁失败执行的逻辑
     * @return T
     */
    <T> T lock(String key, int waitTime, int leaseTime, Supplier<T> success, Supplier<T> fail);

    void unlock(String key);

}
