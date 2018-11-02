package club.ihere.wechat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: fengshibo
 * @date: 2018/11/2 18:19
 * @description:
 */
@Controller
@RequestMapping("swagger")
@Api(tags = "swagger测试接口")
public class SwaggerController {

    @GetMapping(value = "get", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get方法测试", notes = "get方法测试")
    @ResponseBody
    public String get(String param) {
        return param;
    }

    @PostMapping(value = "post", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "post方法测试", notes = "post方法测试")
    @ResponseBody
    public String post(String param) {
        return param;
    }
}