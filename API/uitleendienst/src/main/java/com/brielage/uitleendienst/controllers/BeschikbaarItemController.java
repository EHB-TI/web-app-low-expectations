package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.BeschikbaarItem;
import com.brielage.uitleendienst.models.Magazijn;
import com.brielage.uitleendienst.models.UitleenbaarItem;
import com.brielage.uitleendienst.repositories.BeschikbaarItemRepository;
import com.brielage.uitleendienst.repositories.MagazijnRepository;
import com.brielage.uitleendienst.repositories.UitleenbaarItemRepository;
import com.brielage.uitleendienst.tools.RemoveDuplicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping (value = "/beschikbaaritem")
public class BeschikbaarItemController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private BeschikbaarItemRepository beschikbaarItemRepository;
    @Autowired
    private UitleenbaarItemRepository uitleenbaarItemRepository;
    @Autowired
    private MagazijnRepository        magazijnRepository;

    @GetMapping (value = { "/", "" })
    public ResponseEntity findByProperties (
            @RequestParam (required = false) List<String> uitleenbaarItemId,
            @RequestParam (required = false) List<String> magazijnId,
            @RequestParam (required = false) List<String> categorieId,
            @RequestHeader ("Authorization") String token) {
        List<BeschikbaarItem> returnValue = new ArrayList<>();

        //return findAll() if no properties
        if ((uitleenbaarItemId == null || uitleenbaarItemId.isEmpty())
                && (magazijnId == null || magazijnId.isEmpty())
                && (categorieId == null || categorieId.isEmpty())) {
            returnValue = beschikbaarItemRepository.findAll();

            if (returnValue.isEmpty())
                return ResponseEntity.notFound()
                                     .build();

            return ResponseEntity.ok()
                                 .body(returnValue);
        }
        List<UitleenbaarItem> uitleenbaarItems;
        List<String>          uitleenbaarItemIds;

        //add all elements found by the properties to returnValue
        if (uitleenbaarItemId != null && !uitleenbaarItemId.isEmpty())
            returnValue.addAll(
                    beschikbaarItemRepository.findAllByUitleenbaarItemIdIsIn(uitleenbaarItemId));

        if (magazijnId != null && !magazijnId.isEmpty())
            returnValue.addAll(beschikbaarItemRepository.findAllByMagazijnIdIsIn(magazijnId));

        if (categorieId != null && !categorieId.isEmpty()) {
            uitleenbaarItems   = uitleenbaarItemRepository.findAllByCategorieIdIsIn(categorieId);
            uitleenbaarItemIds = uitleenbaarItems.stream()
                                                 .map(UitleenbaarItem::getId)
                                                 .collect(Collectors.toList());
            returnValue.addAll(
                    beschikbaarItemRepository.findAllByUitleenbaarItemIdIsIn(uitleenbaarItemIds));
        }

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
        Optional<BeschikbaarItem> b = beschikbaarItemRepository.findById(id);

        if (b.isPresent())
            return ResponseEntity.ok()
                                 .body(b.get());
        return ResponseEntity.notFound()
                             .build();
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (
            @RequestBody BeschikbaarItem beschikbaarItem,
            @RequestHeader ("Authorization") String token) {
        try {
            if (!validateBeschikbaarItem(beschikbaarItem))
                return ResponseEntity.badRequest()
                                     .build();

            Optional<BeschikbaarItem> optionalBeschikbaarItem =
                    beschikbaarItemRepository.findByUitleenbaarItemIdAndMagazijnId(
                            beschikbaarItem.getUitleenbaarItemId(),
                            beschikbaarItem.getMagazijnId());

            if (optionalBeschikbaarItem.isPresent())
                return ResponseEntity.badRequest()
                                     .build();

            beschikbaarItem.setId(null);
            BeschikbaarItem b = beschikbaarItemRepository.save(beschikbaarItem);

            return new ResponseEntity(b, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (
            @PathVariable String id,
            @RequestBody BeschikbaarItem beschikbaarItem,
            @RequestHeader ("Authorization") String token) {
        try {
            if (!validateBeschikbaarItemId(beschikbaarItem))
                return ResponseEntity.badRequest()
                                     .build();

            Optional<BeschikbaarItem> b = beschikbaarItemRepository.findById(id);

            if (b.isEmpty())
                return ResponseEntity.notFound()
                                     .build();

            beschikbaarItem.setId(b.get()
                                   .getId());
            BeschikbaarItem result = beschikbaarItemRepository.save(beschikbaarItem);
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
        try {
            Optional<BeschikbaarItem> b = beschikbaarItemRepository.findById(id);

            if (b.isEmpty())
                return ResponseEntity.badRequest()
                                     .build();

            beschikbaarItemRepository.delete(b.get());
            return ResponseEntity.noContent()
                                 .build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
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
