package com.test.qywx.service.impl;

import com.test.qywx.dao.UserAccessMapper;
import com.test.qywx.entity.Result;
import com.test.qywx.entity.User;
import com.test.qywx.pojo.UserAccess;
import com.test.qywx.service.UserAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;


@Service
public class UserAccessServiceImpl implements UserAccessService {

    @Autowired
    private UserAccessMapper userAccessMapper;


    @Override
    public void insertUsers(UserAccess userAccess) {
        userAccessMapper.insertSelective(userAccess);
    }

    @Override
    public void deleteTimeOutData(Date date) {
        Example example=new Example(UserAccess.class);
        example.setOrderByClause("date DESC");
        Example.Criteria criteria=example.createCriteria();
        criteria.andLessThan("date",date);
        userAccessMapper.deleteByExample(example);
    }
}
