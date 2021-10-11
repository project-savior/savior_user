package com.jerry.savior_user.mybatis.mapper;

import com.jerry.savior_user.mybatis.DO.UmsUserPlatformRelationDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UmsUserPlatformRelationDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsUserPlatformRelationDO record);

    int insertSelective(UmsUserPlatformRelationDO record);

    UmsUserPlatformRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsUserPlatformRelationDO record);

    int updateByPrimaryKey(UmsUserPlatformRelationDO record);
}