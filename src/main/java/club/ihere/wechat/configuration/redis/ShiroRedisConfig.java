package club.ihere.wechat.configuration.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: fengshibo
 * @date: 2018/11/6 14:33
 * @description:
 */
@EnableCaching
@Configuration
public class ShiroRedisConfig extends RedisConfig {

    @Value("${spring.redis.shiro.database}")
    private int dbIndex;

    @Value("${spring.redis.shiro.host}")
    private String host;

    @Value("${spring.redis.shiro.port}")
    private int port;

    @Value("${spring.redis.shiro.password}")
    private String password;

    @Value("${spring.redis.shiro.timeout}")
    private int timeout;

    /**
     * 配置redis连接工厂
     *
     * @return
     */
    @Bean
    public JedisConnectionFactory shiroRedisConnectionFactory() {
        return createJedisConnectionFactory(dbIndex, host, port, password, timeout);
    }

    /**
     * 配置redisTemplate 注入方式使用@Resource(name="") 方式注入
     *
     * @return
     */
    @Bean(name = "shiroRedisConnectionFactory")
    public RedisTemplate defaultRedisTemplate() {
        return super.getRedisTemplate(shiroRedisConnectionFactory());
    }

}
