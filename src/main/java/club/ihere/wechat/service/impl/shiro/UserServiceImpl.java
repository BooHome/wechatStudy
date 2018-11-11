package club.ihere.wechat.service.impl.shiro;


import club.ihere.wechat.bean.pojo.shiro.SysUser;
import club.ihere.wechat.bean.pojo.shiro.SysUserExample;
import club.ihere.wechat.mapper.shiro.SysResourcesMapper;
import club.ihere.wechat.mapper.shiro.SysUserMapper;
import club.ihere.wechat.service.shiro.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysResourcesMapper sysResourcesMapper;

   /* @Override
    public Set<String> findPermissionsByUserId(int userId) {
        Set<String> permissions = sysResourcesMapper.findRoleNameByUserId(userId);
        Set<String> result = new HashSet<>();
        for (String permission : permissions) {
            if (StringUtils.isBlank(permission)) {
                continue;
            }
            permission = StringUtils.trim(permission);
            result.addAll(Arrays.asList(permission.split("\\s*;\\s*")));
        }
        return result;
    }*/

    @Override
    public SysUser getUserByUserInfo(SysUser user) {
        SysUserExample sysUserExample=new SysUserExample();
        sysUserExample.createCriteria().andUserNameEqualTo(user.getUserName()).andPassWordEqualTo(user.getPassWord());
        List<SysUser> sysUsers = userMapper.selectByExample(sysUserExample);
        return sysUsers.size()>0?sysUsers.get(0):null;
    }

    @Override
    public SysUser findByUserName(String username) {
        SysUserExample sysUserExample=new SysUserExample();
        sysUserExample.createCriteria().andUserNameEqualTo(username);
        List<SysUser> sysUsers = userMapper.selectByExample(sysUserExample);
        return sysUsers.size()>0?sysUsers.get(0):null;
    }

    @Override
    public void update(SysUser user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
