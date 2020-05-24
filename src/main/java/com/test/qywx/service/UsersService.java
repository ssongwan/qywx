package com.test.qywx.service;

import com.test.qywx.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersService {

    User getUser(String userId, String accessToken);

    String getUserId(String code, String accessToken);

//    String getStatus(String code,String accessToken);
}
