package com.tara.org.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.tara.org.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.tara.org.domain.Company.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.DevDrillingTool.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.StopDrillingTool.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.DevChargingTool.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.DevSupportingTool.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.DevCleaningTool.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.StopCleaningTool.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.StopChargingTool.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.StopSupportingTool.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.Sensor.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.SensorNode.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.WirelessSensorNetwork.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.PositioningSystem.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.GetWay.class.getName(), jcacheConfiguration);
            cm.createCache(com.tara.org.domain.UserExtra.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
