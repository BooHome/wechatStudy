package club.ihere.wechat.mapper.shiro;

import club.ihere.wechat.bean.pojo.shiro.SysRoleResources;
import club.ihere.wechat.bean.pojo.shiro.SysRoleResourcesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleResourcesMapper {
    long countByExample(SysRoleResourcesExample example);

    int deleteByExample(SysRoleResourcesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleResources record);

    int insertSelective(SysRoleResources record);

    List<SysRoleResources> selectByExample(SysRoleResourcesExample example);

    SysRoleResources selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysRoleResources record, @Param("example") SysRoleResourcesExample example);

    int updateByExample(@Param("record") SysRoleResources record, @Param("example") SysRoleResourcesExample example);

    int updateByPrimaryKeySelective(SysRoleResources record);

    int updateByPrimaryKey(SysRoleResources record);
}