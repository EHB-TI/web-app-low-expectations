package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.ContactHuurder;
import com.brielage.uitleendienst.repositories.ContactHuurderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/contactHuurder")
public class ContactHuurderController {
    @Autowired
    private ContactHuurderRepository contactHuurderRepository;

    @PostMapping ("/add")
    public ContactHuurder add (@RequestBody ContactHuurder contactHuurder) {
        APILogger.logRequest("contactHuurder.add", contactHuurder.toString());
        ContactHuurder ch = contactHuurderRepository.save(contactHuurder);
        APILogger.logResult(ch.toString());
        return ch;
    }
}
