package org.irbis.parser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SiteLoader {
    private final RestTemplate restTemplate;

    public String load(String siteUrl) {
        return restTemplate.getForObject(siteUrl, String.class, Map.of());
    }

}
