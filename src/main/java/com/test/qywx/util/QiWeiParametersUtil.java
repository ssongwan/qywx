package com.test.qywx.util;

import com.alibaba.fastjson.JSONObject;
import com.test.qywx.wechat.SendRequest;


public class QiWeiParametersUtil {
    /**
     * 企业ID（yes）+
     */
    public final static String corpId = "ww744cc9c79ba638e0";
    /**
     * 企业应用的id，整型。可在应用的设置页面查看(yes)项目测试（ebo0.2版本）
     */
    public final static int agentId = 1000002;
    /**
     * 应用的凭证密钥(yes)  这是ebo0.2版本的
     */
    public final static String agentSecret = "zGy0U2-jNpywVehGsPP5k_z_0y73N--R38ILpkGf6yU";
    /**
     * 1.获取AccessToken
     */
    public static String getAccessTokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={corpid}&corpsecret={corpsecret}";

    /**
     * 发送企业微信AccessToken
     */
    public static String sendAccessTokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";

    /**
     * 应用secret
     */
    public static String secret = "zGy0U2-jNpywVehGsPP5k_z_0y73N--R38ILpkGf6yU";

    /**
     * 获得各种access_token
     *
     * @return
     */
    public static String getAccessToken() {
        String url = getAccessTokenUrl.replace("{corpid}", corpId).replace("{corpsecret}", secret);
        JSONObject departmentJson = SendRequest.sendGet(url);
        return departmentJson.getString("access_token");
    }
}
