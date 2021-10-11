package com.jerry.savior_user.constants;

/**
 * @author 22454
 */
public interface UserConstants {
    interface UserStatusConstant {
        /**
         * 正常
         */
        Integer NORMAL = 1;
        /**
         * 不正常
         */
        Integer AB_NORMAL = 0;
    }

    interface UserNameVerifiedConstants {
        /**
         * 已认证
         */
        Integer HAS_VERIFIED = 1;
        /**
         * 未认证
         */
        Integer NOT_VERIFY = 0;
    }

    interface UserGenderConstant {
        /**
         * 男
         */
        Integer MALE = 1;
        /**
         * 女
         */
        Integer FEMALE = 0;
    }

}
