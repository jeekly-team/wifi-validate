package com.jyzn.wifi.validate.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.jyzn.wifi.validate.domain.ValidateCodeLog;
import com.jyzn.wifi.validate.domain.ValidateLog;
import com.jyzn.wifi.validate.domain.WifiUser;
import com.jyzn.wifi.validate.service.PlatformService;
import com.jyzn.wifi.validate.service.ValidateService;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private PlatformService platformservice;

    private static final String validateType = "phmsg"; //验证类型

    @Value("${jyzn.wifi.msgvalidate.url}")
    private String url;
    @Value("${jyzn.wifi.msgvalidate.accout}")
    private String accout;
    @Value("${jyzn.wifi.msgvalidate.pwd}")
    private String pwd;

    @RequestMapping(value = "/test")
    public void test(ServletResponse response) throws IOException {
        ImmutableMap<String, String> map = ImmutableMap.of("status", "sucess", "validateCode", "123456");
        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        String s = om.writeValueAsString(map);
        response.getWriter().write("t" + "(" + s + ")");
    }

    @RequestMapping(value = "/getvalidatecode")
    public void GetValidateCode(
            @RequestParam(value = "callbackparam") String callbackFunName,
            @RequestParam(value = "sid") String sid,
            @RequestParam(value = "pn") String phoneNumber,
            ServletResponse response) throws IOException {

        /*-----------------要返回的变量---------------------
         int djs;
         String templet, avd;
         */
        /*-------------------------------------------------*/
        WifiUser user = validateservice.findWifiUserByName(phoneNumber);
        int vLogTotal = 0;
        int Status = 0;
        String Msg = "";
        String FK = "";

        if (null != user) {
            ImmutableMap<?, ?> params = ImmutableMap.of("EQ_wifiuser.id", user.getId(), "EQ_sid", sid);
            vLogTotal = validateservice.countValidateLogByFilters((Map<String, Object>) params);
        }

        Status = getWifiUserStatus(vLogTotal);
        Clock clock = Clock.DEFAULT;
        String PostStatus = "";

        if (0 == Status) {
            Map<String, String> PostMsgCallBack = getMsgPostCallBack(phoneNumber);
            PostStatus = StringUtils.isNotBlank(PostMsgCallBack.get("status")) ? PostMsgCallBack.get("status") : "";
            if (StringUtils.isNotBlank(PostStatus) && "sucess".equals(PostStatus)) {
                if (null != user) {
                    ValidateLog log = new ValidateLog(user, sid, validateType, clock.getCurrentDate());
                    validateservice.saveValidateLog(log);
                    FK = log.getId();
                    ValidateCodeLog vclog = new ValidateCodeLog(PostMsgCallBack.get("validateCode"), log);
                    validateservice.saveValidateCodeLog(vclog);
                } else {
                    user = new WifiUser(phoneNumber);
                    validateservice.saveWifiUser(user);
                    ValidateLog log = new ValidateLog(user, sid, validateType, clock.getCurrentDate());
                    validateservice.saveValidateLog(log);
                    FK = log.getId();
                    ValidateCodeLog vclog = new ValidateCodeLog(PostMsgCallBack.get("validateCode"), log);
                    validateservice.saveValidateCodeLog(vclog);
                }

                Msg = "输入验证码开始网上冲浪";

            } else {
                Msg = "短信发送失败,请重试!";
            }
        }
        if (1 == Status) {
            ValidateLog log = new ValidateLog(user, sid, validateType, clock.getCurrentDate());
            validateservice.saveValidateLog(log);
            FK = log.getId();
            Msg = "免验证模式";
        }
        if (-1 == Status) {
            Msg = "您的验证次数超过限制";
        }
        /*-------------------------------------------------*/

        ImmutableMap<?, ?> map = ImmutableMap.of("status", Status, "msg", Msg, "fk", FK, "poststatus", PostStatus);
        ObjectMapper om = new ObjectMapper();
        String s = om.writeValueAsString(map);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(callbackFunName + "(" + s + ")");

    }

    @RequestMapping(value = "/validatecode")
    public void ValidateCode(
            @RequestParam(value = "callbackparam") String callbackFunName,
            @RequestParam(value = "fk") String fk,
            @RequestParam(value = "vc") String Vc,
            ServletResponse response) throws IOException {
        int Status = 0;
        String Msg = "";
        if (Vc.equals(validateservice.getValidateCodeLogByValidateLogId(fk))) {
            Status = 1;
        }
        ImmutableMap<?, ?> map = ImmutableMap.of("status", Status, "Msg", Msg);
        ObjectMapper om = new ObjectMapper();
        String s = om.writeValueAsString(map);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(callbackFunName + "(" + s + ")");
    }

    private Map<String, String> getMsgPostCallBack(String phoneNumber) {
        //短信接口
        Random random = new Random();
        //random.nextInt(10000);//5位随机数字.
        int msg = random.nextInt(100000);
        //sname=kwsm&spwd=kwsm&scorpid=&sprdid=101&sdst=13910862579&smsg="+URLEncoder.encode("短信内容","utf-8");
        String PostData = "sname=" + accout + "&spwd=" + pwd + "&pn=" + phoneNumber + "&msg" + msg;

        //Map map = platformservice.HttpSendSmsCallback(url, PostData);
        Map map = platformservice.HttpSendSmsTest(url, PostData);
        map.put("validateCode", "" + msg);

        //输出JSON
        ObjectMapper om = new ObjectMapper();
        try {
            om.writeValue(System.out, map);
        } catch (IOException ex) {
            Logger.getLogger(MsgvalidateController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //ImmutableMap<String, String> map = ImmutableMap.of("status", "sucess", "validateCode", "123456");
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
