package com.tara.org.repository;

import com.tara.org.domain.DevCleaningTool;
import com.tara.org.domain.StopChargingTool;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the StopChargingTool entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StopChargingToolRepository extends JpaRepository<StopChargingTool,Long> {

    @Query("select stop_charging_tool from StopChargingTool stop_charging_tool where stop_charging_tool.addedBy.login = ?#{principal.username}")
    Page<StopChargingTool> findByAddedByIsCurrentUser(Pageable pageable);

    
}
