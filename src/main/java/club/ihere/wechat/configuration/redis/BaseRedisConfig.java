package club.ihere.wechat.configuration.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: fengshibo
 * @date: 2018/11/6 14:33
 * @description:
 */
@EnableCaching
@Configuration
public class BaseRedisConfig extends RedisConfig {

    @Value("${spring.redis.base.database}")
    private int dbIndex;

    @Value("${spring.redis.base.commit.database}")
    private int dbCommitIndex;

    @Value("${spring.redis.base.host}")
    private String host;

    @Value("${spring.redis.base.port}")
    private int port;

    @Value("${spring.redis.base.password}")
    private String password;

    @Value("${spring.redis.base.timeout}")
    private int timeout;

    /**
     * 配置redis连接工厂
     *
     * @return
     */
    @Bean
    @Primary
    public RedisConnectionFactory baseRedisConnectionFactory() {
        return createJedisConnectionFactory(dbIndex, host, port, password, timeout);
    }

    /**
     * 配置redisTemplate 注入方式使用@Resource(name="") 方式注入
     *
     * @return
     */
    @Bean(name = "baseRedisTemplate")
    public RedisTemplate baseRedisTemplate() {
        return super.getRedisTemplate(baseRedisConnectionFactory());
    }


    /**
     * 配置redis连接工厂
     *
     * @return
     */
    @Bean
    public RedisConnectionFactory commitRedisConnectionFactory() {
        return createJedisConnectionFactory(dbCommitIndex, host, port, password, timeout);
    }

    /**
     * 配置redisTemplate 注入方式使用@Resource(name="") 方式注入
     *
     * @return
     */
    @Bean(name = "baseCommitRedisTemplate")
    public RedisTemplate commitRedisTemplate() {
        return super.getRedisTemplate(commitRedisConnectionFactory());
    }

}
