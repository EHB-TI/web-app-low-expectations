package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.ContactMagazijn;
import com.brielage.uitleendienst.repositories.ContactMagazijnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/contactMagazijn")
public class ContactMagazijnController {
    @Autowired
    private ContactMagazijnRepository contactMagazijnRepository;

    @PostMapping ("/add")
    public ContactMagazijn add (@RequestBody ContactMagazijn contactMagazijn) {
        APILogger.logRequest("contactMagazijn.add", contactMagazijn.toString());
        ContactMagazijn cm = contactMagazijnRepository.save(contactMagazijn);
        APILogger.logResult(cm.toString());
        return cm;
    }
}
