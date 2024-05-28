package com.dbs.appservices;

import com.dbs.appservices.services.Hello;
import com.dbs.appservices.services.WelcomeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AppServicesApplication {

    public static void main(String[] args) {
            SpringApplication.run(AppServicesApplication.class, args);
    }


    @Bean
    @Profile("testing")
    public Hello myBean(){
        return new Hello() {
            @Override
            public String getMessage() {
                return "MyBean";
            }
        };
    }

}
