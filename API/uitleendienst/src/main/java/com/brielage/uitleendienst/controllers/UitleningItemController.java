package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.brielage.uitleendienst.models.Uitlening;
import com.brielage.uitleendienst.models.UitleningItem;
import com.brielage.uitleendienst.repositories.UitleenbaarItemRepository;
import com.brielage.uitleendienst.repositories.UitleningItemRepository;
import com.brielage.uitleendienst.repositories.UitleningRepository;
import com.brielage.uitleendienst.responses.Responder;
import com.brielage.uitleendienst.tools.APILogger;
import com.brielage.uitleendienst.tools.RemoveDuplicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @SuppressWarnings ("rawtypes")
    @GetMapping (value = { "/", "" })
    public ResponseEntity findByProperties (
            @RequestParam (required = false) List<String> uitleenbaarItemId,
            @RequestParam (required = false) List<String> uitleningId,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        List<UitleningItem> returnValue = new ArrayList<>();

        //return findAll() if no properties
        if (uitleenbaarItemId == null || uitleenbaarItemId.isEmpty()
                && (uitleningId == null || uitleningId.isEmpty())) {
            APILogger.logRequest("uitleningitem.findAll");
            returnValue = uitleningItemRepository.findAll();

            if (returnValue.isEmpty()) return Responder.respondNotFound();

            return Responder.respondOk(returnValue);
        }

        //add all elements found by the properties to returnValue
        if (!uitleenbaarItemId.isEmpty()){
            APILogger.logRequest("uitleningitem.findAllByUitleenbaarItemIdIsIn");
            returnValue.addAll(
                    uitleningItemRepository.findAllByUitleenbaarItemIdIsIn(uitleenbaarItemId));
        }

        if (uitleningId != null && !uitleningId.isEmpty()){
            APILogger.logRequest("uitleningitem.findAllByUitleningIdIsIn");
            returnValue.addAll(uitleningItemRepository.findAllByUitleningIdIsIn(uitleningId));
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

        Optional<UitleningItem> u = uitleningItemRepository.findById(id);

        if (u.isEmpty()) return Responder.respondNotFound();

        return Responder.respondOk(u.get());
    }

    @SuppressWarnings ("rawtypes")
    @PostMapping (value = { "/", "" })
    public ResponseEntity create (
            @RequestBody List<UitleningItem> uitleningItems,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("uitleenbaarItem.create", uitleningItems.toString());

        if (uitleningItems == null || uitleningItems.isEmpty()) {
            APILogger.logFail("uitleningitems is null or empty");
            return Responder.respondBadRequest("not valid");
        }

        StringBuilder logoutput = new StringBuilder();

        for (int i = 0; i < uitleningItems.size(); i++) {
            logoutput.append(uitleningItems.get(i)
                                           .toString());
            if (i < uitleningItems.size()) logoutput.append("; ");
        }

        APILogger.logRequest("uitleningItem.createMany", logoutput.toString());

        try {
            for (UitleningItem ui : uitleningItems) {
                if (!validateUitleningItem(ui)) {
                    APILogger.logFail("not valid");
                    return Responder.respondBadRequest("not valid");
                }

                Optional<UitleningItem> optionalUitleningItem =
                        uitleningItemRepository.findByUitleenbaarItemIdAndUitleningId(
                                ui.getUitleenbaarItemId(), ui.getUitleningId());

                if (optionalUitleningItem.isPresent()) {
                    APILogger.logFail("uitleningItem already exists");
                    return Responder.respondBadRequest("already present");
                }
            }

            List<UitleningItem> createdItems = new ArrayList<>();

            for (UitleningItem ui : uitleningItems) {
                ui.setId(null);
                UitleningItem created = uitleningItemRepository.save(ui);
                createdItems.add(created);
            }

            //noinspection unchecked
            APILogger.logSuccess(createdItems);
            return Responder.respondCreated(createdItems);
        } catch (Exception e) {
            APILogger.logException(e.getMessage());
            return Responder.respondBadRequest(e.getMessage());
        }
    }

    @SuppressWarnings ("rawtypes")
    @PutMapping (value = "/{id}")
    public ResponseEntity put (
            @PathVariable String id,
            @RequestBody UitleningItem uitleningItem,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("uitleningItem.put", id);
        try {
            if (!validateUitleningItemId(uitleningItem))
                return Responder.respondBadRequest("not valid");

            Optional<UitleningItem> u = uitleningItemRepository.findById(id);

            if (u.isEmpty())
                return Responder.respondNotFound();

            uitleningItem.setId(u.get()
                                 .getId());
            UitleningItem result = uitleningItemRepository.save(uitleningItem);

            //noinspection unchecked
            return Responder.respondCreated(result);
        } catch (Exception e) {
            return Responder.respondBadRequest(e.getMessage());
        }
    }

    @SuppressWarnings ("rawtypes")
    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (
            @PathVariable String id,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("uitleningItem.delete", id);

        try {
            Optional<UitleningItem> u = uitleningItemRepository.findById(id);

            if (u.isEmpty())
                return Responder.respondNotFound();

            uitleningItemRepository.delete(u.get());

            return Responder.respondNoContent("deleted");
        } catch (Exception e) {
            return Responder.respondBadRequest(e.getMessage());
        }
    }

    private boolean validateUitleningItemId (UitleningItem u) {
        if (u.getId()
             .isEmpty()){
            APILogger.logFail("id is empty");
            return false;}
        return validateUitleningItem(u);
    }

    private boolean validateUitleningItem (UitleningItem u) {
        if (u.getAantal() < 0) APILogger.logFail("aantal < 0");
        if (u.getTeruggebrachtOp() != null && u.getTeruggebrachtOp()
             .isEmpty()) APILogger.logFail("teruggebrachtOp is empty");
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
        if (teruggebrachtOp.equals("null")) return true;
        return false;
    }

    private boolean validateUitleningId (String uitleningId) {
        if (uitleningId == null) APILogger.logFail("uitleningId is null");
        assert uitleningId != null;
        if (uitleningId.isEmpty()) APILogger.logFail("uitleningId is empty");
        if (uitleningId == null || uitleningId.isEmpty()) return false;
        Optional<Uitlening> u = uitleningRepository.findById(uitleningId);
        if (u.isEmpty()) APILogger.logFail("optionalUitlening is empty");
        return u.isPresent();
    }

    private boolean validateUitleenbaarItemId (String uitleenbaarItemId) {
        if (uitleenbaarItemId == null) APILogger.logFail("uitleenbaarItemId is null");
        assert uitleenbaarItemId != null;
        if (uitleenbaarItemId.isEmpty()) APILogger.logFail("uitleenbaarItemId is empty");
        if (uitleenbaarItemId == null || uitleenbaarItemId.isEmpty()) return false;
        Optional<UitleenbaarItem> ui = uitleenbaarItemRepository.findById(uitleenbaarItemId);
        if (ui.isEmpty()) APILogger.logFail("optionalUitlening is empty");
        return ui.isPresent();
    }
}
