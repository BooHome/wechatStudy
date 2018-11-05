package club.ihere.wechat.common.config;

import club.ihere.common.api.config.ApiConfig;
import club.ihere.wechat.common.config.base.BaseConfig;

/**
 * @author: fengshibo
 * @date: 2018/11/5 18:04
 * @description:
 */
public class WeChatConfig extends BaseConfig {

    private static final WeChatConfig config = new WeChatConfig("application-wechat.properties");

    protected WeChatConfig(String filename) {
        super(filename);
    }

    public static ApiConfig apiConfig;

    static {
        String appId=config.getString("wechat.appid");
        String appSecret=config.getString("wechat.secret");
        Boolean enableJsApi=config.getString("wechat.secret").equals("true");
        apiConfig=new ApiConfig(appId,appSecret,enableJsApi);
    }

    public static String getToken(){
        return config.getString("wechat.token");
    }
}
