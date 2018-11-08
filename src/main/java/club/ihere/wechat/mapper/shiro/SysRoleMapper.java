package club.ihere.wechat.mapper.shiro;

import club.ihere.wechat.bean.pojo.shiro.SysRole;
import club.ihere.wechat.bean.pojo.shiro.SysRoleExample;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper {
    long countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    Set<String> findRoleNameByUserId(@Param("userId") int userId);

    Set<SysRole> findRoleByUserId(@Param("userId") Integer id);
}