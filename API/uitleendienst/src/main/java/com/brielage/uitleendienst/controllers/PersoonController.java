package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.Persoon;
import com.brielage.uitleendienst.repositories.PersoonRepository;
import com.brielage.uitleendienst.responses.Responder;
import com.brielage.uitleendienst.tools.APILogger;
import com.brielage.uitleendienst.tools.RemoveDuplicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings ("rawtypes")
@RestController
@RequestMapping (value = "/persoon")
public class PersoonController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersoonRepository persoonRepository;

    @RequestMapping (value = { "/", "" }, method = RequestMethod.GET)
    public ResponseEntity findByProperties (
            @RequestParam (required = false) List<String> voornaam,
            @RequestParam (required = false) List<String> familienaam,
            @RequestParam (required = false) List<String> email,
            @RequestParam (required = false) List<String> username,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        List<Persoon> returnValue = new ArrayList<>();

        //return findAll() if no properties
        if ((voornaam == null || voornaam.isEmpty())
                && (familienaam == null || familienaam.isEmpty())
                && (email == null || email.isEmpty())
                && (username == null || username.isEmpty())) {
            returnValue = persoonRepository.findAll();

            if (returnValue.isEmpty()) return Responder.respondNotFound();

            return Responder.respondOk(returnValue);
        }

        //add all elements found by the properties to returnValue
        if (voornaam != null && !voornaam.isEmpty()) {
            APILogger.logRequest("persoon.findAllByVoornaamIsIn");
            returnValue.addAll(persoonRepository.findAllByVoornaamIsIn(voornaam));
        }

        if (familienaam != null && !familienaam.isEmpty()) {
            APILogger.logRequest("persoon.findAllByFamilienaamIsIn");
            returnValue.addAll(persoonRepository.findAllByFamilienaamIsIn(familienaam));
        }

        if (email != null && !email.isEmpty()) {
            APILogger.logRequest("persoon.findAllByEmailIsIn");
            returnValue.addAll(persoonRepository.findAllByEmailIsIn(email));
        }

        if (username != null && !username.isEmpty()) {
            APILogger.logRequest("persoon.findAllByUsernameIsIn");
            returnValue.addAll(persoonRepository.findAllByUsernameIsIn(username));
        }

        //remove duplicates
        returnValue = RemoveDuplicates.removeDuplicates(returnValue);

        if (returnValue.isEmpty()) return Responder.respondNotFound();

        return Responder.respondOk(returnValue);
    }

    @GetMapping ("/{id}")
    public ResponseEntity findById (
            @PathVariable String id,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("persoon.findById", id);
        Optional<Persoon> p = persoonRepository.findById(id);

        if (p.isEmpty()) return Responder.respondNotFound();

        return Responder.respondOk(p.get());
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (
            @RequestBody Persoon persoon,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("persoon.create", persoon.toString());
        try {
            if (!validatePersoon(persoon))
                return Responder.respondBadRequest("not valid");

            Persoon p = persoonRepository.save(persoon);

            return Responder.respondCreated(p);
        } catch (Exception e) {
            return Responder.respondBadRequest(e.getMessage());
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (
            @PathVariable String id,
            @RequestBody Persoon persoon,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("persoon.put", id);

        try {
            if (!validatePersoonId(persoon))
                return Responder.respondBadRequest("not valid");

            Optional<Persoon> p = persoonRepository.findById(id);

            if (p.isEmpty())
                return Responder.respondNotFound();


            persoon.setId(p.get()
                           .getId());
            Persoon result = persoonRepository.save(persoon);

            return Responder.respondCreated(result);
        } catch (Exception e) {
            return Responder.respondBadRequest(e.getMessage());
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (
            @PathVariable String id,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("persoon.delete", id);

        try {
            Optional<Persoon> p = persoonRepository.findById(id);

            if (p.isEmpty())
                return Responder.respondNotFound();

            persoonRepository.delete(p.get());

            return Responder.respondNoContent("deleted");
        } catch (Exception e) {
            return Responder.respondBadRequest(e.getMessage());
        }
    }

    private boolean validatePersoonId (Persoon p) {
        if (p.getId()
             .isEmpty())
            return false;
        return validatePersoon(p);
    }

    private boolean validatePersoon (Persoon p) {
        return !p.getVoornaam()
                 .isEmpty()
                && !p.getFamilienaam()
                     .isEmpty()
                && !p.getAdres()
                     .isEmpty()
                && !p.getTelefoon()
                     .isEmpty()
                && !p.getEmail()
                     .isEmpty()
                && !p.getUsername()
                     .isEmpty();
    }
}
