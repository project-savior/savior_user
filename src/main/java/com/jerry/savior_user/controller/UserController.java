package com.jerry.savior_user.controller;

import com.jerry.savior_common.util.ObjectMapperHelper;
import com.jerry.savior_user.pojo.VO.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 22454
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final ObjectMapperHelper objectMapperHelper;

    public UserController(ObjectMapperHelper objectMapperHelper) {
        this.objectMapperHelper = objectMapperHelper;
    }

    @GetMapping("/getUserInfoByPhone")
    public UserVO getUserInfoByPhone(@RequestParam("phone") String phone) {
        UserVO userVO = new UserVO();
        userVO.setName("yzl");
        return userVO;
    }


}
