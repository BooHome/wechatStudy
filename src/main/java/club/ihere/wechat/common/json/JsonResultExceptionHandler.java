package club.ihere.wechat.common.json;

import club.ihere.wechat.common.exception.JsonResultWrapException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
public class JsonResultExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(JsonResultExceptionHandler.class);

	private List<Class<?>> printCauseException;

	public void setPrintCauseException(List<Class<?>> printCauseException) {
		this.printCauseException = printCauseException;
	}

	@ExceptionHandler(JsonResultWrapException.class)
	@ResponseBody
	public JsonResult<?> handlerException(JsonResultWrapException wrapException) {
		Throwable throwable = wrapException.getCause();
		Throwable cause;
		if (instanceofPrintCauseException(throwable)) {
			cause = throwable.getCause();
		} else {
			cause = throwable;
		}
		if (cause != null) {
			logger.error("", cause);
		}
		return JsonResultBuilder.create().setStatus(false).setMessage(throwable.getMessage()).build();
	}

	private boolean instanceofPrintCauseException(Throwable throwable) {
		if (printCauseException == null) {
			return false;
		}
		for (Class<?> exception : printCauseException) {
			if (exception.isInstance(throwable)) {
				return true;
			}
		}
		return false;
	}

}
