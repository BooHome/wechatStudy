package club.ihere.wechat.common.config;

import club.ihere.wechat.common.config.base.BaseConfig;

/**
 * @author: fengshibo
 * @date: 2018/11/6 14:25
 * @description:
 */
public class ConstantConfig extends BaseConfig {

    private static final ConstantConfig config = new ConstantConfig("application-common.properties");

    protected ConstantConfig(String filename) {
        super(filename);
    }

    public static long getAvoidRepeatableCommitTime() {
        return config.getLong("avoid.repeatable.commit.time");
    }

    public static String getAvoidRepeatableCommitText() {
        return config.getString("avoid.repeatable.commit.text");
    }
}
