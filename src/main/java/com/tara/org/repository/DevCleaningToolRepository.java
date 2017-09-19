package com.tara.org.repository;

import com.tara.org.domain.DevChargingTool;
import com.tara.org.domain.DevCleaningTool;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the DevCleaningTool entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DevCleaningToolRepository extends JpaRepository<DevCleaningTool,Long> {

    @Query("select dev_cleaning_tool from DevCleaningTool dev_cleaning_tool where dev_cleaning_tool.addedBy.login = ?#{principal.username}")
    Page<DevCleaningTool> findByAddedByIsCurrentUser(Pageable pageable);

    
}
