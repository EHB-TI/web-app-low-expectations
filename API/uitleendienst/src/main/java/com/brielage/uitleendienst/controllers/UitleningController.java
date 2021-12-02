package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.Magazijn;
import com.brielage.uitleendienst.models.Persoon;
import com.brielage.uitleendienst.models.Uitlening;
import com.brielage.uitleendienst.repositories.MagazijnRepository;
import com.brielage.uitleendienst.repositories.OrganisatieRepository;
import com.brielage.uitleendienst.repositories.PersoonRepository;
import com.brielage.uitleendienst.repositories.UitleningRepository;
import com.brielage.uitleendienst.tools.APILogger;
import com.brielage.uitleendienst.tools.RemoveDuplicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/uitlening")
public class UitleningController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UitleningRepository   uitleningRepository;
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private OrganisatieRepository organisatieRepository;
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private MagazijnRepository    magazijnRepository;
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersoonRepository     persoonRepository;

    @GetMapping (value = { "/", "" })
    public ResponseEntity findByProperties (
            @RequestParam (required = false) List<String> organisatieId,
            @RequestParam (required = false) List<String> magazijnId,
            @RequestHeader ("Authorization") String token) {
        List<Uitlening> returnValue = new ArrayList<>();

        //return findAll() if no properties
        if ((organisatieId == null || organisatieId.isEmpty())
                && (magazijnId == null || magazijnId.isEmpty())) {
            returnValue = uitleningRepository.findAll();

            if (returnValue.isEmpty())
                return ResponseEntity.notFound()
                                     .build();
            return ResponseEntity.ok()
                                 .body(returnValue);
        }

        //add all elements found by the properties to returnValue
        if (organisatieId != null && !organisatieId.isEmpty())
            returnValue.addAll(uitleningRepository.findAllByPersoonIdIsIn(organisatieId));
        if (magazijnId != null && !magazijnId.isEmpty())
            returnValue.addAll(uitleningRepository.findAllByMagazijnIdIsIn(magazijnId));

        if (returnValue.isEmpty())
            return ResponseEntity.notFound()
                                 .build();

        //remove duplicates
        returnValue = RemoveDuplicates.removeDuplicates(returnValue);

        return ResponseEntity.ok()
                             .body(returnValue);
    }

    @GetMapping ("/{id}")
    public ResponseEntity findById (
            @PathVariable String id,
            @RequestHeader ("Authorization") String token) {
        APILogger.logRequest("uitlening.findById", id);
        Optional<Uitlening> u = uitleningRepository.findById(id);

        if (u.isPresent())
            return ResponseEntity.ok()
                                 .body(u.get());
        return ResponseEntity.notFound()
                             .build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (
            @RequestBody Uitlening uitlening,
            @RequestHeader ("Authorization") String token) {
        APILogger.logRequest("uitlening.create", uitlening.toString());
        try {
            if (!validateUitlening(uitlening))
                return ResponseEntity.badRequest()
                                     .build();

            uitlening.setId(null);
            Uitlening u = uitleningRepository.save(uitlening);

            return new ResponseEntity(u, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (
            @PathVariable String id,
            @RequestBody Uitlening uitlening,
            @RequestHeader ("Authorization") String token) {
        APILogger.logRequest("uitlening.put", id);
        try {
            if (!validateUitleningId(uitlening))
                return ResponseEntity.badRequest()
                                     .build();

            Optional<Uitlening> u = uitleningRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.notFound()
                                     .build();

            uitlening.setId(u.get()
                             .getId());
            Uitlening result = uitleningRepository.save(uitlening);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (
            @PathVariable String id,
            @RequestHeader ("Authorization") String token) {
        APILogger.logRequest("uitlening.delete", id);
        try {
            Optional<Uitlening> u = uitleningRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.badRequest()
                                     .build();

            uitleningRepository.delete(u.get());
            return ResponseEntity.noContent()
                                 .build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
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
