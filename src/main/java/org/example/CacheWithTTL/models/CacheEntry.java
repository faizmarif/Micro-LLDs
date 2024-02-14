package org.example.CacheWithTTL.models;

import java.time.Instant;

public class CacheEntry {
  private String value;
  private long epochWithTtl;

  public String getValue() {
    return value;
  }

  public CacheEntry setValue(String value) {
    this.value = value;
    return this;
  }

  public long getEpochWithTtl() {
    return epochWithTtl;
  }

  public CacheEntry setEpochWithTtl(long epochWithTtl) {
    this.epochWithTtl = epochWithTtl;
    return this;
  }

}
