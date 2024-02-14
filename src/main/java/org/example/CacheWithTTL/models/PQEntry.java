package org.example.CacheWithTTL.models;

public class PQEntry {
  private String key;
  private long epochWithTtl;

  public String getKey() {
    return key;
  }

  public PQEntry setKey(String key) {
    this.key = key;
    return this;
  }

  public long getEpochWithTtl() {
    return epochWithTtl;
  }

  public PQEntry setEpochWithTtl(long epochWithTtl) {
    this.epochWithTtl = epochWithTtl;
    return this;
  }
}
