package com.jerry.savior_user.mybatis.mapper;

import com.jerry.savior_user.mybatis.DO.UmsComponentDO;

public interface UmsComponentDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsComponentDO record);

    int insertSelective(UmsComponentDO record);

    UmsComponentDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsComponentDO record);

    int updateByPrimaryKey(UmsComponentDO record);
}