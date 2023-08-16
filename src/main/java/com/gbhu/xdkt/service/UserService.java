package com.gbhu.xdkt.service;

import com.gbhu.xdkt.model.entity.User;

import java.util.Map;

public interface UserService {

    int save(Map<String, String> userInfo);



    String findByPhoneAndPwd(String phone, String pwd);


    User findUserInfoById(Integer userId);

}
