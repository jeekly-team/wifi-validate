/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.validate.web;

import com.jyzn.wifi.validate.domain.WifiUser;
import com.jyzn.wifi.validate.service.ValidateService;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author zyt
 */
@Controller
@RequestMapping("/msgvalidate")
public class MsgvalidateController {

    @Autowired
    private ValidateService validateservice;

    @RequestMapping(value = "/getvalidatecode")
    public void GetValidateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String callbackFunName = request.getParameter("callbackparam"); //获取回调方法
        String sid = request.getParameter("sid");//商户ID
        String wuser_phno = request.getParameter("phno");//wifi用户电话号码

        /*
         构建查询条件
         */
        Map<String, Object> params = new TreeMap<>();
        params.put("EQ_wifiuser.id", "402881e437d47b250137d481b6920001");
        params.put("EQ_sid", "001");
        int status=getWifiUserStatus(params);
        if(status==0){
            WifiUser wifiuser =new WifiUser();
            wifiuser.setName(sid);
            wifiuser.setCategory("phmsg");
            
            
        }
        
        
        
        /*
         构建返回JSONObject
         */
        JSONObject json = new JSONObject();
        //回调JS方法
        json.put("callbackparam", callbackFunName);

        //...
    }

    /*
     获取用户的状态
     * status(0,1,-1)
     * 0为库中没有 要求填写验证码
     * 1为库中已有 免验证
     * -1为此号码已两次验证 用户未能输入验证码 当天内拒绝此号码再次验证
     */
    private int getWifiUserStatus(Map<String, Object> params) {
        int c = validateservice.countValidateLogByFilters(params);
        int ret = 0;
        if (c == 0) {
            ret = 0;
        }
        if (c > 0 && c < 2) {
            ret = 1;
        }
        if (c > 0 && c >= 2) {
            ret = -1;
        }
        return ret;
    }

}
