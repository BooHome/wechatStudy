package club.ihere.wechat.service.shiro;

import club.ihere.wechat.bean.pojo.shiro.SysRole;

import java.util.Set;

public interface SysRoleService {
    Set<String> findRoleNameByUserId(int userId);

    Set<SysRole> findRoleByUserId(Integer id);
}
