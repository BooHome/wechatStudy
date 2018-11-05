package club.ihere.wechat.common.exception;

/**
 * 配置文件内容解析异常
 * 
 * @author
 */
public class ConfigParseException extends ConfigException {

	private static final long serialVersionUID = -8336639507992019216L;

	public ConfigParseException(String key, String filename) {
		super("[" + key + "] parse exception in [" + filename + "]");
	}

}
