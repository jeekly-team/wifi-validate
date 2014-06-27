/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.validate.web;

import com.google.common.collect.ImmutableMap;
import com.jyzn.wifi.validate.domain.ValidateCodeLog;
import com.jyzn.wifi.validate.domain.ValidateLog;
import com.jyzn.wifi.validate.domain.WifiUser;
import com.jyzn.wifi.validate.service.ValidateService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.utils.Clock;

/**
 *
 * @author zyt
 */
@Controller
@RequestMapping("/msgvalidate")
public class MsgvalidateController {

    @Autowired
    private ValidateService validateservice;
    private static final String validateType = "phmsg"; //验证类型

    @RequestMapping(value = "/getvalidatecode")
    public void GetValidateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String callbackFunName = request.getParameter("callbackparam"); //获取回调方法
        String sid = request.getParameter("sid");//商户ID
        String phoneNumber = request.getParameter("pn");//wifi用户电话号码

        /*-----------------要返回的变量---------------------
         int djs;
         String templet, avd;
         */
        /*-------------------------------------------------*/
        WifiUser user = validateservice.findWifiUserByName(phoneNumber);
        int vLogTotal = 0;
        int Status = 0;
        String Msg = null;
        if (null != user) {
            ImmutableMap<?, ?> params = ImmutableMap.of("EQ_wifiuser.id", user.getId(), "EQ_sid", sid);
            vLogTotal = validateservice.countValidateLogByFilters((Map<String, Object>) params);
        }
        Status = getWifiUserStatus(vLogTotal);
        if (-1 == Status) {
            Msg = "您的验证次数超过限制";
        }
        Clock clock = Clock.DEFAULT;
        if (0 == Status) {
            Map<String, String> PostMsgCallBack = getMsgPostCallBack(phoneNumber);
            String PostStatus = PostMsgCallBack.get("status");
            if (StringUtils.isNotBlank(PostStatus) && "sucess".equals(PostStatus)) {
                user = new WifiUser(phoneNumber, validateType);
                validateservice.saveWifiUser(user);
                ValidateLog log = new ValidateLog(user, sid, validateType, clock.getCurrentDate());
                validateservice.saveValidateLog(log);
                ValidateCodeLog vclog = new ValidateCodeLog(PostMsgCallBack.get("validateCode"), log);
                validateservice.saveValidateCodeLog(vclog);
                Msg = "输入验证码开始网上冲浪";
            } else {
                Msg = "短信发送失败,请重试!";
            }

        }
        if (1 == Status) {
            ValidateLog log = new ValidateLog(user, sid, validateType, clock.getCurrentDate());
            validateservice.saveValidateLog(log);
            Msg = "免验证模式";
        }
        /*-------------------------------------------------*/
        //构建返回JSONObject
        JSONObject json = new JSONObject();
        //回调JS方法
        json.put("callbackparam", callbackFunName);
        json.put("status", Status);
        json.put("msg", Msg);
        //response.setContentType("text/html;charset=UTF-8");  
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(callbackFunName + "(" + json.toString() + ")");
    }

    private Map<String, String> getMsgPostCallBack(String phoneNumber) {
        //短信接口
        ImmutableMap<String, String> map = ImmutableMap.of("status", "sucess", "validateCode", "123456");

        return map;
    }
    /*
     获取用户的状态
     * status(0,1,-1)
     * 0为库中没有 要求填写验证码
     * 1为库中已有 免验证
     * -1为此号码已n次验证 用户未能输入验证码 当天内拒绝此号码再次验证
     */

    private int getWifiUserStatus(int c) {
        int ret = 0;
        if (c == 0) {
            ret = 0;
        }
        if (c > 0 && c < 3) {
            ret = 1;
        }
        if (c > 0 && c >= 2) {
            ret = -1;
        }
        return ret;
    }

}
