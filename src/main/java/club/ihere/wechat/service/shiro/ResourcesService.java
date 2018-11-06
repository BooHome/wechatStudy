package club.ihere.wechat.service.shiro;

import club.ihere.wechat.bean.pojo.shiro.SysResources;

import java.util.List;

public interface ResourcesService {
    List<SysResources> selectAll();
}
