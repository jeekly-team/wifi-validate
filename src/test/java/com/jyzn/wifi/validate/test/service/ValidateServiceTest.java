/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.validate.test.service;

import com.google.common.collect.ImmutableMap;
import com.jyzn.wifi.validate.SampleDataJpaApplication;
import com.jyzn.wifi.validate.domain.ValidateCodeLog;
import com.jyzn.wifi.validate.domain.ValidateLog;
import com.jyzn.wifi.validate.domain.WifiUser;
import com.jyzn.wifi.validate.service.ValidateService;
import java.util.List;
import java.util.Map;
import java.util.Random;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springside.modules.utils.Clock;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleDataJpaApplication.class)
@ActiveProfiles("test")
public class ValidateServiceTest {

    @Autowired
    private ValidateService validateservice;
    private final Random random = new Random();

    @Test
    public void findAllValidateLog() {
        List<ValidateLog> result = validateservice.getAllvalidatelogdao();
        assertNotNull(result);
    }

    @Test
    public void findValidateLogByWifiuserId() {
        List<ValidateLog> result = validateservice.getValidateLogByWifiuserId("202881e437d47b250137d481b6920001");
        assertNotNull(result);
    }

    @Test
    public void findValidateLogByparamsMaps() {
        ImmutableMap<?, ?> params = ImmutableMap.of("EQ_wifiuser.id", "202881e437d47b250137d481b6920001", "EQ_sid", "001");
        List<ValidateLog> result = validateservice.getValidateLogByFilters((Map<String, Object>) params);
        int c = validateservice.countValidateLogByFilters((Map<String, Object>) params);
        assertNotEquals(c, 0);
        assertNotNull(result);
    }

    @Test
    public void createAndsave() {
        WifiUser user = new WifiUser("test" + random.nextInt(10000));
        validateservice.saveWifiUser(user);
        assertNotNull(user.getId());

        Clock clock = Clock.DEFAULT;
        ValidateLog log = new ValidateLog(user, "test" + random.nextInt(10000), "test" + random.nextInt(10000), clock.getCurrentDate());
        validateservice.saveValidateLog(log);
        assertNotNull(log.getId());

        ValidateCodeLog vclog = new ValidateCodeLog("test" + random.nextInt(10000), log);
        validateservice.saveValidateCodeLog(vclog);
        assertNotNull(vclog.getId());
    }

    @Test
    public void findWifiUser() {
        WifiUser user = validateservice.findWifiUserByName("18691525183");
        assertNotNull(user.getId());
    }

}
