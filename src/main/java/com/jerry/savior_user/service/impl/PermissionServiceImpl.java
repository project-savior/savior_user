package com.jerry.savior_user.service.impl;

import com.jerry.redis.utils.RedisHelper;
import com.jerry.redis.utils.TaskResult;
import com.jerry.savior_common.constants.StandardResponse;
import com.jerry.savior_common.error.BusinessException;
import com.jerry.savior_common.util.ObjectMapperHelper;
import com.jerry.savior_user.constants.EnumTypeReference;
import com.jerry.savior_user.constants.RedisConstants;
import com.jerry.savior_user.mybatis.DO.UmsUserPermissionRelationDO;
import com.jerry.savior_user.mybatis.mapper.UmsPermissionDOMapper;
import com.jerry.savior_user.mybatis.mapper.UmsUserPermissionRelationDOMapper;
import com.jerry.savior_user.service.IPermissionService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 22454
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    private final UmsPermissionDOMapper permissionDOMapper;
    private final UmsUserPermissionRelationDOMapper userPermissionRelationDOMapper;
    private final RedisHelper redisHelper;
    private final ObjectMapperHelper objectMapperHelper;

    public PermissionServiceImpl(UmsPermissionDOMapper permissionDOMapper,
                                 UmsUserPermissionRelationDOMapper userPermissionRelationDOMapper,
                                 RedisHelper redisHelper,
                                 ObjectMapperHelper objectMapperHelper) {
        this.permissionDOMapper = permissionDOMapper;
        this.userPermissionRelationDOMapper = userPermissionRelationDOMapper;
        this.redisHelper = redisHelper;
        this.objectMapperHelper = objectMapperHelper;
    }

    @Override
    public Set<Long> getPermissionIdsForUser(Long userId, Integer status) {
        Set<Long> permissions = new HashSet<>();
        String userHashKey = String.format("u_%d", userId);


        String json = (String) redisHelper
                .getRedisTemplate()
                .opsForHash()
                .get(RedisConstants.PERMISSION_SET_BIG_KEY, userHashKey);
        if (json == null) {
            String lockKey = String.format("%s-%s", RedisConstants.LockKey.PERMISSION_SET_LOCK_KEY, userId);
            TaskResult<Set<Long>> taskResult = null;
            try {
                taskResult = redisHelper.lockTask(lockKey, 5L, 6L,
                        TimeUnit.SECONDS, () -> {
                            Set<Long> permissionSet = new HashSet<>();
                            UmsUserPermissionRelationDO userPermissionSelective = new UmsUserPermissionRelationDO();
                            userPermissionSelective.setUserId(userId);
                            userPermissionSelective.setStatus(status);
                            // 选出所有的符合条件的关系
                            List<UmsUserPermissionRelationDO> relations =
                                    userPermissionRelationDOMapper.selectBySelective(userPermissionSelective);
                            // 映射成 set
                            relations.forEach(relation -> permissionSet.add(relation.getPermissionId()));
                            redisHelper.getRedisTemplate()
                                    .opsForHash()
                                    .put(RedisConstants.PERMISSION_SET_BIG_KEY, userHashKey, objectMapperHelper.toJson(permissionSet));
                            return permissionSet;
                        });
            } catch (InterruptedException e) {
                throw new BusinessException(StandardResponse.ERROR, null, "获取用户权限失败");
            }
            if (taskResult.getCompleted()) {
                permissions = taskResult.getResult();
            }
        } else {
            permissions = objectMapperHelper.parseJson(json, EnumTypeReference.LONG_SET_TYPE_REFERENCE);
        }
        return permissions;
    }
}
