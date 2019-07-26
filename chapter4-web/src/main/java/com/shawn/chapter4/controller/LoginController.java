package com.shawn.chapter4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping(value = "/login")
    public String login(HttpSession httpSession
            , @RequestParam("username") String username
            , @RequestParam("password") String password
            , Map<String,Object> map){
        // 验证登陆
        if(StringUtils.isEmpty(username) || !"123456".equals(password)){
            map.put("msg","登陆失败！！");
            return "/index";
        }

        //注册session
        httpSession.setAttribute("username",username);
        return "redirect:/dashboard";
    }
}
