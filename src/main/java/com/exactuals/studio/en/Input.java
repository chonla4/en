package com.exactuals.studio.en;

import com.exactuals.indexer.elasticsearch.IndexDestination;
import com.exactuals.indexer.elasticsearch.IndexRouterService;
import com.exactuals.indexer.elasticsearch.SearchType;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.exactuals.indexer" })
public class Input {

}
