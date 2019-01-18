package com.exactuals.studio.en;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class ElasticSearchConfig {
  @Bean
  @ConfigurationProperties("xact.finisher")
  public FinisherProperties finisherProperties(){
    return FinisherProperties.builder().build();
  }

  @Bean
  public RestTemplate restTemplate(final FinisherProperties finisherProperties, final RestTemplateBuilder restTemplateBuilder) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    //TODO remove once devops setup client certs in worker containers
    final TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
    final SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
    final SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
    final CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
    final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    requestFactory.setHttpClient(httpClient);

    return restTemplateBuilder
      .rootUri(finisherProperties.getStudioApiUrl())
      .requestFactory(requestFactory)
      .build();
  }
}
