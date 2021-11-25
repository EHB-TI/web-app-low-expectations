package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.brielage.uitleendienst.repositories.UitleenbaarItemRepository;
import com.brielage.uitleendienst.tools.RemoveDuplicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/uitleenbaarItem")
public class UitleenbaarItemController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UitleenbaarItemRepository uitleenbaarItemRepository;

    @GetMapping (value = { "/", "" })
    public ResponseEntity findByProperties(
            @RequestParam (required = false) List<String> categorieId,
            @RequestParam (required = false) List<String> naam) {
        List<UitleenbaarItem> returnValue = new ArrayList<>();

        //return findAll() if no properties
        if (categorieId == null || categorieId.isEmpty()
                && (naam == null || naam.isEmpty())) {
            returnValue = uitleenbaarItemRepository.findAll();

            if (returnValue.isEmpty())
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok().body(returnValue);
        }

        //add all elements found by the properties to returnValue
        if (categorieId != null && !categorieId.isEmpty())
            returnValue.addAll(uitleenbaarItemRepository.findAllByCategorieIdIsIn(categorieId));
        if (naam != null && !naam.isEmpty())
            returnValue.addAll(uitleenbaarItemRepository.findAllByNaamIsIn(naam));

        if (returnValue.isEmpty())
            return ResponseEntity.notFound().build();

        //remove duplicates
        returnValue = RemoveDuplicates.removeDuplicates(returnValue);

        return ResponseEntity.ok().body(returnValue);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        Optional<UitleenbaarItem> u = uitleenbaarItemRepository.findById(id);

        if (u.isPresent())
            return ResponseEntity.ok().body(u.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody UitleenbaarItem uitleenbaarItem) {
        try {
            if (!validateUitleenbaarItem(uitleenbaarItem))
                return ResponseEntity.badRequest().build();

            Optional<UitleenbaarItem> optionalUitleenbaarItem =
                    uitleenbaarItemRepository.findByNaamAndCategorieId(
                            uitleenbaarItem.getNaam(), uitleenbaarItem.getCategorieId());

            if (optionalUitleenbaarItem.isPresent())
                return ResponseEntity.badRequest().build();

            uitleenbaarItem.setId(null);
            UitleenbaarItem u = uitleenbaarItemRepository.save(uitleenbaarItem);

            return new ResponseEntity(u, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (@PathVariable String id, @RequestBody UitleenbaarItem uitleenbaarItem) {
        try {
            if (!validateUitleenbaarItemId(uitleenbaarItem))
                return ResponseEntity.badRequest().build();

            Optional<UitleenbaarItem> u = uitleenbaarItemRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.notFound().build();

            uitleenbaarItem.setId(u.get().getId());
            UitleenbaarItem result = uitleenbaarItemRepository.save(uitleenbaarItem);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        try {
            Optional<UitleenbaarItem> u = uitleenbaarItemRepository.findById(id);

            if (u.isEmpty())
                return ResponseEntity.badRequest().build();

            uitleenbaarItemRepository.delete(u.get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean validateUitleenbaarItemId(UitleenbaarItem u) {
        if (u.getId().isEmpty())
            return false;
        return validateUitleenbaarItem(u);
    }

    private boolean validateUitleenbaarItem(UitleenbaarItem u) {
        return !u.getNaam().isEmpty()
                && u.getEenheid() < 0
                && u.getPrijs() < 0
                && u.getPeriode() != null
                && validateCategorieId(u.getCategorieId());
    }

    private boolean validateCategorieId (String categorieId) {
        if (categorieId == null || categorieId.isEmpty()) return false;
        List<UitleenbaarItem> u = uitleenbaarItemRepository.findAllByCategorieId(categorieId);
        return !u.isEmpty();
    }
}
