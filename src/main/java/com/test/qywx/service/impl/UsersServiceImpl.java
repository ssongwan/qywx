package com.test.qywx.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.test.qywx.entity.Result;
import com.test.qywx.entity.User;
import com.test.qywx.service.UsersService;
import com.test.qywx.util.QiWeiParametersUtil;
import com.test.qywx.util.WeiXinUtil;
import com.test.qywx.wechat.SendRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    private static Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);

    private String getUserUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";
    private String deleteUserUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=USERID";
    private String batchdeleteUserUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=ACCESS_TOKEN";
    private String getDepartmentUserUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";
    private String getUserUrl2 = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&agentid=AGENTID&code=CODE";


    //获取用户详细信息
    public User getUser(String userId, String accessToken) {
        getUserUrl = getUserUrl.replace("ACCESS_TOKEN", accessToken).replace("USERID", userId);
        JSONObject jsonObject = SendRequest.sendGet(getUserUrl);
        User users=new User();
        users.setName(jsonObject.get("name").toString());
//        users.setDormnum(jsonObject.get("dormnum").toString());
        users.setEmail(jsonObject.get("email").toString());
        users.setGender(Integer.parseInt(jsonObject.get("gender").toString()));
        users.setMobile(jsonObject.get("mobile").toString());
        users.setStatus(Integer.parseInt(jsonObject.get("status").toString()));
        users.setUserid(userId);
        users.setErrcode(jsonObject.get("errcode").toString());

        JSONObject jsonObject1 = jsonObject.getJSONObject("extattr");
        JSONArray extattr = jsonObject1.getJSONArray("attrs");
        String dormnum=extattr.getJSONObject(1).getJSONObject("text").get("value").toString();
        users.setDormnum(dormnum);
        //3.错误消息处理
        if (0 != jsonObject.getIntValue("errcode")) {
            log.error("获取成员失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
        }
        return users;
    }

    //获取userID
    public String getUserId(String code, String accessToken) {
        getUserUrl2 = getUserUrl2.replace("ACCESS_TOKEN", accessToken).replace("CODE", code).replace("AGENTID", QiWeiParametersUtil.agentId + "");
        JSONObject jsonObject = SendRequest.sendGet(getUserUrl2);
        String userId="";
        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                log.error("获取成员失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
            }else {
                userId=jsonObject.get("UserId").toString();
            }
        }
        return userId;
    }
}
