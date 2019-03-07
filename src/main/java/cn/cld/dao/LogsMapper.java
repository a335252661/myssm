package cn.cld.dao;

import cn.cld.pojo.Logs;
import cn.cld.pojo.LogsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LogsMapper {
    int countByExample(LogsExample example);

    int deleteByExample(LogsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Logs record);

    int insertSelective(Logs record);

    List<Logs> selectByExampleWithRowbounds(LogsExample example, RowBounds rowBounds);

    List<Logs> selectByExample(LogsExample example);

    Logs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Logs record, @Param("example") LogsExample example);

    int updateByExample(@Param("record") Logs record, @Param("example") LogsExample example);

    int updateByPrimaryKeySelective(Logs record);

    int updateByPrimaryKey(Logs record);
}