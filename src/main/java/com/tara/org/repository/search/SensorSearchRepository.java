package com.tara.org.repository.search;

import com.tara.org.domain.Sensor;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Sensor entity.
 */
public interface SensorSearchRepository extends ElasticsearchRepository<Sensor, Long> {
}
