package com.jerry.savior_user.mybatis.DO;

import cn.hutool.core.date.DateUtil;
import com.jerry.savior_user.constants.UserConstants;

import java.util.Date;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNameVerified() {
        return nameVerified;
    }

    public void setNameVerified(Integer nameVerified) {
        this.nameVerified = nameVerified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto == null ? null : motto.trim();
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public static UmsUserDO newUser(String phone){
        UmsUserDO userDO=new UmsUserDO();
        userDO.setId(null);
        userDO.setPassword(null);
        userDO.setNickname(String.format("u_%s", phone));
        userDO.setPhone(phone);
        userDO.setStatus(UserConstants.UserStatusConstant.NORMAL);
        userDO.setNameVerified(UserConstants.UserNameVerifiedConstants.NOT_VERIFY);
        userDO.setEmail(null);
        userDO.setRealName(null);
        userDO.setIdCard(null);
        userDO.setStudentId(null);
        userDO.setAvatar(null);
        userDO.setGender(UserConstants.UserGenderConstant.MALE);
        userDO.setBirthday(null);
        userDO.setCity(null);
        userDO.setMotto(null);
        userDO.setExp(0);
        userDO.setCreateTime(DateUtil.date());
        userDO.setUpdateTime(null);
        return userDO;
    }
}