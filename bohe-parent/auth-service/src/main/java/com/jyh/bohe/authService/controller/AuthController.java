package com.jyh.bohe.authService.controller;

import com.alibaba.fastjson.JSON;
import com.jyh.bohe.authService.dao.UserMapper;
import com.jyh.bohe.authService.pojo.User;
import com.jyh.bohe.authService.util.JwtTokenUtil;
import com.jyh.bohe.boheapi.bean.AjaxResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@Controller
public class AuthController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/index")
    public String index() {
        return "Hello";
    }

    @GetMapping("/getUser")
    public String getUser(@RequestHeader(value = "Authorization") String tokenHeader) {
        AjaxResponseBody result = new AjaxResponseBody();
        String authHeader = tokenHeader;
        String tokenHead = "token";
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            User user = userMapper.get(username);
            result.setMsg("200");
            result.setMsg("ok");
            result.setResult(user);
        } else {
            result.setStatus("402");
            result.setMsg("未登录");
            return JSON.toJSONString(result);
        }
        return JSON.toJSONString(result);
    }
}

