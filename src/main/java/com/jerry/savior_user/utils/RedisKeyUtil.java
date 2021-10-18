package com.jerry.savior_user.utils;

import com.jerry.savior_user.constants.RedisConstants;

/**
 * @author 22454
 */
public class RedisKeyUtil {
    public static String buildUserInfoCacheKey(String prefix, String keyWord) {
        return String.format("%s-%s", prefix, keyWord);
    }

    public static String buildNameVerifyLockKey(Long userId) {
        return String.format("%s-%s", RedisConstants.LockKey.NAME_VERIFY_LOCK_KEY, userId);
    }
}
