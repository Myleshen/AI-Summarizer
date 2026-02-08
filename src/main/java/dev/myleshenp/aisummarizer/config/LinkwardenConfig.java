package dev.myleshenp.aisummarizer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "linkwarden")
public class LinkwardenConfig {

    private String apiKey;
    private String apiSecret;
    private String apiUrl;

}
