package com.zikozee.redis.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;


@Configuration
@EnableRedisRepositories
@EnableCaching
@RequiredArgsConstructor
public class RedisConfig {

    private final Environment env;

    @Bean
    public JedisConnectionFactory connectionFactory(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(env.getProperty("custom.redis.host", String.class));
        configuration.setPort(env.getProperty("custom.redis.port", Integer.class, 6379));
        return new JedisConnectionFactory(configuration);
    }

    //to connect to redis server
    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate(){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        template.setKeySerializer(new JdkSerializationRedisSerializer()); //because key is string,  there is also long, int ... serializer
//        template.setHashKeySerializer(new StringRedisSerializer());//because key is string
//        template.setHashKeySerializer(new JdkSerializationRedisSerializer());//because key is string
        template.setValueSerializer(new JdkSerializationRedisSerializer());//because value is object
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisCacheManager redisCacheManager(){
        return RedisCacheManager.create(connectionFactory());
    }
}
