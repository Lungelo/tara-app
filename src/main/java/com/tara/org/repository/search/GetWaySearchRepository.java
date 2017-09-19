package com.tara.org.repository.search;

import com.tara.org.domain.GetWay;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the GetWay entity.
 */
public interface GetWaySearchRepository extends ElasticsearchRepository<GetWay, Long> {
}
