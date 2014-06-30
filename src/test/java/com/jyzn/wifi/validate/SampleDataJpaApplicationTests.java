package com.jyzn.wifi.validate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Integration test to run the application.
 *
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleDataJpaApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
// Separate profile for web tests to avoid clashing databases
public class SampleDataJpaApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    private static final Logger logger = LoggerFactory.getLogger(SampleDataJpaApplicationTests.class);

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testHome() throws Exception {
        this.mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    /*
    @Test
    public void testMsgvalidate() throws Exception {
        String Param="?callbackparam=validateCode&sid=test&phno=test";
        this.mvc.perform(get("/msgvalidate/getvalidatecode"+Param)).andExpect(status().isOk());
        logger.info(content().toString());
        
        
    }
    */
}
