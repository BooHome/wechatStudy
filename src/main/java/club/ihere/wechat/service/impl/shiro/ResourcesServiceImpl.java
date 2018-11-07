package club.ihere.wechat.service.impl.shiro;

import club.ihere.wechat.bean.pojo.shiro.SysResources;
import club.ihere.wechat.mapper.shiro.SysResourcesMapper;
import club.ihere.wechat.service.shiro.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    private SysResourcesMapper sysResourcesMapper;

    @Override
    public List<SysResources> selectAll() {
        List<SysResources> resourcesList = sysResourcesMapper.selectByExample(null);
        return resourcesList;
    }
}
