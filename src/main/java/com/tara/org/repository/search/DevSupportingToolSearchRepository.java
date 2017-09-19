package com.tara.org.repository.search;

import com.tara.org.domain.DevSupportingTool;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the DevSupportingTool entity.
 */
public interface DevSupportingToolSearchRepository extends ElasticsearchRepository<DevSupportingTool, Long> {
}
