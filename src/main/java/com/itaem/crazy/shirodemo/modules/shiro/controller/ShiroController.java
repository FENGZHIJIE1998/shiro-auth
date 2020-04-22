package com.itaem.crazy.shirodemo.modules.shiro.controller;


import com.itaem.crazy.shirodemo.common.utils.TokenUtil;
import com.itaem.crazy.shirodemo.modules.shiro.dto.LoginDTO;
import com.itaem.crazy.shirodemo.modules.shiro.entity.User;
import com.itaem.crazy.shirodemo.modules.shiro.service.ShiroService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author 大誌
 * @Date 2019/3/30 22:04
 * @Version 1.0
 */
@RestController
public class ShiroController {

    private final ShiroService shiroService;

    public ShiroController(ShiroService shiroService) {
        this.shiroService = shiroService;
    }


    /**
     * 登录
     */
    @ApiOperation(value = "登陆", notes = "参数:用户名 密码")
    @PostMapping("/sys/login")
    public Map<String, Object> login(@RequestBody LoginDTO loginDTO) {

        Map<String, Object> result = new HashMap<>();
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        //用户信息
        User user = shiroService.findByUsername(username);
        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(password)) {
            result.put("status", 400);
            result.put("msg", "账号或密码有误");
        } else {
            //生成token，并保存到数据库
            result = shiroService.createToken(user.getUserId());
            result.put("status", 200);
            result.put("msg", "登陆成功");
        }
        return result;
    }

    /**
     * 退出
     */
    @ApiOperation(value = "登出", notes = "参数:token")
    @PostMapping("/sys/logout")
    public Map<String, Object> logout(String token ,HttpServletRequest httpServletRequest) {
        Map<String, Object> result = new HashMap<>();
        //参数里的token是swagger测试用，开发中用下面方法
        token = TokenUtil.getRequestToken(httpServletRequest);
        shiroService.logout(token);
        result.put("status", "200");
        result.put("msg", "您已安全退出系统");
        return result;
    }
}


