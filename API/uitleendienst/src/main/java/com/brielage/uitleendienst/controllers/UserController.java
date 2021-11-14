package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.User;
import com.brielage.uitleendienst.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/user")
public class UserController {
    Logger logger =
            LoggerFactory.getLogger(UserController.class.getName());

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public User add (@RequestBody User user) {
        User u = userRepository.save(user);
        logger.info(u.getUsername());
        return u;
    }
}
