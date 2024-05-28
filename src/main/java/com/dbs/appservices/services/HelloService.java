package com.dbs.appservices.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service //name=> helloService
@Profile({"windows"})
public class HelloService implements Hello {

    @Override
    public String getMessage(){
        return "Hello";
    }

}
