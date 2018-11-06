package club.ihere.wechat.mapper.base;

import club.ihere.wechat.bean.pojo.base.BaseTest;
import club.ihere.wechat.bean.pojo.base.BaseTestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseTestMapper {
    long countByExample(BaseTestExample example);

    int deleteByExample(BaseTestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseTest record);

    int insertSelective(BaseTest record);

    List<BaseTest> selectByExample(BaseTestExample example);

    BaseTest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseTest record, @Param("example") BaseTestExample example);

    int updateByExample(@Param("record") BaseTest record, @Param("example") BaseTestExample example);

    int updateByPrimaryKeySelective(BaseTest record);

    int updateByPrimaryKey(BaseTest record);
}