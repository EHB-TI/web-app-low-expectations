package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.brielage.uitleendienst.models.Uitlening;
import com.brielage.uitleendienst.models.UitleningItem;
import com.brielage.uitleendienst.repositories.UitleenbaarItemRepository;
import com.brielage.uitleendienst.repositories.UitleningItemRepository;
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
            @RequestParam (required = false) List<String> uitleningId) {
        List<UitleningItem> returnValue = new ArrayList<>();

        //return findAll() if no properties
        if (uitleenbaarItemId == null || uitleenbaarItemId.isEmpty()
                && (uitleningId == null || uitleningId.isEmpty())) {
            returnValue = uitleningItemRepository.findAll();

            if (returnValue.isEmpty())
                return ResponseEntity.notFound()
                                     .build();

            return ResponseEntity.ok()
                                 .body(returnValue);
        }

        //add all elements found by the properties to returnValue
        if (!uitleenbaarItemId.isEmpty())
            returnValue.addAll(
                    uitleningItemRepository.findAllByUitleenbaarItemIdIsIn(uitleenbaarItemId));
        if (uitleningId != null && !uitleningId.isEmpty())
            returnValue.addAll(uitleningItemRepository.findAllByUitleningIdIsIn(uitleningId));

        if (returnValue.isEmpty())
            return ResponseEntity.notFound()
                                 .build();

        //remove duplicates
        returnValue = RemoveDuplicates.removeDuplicates(returnValue);

        return ResponseEntity.ok()
                             .body(returnValue);
    }

    @SuppressWarnings ("rawtypes")
    @GetMapping ("/{id}")
    public ResponseEntity findById (@PathVariable String id) {
        APILogger.logRequest("uitleningItem.findById", id);
        Optional<UitleningItem> u = uitleningItemRepository.findById(id);

        if (u.isPresent())
            return ResponseEntity.ok()
                                 .body(u.get());
        return ResponseEntity.notFound()
                             .build();
    }

    @SuppressWarnings ("rawtypes")
    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody List<UitleningItem> uitleningItems) {
        if (uitleningItems == null || uitleningItems.isEmpty()) {
            APILogger.logFail("uitleningitems is null or empty");
            return ResponseEntity.badRequest()
                                 .build();
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
                    return ResponseEntity.badRequest()
                                         .build();
                }

                Optional<UitleningItem> optionalUitleningItem =
                        uitleningItemRepository.findByUitleenbaarItemIdAndUitleningId(
                                ui.getUitleenbaarItemId(), ui.getUitleningId());

                if (optionalUitleningItem.isPresent()) {
                    APILogger.logFail("uitleningItem already exists");
                    return ResponseEntity.badRequest()
                                         .build();
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
            return new ResponseEntity(createdItems, HttpStatus.CREATED);
        } catch (Exception e) {
            APILogger.logException(e.getMessage());
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    @SuppressWarnings ("rawtypes")
    @PutMapping (value = "/{id}")
    public ResponseEntity put (
            @PathVariable String id,
            @RequestBody UitleningItem uitleningItem) {
        APILogger.logRequest("uitleningItem.put", id);
        try {
            if (!validateUitleningItemId(uitleningItem))
                return ResponseEntity.badRequest()
                                     .build();

            Optional<UitleningItem> u = uitleningItemRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.notFound()
                                     .build();

            uitleningItem.setId(u.get()
                                 .getId());
            UitleningItem result = uitleningItemRepository.save(uitleningItem);

            //noinspection unchecked
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            APILogger.logException(e.getMessage());
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    @SuppressWarnings ("rawtypes")
    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        APILogger.logRequest("uitleningItem.delete", id);
        try {
            Optional<UitleningItem> u = uitleningItemRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.badRequest()
                                     .build();

            uitleningItemRepository.delete(u.get());
            return ResponseEntity.noContent()
                                 .build();
        } catch (Exception e) {
            APILogger.logException(e.getMessage());
            return ResponseEntity.badRequest()
                                 .build();
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
        if (u.getTeruggebrachtOp() == null) APILogger.logFail("teruggebrachtOp is null");
        if (u.getTeruggebrachtOp()
             .isEmpty()) APILogger.logFail("teruggebrachtOp is empty");
        if (u.getAantalTeruggebracht() < 0) APILogger.logFail("aantalTeruggebracht < 0");
        return validateUitleningId(u.getUitleningId())
                && validateUitleenbaarItemId(u.getUitleenbaarItemId())
                && u.getAantal() > 0
                && (u.getTeruggebrachtOp() != null && !u.getTeruggebrachtOp()
                                                        .isEmpty())
                && u.getAantalTeruggebracht() >= 0;
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
