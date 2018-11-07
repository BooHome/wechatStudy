package club.ihere.wechat.common.config;

import club.ihere.wechat.common.config.base.BaseConfig;

/**
 * @author: fengshibo
 * @date: 2018/11/7 11:20
 * @description:
 */
public class RedisConstantConfig extends BaseConfig {

    private static final RedisConstantConfig config = new RedisConstantConfig("application-redis.properties");

    protected RedisConstantConfig(String filename) {
        super(filename);
    }

    public static Integer getDatabase(){
        return config.getInt("spring.redis.shiro.database");
    }

    public static String getHost(){
        return config.getString("spring.redis.shiro.host");
    }

    public static Integer getPort(){
        return config.getInt("spring.redis.shiro.port");
    }

    public static String getPassword(){
        String result ="";
        try{
            result = config.getString("spring.redis.shiro.password");
        }catch (Exception e){

        }
        return result;
    }

    public static Integer getTimeout(){
        return config.getInt("spring.redis.shiro.timeout");
    }

    public static Integer getExpire(){
        return config.getInt("spring.redis.shiro.expire");
    }
}
