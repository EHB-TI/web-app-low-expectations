package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.brielage.uitleendienst.models.Uitlening;
import com.brielage.uitleendienst.repositories.UitleenbaarItemRepository;
import com.brielage.uitleendienst.repositories.UitleningRepository;
import com.brielage.uitleendienst.tools.APILogger;
import com.brielage.uitleendienst.models.UitleningItem;
import com.brielage.uitleendienst.repositories.UitleningItemRepository;
import com.brielage.uitleendienst.tools.RemoveDuplicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/uitleningItem")
public class UitleningItemController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UitleningItemRepository uitleningItemRepository;
    @Autowired
    private UitleenbaarItemRepository uitleenbaarItemRepository;
    @Autowired
    private UitleningRepository uitleningRepository;

    @GetMapping (value = { "/", "" })
    public ResponseEntity findByProperties(
            @RequestParam (required = false) List<String> uitleenbaarItemId,
            @RequestParam (required = false) List<String> uitleningId) {
        List<UitleningItem> returnValue = new ArrayList<>();

        //return findAll() if no properties
        if (uitleenbaarItemId == null || uitleenbaarItemId.isEmpty()
                && (uitleningId == null || uitleningId.isEmpty())) {
            returnValue = uitleningItemRepository.findAll();

            if (returnValue.isEmpty())
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok().body(returnValue);
        }

        //add all elements found by the properties to returnValue
        if (uitleenbaarItemId != null && !uitleenbaarItemId.isEmpty())
            returnValue.addAll(uitleningItemRepository.findAllByUitleenbaarItemIdIsIn(uitleenbaarItemId));
        if (uitleningId != null && !uitleningId.isEmpty())
            returnValue.addAll(uitleningItemRepository.findAllByUitleningIdIsIn(uitleningId));

        if (returnValue.isEmpty())
            return ResponseEntity.notFound().build();

        //remove duplicates
        returnValue = RemoveDuplicates.removeDuplicates(returnValue);

        return ResponseEntity.ok().body(returnValue);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        APILogger.logRequest("uitleningItem.findById", id);
        Optional<UitleningItem> u = uitleningItemRepository.findById(id);

        if (u.isPresent())
            return ResponseEntity.ok().body(u.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody UitleningItem uitleningItem) {
        APILogger.logRequest("uitleningItem.create", uitleningItem.toString());
        try {
            if (!validateUitleningItem(uitleningItem))
                return ResponseEntity.badRequest().build();

            Optional<UitleningItem> optionalUitleningItem =
                    uitleningItemRepository.findByUitleenbaarItemIdAndUitleningId(
                            uitleningItem.getUitleenbaarItemId(), uitleningItem.getUitleningId());

            if (optionalUitleningItem.isPresent())
                return ResponseEntity.badRequest().build();

            uitleningItem.setId(null);
            UitleningItem u = uitleningItemRepository.save(uitleningItem);

            return new ResponseEntity(u, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (@PathVariable String id, @RequestBody UitleningItem uitleningItem) {
        APILogger.logRequest("uitleningItem.put", id);
        try {
            if (!validateUitleningItemId(uitleningItem))
                return ResponseEntity.badRequest().build();

            Optional<UitleningItem> u = uitleningItemRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.notFound().build();

            uitleningItem.setId(u.get().getId());
            UitleningItem result = uitleningItemRepository.save(uitleningItem);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        APILogger.logRequest("uitleningItem.delete", id);
        try {
            Optional<UitleningItem> u = uitleningItemRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.badRequest().build();

            uitleningItemRepository.delete(u.get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean validateUitleningItemId(UitleningItem u) {
        if (u.getId().isEmpty())
            return false;
        return validateUitleningItem(u);
    }

    private boolean validateUitleningItem(UitleningItem u) {
        return validateUitleningId(u.getUitleningId())
                && validateUitleenbaarItemId(u.getUitleenbaarItemId())
                && u.getAantal() > 0
                && (u.getTeruggebrachtOp() != null && !u.getTeruggebrachtOp().isEmpty())
                && u.getAantalTeruggebracht() > 0;
    }

    private boolean validateUitleningId (String uitleningId) {
        if (uitleningId == null || uitleningId.isEmpty()) return false;
        Optional<Uitlening> u = uitleningRepository.findById(uitleningId);
        return u.isPresent();
    }

    private boolean validateUitleenbaarItemId (String uitleenbaarItemId) {
        if (uitleenbaarItemId == null || uitleenbaarItemId.isEmpty()) return false;
        Optional<UitleenbaarItem> ui = uitleenbaarItemRepository.findById(uitleenbaarItemId);
        return ui.isPresent();
    }
}
