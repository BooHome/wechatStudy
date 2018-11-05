package club.ihere.wechat.common.exception;

/**
 * 配置文件内容为空的异常
 * 
 * @author
 */
public class ConfigEmptyException extends ConfigException {

	private static final long serialVersionUID = 8284744227571481836L;

	public ConfigEmptyException(String key, String filename) {
		super("[" + key + "] is empty in [" + filename + "]");
	}

}
