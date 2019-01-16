package com.exactuals.studio.en;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Client {

  private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

  RestHighLevelClient client;

  public Client(final String hostname) {

    client = new RestHighLevelClient(
      RestClient.builder(
        new HttpHost(hostname, 9200, "http"),
        new HttpHost(hostname, 9201, "http")));
  }

  public void close() {
    try {
      client.close();
    } catch (IOException ex) {
      LOGGER.error(ex.getMessage());
    }
  }

}
