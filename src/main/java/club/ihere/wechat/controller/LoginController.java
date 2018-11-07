package club.ihere.wechat.controller;

import club.ihere.wechat.aspect.annotation.AvoidRepeatableCommit;
import club.ihere.wechat.bean.pojo.shiro.SysUser;
import club.ihere.wechat.common.exception.ParameterValidationException;
import club.ihere.wechat.common.json.JsonResult;
import club.ihere.wechat.common.json.JsonResultBuilder;
import club.ihere.wechat.common.util.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @author
 */
@Controller
@RequestMapping(value = "/auth")
@Api(tags = "登陆测试接口")
public class LoginController {

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "login", notes = "login")
    @ResponseBody
    @AvoidRepeatableCommit
    public JsonResult<SysUser> submitLogin(String username, String password) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            SysUser user = (SysUser) subject.getPrincipal();
            // 执行到这里说明用户已登录成功
            return JsonResultBuilder.build(user);
        } catch (LockedAccountException e) {
            throw new ParameterValidationException("账户已被锁定");
        } catch (DisabledAccountException e) {
            throw new ParameterValidationException("账户已被禁用");
        } catch (ExpiredCredentialsException e) {
            throw new ParameterValidationException("登陆凭证已过期");
        } catch (UnknownAccountException e) {
            throw new ParameterValidationException("用户名或密码错误");
        } catch (ExcessiveAttemptsException e) {
            throw new ParameterValidationException("登录失败次数过多，请稍后重试");
        } catch (AuthenticationException e) {
            throw new ParameterValidationException("用户名或密码错误");
        } catch (Exception e) {
            throw new ParameterValidationException("发生未知错误");
        }
    }

    @GetMapping(value = "index", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "index", notes = "index")
    @ResponseBody
    public JsonResult<String> loginSuccessMessage() {
        String username = "未登录";
        SysUser currentLoginUser = RequestUtils.currentLoginUser();
        if (currentLoginUser != null && StringUtils.isNotEmpty(currentLoginUser.getUserName())) {
            username = currentLoginUser.getUserName();
        }
        return JsonResultBuilder.build(username);
    }

    @GetMapping(value = "logout", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "退出", notes = "退出")
    @ResponseBody
    public JsonResult<String> logoutSuccessMessage() {
        return JsonResultBuilder.build("退出成功");
    }

}
