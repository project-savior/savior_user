package com.jerry.savior_user.mybatis.mapper;

import com.jerry.savior_user.mybatis.DO.UmsPermissionDO;

public interface UmsPermissionDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsPermissionDO record);

    int insertSelective(UmsPermissionDO record);

    UmsPermissionDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsPermissionDO record);

    int updateByPrimaryKey(UmsPermissionDO record);
}