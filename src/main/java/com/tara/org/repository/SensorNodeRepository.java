package com.tara.org.repository;

import com.tara.org.domain.DevCleaningTool;
import com.tara.org.domain.SensorNode;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the SensorNode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SensorNodeRepository extends JpaRepository<SensorNode,Long> {

    @Query("select sensor_node from SensorNode sensor_node where sensor_node.addedBy.login = ?#{principal.username}")
    Page<SensorNode> findByAddedByIsCurrentUser(Pageable pageable);

    
}
