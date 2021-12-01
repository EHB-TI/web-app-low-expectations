package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.Categorie;
import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.brielage.uitleendienst.repositories.CategorieRepository;
import com.brielage.uitleendienst.repositories.UitleenbaarItemRepository;
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
@RequestMapping (value = "/uitleenbaaritem")
public class UitleenbaarItemController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UitleenbaarItemRepository uitleenbaarItemRepository;
    @Autowired
    private CategorieRepository       categorieRepository;

    @GetMapping (value = { "/", "" })
    public ResponseEntity findByProperties (
            @RequestParam (required = false) List<String> categorieId,
            @RequestParam (required = false) List<String> naam) {
        List<UitleenbaarItem> returnValue = new ArrayList<>();

        //return findAll() if no properties
        if ((categorieId == null || categorieId.isEmpty())
                && (naam == null || naam.isEmpty())) {
            returnValue = uitleenbaarItemRepository.findAll();

            if (returnValue.isEmpty())
                return ResponseEntity.notFound()
                                     .build();

            return ResponseEntity.ok()
                                 .body(returnValue);
        }

        //add all elements found by the properties to returnValue
        if (categorieId != null && !categorieId.isEmpty())
            returnValue.addAll(uitleenbaarItemRepository.findAllByCategorieIdIsIn(categorieId));
        if (naam != null && !naam.isEmpty())
            returnValue.addAll(uitleenbaarItemRepository.findAllByNaamIsIn(naam));

        if (returnValue.isEmpty())
            return ResponseEntity.notFound()
                                 .build();

        //remove duplicates
        returnValue = RemoveDuplicates.removeDuplicates(returnValue);

        return ResponseEntity.ok()
                             .body(returnValue);
    }

    @GetMapping ("/{id}")
    public ResponseEntity findById (@PathVariable String id) {
        Optional<UitleenbaarItem> u = uitleenbaarItemRepository.findById(id);

        if (u.isPresent())
            return ResponseEntity.ok()
                                 .body(u.get());
        return ResponseEntity.notFound()
                             .build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody UitleenbaarItem uitleenbaarItem) {
        try {
            if (!validateUitleenbaarItem(uitleenbaarItem)) {
                APILogger.logFail("not valid");
                return ResponseEntity.badRequest()
                                     .build();
            }

            Optional<UitleenbaarItem> optionalUitleenbaarItem =
                    uitleenbaarItemRepository.findByNaamAndCategorieId(
                            uitleenbaarItem.getNaam(), uitleenbaarItem.getCategorieId());

            if (optionalUitleenbaarItem.isPresent()) {
                APILogger.logFail("not present");
                return ResponseEntity.badRequest()
                                     .build();
            }

            uitleenbaarItem.setId(null);
            UitleenbaarItem u = uitleenbaarItemRepository.save(uitleenbaarItem);

            return new ResponseEntity(u, HttpStatus.CREATED);
        } catch (Exception e) {
            APILogger.logException(e.getMessage());
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (
            @PathVariable String id,
            @RequestBody UitleenbaarItem uitleenbaarItem) {
        try {
            if (!validateUitleenbaarItemId(uitleenbaarItem))
                return ResponseEntity.badRequest()
                                     .build();

            Optional<UitleenbaarItem> u = uitleenbaarItemRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.notFound()
                                     .build();

            uitleenbaarItem.setId(u.get()
                                   .getId());
            UitleenbaarItem result = uitleenbaarItemRepository.save(uitleenbaarItem);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        try {
            Optional<UitleenbaarItem> u = uitleenbaarItemRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.badRequest()
                                     .build();

            uitleenbaarItemRepository.delete(u.get());
            return ResponseEntity.noContent()
                                 .build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    private boolean validateUitleenbaarItemId (UitleenbaarItem u) {
        if (u.getId()
             .isEmpty())
            return false;
        return validateUitleenbaarItem(u);
    }

    private boolean validateUitleenbaarItem (UitleenbaarItem u) {
        if (u.getNaam()
             .isEmpty()) APILogger.logFail("naam empty");
        if (u.getEenheid() < 0) APILogger.logFail("eenheid < 0");
        if (u.getPrijs() < 0) APILogger.logFail("prijs < 0");
        if (u.getPeriode() == null) APILogger.logFail("periode null");
        return !u.getNaam()
                 .isEmpty()
                && u.getEenheid() > 0
                && u.getPrijs() > 0
                && u.getPeriode() != null
                && validateCategorieId(u.getCategorieId());
    }

    private boolean validateCategorieId (String categorieId) {
        if (categorieId == null || categorieId.isEmpty()) {
            APILogger.logFail("categorieId null or empty");
            return false;
        }
        Optional<Categorie> c = categorieRepository.findById(categorieId);
        if (c.isEmpty()) APILogger.logFail("optional categorie empty");
        return c.isPresent();
    }
}
