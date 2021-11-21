package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.ContactMagazijn;
import com.brielage.uitleendienst.repositories.ContactMagazijnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping (value = "/contactMagazijn")
public class ContactMagazijnController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private ContactMagazijnRepository contactMagazijnRepository;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public List<ContactMagazijn> findAll() {
        APILogger.logRequest("contactMagazijn.findAll");
        return contactMagazijnRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        APILogger.logRequest("contactMagazijn.findById", id);
        Optional<ContactMagazijn> c = contactMagazijnRepository.findById(id);

        if (c.isPresent())
            return ResponseEntity.ok().body(c.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody ContactMagazijn contactMagazijn) {
        APILogger.logRequest("contactMagazijn.create", contactMagazijn.toString());
        try {
            if (!validateContactMagazijn(contactMagazijn))
                return ResponseEntity.badRequest().build();

            ContactMagazijn c = contactMagazijnRepository.save(contactMagazijn);

            return new ResponseEntity(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (@PathVariable String id, @RequestBody ContactMagazijn contactMagazijn) {
        APILogger.logRequest("contactMagazijn.put", id);
        try {
            if (!validateContactMagazijnId(contactMagazijn))
                return ResponseEntity.badRequest().build();

            Optional<ContactMagazijn> c = contactMagazijnRepository.findById(id);

            if (c.isEmpty())
                return ResponseEntity.notFound().build();

            contactMagazijn.setId(c.get().getId());
            ContactMagazijn result = contactMagazijnRepository.save(contactMagazijn);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        APILogger.logRequest("contactMagazijn.delete", id);
        try {
            Optional<ContactMagazijn> c = contactMagazijnRepository.findById(id);

            if (c.isEmpty())
                return ResponseEntity.badRequest().build();

            contactMagazijnRepository.delete(c.get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean validateContactMagazijnId (ContactMagazijn c) {
        if (c.getId().isEmpty())
            return false;
        return validateContactMagazijn(c);
    }

    private boolean validateContactMagazijn (ContactMagazijn c) {
        return c.getPersoon() != null
                && c.getMagazijn() != null;
    }
}
