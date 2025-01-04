package com.yunche.common.config.redis;

//@Configuration
//public class RedisTemplateConfiguration {
//    /**
//     * @param redisConnectionFactory
//     * @return
//     */
//
//    @Resource
//    private RedisConnectionFactory redisConnectionFactory;
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        //创建一个createObjectMapperConfig对象
//        CreateObjectMapperConfig createObjectMapperConfig = new CreateObjectMapperConfig();
//        // ObjectMapper 转译
//        ObjectMapper objectMapper = createObjectMapperConfig.createObjectMapper();
//
//        // Json 序列化配置
//        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);
//
//        // 设置key和value的序列化规则
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(objectJackson2JsonRedisSerializer);
//        //设置hashKey和hashValue的序列化规则
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(objectJackson2JsonRedisSerializer);
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        redisTemplate.afterPropertiesSet();
//
//        return redisTemplate;
//    }
//
//}