package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.authorization.JWTChecker;
import com.brielage.uitleendienst.authorization.OriginChecker;
import com.brielage.uitleendienst.authorization.Permission;
import com.brielage.uitleendienst.models.BeschikbaarItem;
import com.brielage.uitleendienst.models.Magazijn;
import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.brielage.uitleendienst.repositories.BeschikbaarItemRepository;
import com.brielage.uitleendienst.repositories.MagazijnRepository;
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
import java.util.stream.Collectors;

@SuppressWarnings ("rawtypes")
@RestController
@RequestMapping (value = "/beschikbaaritem")
public class BeschikbaarItemController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private BeschikbaarItemRepository beschikbaarItemRepository;
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UitleenbaarItemRepository uitleenbaarItemRepository;
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private MagazijnRepository        magazijnRepository;

    @GetMapping (value = { "/", "" })
    public ResponseEntity findByProperties (
            @RequestParam (required = false) List<String> uitleenbaarItemId,
            @RequestParam (required = false) List<String> magazijnId,
            @RequestParam (required = false) List<String> categorieId,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("beschikbaarItem.get*");

        if (!OriginChecker.checkOrigin(origin))
            return Responder.respondBadRequest("origin not allowed " + origin);

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        if (!JWTChecker.checkPermission(token, Permission.ADMIN))
            return Responder.respondForbidden();

        List<BeschikbaarItem> returnValue = new ArrayList<>();

        //return findAll() if no properties
        if ((uitleenbaarItemId == null || uitleenbaarItemId.isEmpty())
                && (magazijnId == null || magazijnId.isEmpty())
                && (categorieId == null || categorieId.isEmpty())) {
            APILogger.logRequest("beschikbaaritem.findAll");
            returnValue = beschikbaarItemRepository.findAll();

            if (returnValue.isEmpty()) return Responder.respondNotFound();

            return Responder.respondOk(returnValue);
        }
        List<UitleenbaarItem> uitleenbaarItems;
        List<String>          uitleenbaarItemIds;

        //add all elements found by the properties to returnValue
        if (uitleenbaarItemId != null && !uitleenbaarItemId.isEmpty()) {
            APILogger.logRequest("beschikbaaritem.findAllByUitleenbaarItemIdIsIn");
            returnValue.addAll(
                    beschikbaarItemRepository.findAllByUitleenbaarItemIdIsIn(uitleenbaarItemId));
        }

        if (magazijnId != null && !magazijnId.isEmpty()) {
            APILogger.logRequest("beschikbaaritem.findAllByMagazijnIdIsIn");
            returnValue.addAll(beschikbaarItemRepository.findAllByMagazijnIdIsIn(magazijnId));
        }

        if (categorieId != null && !categorieId.isEmpty()) {
            APILogger.logRequest("beschikbaaritem.findAllByCategorieIdIsIn");
            uitleenbaarItems   = uitleenbaarItemRepository.findAllByCategorieIdIsIn(categorieId);
            uitleenbaarItemIds = uitleenbaarItems.stream()
                                                 .map(UitleenbaarItem::getId)
                                                 .collect(Collectors.toList());
            returnValue.addAll(
                    beschikbaarItemRepository.findAllByUitleenbaarItemIdIsIn(uitleenbaarItemIds));
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
        APILogger.logRequest("beschikbaaritem.findById", id);

        if (!OriginChecker.checkOrigin(origin))
            return Responder.respondBadRequest("origin not allowed " + origin);

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        if (!JWTChecker.checkPermission(token, Permission.ADMIN))
            return Responder.respondForbidden();

        Optional<BeschikbaarItem> b = beschikbaarItemRepository.findById(id);

        if (b.isEmpty()) return Responder.respondNotFound();

        return Responder.respondOk(b.get());
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (
            @RequestBody BeschikbaarItem beschikbaarItem,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("beschikbaarItem.create", beschikbaarItem.toString());

        if (!OriginChecker.checkOrigin(origin))
            return Responder.respondBadRequest("origin not allowed " + origin);

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        if (!JWTChecker.checkPermission(token, Permission.ADMIN))
            return Responder.respondForbidden();

        try {
            if (!validateBeschikbaarItem(beschikbaarItem))
                return Responder.respondBadRequest("not valid");

            Optional<BeschikbaarItem> optionalBeschikbaarItem =
                    beschikbaarItemRepository.findByUitleenbaarItemIdAndMagazijnId(
                            beschikbaarItem.getUitleenbaarItemId(),
                            beschikbaarItem.getMagazijnId());

            if (optionalBeschikbaarItem.isPresent())
                return Responder.respondBadRequest("already present");

            beschikbaarItem.setId(null);
            BeschikbaarItem b = beschikbaarItemRepository.save(beschikbaarItem);

            return Responder.respondCreated(b);
        } catch (Exception e) {return Responder.respondBadRequest(e.getMessage());}
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (
            @PathVariable String id,
            @RequestBody BeschikbaarItem beschikbaarItem,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("beschikbaarItem.put", id);

        if (!OriginChecker.checkOrigin(origin))
            return Responder.respondBadRequest("origin not allowed " + origin);

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        if (!JWTChecker.checkPermission(token, Permission.ADMIN))
            return Responder.respondForbidden();

        try {
            if (!validateBeschikbaarItemId(beschikbaarItem))
                return Responder.respondBadRequest("not valid");

            Optional<BeschikbaarItem> b = beschikbaarItemRepository.findById(id);

            if (b.isEmpty())
                return Responder.respondNotFound();

            beschikbaarItem.setId(b.get()
                                   .getId());
            BeschikbaarItem result = beschikbaarItemRepository.save(beschikbaarItem);

            return Responder.respondCreated(result);
        } catch (Exception e) {return Responder.respondBadRequest(e.getMessage());}
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (
            @PathVariable String id,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("beschikbaarItem.delete", id);

        if (!OriginChecker.checkOrigin(origin))
            return Responder.respondBadRequest("origin not allowed " + origin);

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        if (!JWTChecker.checkPermission(token, Permission.ADMIN))
            return Responder.respondForbidden();

        try {
            Optional<BeschikbaarItem> b = beschikbaarItemRepository.findById(id);

            if (b.isEmpty())
                return Responder.respondNotFound();

            beschikbaarItemRepository.delete(b.get());

            return Responder.respondNoContent("deleted");
        } catch (Exception e) {return Responder.respondBadRequest(e.getMessage());}
    }

    private boolean validateBeschikbaarItemId (BeschikbaarItem b) {
        if (b.getId()
             .isEmpty())
            return false;
        return validateBeschikbaarItem(b);
    }

    private boolean validateBeschikbaarItem (BeschikbaarItem b) {
        return validateMagazijnId(b.getMagazijnId())
                && validateUitleenbaarItemId(b.getUitleenbaarItemId())
                && b.getAantalTotaal() > 0
                && b.getAantalBeschikbaar() > 0
                && b.getAantalGereserveerd() > 0;
    }

    private boolean validateUitleenbaarItemId (String uitleenbaarItemId) {
        if (uitleenbaarItemId == null || uitleenbaarItemId.isEmpty()) return false;
        Optional<UitleenbaarItem> ui = uitleenbaarItemRepository.findById(uitleenbaarItemId);
        return ui.isPresent();
    }

    private boolean validateMagazijnId (String magazijnId) {
        if (magazijnId == null || magazijnId.isEmpty()) return false;
        Optional<Magazijn> m = magazijnRepository.findById(magazijnId);
        return m.isPresent();
    }
}
