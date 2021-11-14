package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.ContactMagazijn;
import com.brielage.uitleendienst.repositories.ContactMagazijnRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/contactMagazijn")
public class ContactMagazijnController {
    Logger logger =
            LoggerFactory.getLogger(ContactMagazijnController.class.getName());

    @Autowired
    private ContactMagazijnRepository contactMagazijnRepository;

    @PostMapping("/add")
    public ContactMagazijn add (@RequestBody ContactMagazijn contactMagazijn) {
        ContactMagazijn cm = contactMagazijnRepository.save(contactMagazijn);
        logger.info(cm.getId());
        return cm;
    }
}
