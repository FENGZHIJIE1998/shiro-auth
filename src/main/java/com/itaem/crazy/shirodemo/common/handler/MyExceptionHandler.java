package com.itaem.crazy.shirodemo.common.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class MyExceptionHandler {


    @ExceptionHandler(value = AuthorizationException.class)
    public Map<String, String> handleException(AuthorizationException e) {
        //e.printStackTrace();
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "400");
        //获取错误中中括号的内容
        String message = e.getMessage();
        String msg=message.substring(message.indexOf("[")+1,message.indexOf("]"));
        //判断是角色错误还是权限错误
        if (message.contains("role")) {
            result.put("msg", "对不起，您没有" + msg + "角色");
        } else if (message.contains("permission")) {
            result.put("msg", "对不起，您没有" + msg + "权限");
        } else {
            result.put("msg", "对不起，您的权限有误");
        }
        return result;
    }
}
