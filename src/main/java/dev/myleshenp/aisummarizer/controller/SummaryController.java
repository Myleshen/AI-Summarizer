package dev.myleshenp.aisummarizer.controller;

import dev.myleshenp.aisummarizer.config.PromptsConfig;
import dev.myleshenp.aisummarizer.service.HtmlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class SummaryController {

    public static final String MESSAGE = "message";
    public static final String RESULT = "result";
    private final OllamaChatModel ollamaChatModel;
    private final PromptsConfig promptsConfig;
    private final HtmlService htmlService;

    /**
     * Generates a response using the AI model based on the provided message.
     *
     * @param requestMap the request map containing the message to process
     * @return the AI-generated response
     */
    @PostMapping("/generate")
    public Map<String, String> generate(@RequestBody Map<String, String> requestMap) {
        return Map.of(RESULT, Objects.requireNonNull(ollamaChatModel.call(requestMap.get(MESSAGE))));
    }


    /**
     * Generates a summary of articles from URLs found in the provided message.
     *
     * @param requestMap the request map containing the message with URLs to summarize
     * @return a map containing the combined summary results
     */
    @PostMapping("/generate-summary")
    public Map<String, String> generateSummary(@RequestBody Map<String, String> requestMap) {
        List<String> urls = HtmlService.extractUrls(requestMap.get(MESSAGE));
        if (urls.isEmpty()) {
            throw new IllegalArgumentException("Please use the /generate endpoint instead of /generate-summary when no URLs are provided.");
        }
        String combinedSummary = urls.stream()
                .map(url -> {
                    String html = htmlService.fetchHtml(url);
                    String content = htmlService.sanitizeHtml(html);
                    String prompt = promptsConfig.getSummaryPrompt() + "\n\n" + content;
                    String summary = ollamaChatModel.call(prompt);
                    return "URL: " + url + "\nSummary: " + summary + "\n----\n";
                })
                .collect(Collectors.joining());
        return Map.of(RESULT, combinedSummary);
    }

    /**
     * Scheduled method that generates a daily digest response at 9 AM every day.
     * Logs the generated content.
     */
//    @Scheduled(cron = "0 0 9 * * ?")
    public void generateDailyDigestCron() {
        Map<String, String> requestMap = Map.of(MESSAGE, "");
        Map<String, String> emailContent = generate(requestMap);
        log.info(emailContent.get(RESULT));
    }
}
