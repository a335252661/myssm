package cn.cld.dao;

import cn.cld.pojo.LogsDetail;
import cn.cld.pojo.LogsDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LogsDetailMapper {
    int countByExample(LogsDetailExample example);

    int deleteByExample(LogsDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LogsDetail record);

    int insertSelective(LogsDetail record);

    List<LogsDetail> selectByExampleWithRowbounds(LogsDetailExample example, RowBounds rowBounds);

    List<LogsDetail> selectByExample(LogsDetailExample example);

    LogsDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LogsDetail record, @Param("example") LogsDetailExample example);

    int updateByExample(@Param("record") LogsDetail record, @Param("example") LogsDetailExample example);

    int updateByPrimaryKeySelective(LogsDetail record);

    int updateByPrimaryKey(LogsDetail record);
}