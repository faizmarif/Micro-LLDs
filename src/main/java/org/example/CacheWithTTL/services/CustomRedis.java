package org.example.CacheWithTTL.services;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.CompletableFuture;
import org.example.CacheWithTTL.models.CacheEntry;
import org.example.CacheWithTTL.models.RedisResult;
import org.example.CacheWithTTL.models.PQEntry;

public class CustomRedis {
  private final Map<String, CacheEntry> redisDbMap;
  private final PriorityQueue<PQEntry> pqEntries;

  public CustomRedis() {
    this.redisDbMap = new HashMap<>();
    this.pqEntries = new PriorityQueue<>((a, b) -> Math.toIntExact(
        a.getEpochWithTtl() - b.getEpochWithTtl()));
  }

  public boolean put(String key, String value, long ttlInMillis) {
    long currentEpoch = Instant.now().toEpochMilli();
    CacheEntry cacheEntry = new CacheEntry().setValue(value).setEpochWithTtl(currentEpoch + ttlInMillis);
    redisDbMap.put(key, cacheEntry);

    PQEntry pqEntry = new PQEntry().setKey(key).setEpochWithTtl(currentEpoch + ttlInMillis);
    pqEntries.add(pqEntry);
    CompletableFuture.runAsync(this::refreshCache);
    return true;
  }

  public RedisResult get(String key) {
    RedisResult result = null;
    CacheEntry cacheEntry = redisDbMap.get(key);
    if(cacheEntry != null) {
      long currentEpoch = Instant.now().toEpochMilli();
      if(cacheEntry.getEpochWithTtl() > currentEpoch) {
        result = new RedisResult().setValue(cacheEntry.getValue()).setTtlInMillis(cacheEntry.getEpochWithTtl() - currentEpoch);
      }
    }
    CompletableFuture.runAsync(this::refreshCache);
    return result;
  }

  private void refreshCache() {
    long currentEpoch = Instant.now().toEpochMilli();
    PQEntry pqEntry = pqEntries.peek();
    while (pqEntry!=null && pqEntry.getEpochWithTtl() < currentEpoch) {
      String key = pqEntry.getKey();
      CacheEntry cacheEntry = redisDbMap.get(key);
      if(cacheEntry !=  null && cacheEntry.getEpochWithTtl() < currentEpoch) {
        redisDbMap.remove(key);
      }
      pqEntries.poll();
      pqEntry = pqEntries.peek();
    }
  }
}
