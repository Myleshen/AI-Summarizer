package dev.myleshenp.aisummarizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AiSummarizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiSummarizerApplication.class, args);
    }

}
