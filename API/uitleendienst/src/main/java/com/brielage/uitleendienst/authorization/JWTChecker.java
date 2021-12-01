
package com.brielage.uitleendienst.authorization;

import com.brielage.uitleendienst.tools.APILogger;

import java.util.Base64;

public class JWTChecker {
    String             token;
    String[]           chunks;
    Base64.Decoder     decoder       = Base64.getUrlDecoder();

    public JWTChecker (String token) {
        this.token=token;
        chunks = token.split("\\.");
    }

    public void log () {
        String header                = new String(decoder.decode(chunks[0]));
        String payload               = new String(decoder.decode(chunks[1]));

        APILogger.logResult(header);
        APILogger.logResult(payload);
    }
}
