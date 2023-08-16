package com.gbhu.xdkt.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbhu.xdkt.model.entity.User;
import com.gbhu.xdkt.service.UserService;
import com.gbhu.xdkt.utils.JWTUtils;
import com.gbhu.xdkt.utils.JsonData;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = "api/v1/pri/**")
public class LoginFilter implements Filter {



    //ioc容器加载LoginFilter时候
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
    //编写过滤逻辑
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest reg = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String token = reg.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = reg.getParameter("token");
        }
        if (!StringUtils.isEmpty(token)) {
            //判断token是否合法
            Claims claims = JWTUtils.checkJWT(token);
            if (claims == null) {
                renderJson(resp, JsonData.Fail("登录过期,重新登录"));
            }else{
                JsonData jsonData = JsonData.Fail("登录失败，token无效", "-2");
                renderJson(resp,jsonData);
            }
        }else{
            JsonData jsonData = JsonData.Fail("未登录", "-3");
            renderJson(resp,jsonData);
        }
    }

    private void renderJson(HttpServletResponse response, Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json=objectMapper.writeValueAsString(obj);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ioc容器销毁LoginFilter时候
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
