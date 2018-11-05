package club.ihere.wechat.common.config.base;


import club.ihere.common.util.current.StringUtil;
import club.ihere.wechat.common.exception.ConfigParseException;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常配置对象
 * 
 * @author
 */
public class ExceptionConfig extends BaseConfig {

	private int defaultCode;

	private static Map<String, Integer> codeMap = new HashMap<String, Integer>();

	public ExceptionConfig(String filename) {
		super(filename);
	}

	@Override
	protected void load(boolean isFirst) {
		if (!isFirst) {
			codeMap.clear();
		}
		// default.code
		defaultCode = getInt("default.code");
		// types.type
		String[] typeNames = config.getValues("types.type[@name]");
		Map<String, Integer> typeMap = new HashMap<String, Integer>();
		for (int i = 0, j = 0; i < typeNames.length; i++, j++) {
			String name = StringUtil.trim(config.getValue("types.type(" + j + ")[@name]"));
			if (name == null) {
				i--;
				continue;
			}
			if (!name.isEmpty()) {
				String codeString = StringUtil.trimToNull(config.getValue("types.type(" + j + ")[@code]"));
				int code = defaultCode;
				if (codeString != null) {
					try {
						code = Integer.parseInt(codeString);
					} catch (Exception e) {
						throw new ConfigParseException("types.type(" + j + ")[@code]", filename);
					}
				}
				typeMap.put(name, code);
			}
		}
		// properties.property
		String[] classes = config.getValues("properties.property[@class]");
		for (int i = 0, j = 0; i < classes.length; i++, j++) {
			String clazz = StringUtil.trim(config.getValue("properties.property(" + j + ")[@class]"));
			if (clazz == null) {
				i--;
				continue;
			}
			if (!clazz.isEmpty()) {
				String codeString = StringUtil.trimToNull(config.getValue("properties.property(" + j + ").code"));
				int code = defaultCode;
				if (codeString != null) {
					try {
						code = Integer.parseInt(codeString);
					} catch (Exception e) {
						throw new ConfigParseException("types.type(" + j + ")[@code]", filename);
					}
				} else {
					String type = StringUtil.trimToNull(config.getValue("properties.property(" + j + ").type"));
					if (type != null && typeMap.containsKey(type)) {
						code = typeMap.get(type);
					}
				}
				codeMap.put(clazz, code);
			}
		}
	}

	public int getCode(Class<? extends Exception> clazz) {
		reload();
		if (clazz == null) {
			return defaultCode;
		}
		Integer code = codeMap.get(clazz.getName());
		return code != null ? code : defaultCode;
	}

}
