package club.ihere.wechat.mapper.shiro;

import club.ihere.wechat.bean.pojo.shiro.ShiroTest;
import club.ihere.wechat.bean.pojo.shiro.ShiroTestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShiroTestMapper {
    long countByExample(ShiroTestExample example);

    int deleteByExample(ShiroTestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShiroTest record);

    int insertSelective(ShiroTest record);

    List<ShiroTest> selectByExample(ShiroTestExample example);

    ShiroTest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShiroTest record, @Param("example") ShiroTestExample example);

    int updateByExample(@Param("record") ShiroTest record, @Param("example") ShiroTestExample example);

    int updateByPrimaryKeySelective(ShiroTest record);

    int updateByPrimaryKey(ShiroTest record);
}