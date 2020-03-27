package com.itaem.crazy.shirodemo.modules.shiro.controller;


import com.itaem.crazy.shirodemo.common.utils.TokenUtil;
import com.itaem.crazy.shirodemo.modules.shiro.DTO.LoginDTO;
import com.itaem.crazy.shirodemo.modules.shiro.entity.User;
import com.itaem.crazy.shirodemo.modules.shiro.service.ShiroService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author 大誌
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
    @PostMapping("/sys/login")
    public Map<String, Object> login(@RequestBody LoginDTO loginDTO, HttpSession session) {

        Map<String, Object> result = new HashMap<>();
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        //用户信息
        User user = shiroService.findByUsername(username);
        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(password)) {
            result.put("status", 400);
            result.put("msg", "账号或密码有误");
            return result;
        } else {
            //生成token，并保存到数据库
            result = shiroService.createToken(user.getUserId());
            result.put("status", 200);
            result.put("msg", "登陆成功");
            return result;
        }
    }

    /**
     * 退出
     */
    @ApiOperation(value = "登出", notes = "参数:token")
    @PostMapping("/sys/logout")
    public Map<String, Object> logout(HttpServletRequest httpServletRequest) {
        Map<String, Object> result = new HashMap<>();
        String token = TokenUtil.getRequestToken(httpServletRequest);
        shiroService.logout(token);
        result.put("status", "200");
        result.put("msg", "您已安全退出系统");
        return result;
    }
}


