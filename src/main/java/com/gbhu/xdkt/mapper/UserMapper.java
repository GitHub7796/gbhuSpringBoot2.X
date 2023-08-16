package com.gbhu.xdkt.mapper;

import com.gbhu.xdkt.model.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int save(User user);

    User findPwdByPhoneAndPwd(@Param("phone") String phone,@Param("pwd")String pwd);

    User findUserInfoById(@Param("user_id")Integer userId);
}
