package com.brielage.uitleendienst.CORS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig
        implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer configurer () {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings (CorsRegistry registry) {
                // application wide, don't use yet, add new mappings below
                //registry.addMapping("/**");

                registry.addMapping("/exmpl")
                        .allowedMethods("GET", "OPTIONS", "POST");

                registry.addMapping("/categorie")
                        .allowedMethods("GET", "OPTIONS", "POST");
            }
        };
    }
}