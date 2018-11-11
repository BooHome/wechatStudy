package club.ihere.wechat.service.shiro;


import club.ihere.wechat.bean.pojo.shiro.SysUser;

import java.util.Set;

public interface UserService {

/*    *//**
     * 获取用户权限
     *
     * @param userId userId
     * @return 用户权限
     *//*
    Set<String> findPermissionsByUserId(int userId);*/

    /**
     * 根据用户名密码获取用户
     * @param user
     * @return
     */
    SysUser getUserByUserInfo(SysUser user);

    SysUser findByUserName(String username);

    void update(SysUser user);
}
