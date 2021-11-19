package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.Exmpl;
import com.brielage.uitleendienst.repositories.ExmplRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin ("http://localhost:8080")
@RequestMapping (value = "/exmpl")
public class ExmplController {
    private ExmplRepository exmplRepository = new ExmplRepository();

    @RequestMapping (value = { "/", "" }, method = RequestMethod.GET)
    public List<Exmpl> exmpl () {
        return exmplRepository.findAll();
    }

    // return http code 200 with object if success
    // otherwise return 404 not found
    @GetMapping (value = "/{id}")
    public ResponseEntity findById (@PathVariable String id) {
        Optional<Exmpl> optionalExmpl = exmplRepository.findById(id);
        if (optionalExmpl.isPresent())
            return ResponseEntity.ok()
                                 .body(optionalExmpl.get());
        return ResponseEntity.notFound()
                             .build();
    }

    // POST returns a 201 (HttpStatus.CREATED) with the object it has saved
    // on missing required attributes returns a 400
    // on failure returns a 400
    @PostMapping (value = { "/", "" })
    public ResponseEntity create (@RequestBody Exmpl exmpl) {
        try {
            if (!validateExmpl(exmpl))
                return ResponseEntity.badRequest()
                                     .build();

            Exmpl e = exmplRepository.save(exmpl);

            return new ResponseEntity(e, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    // PUT returns a 201 (HttpStatus.CREATED) with the object it has saved
    // on missing required attributes returns a 400
    // when the resource to be updated is not found it returns a 404
    // on failure returns a 400
    @PutMapping (value = "/{id}")
    public ResponseEntity put (
            @PathVariable String id,
            @RequestBody Exmpl exmpl) {
        try {
            if (!validateExmplWithId(exmpl))
                return ResponseEntity.badRequest()
                                     .build();

            Optional<Exmpl> e = exmplRepository.findById(id);

            if (e.isEmpty())
                return ResponseEntity.notFound()
                                     .build();

            exmpl.setId(e.get()
                         .getId());
            Exmpl result = exmplRepository.save(exmpl);

            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }


    // DELETE returns a 204 (noContent) when successfull
    // on failure returns a 400
    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (@PathVariable String id) {
        try {
            Optional<Exmpl> e = exmplRepository.findById(id);

            if (e.isEmpty()) return ResponseEntity.badRequest()
                                                  .build();

            exmplRepository.delete(e.get());
            return ResponseEntity.noContent()
                                 .build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }

    private boolean validateExmplWithId (Exmpl e) {
        if (e.getId()
             .isEmpty()) return false;
        return validateExmpl(e);
    }

    @SuppressWarnings ("RedundantIfStatement")
    private boolean validateExmpl (Exmpl e) {
        if (e.getName()
             .isEmpty()) return false;
        if (e.getTest()
             .isEmpty()) return false;
        return true;
    }
}
