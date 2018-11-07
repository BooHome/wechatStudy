package club.ihere.wechat.configuration.redis;

import club.ihere.wechat.common.config.RedisConstantConfig;
import club.ihere.wechat.common.util.SerializeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: fengshibo
 * @date: 2018/11/6 14:33
 * @description:
 */
@EnableCaching
@Configuration
public class ShiroRedisConfig extends RedisConfig {

    /**
     * 配置redis连接工厂
     *
     * @return
     */
    @Bean
    public RedisConnectionFactory shiroRedisConnectionFactory() {
        return createJedisConnectionFactory(RedisConstantConfig.getDatabase(), RedisConstantConfig.getHost(), RedisConstantConfig.getPort(), RedisConstantConfig.getPassword(), RedisConstantConfig.getTimeout());
    }

    /**
     * 配置redisTemplate 注入方式使用@Resource(name="") 方式注入
     *
     * @return
     */
    @Bean(name = "shiroRedisTemplate")
    public RedisTemplate shiroRedisTemplate() {
        RedisTemplate redisTemplate = super.getRedisTemplate(shiroRedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new SerializeUtils());
        redisTemplate.setValueSerializer(new SerializeUtils());
        return redisTemplate;
    }


}
