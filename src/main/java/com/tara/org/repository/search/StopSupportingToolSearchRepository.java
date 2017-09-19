package com.tara.org.repository.search;

import com.tara.org.domain.StopSupportingTool;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StopSupportingTool entity.
 */
public interface StopSupportingToolSearchRepository extends ElasticsearchRepository<StopSupportingTool, Long> {
}
