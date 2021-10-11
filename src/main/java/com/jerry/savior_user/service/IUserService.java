package com.jerry.savior_user.service;

import com.jerry.savior_user.pojo.BO.UserBO;

/**
 * @author 22454
 */
public interface IUserService {
    /**
     * 根据手机号获取用户信息
     *
     * @param phone 手机号
     * @return 用户信息
     */
    UserBO getUserInfoByPhone(String phone);
}
