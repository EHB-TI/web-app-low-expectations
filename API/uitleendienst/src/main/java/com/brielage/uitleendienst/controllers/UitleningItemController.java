package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.authorization.JWTChecker;
import com.brielage.uitleendienst.authorization.OriginChecker;
import com.brielage.uitleendienst.authorization.Permission;
import com.brielage.uitleendienst.models.Persoon;
import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.brielage.uitleendienst.models.Uitlening;
import com.brielage.uitleendienst.models.UitleningItem;
import com.brielage.uitleendienst.repositories.PersoonRepository;
import com.brielage.uitleendienst.repositories.UitleenbaarItemRepository;
import com.brielage.uitleendienst.repositories.UitleningItemRepository;
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
@RequestMapping (value = "/uitleningitem")
public class UitleningItemController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UitleningItemRepository   uitleningItemRepository;
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UitleenbaarItemRepository uitleenbaarItemRepository;
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UitleningRepository       uitleningRepository;
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersoonRepository         persoonRepository;

    @SuppressWarnings ("rawtypes")
    @GetMapping (value = { "/", "" })
    public ResponseEntity findByProperties (
            @RequestParam (required = false) List<String> uitleenbaarItemId,
            @RequestParam (required = false) List<String> uitleningId,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("uitleningItem.get*");

        if (!OriginChecker.checkOrigin(origin))
            return Responder.respondBadRequest("origin not allowed " + origin);

        List<UitleningItem> returnValue = new ArrayList<>();

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        //return findAll() if no properties
        if ((uitleenbaarItemId == null || uitleenbaarItemId.isEmpty())
                && (uitleningId == null || uitleningId.isEmpty())) {
            APILogger.logRequest("uitleningitem.findAll");

            if (!JWTChecker.checkPermission(token, Permission.ADMIN))
                return Responder.respondForbidden();

            returnValue = uitleningItemRepository.findAll();

            if (returnValue.isEmpty()) return Responder.respondNotFound();

            return Responder.respondOk(returnValue);
        }

        if ((uitleenbaarItemId != null && !uitleenbaarItemId.isEmpty())
                && (uitleningId == null || uitleningId.isEmpty()))
            if (!JWTChecker.checkPermission(token, Permission.ADMIN))
                return Responder.respondForbidden();

        //add all elements found by the properties to returnValue

        if (uitleningId != null && !uitleningId.isEmpty()) {
            APILogger.logRequest("uitleningitem.findAllByUitleningIdIsIn");

            List<Uitlening> uitlenings = uitleningRepository.findAllById(uitleningId);

            if (uitlenings.isEmpty()) return Responder.respondForbidden();

            for (Uitlening uitlening : uitlenings) {
                Optional<Persoon> optionalPersoon =
                        persoonRepository.findById(uitlening.getPersoonId());

                if (optionalPersoon.isEmpty()
                        || (!JWTChecker.checkUsername(token, optionalPersoon.get()
                                                                            .getUsername()))
                        && !JWTChecker.checkPermission(token, Permission.ADMIN))
                    return Responder.respondForbidden();
            }

            returnValue.addAll(uitleningItemRepository.findAllByUitleningIdIsIn(uitleningId));
        }

        if (uitleenbaarItemId != null && !uitleenbaarItemId.isEmpty()) {
            APILogger.logRequest("uitleningitem.findAllByUitleenbaarItemIdIsIn");

            returnValue.addAll(
                    uitleningItemRepository.findAllByUitleenbaarItemIdIsIn(uitleenbaarItemId));
        }

        if (returnValue.isEmpty()) return Responder.respondNotFound();

        //remove duplicates
        returnValue = RemoveDuplicates.removeDuplicates(returnValue);

        return Responder.respondOk(returnValue);
    }

    @SuppressWarnings ("rawtypes")
    @GetMapping ("/{id}")
    public ResponseEntity findById (
            @PathVariable String id,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("uitleningItem.findById", id);

        if (!OriginChecker.checkOrigin(origin))
            return Responder.respondBadRequest("origin not allowed " + origin);

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        Optional<UitleningItem> u = uitleningItemRepository.findById(id);

        if (u.isEmpty()) return Responder.respondForbidden();

        Optional<Uitlening> optionalUitlening =
                uitleningRepository.findById(u.get()
                                              .getUitleningId());

        if (optionalUitlening.isEmpty()) return Responder.respondForbidden();

        Optional<Persoon> optionalPersoon = persoonRepository.findById(optionalUitlening.get()
                                                                                        .getPersoonId());

        if (optionalPersoon.isEmpty()
                || (!JWTChecker.checkUsername(token, optionalPersoon.get()
                                                                    .getUsername()))
                && !JWTChecker.checkPermission(token, Permission.ADMIN))
            return Responder.respondForbidden();

        return Responder.respondOk(u.get());
    }

    @SuppressWarnings ("rawtypes")
    @PostMapping (value = { "/", "" })
    public ResponseEntity create (
            @RequestBody List<UitleningItem> uitleningItems,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("uitleenbaarItem.create", uitleningItems.toString());

        if (!OriginChecker.checkOrigin(origin))
            return Responder.respondBadRequest("origin not allowed " + origin);

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        if (uitleningItems.isEmpty())
            return Responder.respondBadRequest("not valid, uitleningitems is null or empty");

        StringBuilder logoutput = new StringBuilder();

        for (int i = 0; i < uitleningItems.size(); i++) {
            logoutput.append(uitleningItems.get(i)
                                           .toString());
            if (i < uitleningItems.size()) logoutput.append("; ");
        }

        APILogger.logRequest("uitleningItem.createMany", logoutput.toString());

        String uitleningId = "";
        for (UitleningItem ui : uitleningItems)
            if (uitleningId.equals("")) uitleningId = ui.getUitleningId();
            else if (!uitleningId.equals(ui.getUitleningId()))
                return Responder.respondBadRequest("different uitleningIds");

        Optional<Uitlening> optionalUitlening = uitleningRepository.findById(uitleningId);

        if (optionalUitlening.isEmpty()) return Responder.respondForbidden();

        Optional<Persoon> optionalPersoon = persoonRepository.findById(optionalUitlening.get()
                                                                                        .getPersoonId());

        if (optionalPersoon.isEmpty()
                || (!JWTChecker.checkUsername(token, optionalPersoon.get()
                                                                    .getUsername()))
                && !JWTChecker.checkPermission(token, Permission.ADMIN))
            return Responder.respondForbidden();

        try {
            for (UitleningItem ui : uitleningItems) {
                if (!validateUitleningItem(ui))
                    return Responder.respondBadRequest("not valid");

                Optional<UitleningItem> optionalUitleningItem =
                        uitleningItemRepository.findByUitleenbaarItemIdAndUitleningId(
                                ui.getUitleenbaarItemId(), ui.getUitleningId());

                if (optionalUitleningItem.isPresent())
                    return Responder.respondBadRequest("already present");
            }

            List<UitleningItem> createdItems = new ArrayList<>();

            for (UitleningItem ui : uitleningItems) {
                ui.setId(null);
                UitleningItem created = uitleningItemRepository.save(ui);
                createdItems.add(created);
            }

            return Responder.respondCreated(createdItems);
        } catch (Exception e) {return Responder.respondBadRequest(e.getMessage());}
    }

    @SuppressWarnings ("rawtypes")
    @PutMapping (value = "/{id}")
    public ResponseEntity put (
            @PathVariable String id,
            @RequestBody UitleningItem uitleningItem,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("uitleningItem.put", id);

        if (!OriginChecker.checkOrigin(origin))
            return Responder.respondBadRequest("origin not allowed " + origin);

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        Optional<UitleningItem> u = uitleningItemRepository.findById(id);

        if (u.isEmpty()) return Responder.respondForbidden();

        Optional<Uitlening> optionalUitlening = uitleningRepository.findById(u.get()
                                                                              .getUitleningId());

        if (optionalUitlening.isEmpty()) return Responder.respondForbidden();

        Optional<Persoon> optionalPersoon = persoonRepository.findById(optionalUitlening.get()
                                                                                        .getPersoonId());

        if (optionalPersoon.isEmpty()
                || (!JWTChecker.checkUsername(token, optionalPersoon.get()
                                                                    .getUsername()))
                && !JWTChecker.checkPermission(token, Permission.ADMIN))
            return Responder.respondForbidden();

        try {
            if (!validateUitleningItemId(uitleningItem))
                return Responder.respondBadRequest("not valid");

            uitleningItem.setId(u.get()
                                 .getId());
            UitleningItem result = uitleningItemRepository.save(uitleningItem);

            return Responder.respondCreated(result);
        } catch (Exception e) {return Responder.respondBadRequest(e.getMessage());}
    }

    @SuppressWarnings ("rawtypes")
    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (
            @PathVariable String id,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("uitleningItem.delete", id);

        if (!OriginChecker.checkOrigin(origin))
            return Responder.respondBadRequest("origin not allowed " + origin);

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        Optional<UitleningItem> u = uitleningItemRepository.findById(id);

        if (u.isEmpty()) return Responder.respondForbidden();

        Optional<Uitlening> optionalUitlening = uitleningRepository.findById(u.get()
                                                                              .getUitleningId());

        if (optionalUitlening.isEmpty()) return Responder.respondForbidden();

        Optional<Persoon> optionalPersoon = persoonRepository.findById(optionalUitlening.get()
                                                                                        .getPersoonId());

        if (optionalPersoon.isEmpty()
                || (!JWTChecker.checkUsername(token, optionalPersoon.get()
                                                                    .getUsername()))
                && !JWTChecker.checkPermission(token, Permission.ADMIN))
            return Responder.respondForbidden();

        try {
            uitleningItemRepository.delete(u.get());

            return Responder.respondNoContent("deleted");
        } catch (Exception e) {return Responder.respondBadRequest(e.getMessage());}
    }

    private boolean validateUitleningItemId (UitleningItem u) {
        if (u.getId()
             .isEmpty()) {
            APILogger.logFail("id is empty");
            return false;
        }
        return validateUitleningItem(u);
    }

    private boolean validateUitleningItem (UitleningItem u) {
        if (u.getAantal() < 0) APILogger.logFail("aantal < 0");
        if (u.getTeruggebrachtOp() != null && u.getTeruggebrachtOp()
                                               .isEmpty()) APILogger.logFail(
                "teruggebrachtOp is empty");
        if (u.getAantalTeruggebracht() < 0) APILogger.logFail("aantalTeruggebracht < 0");
        return validateUitleningId(u.getUitleningId())
                && validateUitleenbaarItemId(u.getUitleenbaarItemId())
                && u.getAantal() > 0
                && validateTeruggebrachtOp(u.getTeruggebrachtOp())
                && u.getAantalTeruggebracht() >= 0;
    }

    // putting the json into the DynamoDB object results in all null values being
    // given a value, so a null value for this is not actually null
    // thus we check on empty or "null"
    private boolean validateTeruggebrachtOp (String teruggebrachtOp) {
        if (teruggebrachtOp == null) return true;
        if (teruggebrachtOp.isEmpty()) return true;
        return teruggebrachtOp.equals("null");
    }

    private boolean validateUitleningId (String uitleningId) {
        if (uitleningId == null) APILogger.logFail("uitleningId is null");
        assert uitleningId != null;
        if (uitleningId.isEmpty()) APILogger.logFail("uitleningId is empty");
        if (uitleningId.isEmpty()) return false;
        Optional<Uitlening> u = uitleningRepository.findById(uitleningId);
        if (u.isEmpty()) APILogger.logFail("optionalUitlening is empty");
        return u.isPresent();
    }

    private boolean validateUitleenbaarItemId (String uitleenbaarItemId) {
        if (uitleenbaarItemId == null) APILogger.logFail("uitleenbaarItemId is null");
        assert uitleenbaarItemId != null;
        if (uitleenbaarItemId.isEmpty()) APILogger.logFail("uitleenbaarItemId is empty");
        if (uitleenbaarItemId.isEmpty()) return false;
        Optional<UitleenbaarItem> ui = uitleenbaarItemRepository.findById(uitleenbaarItemId);
        if (ui.isEmpty()) APILogger.logFail("optionalUitlening is empty");
        return ui.isPresent();
    }
}
