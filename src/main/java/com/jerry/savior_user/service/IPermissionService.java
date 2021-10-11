package com.jerry.savior_user.service;

import java.util.Set;

/**
 * @author 22454
 */
public interface IPermissionService {

    /**
     * 根据用户ID获取他的权限集合
     *
     * @param userId 用户ID
     * @param status 权限状态
     * @return 权限集合
     */
    Set<Long> getPermissionIdsForUser(Long userId, Integer status);
}
