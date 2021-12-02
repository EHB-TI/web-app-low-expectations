package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.Categorie;
import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.brielage.uitleendienst.repositories.CategorieRepository;
import com.brielage.uitleendienst.repositories.UitleenbaarItemRepository;
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
            @RequestParam (required = false) List<String> naam,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        List<UitleenbaarItem> returnValue = new ArrayList<>();

        //return findAll() if no properties
        if ((categorieId == null || categorieId.isEmpty())
                && (naam == null || naam.isEmpty())) {
            APILogger.logRequest("uitleenbaarItem.findAll");
            returnValue = uitleenbaarItemRepository.findAll();

            if (returnValue.isEmpty()) return Responder.respondNotFound();

            return Responder.respondOk(returnValue);
        }

        //add all elements found by the properties to returnValue
        if (categorieId != null && !categorieId.isEmpty()){
            APILogger.logRequest("uitleenbaarItem.findAllByCategorieIdIsIn");
            returnValue.addAll(uitleenbaarItemRepository.findAllByCategorieIdIsIn(categorieId));
        }

        if (naam != null && !naam.isEmpty()){
            APILogger.logRequest("uitleenbaarItem.findAllByNaamIsIn");
            returnValue.addAll(uitleenbaarItemRepository.findAllByNaamIsIn(naam));
        }


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
        APILogger.logRequest("uitleenbaarItem.findById", id);

        Optional<UitleenbaarItem> u = uitleenbaarItemRepository.findById(id);

        if (u.isEmpty()) return Responder.respondNotFound();

        return Responder.respondOk(u.get());
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (
            @RequestBody UitleenbaarItem uitleenbaarItem,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("uitleenbaarItem.create", uitleenbaarItem.toString());

        try {
            if (!validateUitleenbaarItem(uitleenbaarItem)) {
                return Responder.respondBadRequest("not valid");
            }

            Optional<UitleenbaarItem> optionalUitleenbaarItem =
                    uitleenbaarItemRepository.findByNaamAndCategorieId(
                            uitleenbaarItem.getNaam(), uitleenbaarItem.getCategorieId());

            if (optionalUitleenbaarItem.isPresent()) {
                return Responder.respondBadRequest("already present");
            }

            uitleenbaarItem.setId(null);
            UitleenbaarItem u = uitleenbaarItemRepository.save(uitleenbaarItem);

            return Responder.respondCreated(u);
        } catch (Exception e) {
            APILogger.logException(e.getMessage());
            return Responder.respondBadRequest(e.getMessage());
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (
            @PathVariable String id,
            @RequestBody UitleenbaarItem uitleenbaarItem,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("uitleenbaarItem.put", id);

        try {
            if (!validateUitleenbaarItemId(uitleenbaarItem))
                return Responder.respondBadRequest("not valid");

            Optional<UitleenbaarItem> u = uitleenbaarItemRepository.findById(id);

            if (u.isEmpty())
                return Responder.respondNotFound();

            uitleenbaarItem.setId(u.get()
                                   .getId());
            UitleenbaarItem result = uitleenbaarItemRepository.save(uitleenbaarItem);

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
        APILogger.logRequest("uitleenbaarItem.delete", id);

        try {
            Optional<UitleenbaarItem> u = uitleenbaarItemRepository.findById(id);

            if (u.isEmpty())
                return Responder.respondNotFound();

            uitleenbaarItemRepository.delete(u.get());

            return Responder.respondNoContent("deleted");
        } catch (Exception e) {
            return Responder.respondBadRequest(e.getMessage());
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
