package com.test.qywx.service.impl;


import com.test.qywx.dao.AccessTokenMapper;
import com.test.qywx.pojo.AccessToken;
import com.test.qywx.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;


import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccessTokenServiceImpl implements AccessTokenService {
    @Autowired
    private AccessTokenMapper accessTokenMapper;

    @Override
    public void saveAccessToken(AccessToken accessToken) {
        accessTokenMapper.insertSelective(accessToken);
    }

    @Override
    public List<AccessToken> findAccessToken(String types) {
        Example example=createExample(types);
        return accessTokenMapper.selectByExample(example);
    }

    @Override
    public void updateByExpirIn(AccessToken accessToken) {
        accessTokenMapper.insertSelective(accessToken);
    }

    public Example createExample(String types){
        Example example=new Example(AccessToken.class);
        example.setOrderByClause("id DESC");
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("types",types);
        return example;
    }
}
