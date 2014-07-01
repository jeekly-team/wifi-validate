/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.validate.service;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.jyzn.wifi.validate.platforminterface.SmsInterface;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author zyt
 */
@Component
public class PlatformService {

    /*    
     @Autowired
     private SmsInterfaceImpl sms;
     */
    private final SmsInterface sms;

    @Autowired
    public PlatformService(SmsInterface sms) {
        this.sms = sms;
    }

    public boolean HttpSendSmsStatus(String postUrl, String postData) {
        String status = sms.HttpSendSms(postUrl, postData).get("status").toString();
        return "sucess".equals(status);
    }

    public Map HttpSendSmsCallback(String postUrl, String postData) {
        return sms.HttpSendSms(postUrl, postData);
    }

    public Map HttpSendSmsTest(String postUrl, String postData) {
        System.out.println(postUrl + "\n");
        System.out.println(postData + "\n");
        Map resultMap = Maps.newHashMap();
        resultMap.put("status", "sucess");
        resultMap.put("msg", postData);
        //Map map = ImmutableMap.of("status", "sucess", "msg", postData);

        return resultMap;
    }

}
