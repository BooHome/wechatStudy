package club.ihere.wechat.controller;

import club.ihere.wechat.bean.pojo.shiro.SysResources;
import club.ihere.wechat.bean.pojo.shiro.SysRole;
import club.ihere.wechat.bean.pojo.shiro.SysUser;
import club.ihere.wechat.common.json.JsonResult;
import club.ihere.wechat.common.json.JsonResultBuilder;
import club.ihere.wechat.common.util.RequestUtils;
import club.ihere.wechat.service.shiro.ResourcesService;
import club.ihere.wechat.service.shiro.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;


@Controller
@RequestMapping(value = "/permissions")
@Api(tags = "权限测试接口")
public class MusicInfoController {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private ResourcesService resourcesService;

    @GetMapping(value = "usersPage", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequiresPermissions({"DEL"})
    @ApiOperation(value = "usersPage", notes = "usersPage")
    @ResponseBody
    public JsonResult<Set<SysResources>> login() {
        SysUser user = RequestUtils.currentLoginUser();
        Set<SysRole> roles = roleService.findRoleByUserId(user.getId());
        Set<SysResources> sysResourcesByRoleId = resourcesService.findSysResourcesByRoleId(roles);
        return JsonResultBuilder.build(sysResourcesByRoleId);
    }

}
