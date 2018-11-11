package club.ihere.wechat.common.config.base;


import club.ihere.common.util.current.StringUtil;
import club.ihere.wechat.common.exception.ConfigEmptyException;
import club.ihere.wechat.common.exception.ConfigParseException;

import java.util.List;

/**
 * 配置对象基类
 *
 * @author
 */
public abstract class BaseConfig {

    protected final club.ihere.wechat.common.config.base.Config config;

    protected final String filename;

    protected BaseConfig(Config config) {
        if (config == null) {
            throw new NullPointerException("base is null");
        }
        this.config = config;
        this.filename = config.getFile().getName();
        init();
    }

    protected BaseConfig(String filename) {
        this(ConfigFactory.getConfig(filename));
    }

    protected void init() {
        load(true);
    }

    protected void load(boolean isFirst) {
    }

    protected void reload() {
        if (config.isFileReloaded()) {
            load(false);
        }
    }

    protected String getString(String key) {
        String value = config.getValue(key);
        if (StringUtil.isBlank(value)) {
            throw new ConfigEmptyException(key, filename);
        }
        return value.trim();
    }

    protected List<String> getKeys() {
        List<String> keys = config.getKeys();
        return keys;
    }

    protected String[] getStringArray(String key) {
        return config.getValues(key);
    }

    protected int getInt(String key) {
        String value = getString(key);
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new ConfigParseException(key, filename);
        }
    }

    protected long getLong(String key) {
        String value = getString(key);
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            throw new ConfigParseException(key, filename);
        }
    }

}
