package club.ihere.wechat.service.impl.shiro;

import club.ihere.wechat.bean.pojo.shiro.*;
import club.ihere.wechat.mapper.shiro.SysResourcesMapper;
import club.ihere.wechat.mapper.shiro.SysRoleResourcesMapper;
import club.ihere.wechat.service.shiro.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    private SysResourcesMapper sysResourcesMapper;

    @Autowired
    private SysRoleResourcesMapper sysRoleResourcesMapper;

    @Override
    public List<SysResources> selectAll() {
        List<SysResources> resourcesList = sysResourcesMapper.selectByExample(null);
        return resourcesList;
    }

    @Override
    public Set<SysResources> findSysResourcesByRoleId(Set<SysRole> roles) {
        SysRoleResourcesExample sysRoleResourcesExample=new SysRoleResourcesExample();
        List<Integer> roleIds=roles.stream().map(SysRole::getId).collect(Collectors.toList());
        sysRoleResourcesExample.createCriteria().andRoleIdIn(roleIds);
        List<SysRoleResources> sysRoleResources = sysRoleResourcesMapper.selectByExample(sysRoleResourcesExample);
        SysResourcesExample sysResourcesExample=new SysResourcesExample();
        List<Integer> sysRoleResourcesIds=sysRoleResources.stream().map(SysRoleResources::getResourcesId).collect(Collectors.toList());
        sysResourcesExample.createCriteria().andIdIn(sysRoleResourcesIds);
        List<SysResources> sysResourcesList=sysResourcesMapper.selectByExample(sysResourcesExample);
        return  new HashSet<>(sysResourcesList);
    }
}
