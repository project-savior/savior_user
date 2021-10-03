package com.jerry.savior_user.pojo.BO;

import lombok.Data;

import java.util.Set;

/**
 * @author 22454
 */
@Data
public class UserBO {
    private Long id;

    private String password;

    private String phone;

    private Integer status;

    private Integer nameVerified;

    private Set<String> authorities;
}
