package cn.cld.dao;

import cn.cld.pojo.userVo;

public interface userVoMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(userVo record);

    int insertSelective(userVo record);

    userVo selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(userVo record);

    int updateByPrimaryKey(userVo record);
}