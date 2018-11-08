package club.ihere.wechat.controller;

import club.ihere.wechat.aspect.annotation.AvoidRepeatableCommit;
import club.ihere.wechat.bean.pojo.shiro.SysUser;
import club.ihere.wechat.common.exception.ParameterValidationException;
import club.ihere.wechat.common.json.JsonResult;
import club.ihere.wechat.common.json.JsonResultBuilder;
import club.ihere.wechat.configuration.shiro.RetryLimitHashedCredentialsMatcher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


/**
 * @author
 */
@Controller
@RequestMapping(value = "/auth")
@Api(tags = "登陆测试接口")
public class LoginController {

    @Resource(name = "credentialsMatcher")
    private RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher;

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "login", notes = "login")
    @AvoidRepeatableCommit
    @ResponseBody
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

   /* @GetMapping(value = "index", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "index", notes = "index")
    @ResponseBody
    public JsonResult<String> loginSuccessMessage() {
        String username = "未登录";
        SysUser currentLoginUser = RequestUtils.currentLoginUser();
        if (currentLoginUser != null && StringUtils.isNotEmpty(currentLoginUser.getUserName())) {
            username = currentLoginUser.getUserName();
        }
        return JsonResultBuilder.build(username);
    }*/

    @GetMapping(value = "logout", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "退出", notes = "退出")
    @ResponseBody
    public JsonResult<String> logoutSuccessMessage() {

        return JsonResultBuilder.build("退出成功");
    }

    @GetMapping(value = "toLogin")
    public String toLogin(Model model) {
        Subject subject = SecurityUtils.getSubject();
        SysUser user=(SysUser) subject.getPrincipal();
        if (user == null){
            return "/home/login";
        }else{
            return "redirect:/auth/index";
        }

    }




    @RequestMapping("index")
    public String index(HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        SysUser user=(SysUser) subject.getPrincipal();
        if (user == null){
            return "home/login";
        }else{
            model.addAttribute("user",user);
            return "home/index";
        }
    }

    /**
     * 跳转到无权限页面
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/unauthorized")
    public String unauthorized(HttpSession session, Model model) {
        return "redirect:/unauthorized.html";
    }


    /**
     * 解锁用户
     * @return
     */
    @RequestMapping(value = "/unlockAccount",method = RequestMethod.GET)
    @ResponseBody
    public void unlockAccount(String userName) {
        retryLimitHashedCredentialsMatcher.unlockAccount(userName);
    }
}
