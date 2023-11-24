package com.zikozee.redis.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;


import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 24 Nov, 2023
 */

@Slf4j
@RequiredArgsConstructor
public class CacheUtil {

    public static final String EVENT="CACHEUTILS";

    private final JsonUtil jsonUtil;
    private final RedisTemplate redisTemplate;


    public <T> void setGenericData(String key, T data, long period, TimeUnit timeUnit){
        log.info("Event={}, message=saving to cache with key={}", EVENT, key);
        redisTemplate.opsForValue().set(key, jsonUtil.toJSON(data), period, timeUnit);
    }


    public <T> void updateGenericData(String key, T data, Class<T> clazz, long period, TimeUnit timeUnit){
        T availableData = getData(key, clazz);
        if(availableData != null){
            log.info("Event={}, message=updating data with key: {}", EVENT, key);
            redisTemplate.opsForValue().set(key, jsonUtil.toJSON(data), period, timeUnit);
        }
    }

    public <T> T getData(String key, Class<T> clazz){
        String json = this.getString(key);
        if(json == null) return null;
        return (T) jsonUtil.fromJSON(json, clazz);
    }


    public <T> List<T> getDataList(String key, Class<T> clazz){
        String json = this.getString(key);
        if(json == null) return null;
        return jsonUtil.jsonToList(json, clazz);
    }

    public String getString(String key) {
        try {
            return (String) redisTemplate.opsForValue().get(key);
        }catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return null;
        }
    }
}
