package com.yunche.common.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
//
//        // 创建 ObjectMapper 配置 Java 8 时间类型
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 注册 JavaTimeModule 处理 Java 8 日期时间类型
        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.activateDefaultTyping(
                objectMapper.getPolymorphicTypeValidator(), // 启用类型验证
                ObjectMapper.DefaultTyping.NON_FINAL, // 指定启用类型信息的策略
                JsonTypeInfo.As.PROPERTY // 以属性方式添加类型信息
        );

        //老版本已经被弃用
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);

        // 设置 RedisTemplate 序列化方式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key采用string的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);

        // value采用 Jackson 序列化方式
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value采用 Jackson 序列化方式
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }
}