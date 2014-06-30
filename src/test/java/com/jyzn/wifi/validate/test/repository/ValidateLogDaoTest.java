/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.validate.test.repository;

import com.jyzn.wifi.validate.SampleDataJpaApplication;
import com.jyzn.wifi.validate.domain.WifiUser;
import com.jyzn.wifi.validate.repository.WifiUserDao;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = SampleDataJpaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ValidateLogDaoTest {

    @Autowired
    WifiUserDao wifiuserdao;

    @Test
    public void findWifiUsers() {
        List<WifiUser> result = (List<WifiUser>) wifiuserdao.findAll();
        assertNotNull(result);
    }
}
