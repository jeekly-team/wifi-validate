/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.validate.test.service;

import com.jyzn.wifi.validate.SampleDataJpaApplication;
import com.jyzn.wifi.validate.domain.ValidateLog;
import com.jyzn.wifi.validate.domain.WifiUser;
import com.jyzn.wifi.validate.service.ValidateService;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleDataJpaApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ValidateServiceTest {

    @Autowired
    private ValidateService validateservice;

    @Test
    public void findAllValidateLog() {
        List<ValidateLog> result = validateservice.getAllvalidatelogdao();
        assertNotNull(result);
    }

    @Test
    public void findValidateLogByWifiuserId() {
        List<ValidateLog> result = validateservice.getValidateLogByWifiuserId("402881e437d47b250137d481b6920001");
        assertNotNull(result);
    }

    @Test
    public void findValidateLogByparamsMaps() {
        Map<String, Object> params = new TreeMap<>();
        params.put("EQ_wifiuser.id", "402881e437d47b250137d481b6920001");
        params.put("EQ_sid", "001");

        List<ValidateLog> result = validateservice.getValidateLogByFilters(params);
        int c = validateservice.countValidateLogByFilters(params);
        assertNotEquals(c, 0);
        assertNotNull(result);
    }

    @Test
    public void create() {

        WifiUser user = randomNewUser();
        validateservice.saveWifiUser(user);
        assertNotNull(user.getId());

    }

    private static WifiUser randomNewUser() {
        WifiUser user = new WifiUser();
        user.setName("004");
        user.setCategory("phmsg");
        return user;
    }
}
