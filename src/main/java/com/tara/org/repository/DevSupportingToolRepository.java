package com.tara.org.repository;

import com.tara.org.domain.DevCleaningTool;
import com.tara.org.domain.DevSupportingTool;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the DevSupportingTool entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DevSupportingToolRepository extends JpaRepository<DevSupportingTool,Long> {

    @Query("select dev_supporing_tool from DevSupportingTool dev_supporing_tool where dev_supporing_tool.addedBy.login = ?#{principal.username}")
    Page<DevSupportingTool> findByAddedByIsCurrentUser(Pageable pageable);

    
}
