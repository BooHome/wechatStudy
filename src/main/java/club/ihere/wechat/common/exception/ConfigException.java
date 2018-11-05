package club.ihere.wechat.common.exception;

/**
 * 配置文件异常
 * 
 * @author
 */
public class ConfigException extends BaseException {

	private static final long serialVersionUID = 2971617492470193382L;

	public ConfigException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigException(String message) {
		super(message);
	}

}
