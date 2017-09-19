package com.tara.org.repository.search;

import com.tara.org.domain.DevChargingTool;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the DevChargingTool entity.
 */
public interface DevChargingToolSearchRepository extends ElasticsearchRepository<DevChargingTool, Long> {
}
