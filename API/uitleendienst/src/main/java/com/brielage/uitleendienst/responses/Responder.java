package com.brielage.uitleendienst.responses;

import com.brielage.uitleendienst.tools.APILogger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SuppressWarnings ("rawtypes")
public enum Responder {
    ;

    public static ResponseEntity respondOk (Object o) {
        APILogger.logSuccess(o);
        return ResponseEntity.ok()
                             .body(o);
    }

    public static ResponseEntity respondOk (List<Object> objects) {
        StringBuilder output = new StringBuilder();
        for (Object o : objects)
            output.append(o.toString())
                  .append("; ");
        APILogger.logSuccess(output.toString());
        return ResponseEntity.ok()
                             .body(objects);
    }

    public static ResponseEntity respondBadRequest (String reason) {
        APILogger.logFail(reason);
        return ResponseEntity.badRequest()
                             .build();
    }

    public static ResponseEntity respondNotFound () {
        APILogger.logFail("not found");
        return ResponseEntity.notFound()
                             .build();
    }

    @SuppressWarnings ("unchecked")
    public static ResponseEntity respondCreated (Object o) {
        APILogger.logSuccess(o.toString());
        return new ResponseEntity(o, HttpStatus.CREATED);
    }

    public static ResponseEntity respondNoContent (String what) {
        APILogger.logSuccess(what);
        return ResponseEntity.noContent()
                             .build();
    }

    public static ResponseEntity respondForbidden () {
        APILogger.logFail("forbidden");
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    public static ResponseEntity respondUnauthorized () {
        APILogger.logFail("unauthorized");
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
