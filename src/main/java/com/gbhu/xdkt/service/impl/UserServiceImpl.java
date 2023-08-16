package com.gbhu.xdkt.service.impl;

import com.gbhu.xdkt.mapper.UserMapper;
import com.gbhu.xdkt.model.entity.User;
import com.gbhu.xdkt.service.UserService;
import com.gbhu.xdkt.utils.CommonUtils;
import com.gbhu.xdkt.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.Random;

@Service
@Transactional//开启事务
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    //放在cdn上的随机头像
    private static final String[] images = {
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    @Override
    public int save(Map<String, String> userInfo) {
        User user = parseToUser(userInfo);
        if (user == null) {
            return -1;
        }
        return userMapper.save(user);
    }

    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {
        User user = userMapper.findPwdByPhoneAndPwd(phone, CommonUtils.MD5(pwd));
        if (user == null) {
            return null;
        }
        return JWTUtils.getToken(user);
    }

    @Override
    public User findUserInfoById(Integer userId) {
        return userMapper.findUserInfoById(userId);
    }




    //就登录注册用
    private User parseToUser(Map<String, String> userInfo) {
        try {
            User user = new User();
            user.setPhone(userInfo.get("phone"));
            user.setName(userInfo.get("name"));
            user.setHeadImg(getRandomImg());
            String pwd = userInfo.get("pwd");
            user.setPwd(CommonUtils.MD5(pwd));
            user.setCreateTime(new Date());
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获得随机头像
    private String getRandomImg() {
        int size = images.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return images[index];
    }
}
