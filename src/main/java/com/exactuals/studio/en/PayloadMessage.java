package com.exactuals.studio.en;

import org.elasticsearch.search.DocValueFormat;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.exactuals.indexer" })
public class PayloadMessage {

  String realm;
  String organizationId;
  DocValueFormat.DateTime startDate;

  public PayloadMessage() {

  }

  public PayloadMessage(String realm, String organizationId) {
    this.realm = realm;
    this.organizationId = organizationId;
  }

  public String getRealm() {
    return realm;
  }

  public void setRealm(String realm) {
    this.realm = realm;
  }

  public String getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(String organizationId) {
    this.organizationId = organizationId;
  }

}
