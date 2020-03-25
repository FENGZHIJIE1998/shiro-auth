package com.itaem.crazy.shirodemo.common.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {


    @ExceptionHandler(value=AuthorizationException.class)
    @ResponseBody
    public Map<String, String> handleException(AuthorizationException e) {
        //e.printStackTrace();
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "400");
        result.put("msg", "对不起，"+e.getMessage());
        return result;
    }
}
