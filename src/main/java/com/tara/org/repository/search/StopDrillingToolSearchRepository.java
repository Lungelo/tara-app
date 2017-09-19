package com.tara.org.repository.search;

import com.tara.org.domain.StopDrillingTool;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StopDrillingTool entity.
 */
public interface StopDrillingToolSearchRepository extends ElasticsearchRepository<StopDrillingTool, Long> {
}
