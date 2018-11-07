package club.ihere.wechat.controller;

import club.ihere.wechat.bean.pojo.shiro.SysUser;
import club.ihere.wechat.common.exception.ParameterValidationException;
import club.ihere.wechat.common.util.RequestUtils;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


/**
 * @author
 */
@Controller
@RequestMapping(value = "/auth")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitLogin(String username, String password, HttpServletRequest request) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            SysUser user = (SysUser) subject.getPrincipal();
        } catch (LockedAccountException e) {
            throw new ParameterValidationException("账户已被锁定");
        }catch (DisabledAccountException e) {
            throw new ParameterValidationException("账户已被禁用");
        } catch (ExpiredCredentialsException e) {
            throw new ParameterValidationException("登陆凭证已过期");
        }catch (UnknownAccountException e) {
            throw new ParameterValidationException("用户名或密码错误");
        }catch (ExcessiveAttemptsException e) {
            throw new ParameterValidationException("登录失败次数过多，请稍后重试");
        }catch (AuthenticationException e) {
            throw new ParameterValidationException("用户名或密码错误");
        }
        // 执行到这里说明用户已登录成功
        return "redirect:/auth/index";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String loginSuccessMessage(HttpServletRequest request) {
        String username = "未登录";
        SysUser currentLoginUser = RequestUtils.currentLoginUser();
        if (currentLoginUser != null && StringUtils.isNotEmpty(currentLoginUser.getUserName())) {
            username = currentLoginUser.getUserName();
        } else {
            return "redirect:/auth/login";
        }
        request.setAttribute("username", username);
        return "index";
    }

}
