package com.jerry.savior_user.mybatis.mapper;

import com.jerry.savior_user.mybatis.DO.UmsApiDO;

public interface UmsApiDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsApiDO record);

    int insertSelective(UmsApiDO record);

    UmsApiDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsApiDO record);

    int updateByPrimaryKey(UmsApiDO record);
}