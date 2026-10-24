package com.telusko.RedisDemoProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator;

@Configuration
public class RedisConfig
{

    @Bean
    public RedisConnectionFactory connectionFactory()
    {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String , Object> redisTemplate
            (
                    RedisConnectionFactory connectionFactory
            )
    {
        RedisTemplate<String, Object> redisTemplate =
                new RedisTemplate<>();

        redisTemplate.setConnectionFactory(connectionFactory);

        var keySerializer = new StringRedisSerializer();

        var valueSerializer =
                GenericJacksonJsonRedisSerializer.builder()  //building a serializer with the configuration we want.
                        .enableDefaultTyping(  //This is about preserving the Java object's type information when serializing and deserializing objects.
                                BasicPolymorphicTypeValidator.builder()
                                        .allowIfSubType(
                                                "com.telusko.RedisDemoProject.models.")
                                        .build()
                        )
                        .build();

        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);

        // Required for your DAO's opsForHash() operations
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);

        return redisTemplate;

    }
}
