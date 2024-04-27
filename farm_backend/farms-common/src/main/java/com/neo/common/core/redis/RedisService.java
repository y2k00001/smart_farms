package com.neo.common.core.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisCache redisCache;

    public void setMatchList(Long userId, List<Long> userIds) {
        String key = RedisKeys.MATCH_LIST_OF + userId;
        redisCache.setCacheList(key, userIds);
        redisCache.expire(key, 7, TimeUnit.DAYS);
    }

    public List<Long> getMatchList(Long userId) {
        String key = RedisKeys.MATCH_LIST_OF + userId;
        return redisCache.getCacheList(key);
    }

    public String getAddressList() {
        String key = RedisKeys.ADDRESS_LIST_KEY;
        return redisCache.getCacheObject(key);
    }

    public void setAddressList(String list) {
        String key = RedisKeys.ADDRESS_LIST_KEY;
        redisCache.setCacheObject(key,list);
    }

    interface RedisKeys {
        String MATCH_LIST_OF = "MATCH_LIST_OF_";
        String ADDRESS_LIST_KEY = "ADDRESS_LIST_KEY_";
    }

    /**
     * redis实现分布式锁 --- 上锁
     *
     * @param key
     * @param jobInfo
     * @param lockSecond
     * @return
     * @throws Exception
     */
    public void lock(String key, String jobInfo, Integer lockSecond) throws Exception {
        String existJobInfo = redisCache.getCacheObject(key);
        if (StringUtils.isNotEmpty(existJobInfo)) {
            throw new Exception(String.format("获取锁失败: redisKey: %s, existJobInfo: %s", key, existJobInfo));
        }
        redisCache.setCacheObject(key, jobInfo, lockSecond, TimeUnit.SECONDS);
    }

    /**
     * redis实现分布式锁 --- 解锁
     *
     * @param key
     * @param jobInfo
     * @throws Exception
     */
    public void unLock(String key, String jobInfo) throws Exception {
        String existJobInfo = redisCache.getCacheObject(key);
        if (jobInfo.equals(existJobInfo)) {
            redisCache.deleteObject(key);
        } else {
            throw new Exception(String.format("释放锁异常: redisKey: %s, existJobInfo: %s, jobInfo: %s", key, existJobInfo, jobInfo));
        }
    }
}
