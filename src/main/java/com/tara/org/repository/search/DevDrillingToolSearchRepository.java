package com.tara.org.repository.search;

import com.tara.org.domain.DevDrillingTool;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the DevDrillingTool entity.
 */
public interface DevDrillingToolSearchRepository extends ElasticsearchRepository<DevDrillingTool, Long> {
}
