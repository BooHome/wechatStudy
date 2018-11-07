package club.ihere.wechat.configuration.shiro.serializer;

import club.ihere.wechat.configuration.shiro.exception.SerializationException;

public interface RedisSerializer<T> {

    byte[] serialize(T t) throws SerializationException, SerializationException;

    T deserialize(byte[] bytes) throws SerializationException;
}
