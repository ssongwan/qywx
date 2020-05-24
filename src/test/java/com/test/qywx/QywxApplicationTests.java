package com.test.qywx;

import com.test.qywx.pojo.AccessToken;
import com.test.qywx.service.AccessTokenService;
import com.test.qywx.service.UserAccessService;
import com.test.qywx.service.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class QywxApplicationTests {
    @Autowired
    private UsersService usersService;

    @Autowired
    private UserAccessService userAccessService;

    @Autowired
    private AccessTokenService accessTokenService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
//        Users users=new Users(2,"wq","002.jpg",2);
//        usersService.InsertUser(users);
        List<AccessToken> list =accessTokenService.findAccessToken("app");
        for (AccessToken accessToken:list){
            System.out.println(accessToken.getToken());
            System.out.println(accessToken.getSave_date());
            System.out.println(accessToken.getExpiresIn());
            System.out.println(accessToken.getId());
            System.out.println(accessToken.getTypes());
        }

    }
}
