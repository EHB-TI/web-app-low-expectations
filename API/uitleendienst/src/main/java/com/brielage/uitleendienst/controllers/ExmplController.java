package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.models.Exmpl;
import com.brielage.uitleendienst.repositories.ExmplRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "/exmpl")
public class ExmplController {
    private ExmplRepository exmplRepository = new ExmplRepository();

    @GetMapping (value = { "/", "" })
    public List<Exmpl> findByProperties (
            @RequestParam (required = false) List<String> name,
            @RequestParam (required = false) List<String> test) {
        List<Exmpl> list1 = new ArrayList<>();
        List<Exmpl> list2 = new ArrayList<>();

        if ((name == null || name.isEmpty())
                && (test == null || test.isEmpty()))
            return exmplRepository.findAll();

        if (name != null && !name.isEmpty())
            list1 = exmplRepository.findByNameIsIn(name);
        if (test != null && !test.isEmpty())
            list2 = exmplRepository.findByTestIsIn(test);

        List<Exmpl> ret = new ArrayList<>(list1);

        if (ret.isEmpty() && !list2.isEmpty())
            return list2;

        for (Exmpl e1 : list2) {
            boolean alreadyExists = false;
            for (Exmpl e2 : ret)
                if (e1.equals(e2)) {
                    alreadyExists = true;
                    break;
                }
            if (!alreadyExists) ret.add(e1);
        }

        return ret;
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
