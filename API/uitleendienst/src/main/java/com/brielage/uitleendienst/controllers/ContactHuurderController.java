package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.APILogger.APILogger;
import com.brielage.uitleendienst.models.ContactHuurder;
import com.brielage.uitleendienst.repositories.ContactHuurderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping (value = "/contactHuurder")
public class ContactHuurderController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private ContactHuurderRepository contactHuurderRepository;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public List<ContactHuurder> findAll() {
        APILogger.logRequest("contactHuurder.findAll");
        return contactHuurderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        APILogger.logRequest("contactHuurder.findById", id);
        Optional<ContactHuurder> c = contactHuurderRepository.findById(id);

        if (c.isPresent())
            return ResponseEntity.ok().body(c.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody ContactHuurder contactHuurder) {
        APILogger.logRequest("contactHuurder.create", contactHuurder.toString());
        try {
            if (!validateContactHuurder(contactHuurder))
                return ResponseEntity.badRequest().build();

            ContactHuurder c = contactHuurderRepository.save(contactHuurder);

            return new ResponseEntity(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (@PathVariable String id, @RequestBody ContactHuurder contactHuurder) {
        APILogger.logRequest("contactHuurder.put", id);
        try {
            if (!validateContactHuurderId(contactHuurder))
                return ResponseEntity.badRequest().build();

            Optional<ContactHuurder> c = contactHuurderRepository.findById(id);

            if (c.isEmpty())
                return ResponseEntity.notFound().build();

            contactHuurder.setId(c.get().getId());
            ContactHuurder result = contactHuurderRepository.save(contactHuurder);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        APILogger.logRequest("contactHuurder.delete", id);
        try {
            Optional<ContactHuurder> c = contactHuurderRepository.findById(id);

            if (c.isEmpty())
                return ResponseEntity.badRequest().build();

            contactHuurderRepository.delete(c.get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean validateContactHuurderId(ContactHuurder c) {
        if (c.getId().isEmpty())
            return false;
        return validateContactHuurder(c);
    }

    private boolean validateContactHuurder(ContactHuurder c) {
        return c.getPersoon() != null
                && c.getOrganisatie() != null;
    }
}
