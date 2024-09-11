package com.code.fee.integration;

import com.code.fee.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceOrchestrator {
    private final RestClient restClient;

    @Value("${integration.student-service.base-url}")
    private String baseURL;

    public StudentDTO getStudentById(Long studentId) {
        URI uri = UriComponentsBuilder.fromUriString(baseURL + "/api/students/" + studentId).build().toUri();
        return restClient.get(uri, Map.of(), new ParameterizedTypeReference<>() {
        });
    }
}
