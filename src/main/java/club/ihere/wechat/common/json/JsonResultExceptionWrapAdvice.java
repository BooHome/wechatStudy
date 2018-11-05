package club.ihere.wechat.common.json;

import club.ihere.wechat.common.exception.JsonResultWrapException;
import org.springframework.aop.ThrowsAdvice;

public class JsonResultExceptionWrapAdvice implements ThrowsAdvice {

	public void afterThrowing(Exception ex) {
		throw new JsonResultWrapException(ex);
	}

}
