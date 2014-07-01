/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.validate.platforminterface;

import com.google.common.collect.Maps;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.springframework.stereotype.Component;
/**
 *
 * @author Administrator
 */
@Component
public class SmsInterfaceImpl implements SmsInterface {
    @Override
    public Map HttpSendSms(String postUrl, String postData) {
        String result = "";
        Map resultMap = Maps.newHashMap();
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", "" + postData.length());

            try {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                out.write(postData);
                out.flush();//刷新流
            } catch (IOException e) {
            }

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                resultMap.put("status", "fail");
                //return "fail";
            }

            //获取响应内容体
            String line;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                while ((line = in.readLine()) != null) {
                    result += line + "\n";
                }
            }
            resultMap.put("status", "sucess");
            resultMap.put("result", result);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return resultMap;
    }


}
