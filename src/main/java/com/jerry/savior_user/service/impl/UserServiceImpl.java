package com.jerry.savior_user.service.impl;

import cn.hutool.core.date.DateUtil;
import com.jerry.redis.utils.RedisHelper;
import com.jerry.redis.utils.TaskResult;
import com.jerry.savior_common.constants.StandardResponse;
import com.jerry.savior_common.error.BusinessException;
import com.jerry.savior_common.util.ObjectMapperHelper;
import com.jerry.savior_user.constants.PermissionConstants;
import com.jerry.savior_user.constants.RedisConstants;
import com.jerry.savior_user.constants.UserConstants;
import com.jerry.savior_user.errors.EnumUserException;
import com.jerry.savior_user.mybatis.DO.UmsUserDO;
import com.jerry.savior_user.mybatis.mapper.UmsUserDOMapper;
import com.jerry.savior_user.pojo.BO.UserBO;
import com.jerry.savior_user.pojo.DTO.NameVerifyDTO;
import com.jerry.savior_user.pojo.DTO.UpdateUserInfoDTO;
import com.jerry.savior_user.service.IPermissionService;
import com.jerry.savior_user.service.IUserService;
import com.jerry.savior_user.utils.RedisKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 22454
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    private final UmsUserDOMapper userDOMapper;
    private final IPermissionService permissionService;
    private final RedisHelper redisHelper;
    private final ObjectMapperHelper objectMapperHelper;

    public UserServiceImpl(UmsUserDOMapper userDOMapper,
                           IPermissionService permissionService,
                           RedisHelper redisHelper,
                           ObjectMapperHelper objectMapperHelper) {
        this.userDOMapper = userDOMapper;
        this.permissionService = permissionService;
        this.redisHelper = redisHelper;
        this.objectMapperHelper = objectMapperHelper;
    }

    @Override
    public UserBO getUserInfoByPhone(String phone) {
        String userInfoCacheKeyForPhone = RedisKeyUtil.buildUserInfoCacheKey(RedisConstants.USER_INFO_CACHE_FOR_PHONE, phone);
        UserBO userInfo;
        // 查询缓存，查到后直接返回

        String userInfoCache = redisHelper.getRedisTemplate().opsForValue().get(userInfoCacheKeyForPhone);
        if (userInfoCache != null) {
            userInfo = objectMapperHelper.parseJson(userInfoCache, UserBO.class);
            redisHelper.getRedisTemplate().expire(userInfoCacheKeyForPhone, 24, TimeUnit.HOURS);
            return userInfo;
        }

        TaskResult<UserBO> taskResult = null;
        try {
            taskResult = redisHelper.lockTask(RedisConstants.LockKey.USER_INFO_LOCK_KEY, 2L, 3L, TimeUnit.SECONDS,
                    () -> {
                        UmsUserDO userSelective = new UmsUserDO();
                        userSelective.setPhone(phone);

                        // 可能已经注册，查一下数据库
                        UmsUserDO userDO = userDOMapper.selectBySelective(userSelective);
                        // 用户不存在，自动注册
                        if (userDO == null) {
                            userDO = UmsUserDO.newUser(phone);
                            userDOMapper.insertSelective(userDO);
                        }
                        Long userId = userDO.getId();

                        // 选择权限集合
                        Set<Long> permissionSet = permissionService.getPermissionIdsForUser(userId, PermissionConstants.PermissionStatusConstants.NORMAL);
                        UserBO newUser = UserBO.build(userDO);
                        newUser.setPermissionSet(permissionSet);

                        String userInfoCacheKeyForId = RedisKeyUtil.buildUserInfoCacheKey(RedisConstants.USER_INFO_CACHE_FOR_ID, String.valueOf(userId));
                        String userInfoJson = objectMapperHelper.toJson(newUser);
                        // 用户信息存入缓存
                        redisHelper.getRedisTemplate().opsForValue().set(userInfoCacheKeyForPhone, userInfoJson, 24, TimeUnit.HOURS);
                        redisHelper.getRedisTemplate().opsForValue().set(userInfoCacheKeyForId, userInfoJson, 24, TimeUnit.HOURS);
                        return newUser;
                    });
        } catch (InterruptedException e) {
            throw new BusinessException(StandardResponse.ERROR, null, "获取用户信息失败");
        }
        if (taskResult.getCompleted()) {
            return taskResult.getResult();
        }
        throw new BusinessException(StandardResponse.ERROR);
    }

    @Override
    public UserBO getUserInfoByUserId(Long userId) {
        String userInfoCacheKeyForId = RedisKeyUtil.buildUserInfoCacheKey(RedisConstants.USER_INFO_CACHE_FOR_ID, String.valueOf(userId));
        String userInfoJson = redisHelper.getRedisTemplate().opsForValue().get(userInfoCacheKeyForId);
        if (userInfoJson != null) {
            redisHelper.getRedisTemplate().expire(userInfoCacheKeyForId, 24, TimeUnit.HOURS);
            return objectMapperHelper.parseJson(userInfoJson, UserBO.class);

        }
        TaskResult<UserBO> taskResult;
        try {
            taskResult = redisHelper.lockTask(RedisConstants.LockKey.USER_INFO_LOCK_KEY, 2L, 3L, TimeUnit.SECONDS,
                    () -> {
                        UmsUserDO user = userDOMapper.selectByPrimaryKey(userId);

                        UserBO userInfo;
                        EnumUserException.USER_NOT_FOUND.assertNotNull(user);
                        userInfo = UserBO.build(user);
                        Set<Long> permissionSet = permissionService.getPermissionIdsForUser(userId, PermissionConstants.PermissionStatusConstants.NORMAL);
                        userInfo.setPermissionSet(permissionSet);

                        String userJson = objectMapperHelper.toJson(userInfo);
                        String userInfoCacheKeyForPhone = RedisKeyUtil.buildUserInfoCacheKey(RedisConstants.USER_INFO_CACHE_FOR_PHONE, userInfo.getPhone());
                        // 用户信息存入缓存
                        redisHelper.getRedisTemplate().opsForValue().set(userInfoCacheKeyForPhone, userJson, 24, TimeUnit.HOURS);
                        redisHelper.getRedisTemplate().opsForValue().set(userInfoCacheKeyForId, userJson, 24, TimeUnit.HOURS);

                        return userInfo;
                    });
        } catch (InterruptedException e) {
            throw new BusinessException(StandardResponse.ERROR, null, "获取用户信息失败");
        }
        if (taskResult.getCompleted()) {
            return taskResult.getResult();
        }
        throw new BusinessException(StandardResponse.ERROR);
    }


    @Override
    public void updateUserInfo(UpdateUserInfoDTO updateUserInfoDTO, Long userId) {
        UmsUserDO updateUser = new UmsUserDO();
        updateUser.setId(userId);
        String nickname = updateUserInfoDTO.getNickname();
        String email = updateUserInfoDTO.getEmail();
        String avatar = updateUserInfoDTO.getAvatar();
        Integer gender = updateUserInfoDTO.getGender();
        Date birthday = updateUserInfoDTO.getBirthday();
        String city = updateUserInfoDTO.getCity();
        String motto = updateUserInfoDTO.getMotto();
        if (Objects.nonNull(nickname)) {
            updateUser.setNickname(nickname);
        }
        if (Objects.nonNull(email)) {
            updateUser.setEmail(email);
        }
        if (Objects.nonNull(avatar)) {
            updateUser.setAvatar(avatar);
        }
        if (Objects.nonNull(gender)) {
            updateUser.setGender(gender);
        }
        if (Objects.nonNull(birthday)) {
            updateUser.setBirthday(birthday);
        }
        if (Objects.nonNull(city)) {
            updateUser.setCity(city);
        }
        if (Objects.nonNull(motto)) {
            updateUser.setMotto(motto);
        }
        Date now = DateUtil.date();
        updateUser.setUpdateTime(now);
        userDOMapper.updateByPrimaryKeySelective(updateUser);
        UmsUserDO userDO = userDOMapper.selectByPrimaryKey(userId);
        // 更新完数据库，将缓存失效掉
        String userInfoCacheKeyForPhone = RedisKeyUtil.buildUserInfoCacheKey(RedisConstants.USER_INFO_CACHE_FOR_PHONE, userDO.getPhone());
        String userInfoCacheKeyForId = RedisKeyUtil.buildUserInfoCacheKey(RedisConstants.USER_INFO_CACHE_FOR_ID, String.valueOf(userId));
        redisHelper.getRedisTemplate().delete(userInfoCacheKeyForPhone);
        redisHelper.getRedisTemplate().delete(userInfoCacheKeyForId);
    }

    @Override
    public void nameVerify(NameVerifyDTO nameVerifyDTO, Long userId) {
        // 查询缓存，试探实名认证情况
        UserBO userInfoByUserId = getUserInfoByUserId(userId);
        String hasVerifyCode = UserConstants.UserNameVerifiedConstants.HAS_VERIFIED.statusName;
        if (userInfoByUserId != null && hasVerifyCode.equals(userInfoByUserId.getNameVerified())) {
            throw new BusinessException(EnumUserException.HAS_VERIFIED);
        }


        try {
            redisHelper.lockTask(RedisKeyUtil.buildNameVerifyLockKey(userId), 2L, 3L,
                    TimeUnit.SECONDS, () -> {
                        // 上锁后查询数据库，确认实名认证情况
                        UmsUserDO userDO = userDOMapper.selectByPrimaryKey(userId);
                        Integer hasVerify = UserConstants.UserNameVerifiedConstants.HAS_VERIFIED.code;
                        // 已认证，抛出异常
                        if (hasVerify.equals(userDO.getNameVerified())) {
                            throw new BusinessException(EnumUserException.HAS_VERIFIED);
                        }
                        UmsUserDO updateUser = new UmsUserDO();
                        updateUser.setId(userId);
                        String realName = nameVerifyDTO.getRealName();
                        String idCard = nameVerifyDTO.getIdCard();
                        String studentId = nameVerifyDTO.getStudentId();
                        updateUser.setRealName(realName);
                        updateUser.setIdCard(idCard);
                        updateUser.setStudentId(studentId);
                        updateUser.setNameVerified(UserConstants.UserNameVerifiedConstants.HAS_VERIFIED.code);
                        updateUser.setUpdateTime(DateUtil.date());
                        userDOMapper.updateByPrimaryKeySelective(updateUser);
                        String phone = userDO.getPhone();
                        // 更新完数据库，将缓存失效掉
                        String userInfoCacheKeyForPhone = RedisKeyUtil.buildUserInfoCacheKey(RedisConstants.USER_INFO_CACHE_FOR_PHONE, phone);
                        String userInfoCacheKeyForId = RedisKeyUtil.buildUserInfoCacheKey(RedisConstants.USER_INFO_CACHE_FOR_ID, String.valueOf(userId));
                        redisHelper.getRedisTemplate().delete(userInfoCacheKeyForPhone);
                        redisHelper.getRedisTemplate().delete(userInfoCacheKeyForId);
                        return null;
                    });
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception e) {
            throw new BusinessException(StandardResponse.ERROR, null, "实名认证失败");
        }
    }
}
