package club.ihere.wechat.controller;

import club.ihere.wechat.aspect.annotation.AvoidRepeatableCommit;
import club.ihere.wechat.bean.pojo.shiro.SysUser;
import club.ihere.wechat.common.config.ConstantConfig;
import club.ihere.wechat.common.exception.ParameterValidationException;
import club.ihere.wechat.common.json.JsonResult;
import club.ihere.wechat.common.json.JsonResultBuilder;
import club.ihere.wechat.configuration.shiro.RetryLimitHashedCredentialsMatcher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
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
@RequestMapping(value = "/")
@Api(tags = "登陆测试接口")
public class LoginController {

    @Resource(name = "credentialsMatcher")
    private RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher;

    @Autowired
    private SessionDAO sessionDAO;

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "login", notes = "login")
    @AvoidRepeatableCommit
    @ResponseBody
    public JsonResult<SysUser> submitLogin(String username, String password) {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                return null;
            }
            //如果用户已登录，先踢出
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //提前1秒去判断   防止这个if没进  等执行下面的时候它却失效了  <span style="font-family: Arial, Helvetica, sans-serif;">lengthenTimeOut是失效时间</span>
            if ((System.currentTimeMillis() - subject.getSession().getStartTimestamp().getTime()) >= ConstantConfig.getIntVal("shiro.redis.setExpire") - 1000) {
                //移除失效的session
                sessionDAO.delete(subject.getSession());
                //移除线程里面的subject
                ThreadContext.remove(ThreadContext.SUBJECT_KEY);
                //重新获取subject
                subject = SecurityUtils.getSubject();
            }
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
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        return "redirect:/toLogin";
    }

    @GetMapping(value = "toLogin")
    public String toLogin(Model model) {
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        if (user == null) {
            return "/home/login";
        } else {
            return "redirect:/index";
        }

    }


    @RequestMapping("index")
    public String index(HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        if (user == null) {
            return "home/login";
        } else {
            model.addAttribute("user", user);
            return "home/index";
        }
    }

    /**
     * 跳转到无权限页面
     *
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
     *
     * @return
     */
    @RequestMapping(value = "/unlockAccount", method = RequestMethod.GET)
    @ResponseBody
    public void unlockAccount(String userName) {
        retryLimitHashedCredentialsMatcher.unlockAccount(userName);
    }
}
