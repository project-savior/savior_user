package com.jerry.savior_user.controller;

import com.jerry.savior_common.constants.PatternConstants;
import com.jerry.savior_user.pojo.BO.UserBO;
import com.jerry.savior_user.service.IUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

/**
 * @author 22454
 */
@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info-by-phone")
    public UserBO getUserInfoByPhone(@RequestParam("phone")
                                     @Pattern(regexp = PatternConstants.TELEPHONE_REGEXP, message = "手机号码格式错误")
                                             String phone) {
        return userService.getUserInfoByPhone(phone);
    }


}
