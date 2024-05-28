package com.dbs.appservices.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service // name => welcomeService
@Profile("linux")
public class WelcomeService implements  Hello{
    @Override
    public String getMessage() {
        return "Welcome";
    }
}
