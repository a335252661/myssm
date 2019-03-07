package cn.cld.dao;

import cn.cld.pojo.Module;
import cn.cld.pojo.ModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ModuleMapper {
    int countByExample(ModuleExample example);

    int deleteByExample(ModuleExample example);

    int deleteByPrimaryKey(String moduleNo);

    int insert(Module record);

    int insertSelective(Module record);

    List<Module> selectByExampleWithRowbounds(ModuleExample example, RowBounds rowBounds);

    List<Module> selectByExample(ModuleExample example);

    Module selectByPrimaryKey(String moduleNo);

    int updateByExampleSelective(@Param("record") Module record, @Param("example") ModuleExample example);

    int updateByExample(@Param("record") Module record, @Param("example") ModuleExample example);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
}