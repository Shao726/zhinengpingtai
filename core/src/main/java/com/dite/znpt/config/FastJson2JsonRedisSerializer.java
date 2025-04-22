package com.dite.znpt.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {
    private ObjectMapper objectMapper = new ObjectMapper();
    public static final Charset DEFAULT_CHARSET;
    private Class<T> clazz;

    public FastJson2JsonRedisSerializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    public byte[] serialize(T t) throws SerializationException {
        return t == null ? new byte[0] : JSON.toJSONString(t, new SerializerFeature[]{SerializerFeature.WriteClassName}).getBytes(DEFAULT_CHARSET);
    }

    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes != null && bytes.length > 0) {
            String str = new String(bytes, DEFAULT_CHARSET);
            return JSON.parseObject(str, this.clazz);
        } else {
            return null;
        }
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "'objectMapper' must not be null");
        this.objectMapper = objectMapper;
    }

    protected JavaType getJavaType(Class<?> clazz) {
        return TypeFactory.defaultInstance().constructType(clazz);
    }

    static {
        DEFAULT_CHARSET = StandardCharsets.UTF_8;
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }
}