package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.User;
import com.brielage.uitleendienst.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping ("/add")
    public User add (@RequestBody User user) {
        APILogger.logRequest(user.toString());
        User u = userRepository.save(user);
        APILogger.logResult(u.toString());
        return u;
    }
}
