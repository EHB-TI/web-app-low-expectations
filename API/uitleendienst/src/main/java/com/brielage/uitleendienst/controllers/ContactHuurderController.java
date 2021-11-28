package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.*;
import com.brielage.uitleendienst.repositories.OrganisatieRepository;
import com.brielage.uitleendienst.repositories.PersoonRepository;
import com.brielage.uitleendienst.tools.APILogger;
import com.brielage.uitleendienst.repositories.ContactHuurderRepository;
import com.brielage.uitleendienst.tools.RemoveDuplicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/contacthuurder")
public class ContactHuurderController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private ContactHuurderRepository contactHuurderRepository;
    @Autowired
    private PersoonRepository persoonRepository;
    @Autowired
    private OrganisatieRepository organisatieRepository;

    @GetMapping (value = { "/", "" })
    public ResponseEntity findByProperties (
            @RequestParam (required = false) List<String> persoondId,
            @RequestParam (required = false) List<String> organisatieId
    ) {
        List<ContactHuurder> returnValue = new ArrayList<>();

        //return findAll() if no properties
        if ((persoondId == null || persoondId.isEmpty())
                && (organisatieId == null || organisatieId.isEmpty())) {
            returnValue = contactHuurderRepository.findAll();

            if (returnValue.isEmpty())
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok().body(returnValue);
        }

        //add all elements found by the properties to returnValue
        if (persoondId != null && !persoondId.isEmpty())
            returnValue.addAll(contactHuurderRepository.findAllByPersoondIdIsIn(persoondId));
        if (organisatieId != null && !organisatieId.isEmpty())
            returnValue.addAll(contactHuurderRepository.findAllByOrganisatieIdIsIn(organisatieId));

        if (returnValue.isEmpty())
            return ResponseEntity.notFound().build();

        //remove duplicates
        returnValue = RemoveDuplicates.removeDuplicates(returnValue);

        return ResponseEntity.ok().body(returnValue);
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
        try {
            if (!validateContactHuurder(contactHuurder))
                return ResponseEntity.badRequest()
                        .build();

            Optional<ContactHuurder> optionalContactHuurder =
                    contactHuurderRepository.findByPersoonIdAndOrganisatieId(
                            contactHuurder.getPersoonId(), contactHuurder.getOrganisatieId());

            if (optionalContactHuurder.isPresent())
                return ResponseEntity.badRequest()
                        .build();

            contactHuurder.setId(null);
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
        return validatePersoonId(c.getPersoonId())
                && validateOrganisatieId(c.getOrganisatieId());
    }

    private boolean validatePersoonId (String persoonId) {
        if (persoonId == null || persoonId.isEmpty()) return false;
        Optional<Persoon> p = persoonRepository.findById(persoonId);
        return p.isPresent();
    }

    private boolean validateOrganisatieId (String organisatieId) {
        if (organisatieId == null || organisatieId.isEmpty()) return false;
        Optional<Organisatie> o = organisatieRepository.findById(organisatieId);
        return o.isPresent();
    }
}
