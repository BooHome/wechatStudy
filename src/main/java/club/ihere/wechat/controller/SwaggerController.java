package club.ihere.wechat.controller;

import club.ihere.common.api.config.ApiConfig;
import club.ihere.wechat.aspect.annotation.AvoidRepeatableCommit;
import club.ihere.wechat.bean.pojo.base.BaseTest;
import club.ihere.wechat.bean.pojo.shiro.ShiroTest;
import club.ihere.wechat.common.config.WeChatConfig;
import club.ihere.wechat.mapper.base.BaseTestMapper;
import club.ihere.wechat.mapper.shiro.ShiroTestMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
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


    @Autowired
    @Qualifier("baseRedisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private BaseTestMapper baseTestMapper;

    @Autowired
    private ShiroTestMapper shiroTestMapper;

    private static Logger logger = LoggerFactory.getLogger(SwaggerController.class);

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

    @PostMapping(value = "api", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "api方法测试", notes = "api方法测试")
    @ResponseBody
    public ApiConfig api() {
        return WeChatConfig.apiConfig;
    }

    @PostMapping(value = "commit", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "commit方法测试", notes = "commit方法测试")
    @ResponseBody
    @AvoidRepeatableCommit(timeout = 5000)
    public void commit() {
    }

    @PostMapping(value = "redis", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "redis方法测试", notes = "redis方法测试")
    @ResponseBody
    @AvoidRepeatableCommit(timeout = 5000)
    public void redis() {
        BaseTest baseTest = new BaseTest();
        baseTest.setId(1);
        baseTest.setRemark("测试用例");
        redisTemplate.opsForValue().set("baseTest", baseTest);
    }

    @PostMapping(value = "mysql", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "mysql方法测试", notes = "mysql方法测试")
    @ResponseBody
    @AvoidRepeatableCommit(timeout = 5000)
    public void mysql() {
        logger.info("测试日志");
        BaseTest baseTest = new BaseTest();
        baseTest.setId(1);
        baseTest.setRemark("测试用例base");
        ShiroTest shiroTest = new ShiroTest();
        shiroTest.setId(1);
        shiroTest.setRemark("测试用例shiro");
        baseTestMapper.insert(baseTest);
        shiroTestMapper.insert(shiroTest);
    }
}
