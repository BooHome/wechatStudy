package club.ihere.wechat.advince;

import club.ihere.common.util.current.StringConvertUtil;
import club.ihere.exception.ParameterValidationException;
import club.ihere.wechat.common.json.JsonResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author
 */
@Data
@Slf4j
@ControllerAdvice
@PropertySource("classpath:application-exception.properties")
@ConfigurationProperties(prefix = "advince")
public class ExceptionControllerAdvice {

    /**
     * 500-错误页面访问地址
     * 所有不能处理的异常要不经由该页面返回
     * 如果不设置则直接抛出异常
     */
    private String serverErrorUri;

    /**
     * 异常-状态码映射列表
     *
     * @see org.springframework.http.HttpStatus
     */
    private Map<Class<? extends Throwable>, Integer> exceptionStatusMap;

    /**
     * 状态码-页面映射
     *
     * @see org.springframework.http.HttpStatus
     */
    private Map<Integer, String> exceptionStatusUriMap;

    /**
     * 异常-页面映射
     */
    private Map<Class<? extends Throwable>, String> exceptionUriMap;

    /**
     * 400 - Bad Request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ModelAndView handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException e) throws Exception {
        return generateExceptionModelAndView(request, e, new JsonResult<>(false, "[400]参数解析失败(Bad Request): " + e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 405 - Method Not Allowed
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleHttpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException e) throws Exception {
        return generateExceptionModelAndView(request, e, new JsonResult<>(false, "[405]不支持当前请求方法(Method Not Allowed): " + e.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ModelAndView handleHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpMediaTypeNotSupportedException e) throws Exception {
        return generateExceptionModelAndView(request, e, new JsonResult<>(false, "[415]不支持当前媒体类型(Unsupported Media Type): " + e.getMessage()), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * springmvc注解形式参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) throws Exception {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuilder sb = new StringBuilder("[500]参数校验不通过: ");
        for (ObjectError error : allErrors) {
            sb.append("[").append(error.getDefaultMessage()).append("] ");
        }
        return generateExceptionModelAndView(request, e, new JsonResult<>(false, sb.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理异常
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception e) throws Exception {//对于未知异常进行打印日志
        //对于当前类没有定义的异常,从SpringMVC获取异常对应的http状态码
        return generateExceptionModelAndView(request, e, new JsonResult<>(false, e.getMessage()), getExceptionStatus(e));
    }

    /**
     * 获取异常定义的状态码，参考http协议状态码
     *
     * @see HttpStatus
     */
    private HttpStatus getExceptionStatus(Exception e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exceptionStatusMap == null) {
            return httpStatus;
        }
        for (Map.Entry<Class<? extends Throwable>, Integer> entry : exceptionStatusMap.entrySet()) {
            if (entry.getKey().isInstance(e)) {
                if (entry.getValue() == null) {
                    throw new ParameterValidationException("状态码异常映射不能为null");
                }
                return checkHttpStatus(entry.getValue());
            }
        }
        return null;
    }

    /**
     * 生成异常处理ModelAndView
     *
     * @param request HttpServletRequest
     * @param e       待处理的异常信息
     * @param result  当请求方式不为get且请求接收内容为text/html时,返回页面
     * @return modelAndView
     */
    private ModelAndView generateExceptionModelAndView(HttpServletRequest request, Exception e, JsonResult<?> result, HttpStatus httpStatus) throws Exception {
        String accept = request.getHeader("accept");
        boolean needStatus = StringConvertUtil.toBaseBoolean(request.getHeader("need-status"), false);
        if (accept == null) {
            accept = MediaType.TEXT_HTML_VALUE;
        }

        ModelAndView modelAndView;
        // 判断是否接受json形式的数据
        boolean isNotGetJson = accept.contains(MediaType.APPLICATION_JSON_VALUE) || accept.contains(MediaType.APPLICATION_JSON_UTF8_VALUE);
        // 判断是否是json
        if (isNotGetJson) {
            MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
            Map<String, Object> resultMap = new HashMap<>(6);
            resultMap.put("status", result.isStatus());
            resultMap.put("message", result.getMessage() == null ? e.getClass().getName() : result.getMessage());
            resultMap.put("data", result.getData());
            modelAndView = new ModelAndView(mappingJackson2JsonView, resultMap);
            //对于json返回结果,如果要返回正确的http错误代码，则需要在头部信息中加入[xxx]结果进行处理,否则，http状态码一直为成功
            modelAndView.setStatus(needStatus ? httpStatus : HttpStatus.OK);
            if (needStatus) {
                modelAndView.setStatus(httpStatus);
            } else {
                modelAndView.setStatus(HttpStatus.OK);
            }
            return modelAndView;
        }

        if (accept.contains(MediaType.TEXT_HTML_VALUE)) {
            // 处理状态码到页面的映射
            if (exceptionStatusUriMap != null) {
                for (Map.Entry<Integer, String> stateUriEntry : exceptionStatusUriMap.entrySet()) {
                    HttpStatus returnStatus;
                    if ((returnStatus = checkHttpStatus(stateUriEntry.getKey())) == httpStatus) {
                        modelAndView = new ModelAndView("redirect:"+stateUriEntry.getValue());
                        modelAndView.setStatus(returnStatus);
                        return modelAndView;
                    }
                }
            }

            // 处理异常到页面的映射
            if (exceptionUriMap != null) {
                for (Map.Entry<Class<? extends Throwable>, String> classHttpStatusEntry : exceptionUriMap.entrySet()) {
                    if (classHttpStatusEntry.getKey().isInstance(e)) {
                        modelAndView = new ModelAndView("redirect:"+classHttpStatusEntry.getValue());
                        modelAndView.setStatus(httpStatus);
                        return modelAndView;
                    }
                }
            }

            // 处理剩余异常页面
            if (serverErrorUri != null) {
                modelAndView = new ModelAndView("redirect:"+serverErrorUri);
                modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                modelAndView.addObject("exception", e);
                return modelAndView;
            }
        }

        //对于无法处理的页面异常直接抛出
        throw e;
    }

    /**
     * 检验状态码是否属于http协议
     */
    private HttpStatus checkHttpStatus(int status) {
        for (HttpStatus httpStatus : HttpStatus.values()) {
            if (httpStatus.value() == status) {
                return httpStatus;
            }
        }
        throw new RuntimeException("异常映射状态吗错误，请参考org.springframework.http.HttpStatus");
    }
}