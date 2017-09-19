package com.tara.org.repository.search;

import com.tara.org.domain.StopChargingTool;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StopChargingTool entity.
 */
public interface StopChargingToolSearchRepository extends ElasticsearchRepository<StopChargingTool, Long> {
}
