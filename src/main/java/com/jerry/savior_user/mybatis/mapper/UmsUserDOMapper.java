package com.jerry.savior_user.mybatis.mapper;

import com.jerry.savior_user.mybatis.DO.UmsUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UmsUserDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsUserDO record);

    int insertSelective(UmsUserDO record);

    UmsUserDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsUserDO record);

    int updateByPrimaryKey(UmsUserDO record);

    UmsUserDO selectBySelective(@Param("selective") UmsUserDO selective);
}