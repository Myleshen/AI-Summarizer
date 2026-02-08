package dev.myleshenp.aisummarizer.service;

import dev.myleshenp.aisummarizer.network.HtmlClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LinkwardenService {

    private final HtmlClient htmlClient;

    public void getLinks() {

    }
}
