package com.ovi.user.bootstrapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbLoader implements CommandLineRunner {

    @Autowired
    Initializer initializer;

    @Override
    public void run(String... args)  {
        initializer.userInit();
    }
}
