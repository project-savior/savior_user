package com.jerry.savior_user.pojo.DTO;

import com.jerry.savior_common.constants.PatternConstants;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author 22454
 */
@Data
public class NameVerifyDTO {
    @NotBlank(message = "请填写真实姓名")
    @Length(min = 2, max = 20, message = "姓名长度应在 2-20 之间")
    private String realName;

    @NotBlank(message = "请填写身份证号码")
    @Pattern(regexp = PatternConstants.ID_CARD_REGEXP, message = "身份证号码格式错误")
    @Length(min = 18, max = 20, message = "身份证号码格式错误")
    private String idCard;

    @NotBlank(message = "请填写学号")
    @Length(min = 8, max = 8, message = "学号格式错误")
    private String studentId;

}
