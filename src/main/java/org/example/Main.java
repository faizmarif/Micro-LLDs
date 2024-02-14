package org.example;

import org.example.CacheWithTTL.services.CustomRedis;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("Hello world!");
    CustomRedis customRedis = new CustomRedis();

    customRedis.put("abc", "qwerty", 5000);
    System.out.println(customRedis.get("abc"));
    customRedis.put("abc", "qwerty", 10000);
    System.out.println(customRedis.get("abc"));
    customRedis.put("abc", "qwerty", 15000);
    System.out.println(customRedis.get("abc"));
    Thread.sleep(5000);
    System.out.println(customRedis.get("abc"));

    customRedis.put("asd", "qwerty1", 5000);
    customRedis.put("qwe", "qwerty2", 6000);
    customRedis.put("mnb", "qwerty3", 7000);
    System.out.println(customRedis.get("asd"));
    System.out.println(customRedis.get("qwe"));
    System.out.println(customRedis.get("mnb"));
    Thread.sleep(6000);
    System.out.println(customRedis.get("asd"));
    System.out.println(customRedis.get("qwe"));
    System.out.println(customRedis.get("mnb"));
  }
}