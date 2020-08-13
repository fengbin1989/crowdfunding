package com.fengbin.crowd.util;
/*
@作者 冯彬
@时间 2020-06-06-19:54
*/

import com.fengbin.crowd.constant.CrowdConstant;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class CrowdUtil {

    /**
     * 判断当前请求是否为Ajax请求
     * @param request
     * @return
     *  true:为Ajax请求
     *  false：为普通请求
     */
    public static boolean judgeRequestType(HttpServletRequest request){

        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Request-With");

        return (acceptHeader != null && acceptHeader.contains("application/json")) || (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
    }

    /**
     * md5加密
     * @param source
     * @return
     */
    public static String md5(String source){

        if (source == null || source.equals("")){
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        String algorithm = "md5";// 算法
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] input = source.getBytes();
            byte[] output = messageDigest.digest(input);
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum,output);
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();
            return encoded;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static ResultEntity<String> sendCodeByShortMessage(
            String host,
            String path,
            String method,
            String phoneNum,
            String appcode) {

        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();

        // 生成验证码
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            int random = (int) (Math.random() * 10);
            builder.append(random);
        }

        String code = builder.toString();

        querys.put("content", "【云通知】您的验证码是"+code+"。如非本人操作，请忽略本短信");
        querys.put("mobile", phoneNum);


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */

            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);

            StatusLine statusLine = response.getStatusLine();

            // 状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
            int statusCode = statusLine.getStatusCode();

            String reasonPhrase = statusLine.getReasonPhrase();

            if(statusCode == 200) {

                // 操作成功，把生成的验证码返回
                return ResultEntity.successWithData(code);
            }

            return ResultEntity.faild(reasonPhrase);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.faild(e.getMessage());
        }
    }


    }
