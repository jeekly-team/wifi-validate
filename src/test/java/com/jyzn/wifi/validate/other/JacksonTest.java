/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.validate.other;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.jyzn.wifi.validate.SampleDataJpaApplication;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 *
 * @author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleDataJpaApplication.class)
@ActiveProfiles("test")
public class JacksonTest {

    @Test
    public void writeMapJSON() {
      
       ObjectMapper om=new ObjectMapper() ;
        try {
            
            JsonGenerator jg = om.getFactory().createGenerator(System.out, JsonEncoding.UTF8);
            
            ImmutableMap<String, String> map = ImmutableMap.of("status", "sucess", "validateCode", "123456");
            
            jg.writeObject(map);
            //可以仅使用om.writeValue/writeValue具有和JsonGenerator.writeObject相同的功能
            om.writeValue(System.out, map);
           // om.writeValueAsString(map);
            
        } catch (IOException e) {
        }
    }

}
