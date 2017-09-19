package com.tara.org.repository.search;

import com.tara.org.domain.DevCleaningTool;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the DevCleaningTool entity.
 */
public interface DevCleaningToolSearchRepository extends ElasticsearchRepository<DevCleaningTool, Long> {
}
