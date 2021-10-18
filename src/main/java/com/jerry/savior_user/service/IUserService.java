package com.jerry.savior_user.service;

import com.jerry.savior_user.pojo.BO.UserBO;
import com.jerry.savior_user.pojo.DTO.NameVerifyDTO;
import com.jerry.savior_user.pojo.DTO.UpdateUserInfoDTO;

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

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    UserBO getUserInfoByUserId(Long userId);

    /**
     * 修改用户信息
     *
     * @param updateUserInfoDTO DTO
     * @param userId            用户ID
     */
    void updateUserInfo(UpdateUserInfoDTO updateUserInfoDTO, Long userId);

    /**
     * 实名认证
     *
     * @param nameVerifyDTO DTO
     * @param userId        用户ID
     */
    void nameVerify(NameVerifyDTO nameVerifyDTO, Long userId);
}
