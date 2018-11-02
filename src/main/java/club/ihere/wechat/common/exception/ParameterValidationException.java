package club.ihere.wechat.common.exception;

/**
 * @author: fengshibo
 * @date: 2018/11/2 11:20
 * @description:
 */
public class ParameterValidationException extends BaseException {

    private static final long serialVersionUID = 6511252697679361658L;

    public ParameterValidationException() {
        super();
    }

    public ParameterValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterValidationException(String message) {
        super(message);
    }

    public ParameterValidationException(Throwable cause) {
        super(cause);
    }

}
