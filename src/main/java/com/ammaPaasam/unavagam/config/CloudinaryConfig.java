package com.ammaPaasam.unavagam.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Value("${CLOUDINARY_CLOUD_NAME}")
    private String cloudinaryCloudName;

    @Value("${CLOUDINARY_API_KEY}")
    private String cloudinaryApiKey;

    @Value("${CLOUDINARY_API_SECRET}")
    private String cloudinaryApiSecretKey;

    @Bean
    public Cloudinary cloudinary(){
        Map<String,String> config = new HashMap<>();
        config.put("api_key",cloudinaryApiKey);
        config.put("cloud_name",cloudinaryCloudName);
        config.put("api_secret",cloudinaryApiSecretKey);
        return new Cloudinary(config);
    }
}
