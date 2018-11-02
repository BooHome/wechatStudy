package club.ihere.wechat.common.exception;

/**
 * @author: fengshibo
 * @date: 2018/11/2 11:19
 * @description:
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1853281172372595609L;

    public BaseException() {
        super();
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

}
