package com.tara.org.repository.search;

import com.tara.org.domain.WirelessSensorNetwork;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the WirelessSensorNetwork entity.
 */
public interface WirelessSensorNetworkSearchRepository extends ElasticsearchRepository<WirelessSensorNetwork, Long> {
}
