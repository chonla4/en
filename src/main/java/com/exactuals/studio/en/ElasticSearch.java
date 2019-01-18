package com.exactuals.studio.en;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.exactuals.indexer.elasticsearch.IndexDestination;
import com.exactuals.indexer.elasticsearch.IndexRouterService;
import com.exactuals.indexer.elasticsearch.SearchType;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ElasticSearch implements RequestHandler<PayloadMessage, Output> {

  @Autowired
  private IndexRouterService indexRouterService;

  @Autowired
  RestHighLevelClient elasticSearchClient;

  @Override
  public Output handleRequest(PayloadMessage payloadMessage, Context context) {

    context.getLogger().log("Message: " + payloadMessage);


    LOGGER.info("Starting application...");
    final ConfigurableApplicationContext springContext = new SpringApplicationBuilder().sources(FinisherConfig.class).run();
    final FinisherService service = springContext.getBean(FinisherService.class);
    LOGGER.info("Startup complete. Processing finisherInput...");
    final FinisherOutput finisherOutput = service.finish(finisherInput);
    LOGGER.info("Processing complete. Returning finisherOutput: [{}]", finisherOutput);
    springContext.close();
    LOGGER.info("Shutdown complete.");




    // REST low-level client builder
    Client client = new Client("localhost");

    String realm = payloadMessage.getRealm();
    String orgId = payloadMessage.getOrganizationId();

    final IndexDestination destination = this.indexRouterService.determineSearchDestination(SearchType.PAYLOAD_PACKAGE, realm, orgId);
    destination.getReadPath(); // This is the index you can query from.

    SearchRequest searchRequest = new SearchRequest(destination.getReadPath());
    searchRequest.types(SearchType.PAYLOAD_PACKAGE.getElasticSearchType());


    client.close();
    return new Output();
  }

}