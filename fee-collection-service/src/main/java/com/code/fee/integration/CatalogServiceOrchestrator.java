package com.code.fee.integration;

import com.code.fee.dto.CatalogDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatalogServiceOrchestrator {
    private final RestClient restClient;

    @Value("${integration.catalog-service.base-url}")
    private String baseURL;

    public List<CatalogDTO> getActiveCatalogs(Long gradeId) {
        URI uri = UriComponentsBuilder.fromUriString(baseURL + "/api/catalogs/" + gradeId + "/active").build().toUri();
        return restClient.get(uri, Map.of(), new ParameterizedTypeReference<>() {
        });
    }
}
