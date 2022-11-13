package com.zyxx.common.redis;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RedisVirtually {
    private static HashMap<String,CacheVirtually> cacheMap = new HashMap<String,CacheVirtually>();

    //单实例构造方法
    private RedisVirtually() {
        super();
    }
    /**
     * 根据key读取数据
     */
    public Object get(final String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        try {
            CacheVirtually c = cacheMap.get(key);
            if (Objects.isNull(c)) {
                return null;
            }
            else {
                boolean b = cacheExpired(c);
                if(!b){
                    return c.getValue();
                }else{
                    cacheMap.remove(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取redis成功，key：{}", key);
        }
        return null;
    }

    /**
     * 写入数据
     */
    public synchronized  boolean set(final String key, Object value) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        try {
            cacheMap.put(key,new CacheVirtually(key,value));
            log.info("存入RedisVirtually成功，key：{}，value：{}", key, value);
            return true;
        } catch (Exception e) {
            log.error("存入RedisVirtually失败，key：{}，value：{}", key, value);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 写入数据，并设置过期时间
     */
    public synchronized boolean set(final String key, Object value, long seconds) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        try {
            cacheMap.put(key,new CacheVirtually(key,value,1000*seconds+System.currentTimeMillis()));
            log.info("存入RedisVirtually成功，key：{}，value：{}，seconds：{}", key, value, seconds);
            return true;
        } catch (Exception e) {
            log.error("存入RedisVirtually失败，key：{}，value：{}，seconds：{}", key, value, seconds);
            e.printStackTrace();
        }
        return false;
    }




    /**
     * 根据key删除数据
     */
    public synchronized boolean del(final String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        try {
            if(cacheMap.containsKey(key)){
                cacheMap.remove(key);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据key批量删除数据
     */
    public boolean delBatch(final Set<String> keys) {
        if (CollectionUtil.isEmpty(keys)) {
            return false;
        }
        try {
            keys.stream().forEach(k -> {cacheMap.remove(k);});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }




    /**
     * 根据前缀获取所有的key
     * <p>
     * 例如：pro_*
     */
    public Set<String> getKeys(String prefix) {
        try {
            return cacheMap.keySet().stream().filter(k -> k.startsWith(prefix)).collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //判断缓存是否终止
    private  boolean cacheExpired(CacheVirtually cache) {
        if (null == cache) { //传入的缓存不存在
            return false;
        }
        long nowDt = System.currentTimeMillis(); //系统当前的毫秒数
        long cacheDt = cache.getTimeOut(); //缓存内的过期毫秒数
        if (cacheDt <= 0||cacheDt>nowDt) { //过期时间小于等于零时,或者过期时间大于当前时间时，则为FALSE
            return false;
        } else { //大于过期时间 即过期
            return true;
        }
    }



}
