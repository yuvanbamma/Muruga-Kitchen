package com.ammaPaasam.unavagam.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Value("${app.cors.allowed-origins}")
    private List<String> allowdedOrigins;

    @Value("${app.cors.allowed-methods}")
    private List<String> allowdedMethods;

    @Value("${app.cors.allowed-headers}")
    private List<String> allowdedHeaders;

    @Value("${app.cors.allow-credentials}")
    private boolean allowCredientials;

    @Value("${app.cors.max-age}")
    private long maxAge;

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(allowCredientials);
        corsConfiguration.setAllowedOrigins(allowdedOrigins);
        corsConfiguration.setAllowedHeaders(allowdedHeaders);
        corsConfiguration.setMaxAge(maxAge);
        corsConfiguration.setAllowedMethods(allowdedMethods);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }
}
