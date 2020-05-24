package com.test.qywx.service;

import com.test.qywx.pojo.LibraryDorm;

import java.util.List;

public interface LibraryDormService {

    List<String> findExitByDorm(String dorm);

}
