package io.renren.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.renren.common.config.WxConfig;
import io.renren.modules.oss.cloud.OSSFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WxQrcode {
    // URL=> 获取小程序码（类型一）
    private static final String GET_MINI_PROGRAM_CODE_TYPE_ONE = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";


    /**
     * @param    # 用于获取access_token
     * @param  # apiKey 小程序id
     * @param # secretKey	小程序密钥
     * @return  access_token
     * @throws Exception
     */
    public static String postToken() throws Exception {
        String apiKey = "";//小程序id
        String secretKey = "";//小程序密钥
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ WxConfig.APPID +"&secret="+WxConfig.SECRET;
        URL url = new URL(requestUrl);
        // 打开和URL之间的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 设置通用的请求属性
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // 得到请求的输出流对象
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes("");
        out.flush();
        out.close();

        // 建立实际的连接
        connection.connect();
        // 定义 BufferedReader输入流来读取URL的响应
        BufferedReader in = null;
        if (requestUrl.contains("nlp"))
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
        else
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String result = "";
        String getLine;
        while ((getLine = in.readLine()) != null) {
            result += getLine;
        }
        in.close();
        JSONObject jsonObject = JSON.parseObject(result);
        String accesstoken=jsonObject.getString("access_token");
        return accesstoken;
    }


    /**
     * 生成带参小程序二维码
     * @param scene	要输入的内容
     * @param accessToken	token
     */
    public static String postMiniqrQr(String scene, String accessToken) {
        try{
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true); // 打开写入属性
            httpURLConnection.setDoInput(true); // 打开读取属性
            httpURLConnection.setRequestMethod("POST");// 提交方式
            // 不得不说一下这个提交方式转换！！真的坑。。改了好长时间！！一定要记得加响应头
            httpURLConnection.setRequestProperty("Content-Type", "application/x-javascript; charset=UTF-8");// 设置响应头
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene", scene); // 你要放的内容
            paramJson.put("path", "pages/index/index");
            paramJson.put("width", 430); // 宽度
            paramJson.put("auto_color", true);
            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            //上传至资源服务器生成url
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bs = new byte[1024];//缓冲数组
            int len = -1;

            while ((len = bis.read(bs)) != -1) {
                byteArrayOutputStream.write(bs, 0, len);
            }

            byte[] bytes = byteArrayOutputStream.toByteArray();

            byteArrayOutputStream.close();
            bis.close();
            String OSSFurl =OSSFactory.build().uploadSuffix(bytes, ".jpg");
            return  OSSFurl;

//            //创建一个空文件
//            OutputStream os = new FileOutputStream(new File("C:/test/test.jpg"));
//            //ByteArrayOutputStream os = new ByteArrayOutputStream();
//            int len;
//            byte[] arr = new byte[1024];
//            while ((len = bis.read(arr)) != -1)
//            {
//                os.write(arr, 0, len);
//                os.flush();
//            }
//            os.close();
//            // 上传云储存
//            InputStream is = new ByteArrayInputStream(os.toByteArray());
//            retMap = UploadUtils.upload(is);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }

}
