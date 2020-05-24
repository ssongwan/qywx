package com.test.qywx.controller;

import com.test.qywx.entity.User;
import com.test.qywx.pojo.LibraryDorm;
import com.test.qywx.pojo.UserAccess;
import com.test.qywx.service.AccessTokenService;
import com.test.qywx.service.LibraryDormService;
import com.test.qywx.service.UserAccessService;
import com.test.qywx.service.UsersService;
import com.test.qywx.util.QiWeiParametersUtil;
import com.test.qywx.util.WeiXinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserAccessService userAccessService;

    @Autowired
    private LibraryDormService libraryDormService;

    @GetMapping(value = "/login")
    public ResponseEntity<String> login(@RequestParam("code") String code,@RequestParam("status")String status) {
        String accessToken = WeiXinUtil.getAccessToken(accessTokenService, "app", QiWeiParametersUtil.corpId, QiWeiParametersUtil.agentSecret,true);
        String userId=usersService.getUserId(code,accessToken);
        User users=usersService.getUser(userId,accessToken);
        //判断是不是企业用户
        if (users==null){
            System.out.println("用户不存在");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户不存在");
        }
        //判断门号是否一致
        List<String> libraryDorms=libraryDormService.findExitByDorm(users.getDormnum());
        status=status.substring(1,status.length()-1);
        if (!libraryDorms.contains(status)){
            System.out.println("不允许从此门进出");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("不允许从此门进出");
        }
        System.out.println("Status="+status);
        System.out.println("当前用户名字----"+users.getName());
        UserAccess userAccess=new UserAccess(userId,new Date(),1);
        //更新useraccess表，记录进出信息
        userAccessService.insertUsers(userAccess);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/logout")
    public ResponseEntity<String> signout(@RequestParam("code") String code,@RequestParam("status")String status){
        String accessToken = WeiXinUtil.getAccessToken(accessTokenService, "app", QiWeiParametersUtil.corpId, QiWeiParametersUtil.agentSecret,true);
        String userId=usersService.getUserId(code,accessToken);
        User users=usersService.getUser(userId,accessToken);
        //判断是不是企业用户
        if (users==null){
            System.out.println("用户不存在");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户不存在");
        }
        //判断门号是否一致
        List<String> libraryDorms=libraryDormService.findExitByDorm(users.getDormnum());
        status=status.substring(1,status.length()-1);
        if (!libraryDorms.contains(status)){
            System.out.println("不允许从此门进出");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("不允许从此门进出");
        }
        System.out.println("Status="+status);
        System.out.println("当前用户名字----"+users.getName());
        UserAccess userAccess=new UserAccess(userId,new Date(),0);
        //更新useraccess表，记录进出信息
        userAccessService.insertUsers(userAccess);
        return ResponseEntity.ok().build();
    }

}
