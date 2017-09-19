package com.tara.org.repository;

import com.tara.org.domain.DevCleaningTool;
import com.tara.org.domain.DevDrillingTool;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the DevDrillingTool entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DevDrillingToolRepository extends JpaRepository<DevDrillingTool, Long> {

    @Query("select dev_drilling_tool from DevDrillingTool dev_drilling_tool where dev_drilling_tool.addedBy.login = ?#{principal.username}")
    Page<DevDrillingTool> findByAddedByIsCurrentUser(Pageable pageable);


}
