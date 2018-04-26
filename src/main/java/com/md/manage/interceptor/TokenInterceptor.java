package com.md.manage.interceptor;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.md.manage.domain.Hr;
import com.md.manage.exception.BaseException;
import com.md.manage.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class TokenInterceptor implements HandlerInterceptor{

    @Autowired
    RedisService redisService;


    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) throws Exception {
        String path = req.getRequestURI();
        if(path.equals("/api/token/get")){
            return true;
        }
        if(path.contains("/api/")){
            String token = null;
            Hr hr=null;
            if(req.getHeader("token")!=null){
                JsonFactory factory = new JsonFactory();
                factory.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
                ObjectMapper objectMapper = new ObjectMapper();
                token = req.getHeader("token");
                Map json =  objectMapper.readValue(req.getHeader("token"), HashMap.class);
                token =  "token:"+json.get("token");
                hr =(Hr) redisService.get(token);
            }
            if(hr==null){
                res.setStatus(401);
                throw new BaseException("token不存在或已过期!");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
