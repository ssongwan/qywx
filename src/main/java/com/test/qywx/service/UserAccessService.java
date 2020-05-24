package com.test.qywx.service;


import com.test.qywx.entity.Result;
import com.test.qywx.pojo.UserAccess;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserAccessService {

    void insertUsers(UserAccess userAccess);

    void deleteTimeOutData(Date date);

}
