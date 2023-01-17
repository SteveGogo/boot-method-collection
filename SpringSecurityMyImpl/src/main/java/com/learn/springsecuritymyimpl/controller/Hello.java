package com.learn.springsecuritymyimpl.controller;

import com.learn.springsecuritymyimpl.common.R;
import com.learn.springsecuritymyimpl.common.Result;
import com.learn.springsecuritymyimpl.service.SysUserService;
import com.learn.springsecuritymyimpl.utils.JwtTokenUtilUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Hello {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/hello")
    public R hello() {
        String token = JwtTokenUtilUtil.createToken("1", "admin", null);

        return Result.Ok(token);
    }

    @RequestMapping("/hello2")
    public R hello2() {
        String str = "hello";
        return Result.Ok("", str);
    }

    @GetMapping("/deleteUser")
    public R deleteUser() {
        return Result.Ok();
    }

    @GetMapping("/test")
    public R test() {
        Map<String, String> map = new HashMap<>();
        map.put("hell", "wolrd");
        return Result.Ok(map);
    }


}
