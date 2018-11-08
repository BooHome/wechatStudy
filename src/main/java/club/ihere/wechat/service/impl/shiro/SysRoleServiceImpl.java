package club.ihere.wechat.service.impl.shiro;

import club.ihere.wechat.bean.pojo.shiro.SysRole;
import club.ihere.wechat.bean.pojo.shiro.SysRoleExample;
import club.ihere.wechat.mapper.shiro.SysRoleMapper;
import club.ihere.wechat.service.shiro.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Set<String> findRoleNameByUserId(int userId) {
       return sysRoleMapper.findRoleNameByUserId(userId);
    }

    @Override
    public Set<SysRole> findRoleByUserId(Integer id) {
        return sysRoleMapper.findRoleByUserId(id);
    }
}
