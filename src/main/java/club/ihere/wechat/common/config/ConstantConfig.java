package club.ihere.wechat.common.config;

import club.ihere.wechat.common.config.base.BaseConfig;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author: fengshibo
 * @date: 2018/11/6 14:25
 * @description:
 */
public class ConstantConfig extends BaseConfig {

    private static final ConstantConfig config = new ConstantConfig("application-common.properties");

    private static final ConstantConfig shiroUrlCconfig = new ConstantConfig("application-shiro-url.properties");

    protected ConstantConfig(String filename) {
        super(filename);
    }

    public static long gerLongVal(String key) {
        return config.getInt(key);
    }

    public static String gerStringVal(String key) {
        return config.getString(key);
    }

    public static Integer getIntVal(String key) {
        return config.getInt(key);
    }

    public static long getAvoidRepeatableCommitTime() {
        return config.getLong("avoid.repeatable.commit.time");
    }

    public static String getAvoidRepeatableCommitText() {
        return config.getString("avoid.repeatable.commit.text");
    }

    public static LinkedHashMap<String, String> getFilterChainDefinitionMap() {
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        List<String> keys = shiroUrlCconfig.getKeys();
        keys.stream().forEach(key->{
            result.put(key,shiroUrlCconfig.getString(key));
        });
        return result;
    }
}
