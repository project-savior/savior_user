package com.jerry.savior_user.mybatis.mapper;

import com.jerry.savior_user.mybatis.DO.UmsPermissionNamespaceDO;

public interface UmsPermissionNamespaceDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsPermissionNamespaceDO record);

    int insertSelective(UmsPermissionNamespaceDO record);

    UmsPermissionNamespaceDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsPermissionNamespaceDO record);

    int updateByPrimaryKey(UmsPermissionNamespaceDO record);
}