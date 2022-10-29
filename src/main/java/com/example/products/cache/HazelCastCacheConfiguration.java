package com.example.products.cache;

import com.example.products.Entity.Product;
import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

@Configuration
public class HazelCastCacheConfiguration{

    @Bean
    public Config getHazelCastConfig() throws URISyntaxException, MalformedURLException {
        URL url=new URI("http://localhost:8080/mancenter").toURL();
        return new Config()
                .setInstanceName("hazel-instance")
                .addMapConfig(new MapConfig()
                        .setName("product-cache")
                        .setTimeToLiveSeconds(3000)
                        .setEvictionConfig(new EvictionConfig()
                                .setSize(200)
                                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                                .setEvictionPolicy(EvictionPolicy.LRU)
                        )
                )
                .setConfigurationUrl(url)
        .setConfigurationUrl(url).setProperty("usernname","username")
        .setConfigurationUrl(url).setProperty("password","password");
    }

    @Bean
    public HazelcastInstance getHazelCastCacheInstance(Config config){
        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    public Map<Integer, Product> getHazelCastCacheProductCache(HazelcastInstance hazelcastInstance) {
        return hazelcastInstance.getMap("product-cache");
    }
}
