package club.ihere.wechat.common.exception;

/**
 * @author: fengshibo
 * @date: 2018/11/2 18:24
 * @description:
 */
public class ParamException extends RuntimeException{

    public ParamException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
