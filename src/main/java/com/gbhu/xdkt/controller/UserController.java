package com.gbhu.xdkt.controller;

import com.gbhu.xdkt.config.WxPayConfig;
import com.gbhu.xdkt.model.entity.User;
import com.gbhu.xdkt.model.request.LoginRequest;
import com.gbhu.xdkt.service.UserService;
import com.gbhu.xdkt.task.AsyncTask;
import com.gbhu.xdkt.utils.JsonData;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("api/v1/pri/user")
@Log
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WxPayConfig wxPayConfig;

    @GetMapping("testWxPaY")
    public JsonData testWxPayConfig() {
        //不能直接返回，拿到属性后返回
//        return JsonData.Sucess(wxPayConfig);

        Map map = new HashMap<>();
        map.put("wxpay.appid", wxPayConfig.getAppId());
        map.put("wxpay.secret", wxPayConfig.getSecret());
        return JsonData.Sucess(map);
    }

    //注册
    @PostMapping("register")
    //下述是两种不同方式，都可以，看项目组
    //    public JsonData register@RequestBody LoginRequest loginRequest) {
    public JsonData register(@RequestBody Map<String, String> userInfo) {
        int res = userService.save(userInfo);
        return res == 1 ? JsonData.Sucess() : JsonData.Fail("注册失败");
    }

    @PostMapping("loginTest")
//    public JsonData loginTest(
//            @RequestParam("user_name") String username,
//            @RequestParam(value = "passwd",required = true,defaultValue = "123") String pwd) {
    public JsonData loginTest(String username, String pwd) {
        System.out.println(username);
        System.out.println(pwd);
        return JsonData.Sucess();
    }

    @PostMapping("loginJsonTest")
    public JsonData loginTest1(
            @RequestBody String username, String pwd) {
        System.out.println(username);
        System.out.println(pwd);
        System.out.println("username:" + username + ",pwd:" + pwd);
        return JsonData.Sucess();
    }

    @RequestMapping("loginMapTest")
    public JsonData loginMapTest(@RequestBody Map<String, String> map) {
//        public JsonData loginMapTest(@RequestParam  Map<String,String> map) {
//        public JsonData loginMapTest(Map<String,String> map) {
        System.out.println(map.toString());
        return JsonData.Sucess();
    }

    @RequestMapping("loginObjTest")
//    public JsonData loginObjTest(@RequestBody  User user) {
    public JsonData loginMapTest(@RequestParam User user) {
//        public JsonData loginMapTest(User user) {
        System.out.println(user.toString());
        return JsonData.Sucess();
    }

    //登录
    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest) {
        String token = userService.findByPhoneAndPwd(loginRequest.getPhone(), loginRequest.getPwd());
        return token == null ? JsonData.Fail("登录失败") : JsonData.Sucess(token);
    }

    //获取个人信息
    @PostMapping("find_by_token")
    public JsonData findUserInfoByToken(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("user_id");
        User user = userService.findUserInfoById(userId);
        return user == null ? JsonData.Fail("token失效请重新登录") : JsonData.Sucess(user);
    }

    @Autowired
    private AsyncTask asyncTask;

    @RequestMapping("/async_test")
    public JsonData testAsync() {
        long start = System.currentTimeMillis();
        asyncTask.test1();
        asyncTask.test2();
        Future<User> task3 = asyncTask.test3();
//        // get(long timeout, TimeUnit unit)超时就不管了
//        //get()
//        task3.get()
        User user = null;
        for (; ; ) {
            //死循环，必须异步拿到数据
            if (task3.isDone()) {
                try {
                    user = task3.get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }finally {
                    break;
                }
            }
        }
        long end = System.currentTimeMillis();
        return JsonData.Sucess(end-start);

    }
}
