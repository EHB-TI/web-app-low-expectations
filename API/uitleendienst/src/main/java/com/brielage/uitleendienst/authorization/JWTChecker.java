
package com.brielage.uitleendienst.authorization;

import com.brielage.uitleendienst.tools.APILogger;

import java.util.Base64;

public class JWTChecker {
    String         token;
    String[]       chunks;
    Base64.Decoder decoder = Base64.getUrlDecoder();

    public JWTChecker (String token) {
        this.token = token;
        if (!token.isEmpty())
            chunks = token.split("\\.");
    }

    public void log () {
        if (chunks == null || chunks.length == 0) {
            APILogger.logResult("EMPTY TOKEN");
            return;
        }
        
        String header  = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        APILogger.logResult(header);
        APILogger.logResult(payload);
    }
}
