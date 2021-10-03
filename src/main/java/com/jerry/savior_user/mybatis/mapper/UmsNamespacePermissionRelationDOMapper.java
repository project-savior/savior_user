package com.jerry.savior_user.mybatis.mapper;

import com.jerry.savior_user.mybatis.DO.UmsNamespacePermissionRelationDO;

public interface UmsNamespacePermissionRelationDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsNamespacePermissionRelationDO record);

    int insertSelective(UmsNamespacePermissionRelationDO record);

    UmsNamespacePermissionRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsNamespacePermissionRelationDO record);

    int updateByPrimaryKey(UmsNamespacePermissionRelationDO record);
}