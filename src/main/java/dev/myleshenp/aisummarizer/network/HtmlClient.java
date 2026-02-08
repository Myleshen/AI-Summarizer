package dev.myleshenp.aisummarizer.network;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class HtmlClient {

    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
    private final HttpClient httpClient;

    /**
     * Constructs a new HtmlClient with a default HttpClient.
     */
    public HtmlClient() {
        this.httpClient = HttpClient.newBuilder().build();
    }

    /**
     * Fetches the content of the specified URL using HTTP GET.
     *
     * @param url the URL to fetch
     * @return the response body as a string
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public String fetchUrl(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .header("User-Agent", USER_AGENT)
                .uri(URI.create(url))
                .build();
        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        return this.httpClient.send(request, handler).body();
    }
}
