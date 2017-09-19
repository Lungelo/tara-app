package com.tara.org.repository;

import com.tara.org.domain.DevCleaningTool;
import com.tara.org.domain.StopCleaningTool;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the StopCleaningTool entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StopCleaningToolRepository extends JpaRepository<StopCleaningTool,Long> {

    @Query("select stop_cleaning_tool from StopCleaningTool stop_cleaning_tool where stop_cleaning_tool.addedBy.login = ?#{principal.username}")
    Page<StopCleaningTool> findByAddedByIsCurrentUser(Pageable pageable);

    
}
