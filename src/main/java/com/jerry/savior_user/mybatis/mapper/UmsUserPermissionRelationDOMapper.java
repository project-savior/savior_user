package com.jerry.savior_user.mybatis.mapper;

import com.jerry.savior_user.mybatis.DO.UmsUserPermissionRelationDO;

public interface UmsUserPermissionRelationDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsUserPermissionRelationDO record);

    int insertSelective(UmsUserPermissionRelationDO record);

    UmsUserPermissionRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsUserPermissionRelationDO record);

    int updateByPrimaryKey(UmsUserPermissionRelationDO record);
}