package com.jerry.savior_user.service.impl;

import com.jerry.savior_user.constants.PermissionConstants;
import com.jerry.savior_user.mybatis.DO.UmsUserDO;
import com.jerry.savior_user.mybatis.mapper.UmsUserDOMapper;
import com.jerry.savior_user.pojo.BO.UserBO;

import com.jerry.savior_user.service.IPermissionService;
import com.jerry.savior_user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author 22454
 */
@Service
public class UserServiceImpl implements IUserService {
    private final UmsUserDOMapper userDOMapper;
    private final IPermissionService permissionService;

    public UserServiceImpl(UmsUserDOMapper userDOMapper,
                           IPermissionService permissionService) {
        this.userDOMapper = userDOMapper;
        this.permissionService = permissionService;
    }

    @Override
    public UserBO getUserInfoByPhone(String phone) {
        UmsUserDO userSelective = new UmsUserDO();
        userSelective.setPhone(phone);
        UmsUserDO userDO = userDOMapper.selectBySelective(userSelective);
        // 用户不存在，自动注册
        if (userDO == null) {
            userDO = UmsUserDO.newUser(phone);
            userDOMapper.insertSelective(userDO);
            //TODO mq推送
        }
        Long userId = userDO.getId();

        // 选择权限集合
        Set<Long> permissionSet = permissionService.getPermissionIdsForUser(userId, PermissionConstants.PermissionStatusConstants.NORMAL);
        UserBO userInfo = new UserBO();
        BeanUtils.copyProperties(userDO, userInfo);
        userInfo.setPermissionSet(permissionSet);
        return userInfo;
    }


}
