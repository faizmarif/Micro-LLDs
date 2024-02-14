package org.example.CacheWithTTL.models;

public class RedisResult {
  private String value;
  private long ttlInMillis;

  public String getValue() {
    return value;
  }

  public RedisResult setValue(String value) {
    this.value = value;
    return this;
  }

  public long getTtlInMillis() {
    return ttlInMillis;
  }

  public RedisResult setTtlInMillis(long ttlInMillis) {
    this.ttlInMillis = ttlInMillis;
    return this;
  }

  @Override
  public String toString() {
    return "RedisResult{" +
        "value='" + value + '\'' +
        ", ttlInMillis=" + ttlInMillis +
        '}';
  }
}
