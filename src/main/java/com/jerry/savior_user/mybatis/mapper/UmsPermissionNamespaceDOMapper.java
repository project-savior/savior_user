package com.jerry.savior_user.mybatis.mapper;

import com.jerry.savior_user.mybatis.DO.UmsPermissionNamespaceDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UmsPermissionNamespaceDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsPermissionNamespaceDO record);

    int insertSelective(UmsPermissionNamespaceDO record);

    UmsPermissionNamespaceDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsPermissionNamespaceDO record);

    int updateByPrimaryKey(UmsPermissionNamespaceDO record);
}