package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.authorization.JWTChecker;
import com.brielage.uitleendienst.authorization.Permission;
import com.brielage.uitleendienst.models.Magazijn;
import com.brielage.uitleendienst.models.Persoon;
import com.brielage.uitleendienst.models.Uitlening;
import com.brielage.uitleendienst.repositories.MagazijnRepository;
import com.brielage.uitleendienst.repositories.PersoonRepository;
import com.brielage.uitleendienst.repositories.UitleningRepository;
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
@RequestMapping (value = "/uitlening")
public class UitleningController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UitleningRepository uitleningRepository;
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private MagazijnRepository  magazijnRepository;
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersoonRepository   persoonRepository;

    @GetMapping (value = { "/", "" })
    public ResponseEntity findByProperties (
            @RequestParam (required = false) List<String> persoonId,
            @RequestParam (required = false) List<String> magazijnId,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("uitlening.get*");

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        List<Uitlening> returnValue = new ArrayList<>();

        //return findAll() if no properties
        if ((persoonId == null || persoonId.isEmpty())
                && (magazijnId == null || magazijnId.isEmpty())) {
            if (!JWTChecker.checkPermission(token, Permission.ADMIN))
                return Responder.respondForbidden();

            returnValue = uitleningRepository.findAll();

            if (returnValue.isEmpty()) return Responder.respondNotFound();

            return Responder.respondOk(returnValue);
        }

        //add all elements found by the properties to returnValue
        if (persoonId != null && !persoonId.isEmpty()) {
            List<Persoon> personen = persoonRepository.findAllByUsernameIsIn(
                    JWTChecker.getUsername(token));

            if (personen.isEmpty()) return Responder.respondForbidden();

            if (personen.size() == 1 && persoonId.size() == 1) {
                Optional<Persoon> p = persoonRepository.findById(persoonId.get(0));

                if (p.isEmpty() || !JWTChecker.checkUsername(token, p.get()
                                                                     .getUsername()))
                    return Responder.respondForbidden();
            }

            if (persoonId.size() > 1)
                if (!JWTChecker.checkPermission(token, Permission.ADMIN))
                    return Responder.respondForbidden();

            returnValue.addAll(uitleningRepository.findAllByPersoonIdIsIn(persoonId));
        }
        if (magazijnId != null && !magazijnId.isEmpty())
            returnValue.addAll(uitleningRepository.findAllByMagazijnIdIsIn(magazijnId));

        if (returnValue.isEmpty()) return Responder.respondNotFound();

        //remove duplicates
        returnValue = RemoveDuplicates.removeDuplicates(returnValue);

        return Responder.respondOk(returnValue);
    }

    @GetMapping ("/{id}")
    public ResponseEntity findById (
            @PathVariable String id,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("uitlening.findById", id);

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        Optional<Uitlening> u = uitleningRepository.findById(id);

        if (u.isPresent()) {
            Optional<Persoon> optionalPersoon = persoonRepository.findById(u.get()
                                                                            .getPersoonId());

            if (optionalPersoon.isEmpty()) return Responder.respondForbidden();

            if (!JWTChecker.checkUsername(token, optionalPersoon.get()
                                                                .getUsername()))
                return Responder.respondForbidden();
        }

        if (u.isEmpty())
            if (!JWTChecker.checkPermission(token, Permission.ADMIN))
                return Responder.respondForbidden();
            else
                return Responder.respondNotFound();

        return Responder.respondOk(u.get());
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (
            @RequestBody Uitlening uitlening,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("uitlening.create", uitlening.toString());

        if (uitlening.getPersoonId() != null && !uitlening.getPersoonId()
                                                          .isEmpty()) {
            Optional<Persoon> optionalPersoon =
                    persoonRepository.findById(uitlening.getPersoonId());

            if (optionalPersoon.isEmpty()
                    || !JWTChecker.checkUsername(token, optionalPersoon.get()
                                                                       .getUsername()))
                return Responder.respondForbidden();
        }

        try {
            if (!validateUitlening(uitlening))
                return ResponseEntity.badRequest()
                                     .build();

            uitlening.setId(null);
            Uitlening u = uitleningRepository.save(uitlening);

            return Responder.respondCreated(u);
        } catch (Exception e) {return Responder.respondBadRequest(e.getMessage());}
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (
            @PathVariable String id,
            @RequestBody Uitlening uitlening,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("uitlening.put", id);

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        try {
            Optional<Uitlening> u = uitleningRepository.findById(id);

            if (u.isEmpty()) return Responder.respondForbidden();

            Optional<Persoon> optionalPersoon = persoonRepository.findById(u.get()
                                                                            .getPersoonId());

            if (optionalPersoon.isEmpty()) return Responder.respondForbidden();

            if (!JWTChecker.checkUsername(token, optionalPersoon.get()
                                                                .getUsername()))
                return Responder.respondForbidden();

            if (!validateUitleningId(uitlening))
                return ResponseEntity.badRequest()
                                     .build();

            uitlening.setId(u.get()
                             .getId());
            Uitlening result = uitleningRepository.save(uitlening);

            return Responder.respondCreated(result);
        } catch (Exception e) {return Responder.respondBadRequest(e.getMessage());}
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (
            @PathVariable String id,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("uitlening.delete", id);

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        if (!JWTChecker.checkPermission(token, Permission.ADMIN))
            return Responder.respondForbidden();

        try {
            Optional<Uitlening> u = uitleningRepository.findById(id);

            if (u.isEmpty()) return Responder.respondNotFound();

            uitleningRepository.delete(u.get());

            return Responder.respondNoContent("deleted");
        } catch (Exception e) {return Responder.respondBadRequest(e.getMessage());}
    }

    private boolean validateUitleningId (Uitlening u) {
        if (u.getId()
             .isEmpty())
            return false;
        return validateUitlening(u);
    }

    private boolean validateUitlening (Uitlening u) {
        return validatePersoonId(u.getPersoonId())
                && validateMagazijnId(u.getMagazijnId())
                && (u.getStart() != null && !u.getStart()
                                              .isEmpty());
    }

    private boolean validatePersoonId (String persoonId) {
        if (persoonId == null || persoonId.isEmpty()) return false;
        Optional<Persoon> o = persoonRepository.findById(persoonId);
        return o.isPresent();
    }

    private boolean validateMagazijnId (String magazijnId) {
        if (magazijnId == null || magazijnId.isEmpty()) return false;
        Optional<Magazijn> m = magazijnRepository.findById(magazijnId);
        return m.isPresent();
    }
}
