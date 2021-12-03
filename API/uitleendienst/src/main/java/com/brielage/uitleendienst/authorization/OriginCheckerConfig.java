package com.brielage.uitleendienst.authorization;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OriginCheckerConfig {
    @Value ("${origin.1}")
    private String origin1;
    @Value ("${origin.2}")
    private String origin2;

    @Bean
    public OriginChecker originChecker () {
        List<String> allowedOrigins = List.of(origin1/*, origin2*/);
        //noinspection InstantiationOfUtilityClass
        return new OriginChecker(allowedOrigins);
    }
}
