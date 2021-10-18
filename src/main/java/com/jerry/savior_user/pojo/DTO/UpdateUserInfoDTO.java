package com.jerry.savior_user.pojo.DTO;

import com.jerry.savior_common.constants.PatternConstants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author 22454
 */
@Data
public class UpdateUserInfoDTO {
    @ApiModelProperty("昵称")
    private String nickname;
    @Pattern(regexp = PatternConstants.EMAIL_REGEXP,
            message = "邮箱格式错误")
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("头像")
    private String avatar;
    @Pattern(regexp = "[01]", message = "性别错误")
    @ApiModelProperty("性别(0->女，1->男)")
    private Integer gender;
    @ApiModelProperty("生日")
    private Date birthday;
    @ApiModelProperty("城市")
    private String city;
    @ApiModelProperty("个性签名")
    private String motto;
}
