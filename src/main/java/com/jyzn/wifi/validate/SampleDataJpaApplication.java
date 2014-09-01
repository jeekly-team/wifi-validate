package com.jyzn.wifi.validate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class SampleDataJpaApplication {
    /*
     @Autowired
     DataSource dataSource;

     @Bean
     public LocalSessionFactoryBean sessionFactory() {
     LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
     sessionFactory.setDataSource(dataSource);
     sessionFactory.setPackagesToScan(new String[]{"com.jyzn.wifi.validate.domain"});
     return sessionFactory;
     }

    
     @Bean
     @Autowired
     public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
     HibernateTransactionManager txManager = new HibernateTransactionManager();
     txManager.setSessionFactory(sessionFactory);
     return txManager;
     }
     */

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleDataJpaApplication.class, args);
    }

}
