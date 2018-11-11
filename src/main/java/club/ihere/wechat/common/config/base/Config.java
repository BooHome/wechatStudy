package club.ihere.wechat.common.config.base;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.reloading.ReloadingStrategy;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 配置文件对象
 * 
 * @author
 */
public class Config {

	private FileConfiguration configuration;

	public Config(FileConfiguration configuration) {
		if (configuration == null) {
			throw new NullPointerException("configuration is null");
		}
		this.configuration = configuration;
	}

	public String getValue(String key) {
		return configuration.getString(key);
	}

	public List<String>  getKeys() {
		Iterator<String> stringIterator=configuration.getKeys();
		List<String> result= IteratorUtils.toList(stringIterator);
		return result;
	}

	public String getValue(String key, String defaultValue) {
		return configuration.getString(key, defaultValue);
	}

	public String[] getValues(String key) {
		return configuration.getStringArray(key);
	}

	/**
	 * 获取配置文件
	 * 
	 * @return
	 */
	public File getFile() {
		return configuration.getFile();
	}

	/**
	 * 配置文件是否重新加载
	 * 
	 * @return
	 */
	public boolean isFileReloaded() {
		ReloadingStrategy strategy = configuration.getReloadingStrategy();
		return strategy == null ? false : strategy.reloadingRequired();
	}

	/**
	 * 从Config中获取value
	 * 
	 * @param config
	 * @param key
	 * @return
	 */
	public static String getValue(Config config, String key) {
		return config == null ? null : config.getValue(key);
	}

}
