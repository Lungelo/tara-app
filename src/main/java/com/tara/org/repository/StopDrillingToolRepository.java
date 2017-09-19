package com.tara.org.repository;

import com.tara.org.domain.DevCleaningTool;
import com.tara.org.domain.StopDrillingTool;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the StopDrillingTool entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StopDrillingToolRepository extends JpaRepository<StopDrillingTool,Long> {

    @Query("select stop_drilling_tool from StopDrillingTool stop_drilling_tool where stop_drilling_tool.addedBy.login = ?#{principal.username}")
    Page<StopDrillingTool> findByAddedByIsCurrentUser(Pageable pageable);

    
}
