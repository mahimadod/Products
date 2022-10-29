package com.example.products.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CacheController {

    Logger logger = LoggerFactory.getLogger(Service.class);
    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/cache")
    public void getCacheNames() {
        logger.info(cacheManager.getCacheNames().toString());
    }

    //ditto
//    @GetMapping("/data")
//    public void getCacheData() {
//        Cache b= (Cache) cacheManager.getCache("productCache").;
//        b.getKeys().stream().forEach(i->logger.info(i.toString()));
//        //b.entrySet().stream().forEach(i->System.out.println("Key :"+i.getKey()+"  Value :"+i.getValue()));
//      //  logger.info(b.toString());
//    }
}
