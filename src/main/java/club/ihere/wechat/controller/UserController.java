package club.ihere.wechat.controller;

import club.ihere.wechat.bean.pojo.shiro.SysUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:
 * @date: 2018/5/12
 * @description:
 */
@RestController
@RequestMapping("user")
public class UserController {

    /**
     * 创建固定写死的用户
     * @param model
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    @ResponseBody
    public String login(Model model) {

        SysUser user = new SysUser();
        user.setUserName("XXX");
        return "创建用户成功"+user.getUserName();

    }

    /**
     * 删除固定写死的用户
     * @param model
     * @return
     */
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    @ResponseBody
    public String del(Model model) {
        return "删除用户名为wangsaichao用户成功";
    }

    @RequestMapping(value = "/view",method = RequestMethod.GET)
    @ResponseBody
    public String view(Model model) {
        return "这是用户列表页";

    }


}
