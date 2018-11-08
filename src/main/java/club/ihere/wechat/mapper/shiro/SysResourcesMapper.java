package club.ihere.wechat.mapper.shiro;

import club.ihere.wechat.bean.pojo.shiro.SysResources;
import club.ihere.wechat.bean.pojo.shiro.SysResourcesExample;
import java.util.List;
import java.util.Set;

import club.ihere.wechat.bean.pojo.shiro.SysRole;
import org.apache.ibatis.annotations.Param;

public interface SysResourcesMapper {
    long countByExample(SysResourcesExample example);

    int deleteByExample(SysResourcesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysResources record);

    int insertSelective(SysResources record);

    List<SysResources> selectByExample(SysResourcesExample example);

    SysResources selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysResources record, @Param("example") SysResourcesExample example);

    int updateByExample(@Param("record") SysResources record, @Param("example") SysResourcesExample example);

    int updateByPrimaryKeySelective(SysResources record);

    int updateByPrimaryKey(SysResources record);

    /**
     * @param userId
     * @return
     */
    Set<String> findRoleNameByUserId(@Param("userId") int userId);

    Set<SysResources> findSysResourcesByRoleId(Set<SysRole> roles);
}