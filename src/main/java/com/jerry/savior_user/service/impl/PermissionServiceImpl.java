package com.jerry.savior_user.service.impl;

import com.jerry.savior_user.constants.PermissionConstants;
import com.jerry.savior_user.mybatis.DO.UmsUserPermissionRelationDO;
import com.jerry.savior_user.mybatis.mapper.UmsPermissionDOMapper;
import com.jerry.savior_user.mybatis.mapper.UmsUserPermissionRelationDOMapper;
import com.jerry.savior_user.service.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 22454
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    private final UmsPermissionDOMapper permissionDOMapper;
    private final UmsUserPermissionRelationDOMapper userPermissionRelationDOMapper;

    public PermissionServiceImpl(UmsPermissionDOMapper permissionDOMapper,
                                 UmsUserPermissionRelationDOMapper userPermissionRelationDOMapper) {
        this.permissionDOMapper = permissionDOMapper;
        this.userPermissionRelationDOMapper = userPermissionRelationDOMapper;
    }

    @Override
    public Set<Long> getPermissionIdsForUser(Long userId, Integer status) {
        UmsUserPermissionRelationDO userPermissionSelective = new UmsUserPermissionRelationDO();
        userPermissionSelective.setUserId(userId);
        userPermissionSelective.setStatus(status);
        // 选出所有的符合条件的关系
        List<UmsUserPermissionRelationDO> relations =
                userPermissionRelationDOMapper.selectBySelective(userPermissionSelective);
        Set<Long> permissions = new HashSet<>();
        // 映射成 set
        relations.forEach(relation -> permissions.add(relation.getPermissionId()));
        return permissions;
    }
}
