package com.jerry.savior_user.mybatis.mapper;

import com.jerry.savior_user.mybatis.DO.UmsThirdPlatformDO;

public interface UmsThirdPlatformDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsThirdPlatformDO record);

    int insertSelective(UmsThirdPlatformDO record);

    UmsThirdPlatformDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsThirdPlatformDO record);

    int updateByPrimaryKey(UmsThirdPlatformDO record);
}