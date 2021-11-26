package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.ContactMagazijn;
import com.brielage.uitleendienst.repositories.ContactMagazijnRepository;
import com.brielage.uitleendienst.tools.RemoveDuplicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/contactmagazijn")
public class ContactMagazijnController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private ContactMagazijnRepository contactMagazijnRepository;

    @GetMapping (value = { "/", "" })
    public ResponseEntity findByProperties (
            @RequestParam (required = false) List<String> persoondId,
            @RequestParam (required = false) List<String> magazijnId
                                           ) {
        if ((persoondId == null || persoondId.isEmpty())
                && (magazijnId == null || magazijnId.isEmpty())) {
            List<ContactMagazijn> contactMagazijnen = contactMagazijnRepository.findAll();

            if (contactMagazijnen.isEmpty())
                return ResponseEntity.notFound()
                                     .build();

            return ResponseEntity.ok()
                                 .body(contactMagazijnen);
        }

        List<ContactMagazijn> contactMagazijnen = new ArrayList<>();

        if (persoondId != null && !persoondId.isEmpty())
            contactMagazijnen.addAll(contactMagazijnRepository.findAllByPersoonIdIsIn(persoondId));
        if (magazijnId != null && !magazijnId.isEmpty())
            contactMagazijnen.addAll(contactMagazijnRepository.findAllByMagazijnIdIsIn(magazijnId));

        contactMagazijnen = RemoveDuplicates.removeDuplicates(contactMagazijnen);

        if (contactMagazijnen.isEmpty())
            return ResponseEntity.notFound()
                                 .build();

        return ResponseEntity.ok()
                             .body(contactMagazijnen);
    }

    @GetMapping ("/{id}")
    public ResponseEntity findById (@PathVariable String id) {
        Optional<ContactMagazijn> c = contactMagazijnRepository.findById(id);

        if (c.isPresent())
            return ResponseEntity.ok()
                                 .body(c.get());

        return ResponseEntity.notFound()
                             .build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody ContactMagazijn contactMagazijn) {
        try {
            if (!validateContactMagazijn(contactMagazijn))
                return ResponseEntity.badRequest()
                                     .build();

            Optional<ContactMagazijn> optionalContactMagazijn =
                    contactMagazijnRepository.findByMagazijnIdAndPersoonId(
                            contactMagazijn.getMagazijnId(), contactMagazijn.getPersoonId());

            if (optionalContactMagazijn.isPresent())
                return ResponseEntity.badRequest()
                                     .build();

            // ignore ID when creating, will get automagically generated by DB
            contactMagazijn.setId(null);
            ContactMagazijn c = contactMagazijnRepository.save(contactMagazijn);

            return new ResponseEntity(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (
            @PathVariable String id,
            @RequestBody ContactMagazijn contactMagazijn) {
        try {
            if (!validateContactMagazijnId(contactMagazijn))
                return ResponseEntity.badRequest()
                                     .build();

            Optional<ContactMagazijn> c = contactMagazijnRepository.findById(id);

            if (c.isEmpty())
                return ResponseEntity.notFound()
                                     .build();

            contactMagazijn.setId(c.get()
                                   .getId());
            ContactMagazijn result = contactMagazijnRepository.save(contactMagazijn);

            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        try {
            Optional<ContactMagazijn> c = contactMagazijnRepository.findById(id);

            if (c.isEmpty())
                return ResponseEntity.badRequest()
                                     .build();

            contactMagazijnRepository.delete(c.get());

            return ResponseEntity.noContent()
                                 .build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    private boolean validateContactMagazijnId (ContactMagazijn c) {
        if (c.getId()
             .isEmpty())
            return false;
        return validateContactMagazijn(c);
    }

    private boolean validateContactMagazijn (ContactMagazijn c) {
        return validatePersoonId(c.getPersoonId())
                && validateMagazijnId(c.getMagazijnId())
                && validateOpmerking(c.getOpmerking());
    }

    private boolean validatePersoonId (String persoonId) {
        if (persoonId == null || persoonId.isEmpty()) return false;
        List<ContactMagazijn> cm = contactMagazijnRepository.findAllByPersoonId(persoonId);
        return !cm.isEmpty();
    }

    private boolean validateMagazijnId (String magazijnId) {
        if (magazijnId == null || magazijnId.isEmpty()) return false;
        List<ContactMagazijn> cm = contactMagazijnRepository.findAllByMagazijnId(magazijnId);
        return !cm.isEmpty();
    }

    private boolean validateOpmerking (String opmerking) {
        return opmerking != null && !opmerking.isEmpty();
    }
}
