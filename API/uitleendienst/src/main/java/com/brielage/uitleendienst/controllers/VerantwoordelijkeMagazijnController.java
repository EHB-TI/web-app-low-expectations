package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.authorization.OriginChecker;
import com.brielage.uitleendienst.models.Magazijn;
import com.brielage.uitleendienst.models.Persoon;
import com.brielage.uitleendienst.models.VerantwoordelijkeMagazijn;
import com.brielage.uitleendienst.repositories.MagazijnRepository;
import com.brielage.uitleendienst.repositories.PersoonRepository;
import com.brielage.uitleendienst.repositories.VerantwoordelijkeMagazijnRepository;
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
@RequestMapping (value = "verantwoordelijkemagazijn")
public class VerantwoordelijkeMagazijnController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private VerantwoordelijkeMagazijnRepository verantwoordelijkeMagazijnRepository;
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersoonRepository                   persoonRepository;
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private MagazijnRepository                  magazijnRepository;

    @GetMapping (value = { "/", "" })
    public ResponseEntity findByProperties (
            @RequestParam (required = false) List<String> persoondId,
            @RequestParam (required = false) List<String> magazijnId,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        List<VerantwoordelijkeMagazijn> returnValue = new ArrayList<>();

        if (!OriginChecker.checkOrigin(origin))
            return Responder.respondBadRequest("origin not allowed " + origin);

        //return findAll() if no properties
        if ((persoondId == null || persoondId.isEmpty())
                && (magazijnId == null || magazijnId.isEmpty())) {
            APILogger.logRequest("verantwoordelijkemagazijn.findAll");
            returnValue = verantwoordelijkeMagazijnRepository.findAll();

            if (returnValue.isEmpty()) return Responder.respondNotFound();

            return Responder.respondOk(returnValue);
        }

        //add all elements found by the properties to returnValue
        if (persoondId != null && !persoondId.isEmpty()) {
            APILogger.logRequest("verantwoordelijkemagazijn.findAllByPersoonIdIsIn");
            returnValue.addAll(
                    verantwoordelijkeMagazijnRepository.findAllByPersoonIdIsIn(persoondId));
        }

        if (magazijnId != null && !magazijnId.isEmpty()) {
            APILogger.logRequest("verantwoordelijkemagazijn.findAllByMagazijnIdIsIn");
            returnValue.addAll(
                    verantwoordelijkeMagazijnRepository.findAllByMagazijnIdIsIn(magazijnId));
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
        APILogger.logRequest("verantwoordelijkeMagazijn.findById", id);

        if (!OriginChecker.checkOrigin(origin))
            return Responder.respondBadRequest("origin not allowed " + origin);

        Optional<VerantwoordelijkeMagazijn> vm = verantwoordelijkeMagazijnRepository.findById(id);

        if (vm.isEmpty()) return Responder.respondNotFound();

        return Responder.respondOk(vm.get());
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (
            @RequestBody VerantwoordelijkeMagazijn verantwoordelijkeMagazijn,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("verantwoordelijkeMagazijn.create",
                             verantwoordelijkeMagazijn.toString());

        if (!OriginChecker.checkOrigin(origin))
            return Responder.respondBadRequest("origin not allowed " + origin);

        try {
            if (!validateVerantwoordelijkeMagazijn(verantwoordelijkeMagazijn))
                return Responder.respondBadRequest("not valid");

            Optional<VerantwoordelijkeMagazijn> optionalVerantwoordelijkeMagazijn =
                    verantwoordelijkeMagazijnRepository.findByMagazijnIdAndPersoonId(
                            verantwoordelijkeMagazijn.getMagazijnId(),
                            verantwoordelijkeMagazijn.getPersoonId());

            if (optionalVerantwoordelijkeMagazijn.isPresent())
                return Responder.respondBadRequest("already present");

            // ignore ID when creating, will get automagically generated by DB
            verantwoordelijkeMagazijn.setId(null);
            VerantwoordelijkeMagazijn vm = verantwoordelijkeMagazijnRepository.save(
                    verantwoordelijkeMagazijn);

            return Responder.respondCreated(vm);
        } catch (Exception e) {return Responder.respondBadRequest(e.getMessage());}
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (
            @PathVariable String id,
            @RequestBody VerantwoordelijkeMagazijn verantwoordelijkeMagazijn,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("verantwoordelijkeMagazijn.put", id);

        if (!OriginChecker.checkOrigin(origin))
            return Responder.respondBadRequest("origin not allowed " + origin);

        try {
            if (!validateVerantwoordelijkeMagazijnId(verantwoordelijkeMagazijn))
                return Responder.respondBadRequest("not valid");

            Optional<VerantwoordelijkeMagazijn> vm = verantwoordelijkeMagazijnRepository.findById(
                    id);

            if (vm.isEmpty())
                return Responder.respondNotFound();

            verantwoordelijkeMagazijn.setId(vm.get()
                                              .getId());
            VerantwoordelijkeMagazijn result = verantwoordelijkeMagazijnRepository.save(
                    verantwoordelijkeMagazijn);
            return Responder.respondCreated(result);
        } catch (Exception e) {
            return Responder.respondBadRequest(e.getMessage());
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (
            @PathVariable String id,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("verantwoordelijkeMagazijn.delete", id);

        if (!OriginChecker.checkOrigin(origin))
            return Responder.respondBadRequest("origin not allowed " + origin);

        try {
            Optional<VerantwoordelijkeMagazijn> vm = verantwoordelijkeMagazijnRepository.findById(
                    id);

            if (vm.isEmpty())
                return Responder.respondNotFound();

            verantwoordelijkeMagazijnRepository.delete(vm.get());

            return Responder.respondNoContent("deleted");
        } catch (Exception e) {
            return Responder.respondBadRequest(e.getMessage());
        }
    }

    private boolean validateVerantwoordelijkeMagazijnId (VerantwoordelijkeMagazijn vm) {
        if (vm.getId()
              .isEmpty())
            return false;
        return validateVerantwoordelijkeMagazijn(vm);
    }

    private boolean validateVerantwoordelijkeMagazijn (VerantwoordelijkeMagazijn vm) {
        return validatePersoonId(vm.getPersoonId())
                && validateMagazijnId(vm.getMagazijnId());
    }

    private boolean validatePersoonId (String persoonId) {
        if (persoonId == null || persoonId.isEmpty()) return false;
        Optional<Persoon> p = persoonRepository.findById(persoonId);
        return p.isPresent();
    }

    private boolean validateMagazijnId (String magazijnId) {
        if (magazijnId == null || magazijnId.isEmpty()) return false;
        Optional<Magazijn> m = magazijnRepository.findById(magazijnId);
        return m.isPresent();
    }
}
