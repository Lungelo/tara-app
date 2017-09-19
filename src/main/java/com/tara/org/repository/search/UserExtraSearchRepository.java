package com.tara.org.repository.search;

import com.tara.org.domain.UserExtra;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the UserExtra entity.
 */
public interface UserExtraSearchRepository extends ElasticsearchRepository<UserExtra, Long> {
}
