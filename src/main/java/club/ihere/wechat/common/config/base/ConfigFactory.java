package club.ihere.wechat.common.config.base;

import club.ihere.common.constant.Constant;
import club.ihere.common.util.current.StringUtil;
import club.ihere.wechat.common.exception.ConfigException;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.apache.commons.io.FilenameUtils;

/**
 * 读取配置文件的类。读取配置文件的编码默认为UTF-8。当配置文件变更后，会自动重载配置文件
 * 
 * @author
 */
public class ConfigFactory {

	/**
	 * 获得配置对象
	 * 
	 * @param filename 文件名
	 * @return
	 * @throws NullPointerException if filename is null
	 * @throws ConfigException
	 */
	public static club.ihere.wechat.common.config.base.Config getConfig(String filename) {
		if (filename == null) {
			throw new NullPointerException("filename is null");
		}
		FileConfiguration config = getFileConfiguration(filename);
		config.setEncoding(Constant.CHARSET_UTF8_NAME);
		try {
			config.load(filename);
		} catch (Exception e) {
			throw new ConfigException("file load failure：" + filename, e);
		}
		config.setReloadingStrategy(new FileChangedReloadingStrategy()); // 设置为自动重载配置文件
		return new club.ihere.wechat.common.config.base.Config(config);
	}

	/**
	 * 获得XPath配置对象
	 * 
	 * @param filename 文件名
	 * @return
	 * @throws NullPointerException if filename is null
	 * @throws ConfigException
	 */
	public static club.ihere.wechat.common.config.base.Config getXPathConfig(String filename) {
		if (filename == null) {
			throw new NullPointerException("filename is null");
		}
		XMLConfiguration config = new XMLConfiguration();
		config.setEncoding(Constant.CHARSET_UTF8_NAME);
		try {
			config.load(filename);
		} catch (Exception e) {
			throw new ConfigException("file load failure：" + filename, e);
		}
		config.setReloadingStrategy(new FileChangedReloadingStrategy()); // 设置为自动重载配置文件
		config.setExpressionEngine(new XPathExpressionEngine());
		return new Config(config);
	}

	private static FileConfiguration getFileConfiguration(String filename) {
		String extName = StringUtil.trim(FilenameUtils.getExtension(filename));
		if (StringUtil.isEmpty(extName)) {
			throw new ConfigException("unknown file type: " + filename);
		}
		extName = extName.toLowerCase();
		if ("properties".equals(extName)) {
			return new PropertiesConfiguration();
		}
		if ("xml".equals(extName)) {
			return new XMLConfiguration();
		}
		throw new ConfigException("unknown file type: ." + extName);
	}

}
