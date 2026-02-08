package dev.myleshenp.aisummarizer.service;

import dev.myleshenp.aisummarizer.provider.HtmlProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HtmlService {

    private final HtmlProvider htmlProvider;

    public static final String HTTP_REGEX = "https?://(?:[-\\w.]|%[\\da-fA-F]{2})+[\\w\\-._~:/?#\\[\\]@!$&'()*+,;=%]*";

    /**
     * Extracts all HTTP and HTTPS URLs from the given text using regex.
     *
     * @param text the input text to search for URLs
     * @return a list of URLs found in the text
     */
    public static List<String> extractUrls(String text) {
        Pattern pattern = Pattern.compile(HTTP_REGEX);
        Matcher matcher = pattern.matcher(text);

        return matcher.results()
                .map(MatchResult::group)
                .collect(Collectors.toList());
    }

    /**
     * Fetches the HTML content from the specified URL.
     *
     * @param url the URL to fetch HTML from
     * @return the HTML content as a string
     */
    public String fetchHtml(String url) {
        return this.htmlProvider.fetchHtml(url);
    }

    /**
     * Sanitizes HTML content by extracting the text content from the body tag.
     *
     * @param html the HTML content to sanitize
     * @return the sanitized text content
     */
    public String sanitizeHtml(String html) {
        return Jsoup.parse(html).body().text();
    }
}
