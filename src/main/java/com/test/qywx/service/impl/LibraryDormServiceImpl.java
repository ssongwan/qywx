package com.test.qywx.service.impl;


import com.test.qywx.dao.LibraryDormMapper;
import com.test.qywx.pojo.LibraryDorm;
import com.test.qywx.service.LibraryDormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryDormServiceImpl implements LibraryDormService {

    @Autowired
    private LibraryDormMapper libraryDormMapper;

    @Override
    public List<String> findExitByDorm(String dorm) {
        Example example=new Example(LibraryDorm.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("dormnum",dorm);
        List<LibraryDorm> libraryDorms=libraryDormMapper.selectByExample(example);
        List<String> nums=new ArrayList<>();
        for(LibraryDorm libraryDorm:libraryDorms){
            nums.add(libraryDorm.getExitnum());
        }
        return nums;
    }
}
