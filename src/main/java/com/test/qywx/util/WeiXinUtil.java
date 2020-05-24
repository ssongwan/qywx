package com.test.qywx.util;

import com.alibaba.fastjson.JSONObject;
import com.test.qywx.pojo.AccessToken;
import com.test.qywx.service.AccessTokenService;
import com.test.qywx.wechat.SendRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
public class WeiXinUtil {
    /**
     * 获取access_token的接口地址（GET） 限200（次/天）
     * @param
     * @return
     */
    public final static String access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={corpId}&corpsecret={corpsecret}";
    /**
     * 企业微信扫码登录(获取accessToken)
     *
     * @param
     * @return
     */
    @Autowired
    private AccessTokenService accessTokenService;


    //重写
    public static String getAccessToken(AccessTokenService accessTokenService, String types, String corpId, String secret, boolean flag) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<AccessToken> access = accessTokenService.findAccessToken(types);
            // 如果没有AccessToken的话
            AccessToken accessToken = null;
            if (access.size() <= 0) {
                // 首先要查询到accessToken(第一次查询的话，将accessToken保存到数据库中)
                accessToken = WeiXinUtil.getFirstToken(corpId, secret, types);
                System.out.println("accessToken:" + accessToken.getToken());
                accessTokenService.saveAccessToken(accessToken);
            } else {
                accessToken = access.get(0);
                String saveTime = accessToken.getSave_date();
                long time = sdf.parse(saveTime).getTime();
                long now = System.currentTimeMillis();
                long interval = (now - time) / 1000;
                // 超过7000秒，重新获取accessToken
                if (interval > 7000||!flag) {
                    accessToken = WeiXinUtil.getFirstToken(QiWeiParametersUtil.corpId, QiWeiParametersUtil.agentSecret, types);
                    // 更新数据库
                    String newDate = sdf.format(new Date());
                    accessToken.setSave_date(newDate);
                    accessToken.setToken(accessToken.getToken());
                    accessToken.setExpiresIn(Integer.parseInt(String
                            .valueOf(interval)));
                    accessToken.setTypes(types);
                    accessTokenService.updateByExpirIn(accessToken);
                }
            }
            return accessToken.getToken();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    //重写
    public static AccessToken getFirstToken(String appid, String appsecret, String type) {
        AccessToken accessToken = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String saveDate = sdf.format(new Date());
        String requestUrl = access_token_url.replace("{corpId}", appid)
                .replace("{corpsecret}", appsecret);
        JSONObject jsonObject = SendRequest.sendPost(requestUrl, "");
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setTypes(type);
                accessToken.setSave_date(saveDate);
            } catch (Exception e) {
                accessToken = null;
            }
        }
        return accessToken;
    }
}
