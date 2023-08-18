package com.ximao.infinitelyflu.controller;


import com.ximao.infinitelyflu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 用户数据服务控制类
 * @author ximao
 * @date 2021/7/7
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private IUserService userService;

}
