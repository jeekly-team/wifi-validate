/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.validate.other;

import com.jyzn.wifi.validate.SampleDataJpaApplication;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springside.modules.persistence.SearchFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleDataJpaApplication.class)
@ActiveProfiles("test")
public class SpringsidetoolsTest {

    @Test
    public void getParametersStartingWithTest() {

        String paramName = "EQ_wifiuser.id";
        String prefix = "";
        String unprefixed = paramName.substring(prefix.length());
        String[] values = {"18691525183"};
        Map<String, Object> params = new TreeMap<>();
        if ((values == null) || (values.length == 0)) {
        } else if (values.length > 1) {
            params.put(unprefixed, values);
        } else {
            params.put(unprefixed, values[0]);
        }

        params.put("EQ_sid", "001");
        Map<String, SearchFilter> filters = SearchFilter.parse(params);

        assertNotNull(filters);

    }
}
