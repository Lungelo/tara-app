package com.tara.org.repository.search;

import com.tara.org.domain.SensorNode;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SensorNode entity.
 */
public interface SensorNodeSearchRepository extends ElasticsearchRepository<SensorNode, Long> {
}
