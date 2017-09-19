package com.tara.org.repository;

import com.tara.org.domain.DevCleaningTool;
import com.tara.org.domain.StopSupportingTool;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the StopSupportingTool entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StopSupportingToolRepository extends JpaRepository<StopSupportingTool,Long> {

    @Query("select stop_supporing_tool from StopSupportingTool stop_supporing_tool where stop_supporing_tool.addedBy.login = ?#{principal.username}")
    Page<StopSupportingTool> findByAddedByIsCurrentUser(Pageable pageable);

    
}
