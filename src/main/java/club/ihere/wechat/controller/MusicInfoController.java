package club.ihere.wechat.controller;

import club.ihere.wechat.bean.pojo.shiro.SysUser;
import club.ihere.wechat.common.json.JsonResult;
import club.ihere.wechat.common.json.JsonResultBuilder;
import club.ihere.wechat.common.util.RequestUtils;
import club.ihere.wechat.service.shiro.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;


@Controller
@RequestMapping(value = "/permissions")
@Api(tags = "权限测试接口")
public class MusicInfoController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "usersPage", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequiresPermissions({"/usersPage"})
    @ApiOperation(value = "usersPage", notes = "usersPage")
    @ResponseBody
    public JsonResult<String> login(){
        SysUser user = RequestUtils.currentLoginUser();
        Set<String> authorization = userService.findPermissionsByUserId(user.getId());
        return JsonResultBuilder.build("该用户有如下权限" + authorization);
    }

}
