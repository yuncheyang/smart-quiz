package com.yunche.common.config.redis;

//@Configuration
//public class CreateObjectMapperConfig {
//    /**
//     * 自定义 objectMapper
//     */
//    @Bean
//    public ObjectMapper createObjectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // 设置序列化规则
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.activateDefaultTyping(
//                LaissezFaireSubTypeValidator.instance,
//                ObjectMapper.DefaultTyping.NON_FINAL,
//                JsonTypeInfo.As.WRAPPER_ARRAY
//        );
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        return objectMapper;
//    }
//}