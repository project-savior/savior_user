package com.jerry.savior_user.constants;

/**
 * @author 22454
 */
public interface UserConstants {
    enum UserStatusConstant {
        /**
         * 正常
         */
        NORMAL(1, "正常"),
        /**
         * 异常
         */
        AB_NORMAL(0, "异常");
        public final Integer code;
        public final String statusName;

        UserStatusConstant(Integer code, String statusName) {
            this.code = code;
            this.statusName = statusName;
        }
    }

    enum UserNameVerifiedConstants {
        /**
         * 已认证
         */
        HAS_VERIFIED(1, "已认证"),
        /**
         * 未认证
         */
        NOT_VERIFY(0, "未认证");
        public final Integer code;
        public final String statusName;

        UserNameVerifiedConstants(Integer code, String statusName) {
            this.code = code;
            this.statusName = statusName;
        }
    }

    enum UserGenderConstant {
        /**
         * 男
         */
        MALE(1, "男"),
        /**
         * 女
         */
        FEMALE(0,"女");

        ;
        public final Integer code;
        public final String genderName;


        UserGenderConstant(Integer code, String genderName) {
            this.code = code;
            this.genderName = genderName;
        }
    }

}
