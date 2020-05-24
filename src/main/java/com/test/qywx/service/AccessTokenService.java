package com.test.qywx.service;


import com.test.qywx.pojo.AccessToken;

import java.util.List;


public interface AccessTokenService {
    //根据type查询出accessToken中的值

    //将数据插入到AccessTokenModel中
    void saveAccessToken(AccessToken accessToken);

    //查询出AccessTokenModel中的值
    List<AccessToken> findAccessToken(String types);

    //根据expirin去修改时间和token
    void updateByExpirIn(AccessToken accessToken);
}
