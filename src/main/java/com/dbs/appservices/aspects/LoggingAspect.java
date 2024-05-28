package com.dbs.appservices.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    //Advice
    //@within(org.springframework.stereotype.Service.*) => Pointcut
    @Before("@within(org.springframework.stereotype.Service)")
    public void logDetails(JoinPoint joinPoint){

        System.out.println("LoggingAspect: Invoking method: "
                    + joinPoint.getSignature().getName()
                    + " on instance "
                    + joinPoint.getTarget().getClass().getName());
    }

}
