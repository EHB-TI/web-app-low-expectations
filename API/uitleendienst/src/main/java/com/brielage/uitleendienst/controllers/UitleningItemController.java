package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.tools.APILogger;
import com.brielage.uitleendienst.models.UitleningItem;
import com.brielage.uitleendienst.repositories.UitleningItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/uitleningItem")
public class UitleningItemController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UitleningItemRepository uitleningItemRepository;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public List<UitleningItem> findAll() {
        APILogger.logRequest("uitleningItem.findAll");
        return uitleningItemRepository.findAll();
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
        return u.getUitlening() != null
                && u.getItem() != null
                && u.getAantal() < 0
                && u.getTeruggebrachtOp() != null
                && u.getAantalTeruggebracht() < 0;
    }
}
