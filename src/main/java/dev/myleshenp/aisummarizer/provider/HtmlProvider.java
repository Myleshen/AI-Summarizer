package dev.myleshenp.aisummarizer.provider;

import dev.myleshenp.aisummarizer.network.HtmlClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class HtmlProvider {

    private final HtmlClient htmlClient;

    /**
     * Fetches HTML content from the given URL using the HtmlClient.
     * Parses the response and returns the body HTML.
     *
     * @param url the URL to fetch HTML from
     * @return the HTML body content as a string, or empty string on error
     */
    public String fetchHtml(String url) {
       try {
           String webPage = this.htmlClient.fetchUrl(url);
           return Jsoup.parse(webPage).body().toString();
       } catch (IOException | InterruptedException e) {
           log.error("Failed to fetch html", e);
           return "";
       }
    }

}
