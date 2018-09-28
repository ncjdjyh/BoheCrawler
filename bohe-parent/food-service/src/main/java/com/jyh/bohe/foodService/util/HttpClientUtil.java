package com.jyh.bohe.foodService.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class HttpClientUtil {
      private static CloseableHttpClient httpClient;

    //get请求
    public static String downloadPage(String path) {
        httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(path);
        System.out.println(get);
        // 执行，返回状态码
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(get);
            System.out.println(response);
            int statusCode = response.getStatusLine().getStatusCode();
            // 针对状态码进行处理
            if (statusCode == HttpStatus.SC_OK) {
                System.out.println(statusCode);
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity,"utf-8");
                //System.out.println(result);
                //关闭httpclient
                httpClient.close();
                response.close();
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       return "error";
    }
}
