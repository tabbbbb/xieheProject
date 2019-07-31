package io.renren.common.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.renren.common.config.WxConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
public class AppletPayUtil {
    private static Logger log = LoggerFactory.getLogger(AppletPayUtil.class);
    /**
     * 根据 临时登录凭证获取openId
     * 文档:https://developers.weixin.qq.com/miniprogram/dev/api/code2Session.html
     *
     * @param code
     * @return
     * @author majker
     */
    public static String getOpenIdByCode(String code) {

        log.info("获取code成功!{}", code);
        //登录凭证校验
        //
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + WxConfig.APPID + "&secret=" + WxConfig.SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        //发送请求给微信后端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        InputStream inputStream = null;
        CloseableHttpResponse httpResponse = null;
        StringBuilder result = new StringBuilder();
        String openId = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            inputStream = entity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                //这里需要使用fastjson来提取一下内容
                System.out.println(line);
                JSONObject jsonObject = JSON.parseObject(line);
                openId = jsonObject.getString("openid");
                String sessionKey = jsonObject.getString("session_key");
                log.info("openId={},sessionKey={}", openId, sessionKey);
            }
        } catch (IOException e) {
            log.error("获取openId失败" + e.getMessage());
        }
        return openId;

    }
}
