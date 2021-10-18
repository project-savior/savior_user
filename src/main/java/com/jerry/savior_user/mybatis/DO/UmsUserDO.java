package com.jerry.savior_user.mybatis.DO;

import cn.hutool.core.date.DateUtil;
import com.jerry.savior_user.constants.UserConstants;
import lombok.Data;

import java.util.Date;

@Data
public class UmsUserDO {
    private Long id;

    private String password;

    private String nickname;

    private String phone;

    private Integer status;

    private Integer nameVerified;

    private String email;

    private String realName;

    private String idCard;

    private String studentId;

    private String avatar;

    private Integer gender;

    private Date birthday;

    private String city;

    private String motto;

    private Integer exp;

    private Date createTime;

    private Date updateTime;


    public static UmsUserDO newUser(String phone){
        UmsUserDO userDO=new UmsUserDO();
        userDO.setId(null);
        userDO.setPassword(null);
        userDO.setNickname(String.format("u_%s", phone));
        userDO.setPhone(phone);
        userDO.setStatus(UserConstants.UserStatusConstant.NORMAL.code);
        userDO.setNameVerified(UserConstants.UserNameVerifiedConstants.NOT_VERIFY.code);
        userDO.setEmail(null);
        userDO.setRealName(null);
        userDO.setIdCard(null);
        userDO.setStudentId(null);
        userDO.setAvatar(null);
        userDO.setGender(UserConstants.UserGenderConstant.MALE.code);
        userDO.setBirthday(null);
        userDO.setCity(null);
        userDO.setMotto(null);
        userDO.setExp(0);
        userDO.setCreateTime(DateUtil.date());
        userDO.setUpdateTime(null);
        return userDO;
    }
}