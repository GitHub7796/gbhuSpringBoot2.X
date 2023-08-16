package com.gbhu.xdkt.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbhu.xdkt.utils.JWTUtils;
import com.gbhu.xdkt.utils.JsonData;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 登录拦截器
 */
public class LoginInteceptor implements HandlerInterceptor {
    //执行方法前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //token一般在 request Header中
        String token = request.getHeader("token");

        if (StringUtils.isNotBlank(token)) {
            //token不为空
            Claims claims = JWTUtils.checkJWT(token);
            if (claims == null) {
                //token 验证失败
                sendJsonMessage(response, JsonData.Fail("登录过期,重新登录"));
                return false;
            }
            //token 验证成功
            Integer id = (Integer) claims.get("id");
            String name = (String) claims.get("name");
            request.setAttribute("user_id",id);
            request.setAttribute("name",name);
            return true;
        }
        //token 为空
        sendJsonMessage(response, JsonData.Fail("登录过期,重新登录"));
        return false;
    }

    public static void sendJsonMessage(HttpServletResponse response, Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(obj));
            writer.close();
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
