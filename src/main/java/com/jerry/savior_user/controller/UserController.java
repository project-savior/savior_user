package com.jerry.savior_user.controller;

import com.jerry.savior_common.constants.PatternConstants;
import com.jerry.savior_common.util.TokenHelper;
import com.jerry.savior_user.pojo.BO.UserBO;
import com.jerry.savior_user.pojo.DTO.NameVerifyDTO;
import com.jerry.savior_user.pojo.DTO.UpdateUserInfoDTO;
import com.jerry.savior_user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * @author 22454
 */
@Api(value = "/user", description = "用户控制类")
@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    private final TokenHelper tokenHelper;
    private final IUserService userService;

    public UserController(TokenHelper tokenHelper, IUserService userService) {
        this.tokenHelper = tokenHelper;
        this.userService = userService;
    }

    @ApiOperation(value = "根据手机号获取用户信息")
    @GetMapping("/info-by-phone")
    public UserBO getUserInfoByPhone(@ApiParam("手机号") @RequestParam("phone")
                                     @Pattern(regexp = PatternConstants.TELEPHONE_REGEXP, message = "手机号码格式错误")
                                             String phone) {
        return userService.getUserInfoByPhone(phone);
    }

    @ApiOperation("根据用户ID获取用户信息")
    @GetMapping("/info-by-id")
    public UserBO getUserInfoById(@ApiParam("用户ID") @RequestParam(value = "userId", required = false)
                                  @Min(value = 0, message = "用户ID不合法")
                                          Long userId,
                                  HttpServletRequest request) {
        String token = tokenHelper.getTokenFromRequest(request);
        if (Objects.isNull(userId)) {
            userId = Long.valueOf(tokenHelper.getSubject(token));
        }
        return userService.getUserInfoByUserId(userId);
    }

    @ApiOperation("修改用户资料")
    @PutMapping("/update-info")
    public void updateInfo(@RequestBody @Valid UpdateUserInfoDTO updateUserInfoDTO,
                           HttpServletRequest request) {
        String token = tokenHelper.getTokenFromRequest(request);
        Long userId = Long.valueOf(tokenHelper.getSubject(token));
        userService.updateUserInfo(updateUserInfoDTO, userId);
    }

    @ApiOperation("实名认证")
    @PostMapping("/name-verify")
    public void nameVerify(@RequestBody @Valid NameVerifyDTO nameVerifyDTO,
                           HttpServletRequest request) {
        String token = tokenHelper.getTokenFromRequest(request);
        Long userId = Long.valueOf(tokenHelper.getSubject(token));
        userService.nameVerify(nameVerifyDTO, userId);

    }


}
