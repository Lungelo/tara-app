package com.tara.org.repository.search;

import com.tara.org.domain.StopCleaningTool;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StopCleaningTool entity.
 */
public interface StopCleaningToolSearchRepository extends ElasticsearchRepository<StopCleaningTool, Long> {
}
