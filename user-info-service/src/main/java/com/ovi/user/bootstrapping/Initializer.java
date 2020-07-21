package com.ovi.user.bootstrapping;


import com.ovi.user.domain.User;
import com.ovi.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Initializer {


    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(Initializer.class);

    public void userInit() {
        if (userRepository.findAll().size() == 0) {
            User user = User.builder()
                    .fullName("Alex Curry")
                    .email("alex@gmail.com")
                    .password("123456a")
                    .build();
            userRepository.save(user);
            List<User> userList = userRepository.findAll();
            logger.info("{}", userList);
        }
    }
}
