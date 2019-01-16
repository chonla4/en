package com.exactuals.studio.en;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ElasticSearch implements RequestHandler<Input, Output> {

  @Override
  public Output handleRequest(Input input, Context context) {
    context.getLogger().log("Input: " + input);

    Client client = new Client("localhost");

    client.close();
    return new Output();
  }

}