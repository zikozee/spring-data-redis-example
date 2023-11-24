package com.zikozee.redis.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 24 Nov, 2023
 */

@Slf4j
@Component
public class JsonUtil {


    public static <T> T fromJSON(String json, Class<T> clazz){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, clazz);
        }catch (IOException ex){
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    public  <T> List<T> jsonToList(String json, Class<T> clazz){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CollectionType listType =
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
            return objectMapper.readValue(json, listType);
        }catch (IOException ex){
            log.error(ex.getMessage(), ex);
        }
        return null;
    }

    public <T> String toJSON(T data){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(data);
        }catch (IOException ex){
            log.error(ex.getMessage(), ex);
        }
        return null;
    }

    public <T> String toJSON(T data, boolean prettify){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            if(prettify)
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            else
                return objectMapper.writeValueAsString(data);
        }catch (IOException ex){
            log.error(ex.getMessage(), ex);
        }
        return null;
    }
}
