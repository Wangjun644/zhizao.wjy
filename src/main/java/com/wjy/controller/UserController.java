package com.wjy.controller;

import com.wjy.domain.User;
import com.wjy.dto.UserDTO;
import com.wjy.mapper.ProductMapper;
import com.wjy.service.IUserService;
import com.wjy.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    //
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public R login(@RequestBody User user, HttpSession session) {
        try {
            User dbUser = userService.login(user);
            //
            session.setAttribute("user", dbUser);
            return R.success("登陆成功", dbUser.getUserRealName());
        }catch (Exception e){
            //
            String message = e.getMessage();
            return R.fail(message);
        }
    }

    @PostMapping("/register")
    public R register(@RequestBody UserDTO userDTO) {
        try {
            userService.register(userDTO);
            return R.success("注册成功");
        }catch (Exception e) {
            //
            String message = e.getMessage();
            return R.fail(message);
        }
    }
}
