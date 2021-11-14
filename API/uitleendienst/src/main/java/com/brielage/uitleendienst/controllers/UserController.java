package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.Persoon;
import com.brielage.uitleendienst.models.User;
import com.brielage.uitleendienst.repositories.UserRepository;
import com.brielage.uitleendienst.responses.APIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping (value = "/user")
public class UserController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UserRepository userRepository;

    @PostMapping ("/add")
    public String add (@RequestBody User user)
            throws
            JsonProcessingException {
        APILogger.logRequest(user.toString());
        Map fouten = new LinkedHashMap();

        if (user.getUsername()
                .isEmpty()) fouten.put("username_leeg", "");
        if (user.getPersoon() == null) fouten.put("persoon_leeg", "");
        else {
            Persoon p = user.getPersoon();
            if (p.getVoornaam()
                 .isEmpty()) fouten.put("persoon_voornaam_leeg", "");
            if (p.getFamilienaam()
                 .isEmpty()) fouten.put("persoon_familienaam_leeg", "");
            if (p.getEmail()
                 .isEmpty()) fouten.put("persoon_email_leeg", "");
        }

        if (fouten.isEmpty()) {
            try {
                User u = userRepository.save(user);
                APIResponse.respondUser(u);
            } catch (Exception e) {return APIResponse.respond(false, e.getMessage());}
        }

        return APIResponse.respondErrors(fouten);
    }
}
