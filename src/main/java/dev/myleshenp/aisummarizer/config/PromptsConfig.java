package dev.myleshenp.aisummarizer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration("prompts")
@ConfigurationProperties(prefix = "prompts")
@Data
public class PromptsConfig {

    private String summaryPrompt;

}
