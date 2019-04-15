package com.itaem.crazy.shirodemo.modules.shiro.controller;


import com.itaem.crazy.shirodemo.modules.shiro.entity.User;
import com.itaem.crazy.shirodemo.modules.shiro.service.ShiroService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author CrazyJay
 * @Date 2019/3/30 22:04
 * @Version 1.0
 */
@RestController
public class ShiroController {

    @Autowired
    private ShiroService shiroService;


    /**
     * 登录
     */
    @ApiOperation(value = "登陆", notes = "参数:用户名 密码")
    @GetMapping("/sys/login")
    public Map<String, Object> login(String username, String password)  {
        Map<String, Object> result = new HashMap<>();
        //用户信息
        User user = shiroService.findByUsername(username);
        //账号不存在、密码错误
        if (user==null||!user.getPassword().equals(password)) {
            result.put("status", "400");
            result.put("msg", "账号或密码有误");
            return result;
        } else {
            //生成token，并保存到数据库

            result = shiroService.createToken(user.getUserId());
            result.put("status", "200");
            result.put("msg", "登陆成功");
            return result;
        }
    }

    /**
     * 退出
     */
    @PostMapping("/sys/logout")
    public Map<String, Object> logout() {
        Map<String, Object> result = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        shiroService.logout(user.getUserId());
        result.put("status", "200");
        result.put("msg", "登陆成功");
        return result;
    }
}


