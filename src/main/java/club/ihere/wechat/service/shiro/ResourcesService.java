package club.ihere.wechat.service.shiro;

import club.ihere.wechat.bean.pojo.shiro.SysResources;
import club.ihere.wechat.bean.pojo.shiro.SysRole;

import java.util.List;
import java.util.Set;

public interface ResourcesService {
    List<SysResources> selectAll();

    Set<SysResources> findSysResourcesByRoleId(Set<SysRole> roles);
}
