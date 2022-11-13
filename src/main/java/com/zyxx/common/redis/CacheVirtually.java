package com.zyxx.common.redis;

public class CacheVirtually {
    private String key;//缓存ID
    private Object value;//缓存数据
    private long timeOut;//更新时间
    private boolean expired; //是否终止
    public CacheVirtually() {
        super();
    }
    public CacheVirtually(String key, Object value) {
        this.key = key;
        this.value = value;
        this.expired = false;
    }

    public CacheVirtually(String key, Object value, long timeOut) {
        this.key = key;
        this.value = value;
        this.expired = false;
        this.timeOut = timeOut;
    }

    public String getKey() {
        return key;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public Object getValue() {
        return value;
    }

    public void setKey(String string) {
        key = string;
    }

    public void setTimeOut(long l) {
        timeOut = l;
    }

    public void setValue(Object object) {
        value = object;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean b) {
        expired = b;
    }
}
