package club.ihere.wechat.service.impl.shiro;


import club.ihere.wechat.bean.pojo.shiro.SysUser;
import club.ihere.wechat.mapper.shiro.SysResourcesMapper;
import club.ihere.wechat.mapper.shiro.SysUserMapper;
import club.ihere.wechat.service.shiro.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysResourcesMapper sysResourcesMapper;

    @Override
    public SysUser getUser(SysUser user) {
       /* SysUser info = userMapper.selectOne(user);
        return info;*/
       return null;
    }

    @Override
    public Set<String> findPermissionsByUserId(int userId) {
       /* Set<String> permissions = sysResourcesMapper.findRoleNameByUserId(userId);
        Set<String> result = new HashSet<>();
        for (String permission : permissions) {
            if (StringUtils.isBlank(permission)) {
                continue;
            }
            permission = StringUtils.trim(permission);
            result.addAll(Arrays.asList(permission.split("\\s*;\\s*")));
        }
        return result;*/
       return null;
    }
}
