package com.test.aliyun_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @PostMapping("/user/authentication")
    public String authentication(@RequestParam("username")String username,
                        @RequestParam("password")String password){

        return "redirect:/file/upload";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
