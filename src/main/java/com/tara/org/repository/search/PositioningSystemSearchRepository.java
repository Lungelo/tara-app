package com.tara.org.repository.search;

import com.tara.org.domain.PositioningSystem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PositioningSystem entity.
 */
public interface PositioningSystemSearchRepository extends ElasticsearchRepository<PositioningSystem, Long> {
}
