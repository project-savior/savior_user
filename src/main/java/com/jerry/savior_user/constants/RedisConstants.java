package com.jerry.savior_user.constants;

/**
 * @author 22454
 */
public interface RedisConstants {
    /**
     * 权限集合 hash key
     */
    String PERMISSION_SET_BIG_KEY = "Permission-Set";


    String USER_INFO_CACHE_FOR_ID = "User-Info-Cache-For-Id";
    String USER_INFO_CACHE_FOR_PHONE = "User-Info-Cache-For-Phone";

    interface LockKey {
        /**
         * 权限集合锁
         */
        String PERMISSION_SET_LOCK_KEY = "Permission-Set-Lock";
        /**
         * 用户信息锁
         */
        String USER_INFO_LOCK_KEY = "User-Info-Lock";
        /**
         *
         */
        String NAME_VERIFY_LOCK_KEY="Name-Verify-Lock";
    }
}
