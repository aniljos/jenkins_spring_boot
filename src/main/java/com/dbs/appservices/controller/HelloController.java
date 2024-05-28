package com.dbs.appservices.controller;


import com.dbs.appservices.services.Hello;
import com.dbs.appservices.services.HelloService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Scope("request")
public class HelloController {

    @Autowired
    //@Qualifier("helloService")
    private Hello service;

    public HelloController(){
        System.out.println("HelloController.HelloController: " + service);
    }
    @PostConstruct
    public void init(){
        System.out.println("HelloController.init: " + service);
    }
    @PreDestroy
    public void destroy(){
        System.out.println("HelloController.destroy: " + service);
    }

//    public HelloController(HelloService service) {
//        this.service = service;
//    }


//    public void setService(HelloService service) {
//        this.service = service;
//    }

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String message){

        String firstMessage = service.getMessage();
        return firstMessage + " " + message;
    }
}
