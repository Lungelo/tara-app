package com.tara.org.repository;

import com.tara.org.domain.DevCleaningTool;
import com.tara.org.domain.PositioningSystem;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the PositioningSystem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PositioningSystemRepository extends JpaRepository<PositioningSystem,Long> {

    @Query("select positioning_system from PositioningSystem positioning_system where positioning_system.addedBy.login = ?#{principal.username}")
    Page<PositioningSystem> findByAddedByIsCurrentUser(Pageable pageable);

    
}
