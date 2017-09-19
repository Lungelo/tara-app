package com.tara.org.repository;

import com.tara.org.domain.GetWay;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the GetWay entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GetWayRepository extends JpaRepository<GetWay,Long> {

    @Query("select get_way from GetWay get_way where get_way.addedBy.login = ?#{principal.username}")
	Page<GetWay> findByAddedByIsCurrentUser(Pageable pageable);

    
}
