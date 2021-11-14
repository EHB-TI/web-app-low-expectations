package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.ContactHuurder;
import com.brielage.uitleendienst.repositories.ContactHuurderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/contactHuurder")
public class ContactHuurderController {
    Logger logger =
            LoggerFactory.getLogger(ContactHuurderController.class.getName());

    @Autowired
    private ContactHuurderRepository contactHuurderRepository;

    @PostMapping("/add")
    public ContactHuurder add (@RequestBody ContactHuurder contactHuurder) {
        ContactHuurder ch = contactHuurderRepository.save(contactHuurder);
        logger.info(ch.getId());
        return ch;
    }
}
