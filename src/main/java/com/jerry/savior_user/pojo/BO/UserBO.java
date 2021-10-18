package com.jerry.savior_user.pojo.BO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jerry.savior_common.annotations.Desensitization;
import com.jerry.savior_user.constants.UserConstants;
import com.jerry.savior_user.mybatis.DO.UmsUserDO;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author 22454
 */
@Data
public class UserBO {
    private Long id;

    private String nickname;

    @Desensitization(strategy = Desensitization.Strategy.START_ON_LEFT,
            length = 5,
            offset = 3)
    private String phone;

    private String status;

    private String nameVerified;

    @Desensitization(strategy = Desensitization.Strategy.START_ON_LEFT,
            length = 4,
            offset = 3)
    private String email;

    @Desensitization(strategy = Desensitization.Strategy.START_ON_LEFT,
            length = 1,
            offset = 1)
    private String realName;

    private String studentId;

    private String avatar;

    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String city;

    private String motto;

    private Integer exp;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private Set<Long> permissionSet;

    public static UserBO build(UmsUserDO userDO) {
        UserBO userBO = new UserBO();
        userBO.setId(userDO.getId());
        userBO.setNickname(userDO.getNickname());
        userBO.setPhone(userDO.getPhone());
        UserConstants.UserStatusConstant normal = UserConstants.UserStatusConstant.NORMAL;
        userBO.setStatus(normal.code.equals(userDO.getStatus()) ? normal.statusName : UserConstants.UserStatusConstant.AB_NORMAL.statusName);
        UserConstants.UserNameVerifiedConstants hasVerified = UserConstants.UserNameVerifiedConstants.HAS_VERIFIED;
        userBO.setNameVerified(hasVerified.code.equals(userDO.getNameVerified()) ? hasVerified.statusName : UserConstants.UserNameVerifiedConstants.NOT_VERIFY.statusName);
        userBO.setEmail(userDO.getEmail());
        userBO.setRealName(userDO.getRealName());
        userBO.setStudentId(userDO.getStudentId());
        userBO.setAvatar(userDO.getAvatar());
        UserConstants.UserGenderConstant male = UserConstants.UserGenderConstant.MALE;
        userBO.setGender(male.code.equals(userDO.getGender()) ? male.genderName : UserConstants.UserGenderConstant.FEMALE.genderName);
        userBO.setBirthday(userDO.getBirthday());
        userBO.setCity(userDO.getCity());
        userBO.setMotto(userDO.getMotto());
        userBO.setExp(userDO.getExp());
        userBO.setCreateTime(userDO.getCreateTime());
        return userBO;
    }
}
