package com.code.fee.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;

@Service
@Slf4j
public class RestClient {

  RestTemplate restTemplate;

  public RestClient() {
    this.restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofHours(1)).build();
  }

  public <R> R get(URI uri, Map<String, String> headersMap, ParameterizedTypeReference<R> type) {
    HttpEntity request = new HttpEntity(populateHeaders(headersMap));
    return restTemplate.exchange(uri, HttpMethod.GET, request, type).getBody();
  }

  public <R> ResponseEntity<R> getResponseEntity(
      URI uri, Map<String, String> headersMap, ParameterizedTypeReference<R> type) {
    HttpEntity request = new HttpEntity(populateHeaders(headersMap));
    return restTemplate.exchange(uri, HttpMethod.GET, request, type);
  }

  public <T, R> R post(
      URI uri, Map<String, String> headersMap, T body, ParameterizedTypeReference<R> type) {
    HttpEntity request = new HttpEntity(body, populateHeaders(headersMap));
    return restTemplate.exchange(uri, HttpMethod.POST, request, type).getBody();
  }

  private HttpHeaders populateHeaders(Map<String, String> head) {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.APPLICATION_JSON);
    if (head != null) {
      head.entrySet().stream().forEach(e -> headers.add(e.getKey(), e.getValue()));
    }
    return headers;
  }
}
